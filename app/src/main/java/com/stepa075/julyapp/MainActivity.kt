package com.stepa075.julyapp

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.stepa075.julyapp.databinding.ActivityMainBinding
import java.util.*
import kotlin.properties.Delegates.notNull

class MainActivity : AppCompatActivity() {
    private lateinit var bindingClass: ActivityMainBinding
    private var composePrimer: String = ""
    private var composePrimer2: String = ""
    private var composePrimer3: String = ""
    private var tvTrueAnswer by notNull<Int>()
    private var tvFalseAnswer by notNull<Int>()
    private var tvTrueAnswer2 by notNull<Int>()
    private var tvFalseAnswer2 by notNull<Int>()
    private var tvTrueAnswer3 by notNull<Int>()
    private var tvFalseAnswer3 by notNull<Int>()


    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)




        bindingClass = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)


        bindingClass.etOtvet.setOnEditorActionListener { v, actionId, event ->
            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_SEND -> {
                    trueOrFalse()
                    true
                }
                else -> false
            }
        }

        bindingClass.cv2etOtvet.setOnEditorActionListener { v, actionId, event ->
            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_GO -> {
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

        if (savedInstanceState == null) {
            tvTrueAnswer = 0
            tvTrueAnswer2 = 0
            tvTrueAnswer3 = 0
            tvFalseAnswer = 0
            tvFalseAnswer2 = 0
            tvFalseAnswer3 = 0
        } else {
            tvTrueAnswer = savedInstanceState.getInt(KEY_TRUE)
            tvTrueAnswer2 = savedInstanceState.getInt(KEY_TRUE2)
            tvTrueAnswer3 = savedInstanceState.getInt(KEY_TRUE3)
            tvFalseAnswer = savedInstanceState.getInt(KEY_FALSE)
            tvFalseAnswer2 = savedInstanceState.getInt(KEY_FALSE2)
            tvFalseAnswer3 = savedInstanceState.getInt(KEY_FALSE3)
        }
        renderState()
        setOnClicks()
        changePrimerPlusMinus()
        changePrimerMultiplyDivide()
        changePrimerMultiplyDividePlusMinus()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_TRUE, tvTrueAnswer)
        outState.putInt(KEY_TRUE2, tvTrueAnswer2)
        outState.putInt(KEY_TRUE3, tvTrueAnswer3)
        outState.putInt(KEY_FALSE, tvFalseAnswer)
        outState.putInt(KEY_FALSE2, tvFalseAnswer2)
        outState.putInt(KEY_FALSE3, tvFalseAnswer3)
    }

    @SuppressLint("SetTextI18n")
    private fun renderState() = with(bindingClass) {
        val tr = tvTrueAnswer.toString()
        bindingClass.tvTrue.text = "Правильно: $tr"
        val fa = tvFalseAnswer
        bindingClass.tvFalse.text = "Неправильно: $fa"
        val tr2 = tvTrueAnswer2
        bindingClass.cv2tvTrue.text = "Правильно: $tr2"
        val fa2 = tvFalseAnswer2
        bindingClass.cv2tvFalse.text = "Неправильно: $fa2"
        val tr3 = tvTrueAnswer3
        bindingClass.cv3tvTrue.text = "Правильно: $tr3"
        val fa3 = tvFalseAnswer3
        bindingClass.cv3tvFalse.text = "Неправильно: $fa3"

    }

    private fun setOnClicks() = with(bindingClass) {
        val listener = onClicks()
        b3Minus.setOnClickListener(listener)
        b3Ravno.setOnClickListener(listener)
        b3Plus.setOnClickListener(listener)

    }

    private fun onClicks(): View.OnClickListener {
        return View.OnClickListener {
            when (it.id) {
                R.id.b3_minus -> trueOrFalse3("<")
                R.id.b3_ravno -> trueOrFalse3("=")
                R.id.b3_plus -> trueOrFalse3(">")
            }
        }
    }


    @SuppressLint("SetTextI18n")
    private fun trueOrFalse() {
        if (bindingClass.etOtvet.text?.toString()?.trim()?.equals("")!!) {
            Toast.makeText(this, "Введите число", Toast.LENGTH_LONG).show()
        } else {
            val answer: String = bindingClass.etOtvet.text.toString()
            val trueAnswer = composePrimer.substringAfter("=", "0")
            if (answer == trueAnswer) {
                blinkgGreen()
                tvTrueAnswer += 1
                val tr = tvTrueAnswer
                bindingClass.tvTrue.text = "Правильно: $tr"

            } else {
                blinkgRed()
                tvFalseAnswer += 1
                val fa = tvFalseAnswer
                bindingClass.tvFalse.text = "Неправильно: $fa"
            }
            Log.d("Mylog", "answer: $answer")
            Log.d("Mylog", "trueAnswer: $trueAnswer")
            howManyErrors()
            changePrimerPlusMinus()
        }
    }


    @SuppressLint("SetTextI18n")
    private fun changePrimerPlusMinus() {

        while (true) {

            val random = Random()
            val chislo1: Int = (1..100).random()//random.nextInt(100)
            val chislo2: Int = (1..100).random()
            val chislo3: Int = (1..100).random()
            var znak1 = ""
            var znak2 = ""
            val variant: Int = random.nextInt(4)
            var otvet = 0
            if (variant == 0) {
                otvet = chislo1 + chislo2 + chislo3; znak1 = "+"; znak2 = "+"
            }
            if (variant == 1) {
                otvet = chislo1 + chislo2 - chislo3; znak1 = "+"; znak2 = "-"
            }
            if (variant == 2) {
                otvet = chislo1 - chislo2 + chislo3; znak1 = "-"; znak2 = "+"
            }
            if (variant == 3) {
                otvet = chislo1 - chislo2 - chislo3; znak1 = "-"; znak2 = "-"
            }
            if (otvet > 0) {

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
        } else {
            val answer: String = bindingClass.cv2etOtvet.text.toString()
            val trueAnswer = composePrimer2.substringAfter("=", "0")
            if (answer == trueAnswer) {
                blinkgGreen()
                tvTrueAnswer2 += 1
                val tr1 = tvTrueAnswer2
                bindingClass.cv2tvTrue.text = "Правильно: $tr1"

            } else {
                blinkgRed()
                tvFalseAnswer2 += 1
                val fa1 = tvFalseAnswer2
                bindingClass.cv2tvFalse.text = "Неправильно: $fa1"
                Log.d("Mylog", "countAnswer: $fa1")
            }
            Log.d("Mylog", "answer: $answer")
            Log.d("Mylog", "trueAnswer: $trueAnswer")
            howManyErrors()
            changePrimerMultiplyDivide()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun changePrimerMultiplyDivide() {
        while (true) {

            val random = Random()
            val chislo1: Int = (1..20).random()//random.nextInt(100)
            val chislo2: Int = (1..9).random()
            var znak1 = ""
            val variant: Int = random.nextInt(2)
            var otvet = 0

            if (variant == 0) {
                otvet = chislo1 * chislo2; znak1 = "*"
            }
            if (variant == 1) {
                otvet = chislo1 / chislo2; znak1 = ":"
            }
            if (otvet > 0 && variant == 0) {
                composePrimer2 = "$chislo1$znak1$chislo2=$otvet"
                bindingClass.cv2tvPrimer.text = "$chislo1$znak1$chislo2"
                bindingClass.cv2etOtvet.text.clear()
                break
            }
            if (otvet > 0 && chislo1 % chislo2 == 0) {
                composePrimer2 = "$chislo1$znak1$chislo2=$otvet"
                bindingClass.cv2tvPrimer.text = "$chislo1$znak1$chislo2"
                bindingClass.cv2etOtvet.text.clear()
                break

            }

        }
    }

    @SuppressLint("SetTextI18n")
    private fun trueOrFalse3(answer1: String) {

        val answer: String = answer1
        val realAnswer = composePrimer3

        if (answer == realAnswer) {

            blinkgGreen()
            tvTrueAnswer3 += 1
            val tr1 = tvTrueAnswer3
            bindingClass.cv3tvTrue.text = "Правильно: $tr1"

        } else {

            blinkgRed()
            tvFalseAnswer3 += 1
            val fa1 = tvFalseAnswer3
            bindingClass.cv3tvFalse.text = "Неправильно: $fa1"

        }
        howManyErrors()
        changePrimerMultiplyDividePlusMinus()

    }

    @SuppressLint("SetTextI18n")
    private fun changePrimerMultiplyDividePlusMinus() {
        while (true) {

            val random = Random()
            val chislo1: Int = (1..20).random()//random.nextInt(100)
            val chislo2: Int = (1..9).random()
            val chislo3: Int = (1..20).random()
            val chislo4: Int = (1..20).random()
            var znak1 = ""
            var znak2 = ""
            val variant: Int = random.nextInt(4)
            val variant2: Int = random.nextInt(4)
            var otvet = 0
            var otvet2 = 0
            if (variant == 0) {
                otvet = chislo1 * chislo2; znak1 = "*"
            }
            if (variant == 1) {
                otvet = chislo1 / chislo2; znak1 = ":"
            }
            if (variant == 2) {
                otvet = chislo1 - chislo2; znak1 = "-"
            }
            if (variant == 3) {
                otvet = chislo1 + chislo2; znak1 = "+"
            }
            if (variant2 == 0) {
                otvet2 = chislo3 * chislo4; znak2 = "*"
            }
            if (variant2 == 1) {
                otvet2 = chislo3 / chislo4; znak2 = ":"
            }
            if (variant2 == 2) {
                otvet2 = chislo3 - chislo4; znak2 = "-"
            }
            if (variant2 == 3) {
                otvet2 = chislo3 + chislo4; znak2 = "+"
            }
            if (otvet > 0 && otvet2 > 0) {                                   // Если результат примера больше нуля
                if((variant == 1 && chislo1 % chislo2 == 0) && variant2 != 1){ //Если первый вариант - деление, а второй - нет.
                    composePrimer3 = when {
                        otvet > otvet2 -> ">"
                        otvet < otvet2 -> "<"
                        else -> "="
                    }
                    bindingClass.cv3tvPrimer.text = "$chislo1$znak1$chislo2 ? $chislo3$znak2$chislo4"
                    break
                }
                if((variant != 1)&&(variant2 == 1 && chislo3 % chislo4 == 0)){ // Если первый не деление а второй вариант деление...
                    composePrimer3 = when {
                        otvet > otvet2 -> ">"
                        otvet < otvet2 -> "<"
                        else -> "="
                    }
                    bindingClass.cv3tvPrimer.text = "$chislo1$znak1$chislo2 ? $chislo3$znak2$chislo4"
                    break
                }
                if (( variant == 1 && chislo1 % chislo2 == 0) &&  //  ЕСли оба варианта деление
                    (variant2 == 1 && chislo3 % chislo4 == 0)
                ) {
                    composePrimer3 = when {
                        otvet > otvet2 -> ">"
                        otvet < otvet2 -> "<"
                        else -> "="
                    }
                    bindingClass.cv3tvPrimer.text = "$chislo1$znak1$chislo2 ? $chislo3$znak2$chislo4"
                    break
                }
                if(variant != 1 && variant2 != 1){
                    composePrimer3 = when {         // Все остальные варианты
                        otvet > otvet2 -> ">"
                        otvet < otvet2 -> "<"
                        else -> "="
                    }
                    bindingClass.cv3tvPrimer.text = "$chislo1$znak1$chislo2 ? $chislo3$znak2$chislo4"
                    break
                    }
                }





        }
    }

    @SuppressLint("ResourceAsColor")
    private fun howManyErrors() {
        val trueA = tvTrueAnswer + tvTrueAnswer2 + tvTrueAnswer3 + 1
        val falseA = tvFalseAnswer + tvFalseAnswer2 + tvFalseAnswer3 + 1
        if (trueA < 3 && falseA < 3) {
            bindingClass.tvBottom.text = "Пробуй и у тебя получится!!!"
        }
        if ((trueA) < (falseA)) {
            bindingClass.tvBottom.text = "Будь внимательнее!!!"

        }
        if ((trueA) < (((falseA) / 10) * 8)) {
            bindingClass.tvBottom.text = "Плохо, решай не спеша!!!"

        }
        if ((trueA) > ((falseA) * 2)) {
            bindingClass.tvBottom.text = "Отлично, так держать!!!"

        }
//
    }


    private fun blinkgRed() {

            bindingClass.cLayout.setBackgroundColor(Color.RED)

    }


    private fun blinkgGreen() {

            bindingClass.cLayout.setBackgroundColor(Color.GREEN)

    }

    companion object {
        @JvmStatic
        private val KEY_TRUE = "TRUE!"
        @JvmStatic
        private val KEY_TRUE2 = "TRUE2"
        @JvmStatic
        private val KEY_TRUE3 = "TRUE3"
        @JvmStatic
        private val KEY_FALSE = "FALSE"
        @JvmStatic
        private val KEY_FALSE2 = "FALSE2"
        @JvmStatic
        private val KEY_FALSE3 = "FALSE3"
    }
}