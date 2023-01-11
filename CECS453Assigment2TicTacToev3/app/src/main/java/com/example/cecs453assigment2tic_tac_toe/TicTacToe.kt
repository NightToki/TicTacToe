package com.example.cecs453assigment2tic_tac_toe

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.widget.Button
import android.widget.TextView
//import com.example.cecs453assigment2tic_tac_toe.MainActivity.Companion.showNewGameDialog
import com.example.cecs453assigment2tic_tac_toe.MainActivity.Companion.status


class TicTacToe {


    fun do1(): String
    { return "done" }
    companion object {
        var buttons = MainActivity.buttons
        var turn : Int =1
         var game: Array<IntArray>

        fun play(row: Int, col: Int): Int {
            var currentTurn = turn

            if (row >= 0 && col >= 0 && row < 3 && col < SIDE && game[row][col]
                == 0)
            {
                game[row][col] = turn

                if (turn == 1)
                    turn = 2
                else
                    turn = 1
                return currentTurn
            }
            else
                return 0
        }

        fun checkRows(): Int {
            for (row in 0 until SIDE) if (game[row][0] != 0 && game[row][0] ==
                game[row][1] && game[row][1] == game[row][2]) return game[row][0]
            return 0
        }

        fun checkColumns(): Int {
            for (col in 0 until SIDE) if (game[0][col] != 0 && game[0][col] ==
                game[1][col] && game[1][col] == game[2][col]) return game[0][col]
            return 0
        }
        fun checkDiagonals(): Int {
            if (game[0][0] != 0 && game[0][0] == game[1][1] && game[1][1] ==
                game[2][2]) return game[0][0]
            return if (game[0][2] != 0 && game[0][2] == game[1][1] && game[1][1]
                == game[2][0]
            ) game[2][0] else 0
        }
        fun whoWon(): Int
        {
            val rows = checkRows()
            if (rows > 0) return rows
            val columns = checkColumns()
            if (columns > 0) return columns
            val diagonals = checkDiagonals()
            return if (diagonals > 0) diagonals else 0
        }

        fun canNotPlay(): Boolean
        {
            var result = true
            for (row in 0 until SIDE) for (col in 0 until SIDE) if
                                                                        (game[row][col] == 0) result = false
            return result
        }

        fun isGameOver():Boolean
        {  //return true
            return canNotPlay() || (whoWon() > 0)
        }
        fun resetGame() {
            for (row in 0 until SIDE) for (col in 0 until SIDE) game[row][col] = 0
            turn = 1
        }
        fun result(): String {
            if (whoWon() > 0)
                return "Player " + whoWon() + " won"
            else if (canNotPlay())
                return "Tie Game"
            else
                return "PLAY !!"
        }
        fun enableButtons( enabled: Boolean ) {

            for (row in 0 until TicTacToe.SIDE) {
                for (col in 0 until TicTacToe.SIDE) {

                    buttons[row][col].setEnabled(enabled);

                }
            }

        }

        fun  update(
            row: Int,
            col: Int,
            applicationContext: Context,
            button: Button,
            status: TextView
        )
        {  var turn= play(row,col)


            var play=turn
            if(play == 1)
                this.buttons[row][col].text = "O"
            else if (play==2)
                this.buttons[row][col].text = "X"
            if(isGameOver())
            {
                status.setBackgroundColor(Color.RED)
                enableButtons(false)
                status.setText(result().toString())
                showNewGameDialog(applicationContext)
            }





        }

        fun showNewGameDialog(applicationContext:Context) {
            val alert = AlertDialog.Builder(applicationContext)
            alert.setTitle("This is fun")
            alert.setMessage("Play again?")
            val playAgain = PlayDialog()
            alert.setPositiveButton("YES", playAgain)
            alert.setNegativeButton("NO", playAgain)
            alert.show()
        }
        private fun resetButtons() {
            for (row in 0 until TicTacToe.SIDE) {
                for (col in 0 until TicTacToe.SIDE) {
                    buttons[row][col].setText("")

                }
            }
        }


        const val SIDE = 3
        var arr = Array(SIDE) { IntArray(SIDE) }
        init {
            game = Array(SIDE) { IntArray(SIDE) }

        }

    }
    private class PlayDialog : DialogInterface.OnClickListener {
        override fun onClick(dialog: DialogInterface, id: Int) {
            lateinit var activity: MainActivity
            if (id == -1) /* YES button */ {
                resetGame()
                enableButtons(true)
                resetButtons()
                status.setBackgroundColor(Color.GREEN)
                status.setText(result())
            } else if (id == -2) // NO button
                activity.finish()
        }


    }



}