package com.example.gamesetmatch.ui.addplayer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.gamesetmatch.R
import com.example.gamesetmatch.adapter.PlayersAdapter
import com.example.gamesetmatch.data.Player
import com.example.gamesetmatch.databinding.ActivityAddPlayerBinding
import com.example.gamesetmatch.viewmodel.PlayerViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog

class AddPlayerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddPlayerBinding
    private lateinit var addPlayerBottomSheetDialog: BottomSheetDialog
    private lateinit var playerViewModel: PlayerViewModel
    private lateinit var playersAdapter: PlayersAdapter

    private lateinit var playerNameET: EditText
    private lateinit var optionFootballTV: TextView
    private lateinit var optionBasketballTV: TextView
    private lateinit var footballGradeA: TextView
    private lateinit var footballGradeB: TextView
    private lateinit var footballGradeC: TextView
    private lateinit var basketballGradeA: TextView
    private lateinit var basketballGradeB: TextView
    private lateinit var basketballGradeC: TextView
    private lateinit var doneBtn: AppCompatButton
    private lateinit var cancelBtn: AppCompatButton
    private lateinit var closeBtn: ImageView
    private lateinit var footballGradesLayout: ConstraintLayout
    private lateinit var basketballGradesLayout: ConstraintLayout

    private lateinit var allPlayers: List<Player>

    private var isFootballSelected = false
    private var isBasketballSelected = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_player)
        playerViewModel = ViewModelProvider(this)[PlayerViewModel::class.java]

        playerViewModel.allPlayers.observe(this){
            if(it.isNotEmpty()){
                allPlayers = it
                playersAdapter = PlayersAdapter(this, it)
                binding.allPlayersRv.adapter = playersAdapter
                playersAdapter.notifyDataSetChanged()
                binding.allPlayersRv.visibility = View.VISIBLE
                Log.d("AllPlayers", allPlayers.toString())
            }
            else
                binding.noPlayersTv.visibility = View.VISIBLE
        }

        initiateAddPlayerBottomSheet()

        binding.addPlayersBtn.setOnClickListener {
            addPlayerBottomSheetDialog.show()
        }

        binding.backBtn.setOnClickListener {
            finish()
        }
    }

    private fun initiateAddPlayerBottomSheet(){
        val addPlayerBottomSheet = LayoutInflater.from(this).inflate(R.layout.bottomsheet_add_player, null)
        addPlayerBottomSheetDialog = BottomSheetDialog(this, R.style.BottomSheetDialogTheme)
        addPlayerBottomSheetDialog.setContentView(addPlayerBottomSheet)

        playerNameET = addPlayerBottomSheet.findViewById(R.id.player_name_et)
        optionFootballTV = addPlayerBottomSheet.findViewById(R.id.option_football)
        optionBasketballTV = addPlayerBottomSheet.findViewById(R.id.option_basketball)
        footballGradeA = addPlayerBottomSheet.findViewById(R.id.football_grade_a)
        footballGradeB = addPlayerBottomSheet.findViewById(R.id.football_grade_b)
        footballGradeC = addPlayerBottomSheet.findViewById(R.id.football_grade_c)
        basketballGradeA = addPlayerBottomSheet.findViewById(R.id.basketball_grade_a)
        basketballGradeB = addPlayerBottomSheet.findViewById(R.id.basketball_grade_b)
        basketballGradeC = addPlayerBottomSheet.findViewById(R.id.basketball_grade_c)
        doneBtn = addPlayerBottomSheet.findViewById(R.id.done_btn)
        cancelBtn = addPlayerBottomSheet.findViewById(R.id.cancel_button)
        closeBtn = addPlayerBottomSheet.findViewById(R.id.close_btn)
        footballGradesLayout = addPlayerBottomSheet.findViewById(R.id.football_grades)
        basketballGradesLayout = addPlayerBottomSheet.findViewById(R.id.basketball_grades)

        var gradeFootball = 0
        var gradeBasketball = 0

        optionFootballTV.setOnClickListener {
            if(isFootballSelected){
                isFootballSelected = false
                optionFootballTV.setBackgroundResource(R.drawable.bg_button_highlight)
                optionFootballTV.setTextColor(ContextCompat.getColor(this, R.color.white))
                footballGradesLayout.visibility = View.GONE
            }
            else{
                isFootballSelected = true
                optionFootballTV.setBackgroundResource(R.drawable.bg_sport_selected)
                optionFootballTV.setTextColor(ContextCompat.getColor(this, R.color.black))
                footballGradesLayout.visibility = View.VISIBLE
            }
        }

        optionBasketballTV.setOnClickListener {
            if(isBasketballSelected){
                isBasketballSelected = false
                optionBasketballTV.setBackgroundResource(R.drawable.bg_button_highlight)
                optionBasketballTV.setTextColor(ContextCompat.getColor(this, R.color.white))
                basketballGradesLayout.visibility = View.GONE
            }
            else{
                isBasketballSelected = true
                optionBasketballTV.setBackgroundResource(R.drawable.bg_sport_selected)
                optionBasketballTV.setTextColor(ContextCompat.getColor(this, R.color.black))
                basketballGradesLayout.visibility = View.VISIBLE
            }
        }

        footballGradeA.setOnClickListener {

            footballGradeA.setBackgroundResource(R.drawable.bg_grade_a_selected)
            footballGradeA.setTextColor(ContextCompat.getColor(this, R.color.white))
            footballGradeB.setBackgroundResource(R.drawable.bg_grade_b)
            footballGradeB.setTextColor(ContextCompat.getColor(this, R.color.yellow))
            footballGradeC.setBackgroundResource(R.drawable.bg_grade_c)
            footballGradeC.setTextColor(ContextCompat.getColor(this, R.color.red))
            gradeFootball = 3

        }

        footballGradeB.setOnClickListener {

            footballGradeA.setBackgroundResource(R.drawable.bg_grade_a)
            footballGradeA.setTextColor(ContextCompat.getColor(this, R.color.green))
            footballGradeB.setBackgroundResource(R.drawable.bg_grade_b_selected)
            footballGradeB.setTextColor(ContextCompat.getColor(this, R.color.white))
            footballGradeC.setBackgroundResource(R.drawable.bg_grade_c)
            footballGradeC.setTextColor(ContextCompat.getColor(this, R.color.red))
            gradeFootball = 2

        }

        footballGradeC.setOnClickListener {

            footballGradeA.setBackgroundResource(R.drawable.bg_grade_a)
            footballGradeA.setTextColor(ContextCompat.getColor(this, R.color.green))
            footballGradeB.setBackgroundResource(R.drawable.bg_grade_b)
            footballGradeB.setTextColor(ContextCompat.getColor(this, R.color.yellow))
            footballGradeC.setBackgroundResource(R.drawable.bg_grade_c_selected)
            footballGradeC.setTextColor(ContextCompat.getColor(this, R.color.white))
            gradeFootball = 1

        }

        basketballGradeA.setOnClickListener {

            basketballGradeA.setBackgroundResource(R.drawable.bg_grade_a_selected)
            basketballGradeA.setTextColor(ContextCompat.getColor(this, R.color.white))
            basketballGradeB.setBackgroundResource(R.drawable.bg_grade_b)
            basketballGradeB.setTextColor(ContextCompat.getColor(this, R.color.yellow))
            basketballGradeC.setBackgroundResource(R.drawable.bg_grade_c)
            basketballGradeC.setTextColor(ContextCompat.getColor(this, R.color.red))
            gradeBasketball = 3

        }

        basketballGradeB.setOnClickListener {

            basketballGradeA.setBackgroundResource(R.drawable.bg_grade_a)
            basketballGradeA.setTextColor(ContextCompat.getColor(this, R.color.green))
            basketballGradeB.setBackgroundResource(R.drawable.bg_grade_b_selected)
            basketballGradeB.setTextColor(ContextCompat.getColor(this, R.color.white))
            basketballGradeC.setBackgroundResource(R.drawable.bg_grade_c)
            basketballGradeC.setTextColor(ContextCompat.getColor(this, R.color.red))
            gradeBasketball = 2

        }

        basketballGradeC.setOnClickListener {

            basketballGradeA.setBackgroundResource(R.drawable.bg_grade_a)
            basketballGradeA.setTextColor(ContextCompat.getColor(this, R.color.green))
            basketballGradeB.setBackgroundResource(R.drawable.bg_grade_b)
            basketballGradeB.setTextColor(ContextCompat.getColor(this, R.color.yellow))
            basketballGradeC.setBackgroundResource(R.drawable.bg_grade_c_selected)
            basketballGradeC.setTextColor(ContextCompat.getColor(this, R.color.white))
            gradeBasketball = 1

        }

        doneBtn.setOnClickListener {
            val playerName = playerNameET.text.toString()
            val player = Player(0, playerName,
                isFootballSelected, isBasketballSelected, gradeFootball, gradeBasketball)
            playerViewModel.addUser(player)
            closeAddPlayerBottomSheet()
        }

        cancelBtn.setOnClickListener {
            closeAddPlayerBottomSheet()
        }

        closeBtn.setOnClickListener {
            closeAddPlayerBottomSheet()
        }
    }

    private fun closeAddPlayerBottomSheet(){
        addPlayerBottomSheetDialog.dismiss()
        basketballGradeA.setBackgroundResource(R.drawable.bg_grade_a)
        basketballGradeA.setTextColor(ContextCompat.getColor(this, R.color.green))
        basketballGradeB.setBackgroundResource(R.drawable.bg_grade_b)
        basketballGradeB.setTextColor(ContextCompat.getColor(this, R.color.yellow))
        basketballGradeC.setBackgroundResource(R.drawable.bg_grade_c)
        basketballGradeC.setTextColor(ContextCompat.getColor(this, R.color.red))
        isFootballSelected = false
        optionFootballTV.setBackgroundResource(R.drawable.bg_button_highlight)
        optionFootballTV.setTextColor(ContextCompat.getColor(this, R.color.white))
        footballGradesLayout.visibility = View.GONE
        isBasketballSelected = false
        optionBasketballTV.setBackgroundResource(R.drawable.bg_button_highlight)
        optionBasketballTV.setTextColor(ContextCompat.getColor(this, R.color.white))
        basketballGradesLayout.visibility = View.GONE
    }

}
