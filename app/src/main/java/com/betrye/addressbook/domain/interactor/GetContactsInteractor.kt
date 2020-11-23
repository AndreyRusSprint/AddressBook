package com.betrye.addressbook.domain.interactor

import com.betrye.addressbook.domain.entity.Contact
import com.betrye.addressbook.domain.repository.ContactRepository
import com.betrye.addressbook.domain.useCase.GetContacts

class GetContactsInteractor(
    private val contactRepository: ContactRepository
) : GetContacts {
    override suspend fun invoke(): List<Contact> =
        contactRepository.contacts()
}
