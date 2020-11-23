package com.betrye.addressbook.domain.entity

class Phone(
    val contactId: Long,
    val number: String,
    val type: PhoneType
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Phone

        if (contactId != other.contactId) return false
        if (number != other.number) return false
        if (type != other.type) return false

        return true
    }

    override fun hashCode(): Int {
        var result = contactId.hashCode()
        result = 31 * result + number.hashCode()
        result = 31 * result + type.hashCode()
        return result
    }
}
