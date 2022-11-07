package com.example.gamesetmatch.util

object RegistrationUtil {

    /**
     * The input is not valid if ...
     * ... the username/ password field is empty
     * ... the username is already taken
     * ... the confirmed password and password doesn't match
     * ... the password length is less than 8
     */

    fun validateRegistrationInput(
        username: String,
        password: String,
        confirmedPassword: String
    ): Boolean{
        return true
    }

    fun verifyPasswordStrength(password: String): String{
        var hasLowerCase = false
        var hasUpperCase = false
        var hasNumber = false
        return ""
    }
}