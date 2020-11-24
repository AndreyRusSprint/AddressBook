package com.betrye.addressbook.data.mapper

import com.betrye.addressbook.domain.entity.PhoneType
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class PhoneTypeMapperTest : StringSpec({
    "it should convert int code to PhoneType" {
        forAll(
            row(0, PhoneType.OTHER),
            row(1, PhoneType.HOME),
            row(2, PhoneType.MOBILE),
            row(3, PhoneType.WORK),
            row(4, PhoneType.OTHER),
            row(10, PhoneType.OTHER),
            row(12, PhoneType.MAIN),
        ) { phoneTypeCode, phoneType ->
            phoneTypeCode.toPhoneType() shouldBe phoneType
        }
    }
})
