package com.example.gamesetmatch

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.gamesetmatch.databinding.ActivityMainBinding
import com.example.gamesetmatch.signupsignin.SignInActivity
import com.example.gamesetmatch.signupsignin.SignUpActivity
import com.example.gamesetmatch.util.Constants
import com.example.gamesetmatch.util.sharedPreference

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        if(sharedPreference.isSessionActive(this)){
            val username = sharedPreference.getUsername(this)
            Intent(this, HomePageActivity::class.java).also {
                it.putExtra(Constants.USERNAME, username.trim())
                startActivity(it)
                finish()
            }
        }

        binding.getStartedBtn.setOnClickListener {
            Intent(this, SignUpActivity::class.java).also {
                startActivity(it)
            }
        }

        binding.signInNavBtn.setOnClickListener {
            Intent(this, SignInActivity::class.java).also {
                startActivity(it)
            }
        }
    }
}