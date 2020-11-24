package com.betrye.addressbook.data.mapper

import com.betrye.addressbook.data.entity.PhoneLocal
import com.betrye.addressbook.domain.entity.Phone
import com.betrye.addressbook.domain.entity.PhoneType
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class PhoneMapperTest : StringSpec({
    "it should convert PhoneLocal to Phone" {
        val number = "88005553535"
        val phoneLocal = PhoneLocal(5L, number, 12)
        val expected = Phone(5L, number, PhoneType.MAIN)
        phoneLocal.toPhone() shouldBe expected
    }
})
