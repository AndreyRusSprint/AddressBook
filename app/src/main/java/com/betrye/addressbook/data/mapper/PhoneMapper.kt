package com.betrye.addressbook.data.mapper

import com.betrye.addressbook.data.entity.PhoneLocal
import com.betrye.addressbook.domain.entity.Phone

fun PhoneLocal.toPhone() = Phone(
    contactId = this.contactId,
    number = this.number,
    type = this.typeCode.toPhoneType()
)
