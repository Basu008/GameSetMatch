package com.example.gamesetmatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.gamesetmatch.databinding.ActivityHomePageBinding
import com.example.gamesetmatch.util.Constants

class HomePageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomePageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home_page)

        val username = intent.getStringExtra(Constants.USERNAME)

        if(username != null){
            val welcomeMessage = "Hello, $username!"
            binding.welcomeMessageTv.text = welcomeMessage
        }
    }
}