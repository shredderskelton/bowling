package com.nickskelton.bowlingscorer

import android.os.Bundle
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    private val recyclerView: RecyclerView by lazy {
        findViewById<RecyclerView>(R.id.recyclerView)
    }
    private val submitButton: Button by lazy {
        findViewById<Button>(R.id.submitButton)
    }
    private val editText: EditText by lazy {
        findViewById<EditText>(R.id.editText)
    }
    private val toolbar: Toolbar by lazy {
        findViewById<Toolbar>(R.id.toolbar)
    }
    private val coordinatorLayout: CoordinatorLayout by lazy {
        findViewById<CoordinatorLayout>(R.id.contentCoordinatorLayout)
    }


    private var rolls = IntArray(21)
    private var currentRoll: Int = 0

    private val adapter = FrameAdapter(this)
    private var scoreCard = Scorecard(rolls)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        submitButton.setOnClickListener {
            onRoll()
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    private fun onRoll() {
        val validRoll: Int? = validateInput()
        validRoll?.let {

            //finished game
            if (currentRoll >= rolls.size) {
                reset()
            }

            rolls[currentRoll] = it
            val newScorecard = Scorecard(rolls)
            if (!newScorecard.isValid) {
                Snackbar.make(coordinatorLayout, "Invalid entry. Check how many pins remain...", Snackbar.LENGTH_LONG).show()
                return
            }

            adapter.update(newScorecard.frames)
            scoreCard = newScorecard
            currentRoll++
        }
    }

    private fun reset() {
        rolls = IntArray(21)
        currentRoll = 0
    }

    private fun validateInput(): Int? {
        try {
            val roll = editText.text.toString().toInt()
            if (roll in 0..10) {
                return roll
            }else{
                Snackbar.make(coordinatorLayout, "Invalid entry. Numbers between 0 and 10.", Snackbar.LENGTH_LONG).show()
            }
        } catch (e: NumberFormatException) {
            Snackbar.make(coordinatorLayout, "Invalid entry. Numbers only.", Snackbar.LENGTH_LONG).show()
        }

        editText.setText("")
        return null
    }
}
