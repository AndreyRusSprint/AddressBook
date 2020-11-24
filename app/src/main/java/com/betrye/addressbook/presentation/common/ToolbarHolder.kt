package com.betrye.addressbook.presentation.common

import androidx.annotation.StringRes

interface ToolbarHolder {
    fun setToolbarTitle(title: String)
    fun setToolbarTitle(@StringRes titleRes: Int)
}
