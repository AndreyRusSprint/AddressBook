package com.betrye.addressbook.presentation.ui.contactList

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.betrye.addressbook.R
import com.betrye.addressbook.databinding.FragmentContactListBinding
import com.betrye.addressbook.presentation.common.Router
import com.betrye.addressbook.presentation.common.ToolbarHolder
import com.betrye.addressbook.presentation.entity.ContactPM
import com.betrye.addressbook.presentation.entity.ErrorState
import com.betrye.addressbook.presentation.ui.contactDetails.ContactDetailsFragment
import com.betrye.addressbook.presentation.viewModel.contactList.ContactListViewModel

class ContactListFragment(private val viewModel: ContactListViewModel) : Fragment() {

    private var _binding: FragmentContactListBinding? = null
    private val binding: FragmentContactListBinding
        get() = _binding!!

    private val toolbarHolder: ToolbarHolder?
        get() = activity as? ToolbarHolder
    private val router: Router?
        get() = activity as? Router

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
        toolbarHolder?.setToolbarTitle(R.string.contact_list_title)
    }

    private fun setupRecyclerView() {
        contactListAdapter = ContactListAdapter(::openDetails)
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

    private fun openDetails(contact: ContactPM) {
        router?.navigateTo(
            ContactDetailsFragment::class.java, bundleOf(
                ContactDetailsFragment.KEY_CONTACT to contact
            )
        )
    }

    companion object {
        private const val REQUEST_PERMISSIONS_CODE = 1
    }
}
