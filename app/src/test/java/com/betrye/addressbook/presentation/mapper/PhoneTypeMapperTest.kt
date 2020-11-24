package com.betrye.addressbook.presentation.mapper

import com.betrye.addressbook.domain.entity.PhoneType
import com.betrye.addressbook.presentation.entity.PhoneTypePM
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class PhoneTypeMapperTest : StringSpec({
    "it should convert PhoneType to presentation model" {
        forAll(
            row(PhoneType.MAIN, PhoneTypePM.MAIN),
            row(PhoneType.OTHER, PhoneTypePM.OTHER),
            row(PhoneType.WORK, PhoneTypePM.WORK),
            row(PhoneType.MOBILE, PhoneTypePM.MOBILE),
            row(PhoneType.HOME, PhoneTypePM.HOME),
        ) { phoneType, phoneTypePM ->
            phoneType.toPresentationModel() shouldBe phoneTypePM
        }
    }
})
