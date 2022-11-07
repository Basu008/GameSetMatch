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
    ): String{
        if(username.isEmpty())
            return Constants.USERNAME_EMPTY
        if(password.isEmpty())
            return Constants.PASSWORD_EMPTY
        if(confirmedPassword.isEmpty())
            return Constants.CONFIRMED_PASSWORD_EMPTY
        if(password != confirmedPassword)
            return Constants.PASSWORD_UNMATCHED
        if(verifyPasswordStrength(password) == Constants.PASSWORD_UNACCEPTABLE)
            return Constants.PASSWORD_UNACCEPTABLE
        return Constants.VALID
    }

    fun verifyPasswordStrength(password: String): String{
        var hasNumber = false
        var hasUpperCase = false
        var hasLowerCase = false
        var hasSpecialCharacters = false
        val specialCharacters = listOf(
            '!', '@', '#', '$', '%', '^', '&',
            '*', '(', ')', '-', '+'
        )
        if(password.length >= 8){
            for(char in password){
                if(char.isDigit())
                    hasNumber = true
                if(char.isUpperCase())
                    hasUpperCase = true
                if(char.isLowerCase())
                    hasLowerCase = true
                if(char in specialCharacters)
                    hasSpecialCharacters = true
            }
            return if(hasLowerCase && hasUpperCase && hasSpecialCharacters && hasNumber)
                Constants.STRONG
            else if(hasLowerCase && hasUpperCase && hasSpecialCharacters)
                Constants.MEDIUM
            else
                Constants.WEAK
        }
        else
            return Constants.PASSWORD_UNACCEPTABLE
    }
}