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
        if(username.isEmpty() || password.isEmpty() || confirmedPassword.isEmpty())
            return false
        if(username == "Basu")
            return false
        if(password != confirmedPassword)
            return false
        if(verifyPasswordStrength(password) == "Unacceptable")
            return false
        return true
    }

    fun verifyPasswordStrength(password: String): String{
        var hasNumber = false
        var hasUpperCase = false
        var hasLowerCase = false
        var hasSpecialCharacters = false
        var specialCharacters = listOf(
            '!', '@', '#', '$', '%', '^', '&',
            '*', '(', ')', '-', '+'
        )
        if(password.length >= 8){
            for(char in password){
                if(char.isDigit())
                    hasNumber = true
                if(char.isUpperCase())
                    hasLowerCase = true
                if(char.isLowerCase())
                    hasLowerCase = true
                if(char in specialCharacters)
                    hasSpecialCharacters = true
            }
            return if(hasLowerCase && hasUpperCase && hasSpecialCharacters && hasNumber)
                "Strong"
            else if(hasLowerCase && hasUpperCase && hasSpecialCharacters)
                "Medium"
            else
                "Weak"
        }
        else
            return "Unacceptable"
    }
}