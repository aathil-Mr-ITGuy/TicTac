package com.aathil.tictoc

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




    }

    var count1 = 0
    var count2 = 0



    fun buClick(view: View){
       val selectedButton = view as Button
       // Log.d("Clicked Button", selectedButton.id.toString())
        var cellId : Int

        cellId = when(selectedButton.id){
            R.id.button1 -> 1
            R.id.button2 -> 2
            R.id.button3 -> 3
            R.id.button4 -> 4
            R.id.button5 -> 5
            R.id.button6 -> 6
            R.id.button7 -> 7
            R.id.button8 -> 8
            R.id.button9 -> 9
            else -> 0
        }

        playGame(selectedButton, cellId)

    }

    var playerId = 1
    var player1 = ArrayList<Int>()
    var player2 = ArrayList<Int>()

    @SuppressLint("ResourceAsColor")
    private fun playGame(selectedButton: Button, cellId:Int){
        when(playerId){
            1 -> {
                selectedButton.text = "X"
                selectedButton.setBackgroundColor(R.color.blue)
                player1.add(cellId)
                playerId = 2
                autoPlay()
            }
            2 -> {
                selectedButton.text = "O"
                selectedButton.setBackgroundColor(R.color.red)
                player2.add(cellId)
                playerId = 1
            }

            //disable click again

        }
        selectedButton.isEnabled = false
        checkWinner()


    }

    private fun checkWinner(){
        var winner = -1
        //row 1
        if(player1.contains(1) && player1.contains(2) && player1.contains(3)){
            winner = 1
            //count1 += 1
            //Log.d("Score", count1.toString())


        }
        else if(player2.contains(1) && player2.contains(2) && player2.contains(3)){
            winner = 2
           // count2 += 1

        }

        //row 2
        else if(player1.contains(4) && player1.contains(5) && player1.contains(6)){
            winner = 1
           // count1 += 1

        }
        else if(player2.contains(4) && player2.contains(5) && player2.contains(6)){
            winner = 2
           // count2 += 1

        }

        //row3
        else if(player1.contains(7) && player1.contains(8) && player1.contains(9)){
            winner = 1
            //count1 += 1

        }
        else if(player2.contains(7) && player2.contains(8) && player2.contains(9)){
            winner = 2
            //count2 += 1

        }

        //column 1
        else if(player1.contains(1) && player1.contains(4) && player1.contains(7)){
            winner = 1
           // count1 += 1


        }
       else if(player2.contains(1) && player2.contains(4) && player2.contains(7)){
            winner = 2
            //count2 += 1

        }

        //column 2
        else if(player1.contains(2) && player1.contains(5) && player1.contains(8)){
            winner = 1
           // count1 += 1

        }
        else if(player2.contains(2) && player2.contains(5) && player2.contains(8)){
            winner = 2
            //count2 += 1

        }

        //column 3
        else if(player1.contains(3) && player1.contains(6) && player1.contains(9)){
            winner = 1
            //count1 += 1

        }
        else if(player2.contains(3) && player2.contains(6) && player2.contains(9)){
            winner = 2
           // count2 += 1

        }


        announceWinner(winner)
    }

    fun announceWinner(winner: Int) {


        when(winner){
            1 -> {
                Toast.makeText(this, "Player 1 Won the Game", Toast.LENGTH_LONG).show()
                count1 += 1
                score.text = "You Have win: $count1"
                Log.d("Player1 Score", count1.toString())
                restart()
            }
            2 -> {
                Toast.makeText(this, "Computer Won the Game", Toast.LENGTH_LONG).show()
                count2+= 1
                compScore.text = "Computer has won: $count2"
                Log.d("Player 2 Score", count2.toString())
                restart()

            }

        }


    }





    private fun autoPlay(){
        var emptyList = ArrayList<Int>()
        for(cellId in 1..9){
            if(!(player1.contains(cellId) || player2.contains(cellId))){
                emptyList.add(cellId)
            }
        }
        val r = Random
        var randomIndex = r.nextInt(emptyList.size)
        val cellId = emptyList[randomIndex]

        var buttonSelected: Button?

        buttonSelected = when(cellId){
            1 -> button1
            2 -> button2
            3 -> button3
            4 -> button4
            5 -> button5
            6 -> button6
            7 -> button7
            8 -> button8
            9 -> button9
            else -> button1

        }
        playGame(buttonSelected, cellId)


    }



    private fun restart() {
        //var button = v as Button
        player1.clear()
        player2.clear()

       // emptyList.clear()
        playerId = 1
        for(cellId in 1..9){
            var selectedButton: Button? = when(cellId){
                1 -> button1
                2 -> button2
                3 -> button3
                4 -> button4
                5 -> button5
                6 -> button6
                7 -> button7
                8 -> button8
                9 -> button9
                else -> button1
            }
            selectedButton!!.text =""
            selectedButton!!.setBackgroundResource(R.color.blue);
            selectedButton!!.isEnabled = true



        }

    }

    fun restartButton(view: View){

        restart()

    }


}