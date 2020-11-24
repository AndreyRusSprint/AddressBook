package com.betrye.addressbook

import android.content.Context
import com.betrye.addressbook.di.dataModule
import com.betrye.addressbook.di.domainModule
import com.betrye.addressbook.di.fragmentModule
import com.betrye.addressbook.di.viewModelModule
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import org.junit.Rule
import org.junit.Test
import org.koin.android.ext.koin.androidContext
import org.koin.test.KoinTest
import org.koin.test.check.checkModules
import org.koin.test.mock.MockProviderRule
import org.mockito.Mockito

internal class DependencyTest : KoinTest {

    @get:Rule
    val mockProvider = MockProviderRule.create { clazz ->
        Mockito.mock(clazz.java)
    }

    @Test
    fun checkAllModules() {
        val mockedContext = mock<Context> {
            on { contentResolver } doReturn mock()
        }

        checkModules {
            androidContext(mockedContext)
            modules(
                listOf(
                    domainModule,
                    dataModule,
                    viewModelModule,
                    fragmentModule,
                )
            )
        }
    }
}
