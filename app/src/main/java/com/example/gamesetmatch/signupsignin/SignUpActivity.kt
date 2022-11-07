package com.example.gamesetmatch.signupsignin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.gamesetmatch.HomePageActivity
import com.example.gamesetmatch.R
import com.example.gamesetmatch.UserViewModel
import com.example.gamesetmatch.data.User
import com.example.gamesetmatch.databinding.ActivitySignUpBinding
import com.example.gamesetmatch.util.Constants
import com.example.gamesetmatch.util.RegistrationUtil
import com.example.gamesetmatch.util.sharedPreference

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private lateinit var userViewModel: UserViewModel
    private lateinit var allUsers: List<User>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)

        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        userViewModel.allUsers.observe(this){
            allUsers = it
        }
        addTextChangedListener()

        binding.backBtn.setOnClickListener {
            finish()
        }

        binding.doneBtn.setOnClickListener {
            val username = binding.usernameEt.text.toString()
            val password = binding.passwordEt.text.toString()
            val confirmedPassword = binding.confirmPasswordEt.text.toString()

            val formVerificationStatus = RegistrationUtil.validateRegistrationInput(username.trim(),
                password.trim(),
                confirmedPassword.trim())

            when(formVerificationStatus){
                Constants.USERNAME_EMPTY -> {
                    binding.usernameError.visibility = View.VISIBLE
                    binding.usernameError.text = getString(R.string.empty_username)
                }
                Constants.PASSWORD_EMPTY -> {
                    binding.passwordError.setTextColor(
                        ContextCompat.getColor(this@SignUpActivity, R.color.red))
                    binding.passwordError.visibility = View.VISIBLE
                    binding.passwordError.text = getString(R.string.empty_password)
                }
                Constants.CONFIRMED_PASSWORD_EMPTY -> {
                    binding.confirmPasswordError.setTextColor(
                        ContextCompat.getColor(this@SignUpActivity, R.color.red))
                    binding.confirmPasswordError.visibility = View.VISIBLE
                    binding.confirmPasswordError.text = getString(R.string.empty_password)
                }
                Constants.PASSWORD_UNMATCHED -> {
                    binding.confirmPasswordError.setTextColor(
                        ContextCompat.getColor(this@SignUpActivity, R.color.red))
                    binding.confirmPasswordError.visibility = View.VISIBLE
                    binding.confirmPasswordError.text = getString(R.string.unmatched_password)
                }
                Constants.VALID -> {
                    binding.confirmPasswordError.visibility = View.GONE
                    binding.passwordError.visibility = View.GONE
                    binding.usernameError.visibility = View.GONE

                    val user = User(0, username.trim(), password.trim())
                    userViewModel.addUser(user)

                    Intent(this, HomePageActivity::class.java).also {
                        sharedPreference.startSession(this, username.trim())
                        it.putExtra(Constants.USERNAME , username.trim())
                        startActivity(it)
                    }
                }
            }

        }
    }

    private fun addTextChangedListener(){
        binding.passwordEt.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(!s.isNullOrEmpty()){
                    binding.passwordError.visibility = View.GONE
                    if(s.length < 8) {
                        binding.passwordError.visibility = View.VISIBLE
                        binding.doneBtn.isEnabled = false
                        binding.passwordError.setTextColor(
                            ContextCompat.getColor(this@SignUpActivity, R.color.red))
                        binding.passwordError.text = getString(R.string.password_short)
                    }
                    else{
                        binding.doneBtn.isEnabled = true
                        binding.passwordError.visibility = View.VISIBLE
                        when(RegistrationUtil.verifyPasswordStrength(s.toString())){
                            Constants.WEAK -> {
                                binding.passwordError.setTextColor(
                                    ContextCompat.getColor(this@SignUpActivity, R.color.red))
                                binding.passwordError.text = getString(R.string.weak_password)
                            }
                            Constants.MEDIUM -> {
                                binding.passwordError.setTextColor(
                                    ContextCompat.getColor(this@SignUpActivity, R.color.yellow))
                                binding.passwordError.text = getString(R.string.medium_password)
                            }
                            Constants.STRONG -> {
                                binding.passwordError.setTextColor(
                                    ContextCompat.getColor(this@SignUpActivity, R.color.green))
                                binding.passwordError.text = getString(R.string.strong_password)
                            }
                        }
                    }
                }
                else{
                    binding.passwordError.visibility = View.GONE
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })

        binding.confirmPasswordEt.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                binding.passwordError.visibility = View.GONE
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {

            }

        })

        binding.usernameEt.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                binding.passwordError.visibility = View.GONE
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(!s.isNullOrEmpty() && allUsers.isNotEmpty()){
                    binding.usernameError.visibility = View.GONE
                    for(user in allUsers){
                        if(s.toString() == user.username){
                            binding.usernameError.text = getString(R.string.username_taken)
                            binding.usernameError.visibility = View.VISIBLE
                        }
                    }
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })


    }
}