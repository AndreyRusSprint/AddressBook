package com.betrye.addressbook.data.repository

import com.betrye.addressbook.data.dataSource.ContactLocalDataSource
import com.betrye.addressbook.data.mapper.toPhone
import com.betrye.addressbook.domain.entity.Contact
import com.betrye.addressbook.domain.repository.ContactRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class ContactRepositoryImpl(
    private val localDataSource: ContactLocalDataSource
) : ContactRepository {

    override suspend fun contacts(): List<Contact> = coroutineScope {
        val contactListAsync = async { localDataSource.getContacts() }
        val phoneListAsync = async { localDataSource.getPhones() }

        val contactList = contactListAsync.await()
        val phoneMap = phoneListAsync.await()
            .groupBy { it.contactId }

        val result = mutableListOf<Contact>()

        for (index in contactList.indices) {
            val localContact = contactList[index]
            val localPhones = phoneMap[localContact.id] ?: emptyList()
            val contact = Contact(
                id = localContact.id,
                name = localContact.name,
                avatarUri = localContact.avatarUri,
                phones = localPhones.map { it.toPhone() }
            )
            result.add(contact)
        }

        return@coroutineScope result
    }
}
