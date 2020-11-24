package com.betrye.addressbook.presentation.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.betrye.addressbook.R
import com.betrye.addressbook.presentation.ui.contactList.ContactListFragment
import org.koin.androidx.fragment.android.setupKoinFragmentFactory

class MainActivity : AppCompatActivity() {

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
}
