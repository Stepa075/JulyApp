package com.stepa075.julyapp

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.stepa075.julyapp.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var bindingClass : ActivityMainBinding
    private var composePrimer: String = ""
    private var composePrimer2: String = ""
    private var tvTrueAnswer = 0
    private var tvFalseAnswer = 0
    private var tvTrueAnswer2 = 0
    private var tvFalseAnswer2 = 0
 

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindingClass = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)


        bindingClass.etOtvet.setOnEditorActionListener { v, actionId, event ->
            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_SEND -> {
                    Log.d("Mylog", "answer: reterned!")
                    trueOrFalse()
                    true
                }
                else -> false
            }
        }

        bindingClass.cv2etOtvet.setOnEditorActionListener { v, actionId, event ->
            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_GO -> {
                    Log.d("Mylog", "answer: DONEE!")
                    trueOrFalse2()
                    true
                }
                else -> false
            }
        }

        bindingClass.b1.setOnClickListener {
            trueOrFalse()
        }
        bindingClass.cv2b1.setOnClickListener {
            trueOrFalse2()
        }
        change_primer_plus_minus()
        change_primer_multiply_divide()

    }





    @SuppressLint("SetTextI18n")
    private fun trueOrFalse(){
        if (bindingClass.etOtvet.text?.toString()?.trim()?.equals("")!!) {
            Toast.makeText(this, "Введите число", Toast.LENGTH_LONG).show()
        }
        else {
            val answer: String = bindingClass.etOtvet.text.toString()
            var trueAnswer = composePrimer.substringAfter("=", "0")
            if(answer == trueAnswer) {
                blinkgGreen()
                tvTrueAnswer +=1
                val tr =tvTrueAnswer
                bindingClass.tvTrue.text = "Правильно: $tr"

            } else {blinkgRed()
                tvFalseAnswer +=1
                val fa = tvFalseAnswer
                bindingClass.tvFalse.text = "Неправильно: $fa"
            }
            Log.d("Mylog", "answer: $answer")
            Log.d("Mylog", "trueAnswer: $trueAnswer")
            change_primer_plus_minus()
        }
    }


    @SuppressLint("SetTextI18n")
    private fun change_primer_plus_minus() {

        while(true){

            val random = Random()
            val chislo1: Int = (1..100).random()//random.nextInt(100)
            val chislo2: Int = (1..100).random()
            val chislo3: Int = (1..100).random()
            var znak1 = ""
            var znak2 = ""
            val variant: Int = random.nextInt(4)
            var otvet: Int = 0
            if(variant ==0){otvet = chislo1+chislo2+chislo3; znak1 = "+"; znak2 = "+"}
            if(variant ==1){otvet = chislo1+chislo2-chislo3; znak1 = "+"; znak2 = "-"}
            if(variant ==2){otvet = chislo1-chislo2+chislo3; znak1 = "-"; znak2 = "+"}
            if(variant ==3){otvet = chislo1-chislo2-chislo3; znak1 = "-"; znak2 = "-"}
            if(otvet >0){

                composePrimer = "$chislo1$znak1$chislo2$znak2$chislo3=$otvet"
                bindingClass.tvPrimer.text = "$chislo1$znak1$chislo2$znak2$chislo3"
                bindingClass.etOtvet.text.clear()
                break

            }

        }

    }

    @SuppressLint("SetTextI18n")
    private fun trueOrFalse2() {
        if (bindingClass.cv2etOtvet.text?.toString()?.trim()?.equals("")!!) {
            Toast.makeText(this, "Введите число", Toast.LENGTH_LONG).show()
        }
        else {
            val answer: String = bindingClass.cv2etOtvet.text.toString()
            var trueAnswer = composePrimer2.substringAfter("=", "0")
            if(answer == trueAnswer) {
                blinkgGreen()
                tvTrueAnswer2 += 1
                val tr1 = tvTrueAnswer2
                bindingClass.cv2tvTrue.text = "Правильно: $tr1"

            } else {blinkgRed()
                tvFalseAnswer2 += 1
                val fa1 = tvFalseAnswer2
                bindingClass.cv2tvFalse.text = "Неправильно: $fa1"
                Log.d("Mylog", "countAnswer: $fa1")
            }
            Log.d("Mylog", "answer: $answer")
            Log.d("Mylog", "trueAnswer: $trueAnswer")

            change_primer_multiply_divide()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun change_primer_multiply_divide() {
        while(true){

            val random = Random()
            val chislo1: Int = (1..20).random()//random.nextInt(100)
            val chislo2: Int = (1..9).random()
           // val chislo3: Int = (1..100).random()
            var znak1 = ""
            //var znak2 = ""
            val variant: Int = random.nextInt(2)
            var otvet: Int = 0
            if(variant ==0){otvet = chislo1*chislo2; znak1 = "*"}
            if(variant ==1){otvet = chislo1/chislo2; znak1 = ":"}
            //if(variant ==2){otvet = chislo1-chislo2+chislo3; znak1 = "-"; znak2 = "+"}
            //if(variant ==3){otvet = chislo1-chislo2-chislo3; znak1 = "-"; znak2 = "-"}
            if(otvet >0 && variant ==0) {
                composePrimer2 = "$chislo1$znak1$chislo2=$otvet"
                bindingClass.cv2tvPrimer.text = "$chislo1$znak1$chislo2"
                bindingClass.cv2etOtvet.text.clear()
                break
            }
            if(otvet >0 && chislo1 % chislo2 == 0){
                    composePrimer2 = "$chislo1$znak1$chislo2=$otvet"
                    bindingClass.cv2tvPrimer.text = "$chislo1$znak1$chislo2"
                    bindingClass.cv2etOtvet.text.clear()
                break

            }

        }
    }

    fun blinkgRed() {
        Thread {
            bindingClass.cLayout.setBackgroundColor(Color.RED)
            Thread.sleep(100)
            bindingClass.cLayout.setBackgroundColor(Color.GRAY)
            Thread.sleep(100)
            bindingClass.cLayout.setBackgroundColor(Color.RED)
            Thread.sleep(100)
            bindingClass.cLayout.setBackgroundColor(Color.GRAY)
            Thread.sleep(100)
            bindingClass.cLayout.setBackgroundColor(Color.RED)
            Thread.sleep(100)
            bindingClass.cLayout.setBackgroundColor(Color.GRAY)

        }.start()
    }

    fun blinkgGreen() {
        Thread {
            bindingClass.cLayout.setBackgroundColor(Color.GREEN)
            Thread.sleep(100)
            bindingClass.cLayout.setBackgroundColor(Color.GRAY)
            Thread.sleep(100)
            bindingClass.cLayout.setBackgroundColor(Color.GREEN)
            Thread.sleep(100)
            bindingClass.cLayout.setBackgroundColor(Color.GRAY)
            Thread.sleep(100)
            bindingClass.cLayout.setBackgroundColor(Color.GREEN)
            Thread.sleep(100)
            bindingClass.cLayout.setBackgroundColor(Color.GRAY)

        }.start()
    }
}