package com.betrye.addressbook.presentation.mapper

import com.betrye.addressbook.domain.entity.PhoneType
import com.betrye.addressbook.presentation.entity.PhoneTypePM

internal fun PhoneType.toPresentationModel(): PhoneTypePM =
    when (this) {
        PhoneType.MAIN -> PhoneTypePM.MAIN
        PhoneType.MOBILE -> PhoneTypePM.MOBILE
        PhoneType.HOME -> PhoneTypePM.HOME
        PhoneType.WORK -> PhoneTypePM.WORK
        PhoneType.OTHER -> PhoneTypePM.OTHER
    }
