package com.example.cecs453assigment2tic_tac_toe
//import com.example.cecs453assigment2tic_tac_toe.Global.Companion.buttons
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.cecs453assigment2tic_tac_toe.TicTacToe.Companion.result
import com.example.cecs453assigment2tic_tac_toe.databinding.ActivityMainBinding

//gloabal
//view model
class MainActivity : AppCompatActivity()
{


    companion object {
        lateinit var buttons: Array<Array<Button>>
        lateinit var status: TextView
        fun test(str: String, applicationContext: Context): Unit {
            val toast = Toast.makeText(applicationContext, str,
                Toast.LENGTH_SHORT)
            toast.show()
        }

    }


    private lateinit var binding: ActivityMainBinding
    var turn : Int =1
    val SIDE: Int = 3

    private lateinit var game: Array<IntArray>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //    setContentView(R.layout.activity_main)
        game = Array(TicTacToe.SIDE) { IntArray(TicTacToe.SIDE) }
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        buildGuiByCode()
    }



    fun buildGuiByCode() {
        val metrics = resources.displayMetrics
        val width: Int = metrics.widthPixels
        //  val w: Int = size.x / TicTacToe.SIDE
        val w: Int = width / TicTacToe.SIDE

        // Create the layout manager as a GridLayout
        val gridLayout = GridLayout(this)
        gridLayout.setColumnCount(TicTacToe.SIDE)
        gridLayout.setRowCount(TicTacToe.SIDE+1)

        // Create the buttons and add them to gridLayout
        var row: Int
        var col: Int
        buttons = Array(TicTacToe.SIDE) { row ->
            Array(TicTacToe.SIDE) { col ->
                //   Button(row, col)
                Button(this)

            }

        }

        for (row in 0 until TicTacToe.SIDE) {
            for (col in 0 until TicTacToe.SIDE) {
                buttons[row][col].setTextSize(w * 0.2f)
                gridLayout.addView(buttons.get(row).get(col), w, w)

                // Set gridLayout as the View of this Activity
              //  setContentView(gridLayout)
                //      for (row in 0 until TicTacToe.SIDE) {
                //        for (col in 0 until TicTacToe.SIDE) {
                buttons[row][col].setOnClickListener { view: View ->
                    TicTacToe.update(row, col, this, Button(this), status)
                }

            }
        }

        // set up layout parameters of 4th row of gridLayout
        status = TextView(this)
        val rowSpec = GridLayout.spec(3, 1)
        val columnSpec = GridLayout.spec(0, TicTacToe.SIDE)
        val lpStatus = GridLayout.LayoutParams(rowSpec,columnSpec)
        status.layoutParams = lpStatus

        // set up status' characteristics
        status.setWidth(TicTacToe.SIDE*10000)
        status.setHeight(w)
        status.setGravity(Gravity.CENTER)
        status.setBackgroundColor(Color.GREEN)
        status.setTextSize((w * .15).toInt().toFloat())
        status.setText(result())

        gridLayout.addView(status,-1)
// Set gridLayout as the View of this Activity

        // Set gridLayout as the View of this Activity
        setContentView(gridLayout)
    }


}