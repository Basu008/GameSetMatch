package com.example.gamesetmatch.util

import com.google.common.truth.Truth.assertThat
import org.junit.Test


class RegistrationUtilTest{

    @Test
    fun `empty username field returns false`(){
        val result = RegistrationUtil.validateRegistrationInput(
            "",
            "123",
            "123"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `both empty password field returns false`(){
        val result = RegistrationUtil.validateRegistrationInput(
            "Basu",
            "",
            ""
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `empty password field returns false`(){
        val result = RegistrationUtil.validateRegistrationInput(
            "Basu",
            "",
            "123"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `repeated empty password field returns false`(){
        val result = RegistrationUtil.validateRegistrationInput(
            "Basu",
            "123",
            ""
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `username is already taken returns false`(){
        val result = RegistrationUtil.validateRegistrationInput(
            "Basu",
            "123",
            "123"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `valid username and correctly repeated and accepted password return true`(){
        val result = RegistrationUtil.validateRegistrationInput(
            "Basuu",
            "123456789",
            "123456789"
        )
        assertThat(result).isTrue()
    }

    @Test
    fun `password is less than 8 digit return false`(){
        val result = RegistrationUtil.verifyPasswordStrength(
            "12345"
        )
        assertThat(result).isEqualTo("Unacceptable")
    }

}