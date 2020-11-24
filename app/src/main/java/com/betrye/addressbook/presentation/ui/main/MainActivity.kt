package com.betrye.addressbook.presentation.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.betrye.addressbook.R
import com.betrye.addressbook.presentation.common.Router
import com.betrye.addressbook.presentation.common.ToolbarHolder
import com.betrye.addressbook.presentation.ui.contactList.ContactListFragment
import org.koin.androidx.fragment.android.setupKoinFragmentFactory
import org.koin.core.KoinExperimentalAPI

@KoinExperimentalAPI
class MainActivity : AppCompatActivity(), ToolbarHolder, Router {

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

    override fun navigateTo(fragmentClass: Class<out Fragment>, args: Bundle?) {
        supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.container, fragmentClass, args)
            .commit()
    }
}
