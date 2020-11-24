package com.betrye.addressbook.presentation.viewModel.contactList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.betrye.addressbook.domain.useCase.GetContacts
import com.betrye.addressbook.presentation.entity.ContactPM
import com.betrye.addressbook.presentation.entity.ErrorState
import com.betrye.addressbook.presentation.mapper.toPresentationModel
import kotlinx.coroutines.launch

class ContactListViewModel(
    private val getContacts: GetContacts
) : ViewModel() {

    private val contactsObservable = MutableLiveData<List<ContactPM>>()
    private val loadingObservable = MutableLiveData<Boolean>()
    private val errorObservable = MutableLiveData<ErrorState>()

    val contacts: LiveData<List<ContactPM>>
        get() = contactsObservable
    val isLoading: LiveData<Boolean>
        get() = loadingObservable
    val error: LiveData<ErrorState>
        get() = errorObservable

    fun fetchContacts() {
        errorObservable.postValue(ErrorState.NONE)
        loadingObservable.postValue(true)

        viewModelScope.launch {
            try {
                val contacts = getContacts().map { it.toPresentationModel() }
                contactsObservable.postValue(contacts)
            } catch (e: Exception) {
                errorObservable.postValue(ErrorState.DATA_STORE_FAILURE)
            } finally {
                loadingObservable.postValue(false)
            }
        }
    }

    fun permissionDenied() {
        errorObservable.postValue(ErrorState.PERMISSION_DENIED)
    }
}
