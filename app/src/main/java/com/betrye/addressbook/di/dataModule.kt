@file:Suppress("RemoveExplicitTypeArguments")

package com.betrye.addressbook.di

import com.betrye.addressbook.data.dataSource.ContactLocalDataSource
import com.betrye.addressbook.data.dataSource.ContactLocalDataSourceImpl
import com.betrye.addressbook.data.repository.ContactRepositoryImpl
import com.betrye.addressbook.domain.repository.ContactRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {
    single<ContactLocalDataSource> { ContactLocalDataSourceImpl(androidContext().contentResolver) }
    single<ContactRepository> { ContactRepositoryImpl(get<ContactLocalDataSource>()) }
}
