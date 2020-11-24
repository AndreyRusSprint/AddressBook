@file:Suppress("RemoveExplicitTypeArguments")

package com.betrye.addressbook.di

import com.betrye.addressbook.domain.useCase.GetContacts
import com.betrye.addressbook.presentation.viewModel.contactDetails.ContactDetailsViewModel
import com.betrye.addressbook.presentation.viewModel.contactList.ContactListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ContactListViewModel(get<GetContacts>()) }
    viewModel { ContactDetailsViewModel() }
}
