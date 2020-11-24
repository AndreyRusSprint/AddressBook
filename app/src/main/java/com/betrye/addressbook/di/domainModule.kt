@file:Suppress("RemoveExplicitTypeArguments")

package com.betrye.addressbook.di

import com.betrye.addressbook.domain.interactor.GetContactsInteractor
import com.betrye.addressbook.domain.repository.ContactRepository
import com.betrye.addressbook.domain.useCase.GetContacts
import org.koin.dsl.module

val domainModule = module {
    single<GetContacts> { GetContactsInteractor(get<ContactRepository>()) }
}
