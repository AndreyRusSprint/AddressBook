package com.betrye.addressbook.presentation.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.betrye.addressbook.R
import com.betrye.addressbook.presentation.common.ToolbarHolder
import com.betrye.addressbook.presentation.entity.ContactPM
import com.betrye.addressbook.presentation.ui.contactList.ContactListFragment
import org.koin.androidx.fragment.android.setupKoinFragmentFactory
import org.koin.core.KoinExperimentalAPI

@KoinExperimentalAPI
class MainActivity : AppCompatActivity(), ToolbarHolder {

    override fun onCreate(savedInstanceState: Bundle?) {
        setupKoinFragmentFactory()
        super.onCreate(savedInstanceState)

        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ContactListFragment::class.java, null)
                .commitNow()
        }
    }

    override fun setToolbarTitle(title: String) {
        supportActionBar?.title = title
    }

    override fun setToolbarTitle(titleRes: Int) {
        supportActionBar?.setTitle(titleRes)
    }

    fun showContactDetails(contact: ContactPM) {
        
    }
}
