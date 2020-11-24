package com.betrye.addressbook.presentation.mapper

import com.betrye.addressbook.domain.entity.Contact
import com.betrye.addressbook.presentation.entity.ContactPM
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class ContactMapperTest : StringSpec({
    "it should convert Contact to presentation model" {
        val name = "Test name"
        val contact = Contact(5L, name, null, emptyList())
        val expected = ContactPM(5L, name, null, emptyList())
        contact.toPresentationModel() shouldBe expected
    }
})
