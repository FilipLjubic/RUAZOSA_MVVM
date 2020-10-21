package com.fljubic.ruazosa_mvvm_dz

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_calculator.*





class CalculatorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

        var inOperatorMode = false
        var inResultMode = false



        val numericButtonClicked = {view: View ->
            val buttonValue = (view as Button).text.toString()
            if (result_view.text.toString() != "0" && !inOperatorMode && !inResultMode) {
                    result_view?.text = result_view?.text.toString() + buttonValue

            }
            else {
                inOperatorMode = false
                inResultMode = false
                // reset() i dodati u expression buttonValue
                result_view?.text = buttonValue
            }
        }

        val operatorButtonClicked = {view: View ->
            val buttonValue = (view as Button).text.toString()
            Calculator.addNumber(result_view?.text.toString())
            Calculator.addOperator(buttonValue)
            inOperatorMode = true
        }


        button_reset?.setOnClickListener {
            result_view?.text = "0"
        }
        button_comma?.setOnClickListener {
            if (!result_view?.text.toString().contains(char = '.')) {
                result_view?.text = result_view?.text.toString() + "."
            }
        }

        button_zero.setOnClickListener(numericButtonClicked)
        button_one.setOnClickListener(numericButtonClicked)
        button_two.setOnClickListener(numericButtonClicked)
        button_three.setOnClickListener(numericButtonClicked)
        button_four.setOnClickListener(numericButtonClicked)
        button_five.setOnClickListener(numericButtonClicked)
        button_six.setOnClickListener(numericButtonClicked)
        button_seven.setOnClickListener(numericButtonClicked)
        button_eight.setOnClickListener(numericButtonClicked)
        button_nine.setOnClickListener(numericButtonClicked)

        button_multiply.setOnClickListener(operatorButtonClicked)
        button_divide.setOnClickListener(operatorButtonClicked)
        button_plus.setOnClickListener(operatorButtonClicked)
        button_minus.setOnClickListener(operatorButtonClicked)

        button_evaluate.setOnClickListener {
            if (inOperatorMode) {
                Calculator.addNumber("0")
                inOperatorMode = false
            }
            else {
                Calculator.addNumber(result_view?.text.toString())
            }
            Calculator.evaluate()
            result_view?.text = Calculator.result.toString()
            inResultMode = true
            Calculator.reset()
        }


    }
}
