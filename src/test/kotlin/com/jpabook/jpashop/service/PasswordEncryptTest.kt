package com.jpabook.jpashop.service

import org.jasypt.util.password.StrongPasswordEncryptor
import org.junit.jupiter.api.Test

internal class PasswordEncryptTest {
    @Test
    fun funName() {
        val encryptor = StrongPasswordEncryptor()
        val encryptPassword = encryptor.encryptPassword("1234")

        println(encryptPassword)

        encryptor.checkPassword("1234", encryptPassword)
    }
}