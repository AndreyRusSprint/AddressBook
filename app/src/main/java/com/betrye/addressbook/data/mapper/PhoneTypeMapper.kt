package com.betrye.addressbook.data.mapper

import com.betrye.addressbook.domain.entity.PhoneType

fun Int.toPhoneType(): PhoneType =
    when (this) {
        1 -> PhoneType.HOME
        2 -> PhoneType.MOBILE
        3 -> PhoneType.WORK
        12 -> PhoneType.MAIN
        else -> PhoneType.OTHER
    }
