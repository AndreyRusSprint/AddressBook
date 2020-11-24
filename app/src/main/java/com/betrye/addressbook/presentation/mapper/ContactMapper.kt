package com.betrye.addressbook.presentation.mapper

import android.net.Uri
import com.betrye.addressbook.domain.entity.Contact
import com.betrye.addressbook.presentation.entity.ContactPM

internal fun Contact.toPresentationModel() = ContactPM(
    id = id,
    name = name,
    avatarUri = avatarUri?.let(Uri::parse),
    phones = phones.map { it.toPresentationModel() }
)
