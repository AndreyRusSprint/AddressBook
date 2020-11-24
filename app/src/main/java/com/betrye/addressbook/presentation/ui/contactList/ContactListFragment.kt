package com.betrye.addressbook.presentation.ui.contactList

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.betrye.addressbook.R
import com.betrye.addressbook.databinding.FragmentContactListBinding
import com.betrye.addressbook.presentation.common.ToolbarHolder
import com.betrye.addressbook.presentation.entity.ErrorState
import com.betrye.addressbook.presentation.ui.main.MainActivity
import com.betrye.addressbook.presentation.viewModel.contactList.ContactListViewModel

class ContactListFragment(private val viewModel: ContactListViewModel) : Fragment() {

    private var _binding: FragmentContactListBinding? = null
    private val binding: FragmentContactListBinding
        get() = _binding!!

    private var contactListAdapter: ContactListAdapter? = null

    private val hasPermissions: Boolean
        get() = ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.READ_CONTACTS
        ) == PackageManager.PERMISSION_GRANTED

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentContactListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setTitle()

        if (hasPermissions) {
            viewModel.fetchContacts()
        } else {
            requestPermissions(arrayOf(Manifest.permission.READ_CONTACTS), REQUEST_PERMISSIONS_CODE)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupRecyclerView()
        observeViewModel()
    }

    override fun onDestroyView() {
        _binding = null
        contactListAdapter = null
        super.onDestroyView()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == REQUEST_PERMISSIONS_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                viewModel.fetchContacts()
            } else {
                viewModel.permissionDenied()
            }
        }
    }

    private fun setTitle() {
        (activity as? ToolbarHolder)?.setToolbarTitle(R.string.contact_list_title)
    }

    private fun setupRecyclerView() {
        contactListAdapter = ContactListAdapter {
            (activity as? MainActivity)?.showContactDetails(it)
        }
        binding.rvContacts.let { rv ->
            rv.layoutManager = LinearLayoutManager(context)
            rv.setHasFixedSize(false)
            rv.adapter = contactListAdapter
        }
    }

    private fun observeViewModel() {
        viewModel.contacts
            .observe(viewLifecycleOwner) {
                contactListAdapter?.contactList = it
            }

        viewModel.isLoading
            .observe(viewLifecycleOwner) { isLoading ->
                binding.pbLoading.isVisible = isLoading
            }

        viewModel.error
            .observe(viewLifecycleOwner) { errorState ->
                if (errorState == ErrorState.NONE) {
                    binding.tvError.visibility = View.GONE
                } else {
                    errorState.message?.let {
                        binding.tvError.setText(it)
                        binding.tvError.visibility = View.VISIBLE
                    }
                }
            }
    }

    companion object {
        private const val REQUEST_PERMISSIONS_CODE = 1
    }
}
