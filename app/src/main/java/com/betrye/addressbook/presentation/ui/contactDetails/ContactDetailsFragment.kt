package com.betrye.addressbook.presentation.ui.contactDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.betrye.addressbook.databinding.FragmentContactDetailsBinding
import com.betrye.addressbook.presentation.common.ToolbarHolder
import com.betrye.addressbook.presentation.entity.ContactPM
import com.betrye.addressbook.presentation.viewModel.contactDetails.ContactDetailsViewModel

class ContactDetailsFragment(private val viewModel: ContactDetailsViewModel) : Fragment() {

    companion object {
        const val KEY_CONTACT = "contact"
    }

    private var _binding: FragmentContactDetailsBinding? = null
    private val binding: FragmentContactDetailsBinding
        get() = _binding!!

    private val toolbarHolder: ToolbarHolder?
        get() = activity as? ToolbarHolder

    private var phoneAdapter: PhoneAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentContactDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupRecyclerView()
        observeViewModel()

        val contact = arguments?.getParcelable(KEY_CONTACT) as? ContactPM
        contact?.let(viewModel::setContact)
    }

    override fun onResume() {
        super.onResume()
        toolbarHolder?.setToolbarVisibility(false)
    }

    override fun onStop() {
        toolbarHolder?.setToolbarVisibility(true)
        super.onStop()
    }

    override fun onDestroyView() {
        _binding = null
        phoneAdapter = null
        super.onDestroyView()
    }

    private fun setupRecyclerView() {
        phoneAdapter = PhoneAdapter()
        binding.rvNumbers.let { rv ->
            rv.layoutManager = LinearLayoutManager(context)
            rv.setHasFixedSize(false)
            rv.adapter = phoneAdapter
        }
    }

    private fun observeViewModel() {
        viewModel.contact
            .observe(viewLifecycleOwner) { contact ->
                binding.collapsingToolbar.title = contact.name
                contact.avatarUri?.let(binding.ivAvatar::setImageURI)
                phoneAdapter?.phoneList = contact.phones
            }
    }
}
