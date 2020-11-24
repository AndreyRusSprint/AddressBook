package com.betrye.addressbook.presentation.mapper

import com.betrye.addressbook.domain.entity.Phone
import com.betrye.addressbook.presentation.entity.PhonePM

internal fun Phone.toPresentationModel() = PhonePM(
    contactId = contactId,
    number = number,
    type = type.toPresentationModel()
)
