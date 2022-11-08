package com.example.gamesetmatch.ui.signupsignin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.gamesetmatch.ui.HomePageActivity
import com.example.gamesetmatch.R
import com.example.gamesetmatch.viewmodel.UserViewModel
import com.example.gamesetmatch.data.User
import com.example.gamesetmatch.databinding.ActivitySignInBinding
import com.example.gamesetmatch.util.Constants
import com.example.gamesetmatch.util.sharedPreference

class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding
    private lateinit var userViewModel: UserViewModel
    private lateinit var allUsers: List<User>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_in)

        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        userViewModel.allUsers.observe(this){
            allUsers = it
        }

        binding.backBtn.setOnClickListener {
            finish()
        }

        binding.signInBtn.setOnClickListener {
            val username = binding.usernameEt.text.toString()
            val password = binding.passwordEt.text.toString()

            if(username.trim().isNotEmpty() && password.trim().isNotEmpty()){
                var userExist = false
                if(allUsers.isNotEmpty()){
                    for(user in allUsers){
                        if(username == user.username){
                            userExist = true
                            Intent(this, HomePageActivity::class.java).also {
                                sharedPreference.startSession(this@SignInActivity, username.trim())
                                it.putExtra(Constants.USERNAME, username.trim())
                                startActivity(it)
                                finish()
                            }
                        }
                    }
                    if(!userExist)
                        binding.errorTv.visibility = View.VISIBLE
                }
                else{
                    binding.errorTv.visibility = View.VISIBLE
                }
            }
            else{
                binding.errorTv.visibility = View.VISIBLE
            }
        }
    }
}