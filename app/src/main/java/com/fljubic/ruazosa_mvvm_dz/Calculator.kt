package com.fljubic.ruazosa_mvvm_dz

import kotlin.math.exp

/**
 * Created by dejannovak on 24/03/2018.
 */

//Proširiti kalkulator
// ■Napraviti unit testove nad operacijama

object Calculator {

    var result: Double = 0.0
    private set

    var expression: MutableList<String> = mutableListOf()
    private set

    var toSkip: Int = 0
    private set

    fun reset() {
        result = 0.0
        expression = mutableListOf()
    }

    fun addNumber(number: String) {
        try {
            val num = number.toDouble()
        } catch (e: NumberFormatException) {
            throw Exception("Not valid number")
        }

        if (expression.count()%2 == 0) {
            expression.add(number)

        }
        else {
            throw Exception("Not a valid order of numbers in expression")
        }
    }

    fun addOperator(operator: String) {
        if (expression.count()%2 != 1)  {
            throw Exception("Not a valid order of operator in expression")
        }
        when (operator) {
            "+" -> expression.add(operator)
            "-" -> expression.add(operator)
            "/" -> expression.add(operator)
            "*" -> expression.add(operator)
            else -> {
                throw Exception("Not a valid operator")
            }
        }
    }

    private fun calculateInbetweenResult(i: Int) : Double{
        var j = i + 2
        var inbetweenResult = 0.0

        // ako je `i` zadnji operator ili plus/minus, samo vratiti to
        if (j >= expression.count() - 1 || expression[j] == "+" || expression[j] == "-"){
            inbetweenResult = expression[j-1].toDouble()
            return inbetweenResult
        }

        // dok ne dodem do sljedeceg plusa/minusa ili kraja expressiona
        while (j < expression.count() - 1 && (expression[j] != "+" && expression[j] != "-")){
            if(inbetweenResult == 0.0){
                when(expression[j]){
                    "*" -> inbetweenResult = expression[j-1].toDouble() * expression[j+1].toDouble()
                    "/" -> inbetweenResult = expression[j-1].toDouble() / expression[j+1].toDouble()
                }
            } else{
                when(expression[j]){
                    "*" -> inbetweenResult = inbetweenResult * expression[j+1].toDouble()
                    "/" -> inbetweenResult = inbetweenResult / expression[j+1].toDouble()
                }
            }
            toSkip += 2

            j+= 2
        }
        // toSkip doda 2 i kada cemo sljedeci put naletiti na + ili kraj expressiona, pa ga treba smanjit za 2 da i+=2 dobro funkcionira
        toSkip -= 2
        return inbetweenResult

    }

    fun evaluate() {

        if (expression.count() % 2 == 0) {
            throw Exception("Not a valid expression")
        }

        // trenutno ne radi ako pocinje s mnozenjem/djeljenjem
        if (expression[1] == "*" || expression[1] == "/"){
            // -1 jer tamo pocinje od i + 2, tako da pocne od prvog operatora
            result = calculateInbetweenResult(-1)
        } else {
            result = expression[0].toDouble()
        }


        var i = 1 + toSkip
        while(i < expression.count() - 1){
             toSkip = 0

            when(expression[i]) {
                "+" -> {
                    result += calculateInbetweenResult(i)
                }
                "-" -> {
                    result -= calculateInbetweenResult(i)
                }
            }
            // trebam paziti da skipam sve indexe koje sam izmnozio/djelio
            i += toSkip
            i += 2
        }
    }
}