package com.betrye.addressbook.domain.useCase

import com.betrye.addressbook.domain.entity.Contact

interface GetContacts {
    suspend operator fun invoke(): List<Contact>
}
