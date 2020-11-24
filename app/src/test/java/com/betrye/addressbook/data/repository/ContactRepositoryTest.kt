package com.betrye.addressbook.data.repository

import com.betrye.addressbook.data.dataSource.ContactLocalDataSource
import com.betrye.addressbook.data.entity.ContactLocal
import com.betrye.addressbook.data.entity.PhoneLocal
import com.betrye.addressbook.domain.repository.ContactRepository
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe

internal class ContactRepositoryTest : BehaviorSpec({
    val localDataSource = mock<ContactLocalDataSource>()
    val repository: ContactRepository = ContactRepositoryImpl(localDataSource)

    val contactLocal by lazy { ContactLocal(4L, "", null) }
    val phoneLocal by lazy { PhoneLocal(4L, "", 1) }

    given("get contacts") {
        When("no contacts") {
            whenever(localDataSource.getContacts()).thenReturn(emptyList())
            whenever(localDataSource.getPhones()).thenReturn(listOf(phoneLocal))

            then("should return empty list") {
                repository.contacts().shouldBeEmpty()
            }
        }
        When("no phones") {
            whenever(localDataSource.getContacts()).thenReturn(listOf(contactLocal))
            whenever(localDataSource.getPhones()).thenReturn(emptyList())

            then("should return contact without phone") {
                repository.contacts().first() should {
                    it.id shouldBe contactLocal.id
                    it.phones.shouldBeEmpty()
                }
            }
        }
        When("has all data") {
            whenever(localDataSource.getContacts()).thenReturn(listOf(contactLocal))
            whenever(localDataSource.getPhones()).thenReturn(listOf(phoneLocal))

            then("should return correct result") {
                repository.contacts().first() should {
                    it.id shouldBe contactLocal.id
                    it.phones.first().number shouldBe phoneLocal.number
                }
            }
        }
    }
})
