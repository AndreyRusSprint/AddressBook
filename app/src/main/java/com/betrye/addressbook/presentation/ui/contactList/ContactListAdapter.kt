package com.betrye.addressbook.presentation.ui.contactList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.betrye.addressbook.databinding.ItemContactBinding
import com.betrye.addressbook.presentation.entity.ContactPM
import com.betrye.addressbook.presentation.entity.PhoneTypePM
import kotlin.properties.Delegates

internal class ContactListAdapter(
    private val onItemClick: (contact: ContactPM) -> Unit
) : RecyclerView.Adapter<ContactListAdapter.ViewHolder>() {

    var contactList: List<ContactPM> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemContactBinding.inflate(inflater, parent, false)
        return ViewHolder(binding, onItemClick)
    }

    override fun getItemCount(): Int = contactList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(contactList[position])
    }

    class ViewHolder(
        private val itemBinding: ItemContactBinding,
        private val onItemClick: (contact: ContactPM) -> Unit
    ) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(contact: ContactPM) {
            itemBinding.root.setOnClickListener {
                onItemClick(contact)
            }
            with(itemBinding) {
                tvName.text = contact.name
                if (contact.phones.isNotEmpty()) {
                    tvPhone.text = contact.phones
                        .find { it.type == PhoneTypePM.MAIN }?.number
                        ?: contact.phones.first().number
                }
                contact.avatarUri?.let(ivAvatar::setImageURI)
            }
        }
    }
}
