package com.betrye.addressbook.presentation.entity

import android.os.Parcelable
import androidx.annotation.StringRes
import com.betrye.addressbook.R
import kotlinx.parcelize.Parcelize

@Parcelize
enum class PhoneTypePM(@StringRes val humanReadableName: Int) : Parcelable {
    MAIN(R.string.phone_type_main),
    MOBILE(R.string.phone_type_mobile),
    HOME(R.string.phone_type_home),
    WORK(R.string.phone_type_work),
    OTHER(R.string.phone_type_other);
}
