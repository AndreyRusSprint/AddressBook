package com.betrye.addressbook.data.dataSource

import android.content.ContentResolver
import android.provider.ContactsContract
import androidx.core.database.getStringOrNull
import com.betrye.addressbook.data.entity.ContactLocal
import com.betrye.addressbook.data.entity.PhoneLocal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ContactLocalDataSourceImpl(
    private val contentResolver: ContentResolver
) : ContactLocalDataSource {

    override suspend fun getContacts(): List<ContactLocal> = withContext(Dispatchers.IO) {
        val contactList = mutableListOf<ContactLocal>()

        contentResolver.query(
            ContactsContract.Contacts.CONTENT_URI,
            arrayOf(
                ContactsContract.Contacts._ID,
                ContactsContract.Contacts.DISPLAY_NAME,
                ContactsContract.Contacts.PHOTO_URI,
            ),
            null,
            null,
            "${ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME} ASC"
        )?.use { contactsCursor ->
            if (contactsCursor.count > 0) {
                val idIndex = contactsCursor.getColumnIndex(ContactsContract.Contacts._ID)
                val nameIndex = contactsCursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)
                val photoIndex = contactsCursor.getColumnIndex(ContactsContract.Contacts.PHOTO_URI)
                while (contactsCursor.moveToNext()) {
                    val id = contactsCursor.getLong(idIndex)
                    val name = contactsCursor.getString(nameIndex)
                    val photoUri = contactsCursor.getStringOrNull(photoIndex)
                    if (name != null) {
                        contactList.add(ContactLocal(id, name, photoUri))
                    }
                }
            }
        }
        return@withContext contactList
    }

    override suspend fun getPhones(): List<PhoneLocal> = withContext(Dispatchers.IO) {
        val phones = mutableListOf<PhoneLocal>()

        contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            arrayOf(
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
                ContactsContract.CommonDataKinds.Phone.NUMBER,
                ContactsContract.CommonDataKinds.Phone.TYPE,
            ),
            null,
            null,
            null
        )?.use { phoneCursor ->
            if (phoneCursor.count > 0) {
                val contactIdIndex = phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID)
                val numberIndex = phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
                val typeIndex = phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE)
                while (phoneCursor.moveToNext()) {
                    val contactId = phoneCursor.getLong(contactIdIndex)
                    val number = phoneCursor.getString(numberIndex)
                    val type = phoneCursor.getInt(typeIndex)

                    phones.add(PhoneLocal(contactId, number, type))
                }
            }
        }

        return@withContext phones
    }
}
