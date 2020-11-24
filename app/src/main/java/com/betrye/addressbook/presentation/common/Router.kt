package com.betrye.addressbook.presentation.common

import android.os.Bundle
import androidx.fragment.app.Fragment

interface Router {
    fun navigateTo(fragmentClass: Class<out Fragment>, args: Bundle?)
}
