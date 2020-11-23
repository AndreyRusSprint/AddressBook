package com.betrye.addressbook.domain.repository

import com.betrye.addressbook.domain.entity.Contact

interface ContactRepository {
    suspend fun contacts(): List<Contact>
}
