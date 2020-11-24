package com.betrye.addressbook.presentation.entity

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class ContactPM(
    val id: Long,
    val name: String,
    val avatarUri: Uri?,
    val phones: List<PhonePM>
) : Parcelable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ContactPM

        if (id != other.id) return false
        if (name != other.name) return false
        if (avatarUri != other.avatarUri) return false
        if (phones != other.phones) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + (avatarUri?.hashCode() ?: 0)
        result = 31 * result + phones.hashCode()
        return result
    }
}
