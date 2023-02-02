package com.example.quizz

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    @SuppressLint("SetTextI18n")
    override fun onResume() {
        super.onResume()
        val sharedPreferences = getSharedPreferences("google.com", MODE_PRIVATE)

        val userScore = sharedPreferences.getInt("userScore", 0)

        if (userScore > -1) {
            val score: TextView = findViewById(R.id.score)
            score.text = "Ton dernier score est de $userScore"
        }
    }

    fun onClickBtnPlay (view:View) {
        val intent = Intent(this, QuizActivity::class.java)
        startActivity(intent)
    }
}