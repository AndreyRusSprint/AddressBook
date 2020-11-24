package com.betrye.addressbook.presentation.viewModel.contactDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.betrye.addressbook.presentation.entity.ContactPM

class ContactDetailsViewModel : ViewModel() {

    private val contactObservable = MutableLiveData<ContactPM>()

    val contact: LiveData<ContactPM>
        get() = contactObservable

    internal fun setContact(contact: ContactPM) {
        contactObservable.postValue(contact)
    }
}
