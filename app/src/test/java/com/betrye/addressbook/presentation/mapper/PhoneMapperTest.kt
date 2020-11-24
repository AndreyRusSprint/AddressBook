package com.betrye.addressbook.presentation.mapper

import com.betrye.addressbook.domain.entity.Phone
import com.betrye.addressbook.domain.entity.PhoneType
import com.betrye.addressbook.presentation.entity.PhonePM
import com.betrye.addressbook.presentation.entity.PhoneTypePM
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class PhoneMapperTest : StringSpec({
    "it should convert Phone to presentation model" {
        val number = "88005553535"
        val phone = Phone(5L, number, PhoneType.WORK)
        val expected = PhonePM(5L, number, PhoneTypePM.WORK)
        phone.toPresentationModel() shouldBe expected
    }
})
