package com.example.gamesetmatch.ui.addplayer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import com.example.gamesetmatch.R
import com.example.gamesetmatch.databinding.ActivityAddPlayerBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

class AddPlayerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddPlayerBinding
    private lateinit var addPlayerBottomSheetDialog: BottomSheetDialog


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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_player)
        initiateAddPlayerBottomSheet()

        binding.addPlayersBtn.setOnClickListener {
            addPlayerBottomSheetDialog.show()
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

        closeBtn.setOnClickListener {
            addPlayerBottomSheetDialog.dismiss()
        }
    }
}