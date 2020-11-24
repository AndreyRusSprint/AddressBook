@file:Suppress("RemoveExplicitTypeArguments")

package com.betrye.addressbook.di

import com.betrye.addressbook.presentation.ui.contactDetails.ContactDetailsFragment
import com.betrye.addressbook.presentation.ui.contactList.ContactListFragment
import com.betrye.addressbook.presentation.viewModel.contactDetails.ContactDetailsViewModel
import com.betrye.addressbook.presentation.viewModel.contactList.ContactListViewModel
import org.koin.dsl.module

val fragmentModule = module {
    factory { ContactListFragment(get<ContactListViewModel>()) }
    factory { ContactDetailsFragment(get<ContactDetailsViewModel>()) }
}
