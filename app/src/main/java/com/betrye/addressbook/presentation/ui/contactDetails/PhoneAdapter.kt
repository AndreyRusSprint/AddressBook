package com.betrye.addressbook.presentation.ui.contactDetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.betrye.addressbook.databinding.ItemPhoneNumberBinding
import com.betrye.addressbook.presentation.entity.PhonePM
import kotlin.properties.Delegates

class PhoneAdapter(
    private val onItemClick: (phone: PhonePM) -> Unit = {}
) : RecyclerView.Adapter<PhoneAdapter.ViewHolder>() {

    var phoneList: List<PhonePM> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPhoneNumberBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = phoneList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(phoneList[position])
    }

    inner class ViewHolder(
        private val itemBinding: ItemPhoneNumberBinding
    ) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(phone: PhonePM) {
            itemBinding.root.setOnClickListener {
                onItemClick(phone)
            }
            with(itemBinding) {
                tvPhone.text = phone.number
                tvType.setText(phone.type.humanReadableName)
            }
        }
    }
}
