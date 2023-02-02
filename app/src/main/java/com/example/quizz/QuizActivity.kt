package com.example.quizz

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class QuizActivity : AppCompatActivity() {

    var quizs = ArrayList<Quiz>()
    var numberOfCorrectAnswer: Int = 0
    var currentQuizIndex: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        quizs.add(Quiz("Quelle est la capitale de la Suisse", "Genève", "Berne", "Lausanne" , 2))
        quizs.add(Quiz("Quelle est la capitale de la Suede", "Stockholm", "Göteborg", "Malmö" , 1))
        quizs.add(Quiz("Quelle est la capitale du Canada", "Otawa", "Montréal", "Toronto" , 1))
        quizs.add(Quiz("Quelle est la capitale de la Finlande", "Oulu", "Turku", "Helsinki" , 3))

        showQuestion(quizs.get(currentQuizIndex))
    }

    fun showQuestion(quiz: Quiz) {
        val txtQuestion: TextView = findViewById(R.id.txtQuestion)
        val answer1: TextView = findViewById(R.id.answer1)
        val answer2: TextView = findViewById(R.id.answer2)
        val answer3: TextView = findViewById(R.id.answer3)

        txtQuestion.setText(quiz.question)
        answer1.setText(quiz.answer1)
        answer2.setText(quiz.answer2)
        answer3.setText(quiz.answer3)

    }

    fun handleAnswer(answerID: Int) {
        val quiz = quizs.get(currentQuizIndex)

        if(quiz.isCorrect(answerID)) {
            numberOfCorrectAnswer++
            Toast.makeText(this, "+1", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "+0", Toast.LENGTH_SHORT).show()
        }

        currentQuizIndex++

        if(currentQuizIndex >= quizs.size) {
            val sharedPreferences = getSharedPreferences("google.com", MODE_PRIVATE)
            sharedPreferences.edit().putInt("userScore", numberOfCorrectAnswer).apply()

            val alert = AlertDialog.Builder(this)
            alert.setTitle("Partie Finie")
            alert.setMessage("Tu as eu: " + numberOfCorrectAnswer + " bonne(s) réponse(s)")
            alert.setPositiveButton("OK") { dialogInterface: DialogInterface?, i: Int ->
                finish()
            }
            alert.show()
        } else {
            showQuestion(quizs.get(currentQuizIndex))
        }
    }

    fun onClickAnswerOne(view: View) {
        handleAnswer(1)
    }
    fun onClickAnswerTwo(view: View) {
        handleAnswer(2)
    }
    fun onClickAnswerThree(view: View) {
        handleAnswer(3)
    }
}