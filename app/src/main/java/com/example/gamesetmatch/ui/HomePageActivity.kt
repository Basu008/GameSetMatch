package com.example.gamesetmatch.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import com.example.gamesetmatch.R
import com.example.gamesetmatch.databinding.ActivityHomePageBinding
import com.example.gamesetmatch.ui.addplayer.AddPlayerActivity
import com.example.gamesetmatch.util.Constants

class HomePageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomePageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home_page)

        val username = intent.getStringExtra(Constants.USERNAME)

        binding.backBtn.setOnClickListener {
            finish()
        }

        if(username != null){
            val welcomeMessage = "Hello, $username!"
            binding.welcomeMessageTv.text = welcomeMessage
        }

        binding.addPlayersBtn.setOnClickListener {
            Intent(this, AddPlayerActivity::class.java).also {
                startActivity(it)
            }
        }
    }
}