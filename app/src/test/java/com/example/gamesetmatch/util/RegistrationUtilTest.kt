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
        assertThat(result).isEqualTo(Constants.USERNAME_EMPTY)
    }


    @Test
    fun `empty password field returns false`(){
        val result = RegistrationUtil.validateRegistrationInput(
            "Basu",
            "",
            "123"
        )
        assertThat(result).isEqualTo(Constants.PASSWORD_EMPTY)
    }

    @Test
    fun `repeated empty password field returns false`(){
        val result = RegistrationUtil.validateRegistrationInput(
            "Basu",
            "123",
            ""
        )
        assertThat(result).isEqualTo(Constants.CONFIRMED_PASSWORD_EMPTY)
    }


    @Test
    fun `valid username and correctly repeated and accepted password return true`(){
        val result = RegistrationUtil.validateRegistrationInput(
            "Basuu",
            "123456789",
            "123456789"
        )
        assertThat(result).isEqualTo(Constants.VALID)
    }

    @Test
    fun `password is less than 8 digit return false`(){
        val result = RegistrationUtil.verifyPasswordStrength(
            "12345"
        )
        assertThat(result).isEqualTo(Constants.PASSWORD_UNACCEPTABLE)
    }

}