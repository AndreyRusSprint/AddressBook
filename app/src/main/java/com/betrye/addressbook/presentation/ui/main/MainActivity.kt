package com.betrye.addressbook.presentation.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.betrye.addressbook.R
import com.betrye.addressbook.databinding.MainActivityBinding
import com.betrye.addressbook.presentation.common.Router
import com.betrye.addressbook.presentation.common.ToolbarHolder
import com.betrye.addressbook.presentation.ui.contactList.ContactListFragment
import org.koin.androidx.fragment.android.setupKoinFragmentFactory
import org.koin.core.KoinExperimentalAPI

@KoinExperimentalAPI
class MainActivity : AppCompatActivity(), ToolbarHolder, Router {

    private var _binding: MainActivityBinding? = null
    private val binding: MainActivityBinding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        setupKoinFragmentFactory()
        super.onCreate(savedInstanceState)

        _binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ContactListFragment::class.java, null)
                .commitNow()
        }
    }

    override fun setToolbarTitle(title: String) {
        binding.toolbar.title = title
    }

    override fun setToolbarTitle(titleRes: Int) {
        binding.toolbar.setTitle(titleRes)
    }

    override fun setToolbarVisibility(isVisible: Boolean) {
        binding.toolbar.isVisible = isVisible
    }

    override fun navigateTo(fragmentClass: Class<out Fragment>, args: Bundle?) {
        supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.container, fragmentClass, args)
            .commit()
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}
