package com.betrye.addressbook.data.dataSource

import com.betrye.addressbook.data.entity.ContactLocal
import com.betrye.addressbook.data.entity.PhoneLocal

interface ContactLocalDataSource {
    suspend fun getContacts(): List<ContactLocal>
    suspend fun getPhones(): List<PhoneLocal>
}
