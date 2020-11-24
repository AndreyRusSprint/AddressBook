package com.betrye.addressbook.presentation.entity

import androidx.annotation.StringRes
import com.betrye.addressbook.R

enum class ErrorState(@StringRes val message: Int? = null) {
    NONE,
    DATA_STORE_FAILURE(R.string.error_state_data_store_failure),
    PERMISSION_DENIED(R.string.error_state_permission_denied);
}
