package calculator
import java.math.BigInteger
import java.util.ArrayDeque

fun main() {
    var isExit = false
    var sum = 0
    var variables = mutableMapOf<String, String>()
    var input:String? = null

    input = readLine()!!
    var operation = checkOperation(input)

    while (operation != "exit"){
        when (operation) {
            "varAs" -> checkVarAss(input!!, variables)
            "count" -> if(chceckInputData(input!!)) println (count (input!!, variables))
            "help" -> println("The program calculates the sum of numbers")
            "readVar" -> println (readVar (input!!, variables))
            "wrong" -> chceckInputData(input!!)

        }

        input = readLine()!!
        operation = checkOperation(input)
        /*sum = 0
        if (input == "/help"){
            println("The program calculates the sum of numbers")
        } else if (! input.isNullOrEmpty() ) {

            val input2 = input.split(" ")

            if (input2.size > 1){
                sum = operation(input2)

                println(sum)
            } else {
                sum = input.toInt()
                println(sum)
            }
        }

        check = readLine()!!
        isOk = chceckInputData(check)
        if (isOk){
            input = check
        } else {
            input = null
        }*/
    }
    println("Bye!")
}
fun count(input: String, variables: MutableMap<String, String> ): BigInteger {
    val expression = changeToPostfix(input)
    val exprs = Array<String>(expression.size){""}
    var stack = ArrayDeque<String>()
    var result: BigInteger
    var isBigInt = false

    for (i in 0 until expression.size) {
        val temp = expression.pop()
        exprs[i] = temp
    }
    exprs.reverse()

    for (i in exprs.indices) {
        if (exprs[i].length >= 9) {
            isBigInt = true
        }
    }

    if (isBigInt){
        result = countBigInt(exprs, variables)
    } else {

        for (i in exprs.indices) {
            val temp = exprs[i]
            if (temp[0].isLetterOrDigit()) {
                if (temp[0].isLetter()) {
                    stack.push(variables[temp].toString())
                } else {
                    stack.push(temp)
                }
            } else if (stack.size > 1) {
                val operator = temp
                val b = stack.pop().toInt()
                val a = stack.pop().toInt()
                var res = 0

                when (operator) {
                    "+" -> res = a + b
                    "-" -> res = a - b
                    "*" -> res = a * b
                    "/" -> res = a / b
                }
                stack.push(res.toString())
            }
        }
        result = BigInteger(stack.pop())
    }

    return result
}
fun countBigInt (expression: Array<String>, variables: MutableMap<String, String>) :BigInteger {
    val exprs = expression
    val stack = ArrayDeque<String>()
    var result: BigInteger

        for (i in exprs.indices) {
            val temp = exprs[i]
            if (temp[0].isLetterOrDigit()) {
                if (temp[0].isLetter()) {
                    stack.push(variables[temp].toString())
                } else {
                    stack.push(temp)
                }
            } else if (stack.size > 1) {
                val operator = temp
                val b = BigInteger(stack.pop())
                val a = BigInteger(stack.pop())
                var res = BigInteger("0")

                when (operator) {
                    "+" -> res = a + b
                    "-" -> res = a - b
                    "*" -> res = a * b
                    "/" -> res = a / b
                }
                stack.push(res.toString())
            }
        }
    result = BigInteger(stack.pop())
    return result
}
fun changeToPostfix (input: String) : ArrayDeque<String> {

    val isOk = chceckInputData(input)
    var stack = ArrayDeque<String>()
    var result = ArrayDeque<String>()

    if (isOk) {

        val inputSplit = input.trim().split(" ")

        for (i in inputSplit.indices) {
            if (inputSplit[i][0].isLetterOrDigit() && inputSplit[i][inputSplit[i].length - 1] != ')') {
                result.push(inputSplit[i])
            }
            for (ch in inputSplit[i]) {
                if (!ch.isLetterOrDigit() && ch != ' ') {
                    if (ch == '(') {
                        stack.push(ch.toString())

                        for (j in 1 until inputSplit[i].length) {
                            if (inputSplit[i][j] == '(') {
                                stack.push(inputSplit[i][j].toString())
                            } else {
                                var next = inputSplit[i].substring(j)
                                result.push(next.toString())
                                break
                            }

                        }
                        break


                    } else if (ch == ')') {

                        var next = inputSplit[i].substring(0, inputSplit[i].length - 1)
                        result.push(next.toString())
                        /*for (j in inputSplit[i].length - 2 downTo 0) {
                            var next = inputSplit[i][j]
                            result.push(next.toString())
                        }*/
                        if (stack.size > 0 ) {
                            var temp = stack.pop()
                            while (temp != "(" && stack.isNotEmpty()) {
                                result.push(temp)
                                temp = stack.pop()
                            }
                            if (temp != "(") result.push(temp)
                        }

                    } else if (ch == '*' && ch + 1 != '*'
                            || ch == '/' && ch + 1 != '/') {
                        if (stack.isEmpty()) {
                            stack.push(ch.toString())
                        } else {
                            val temp = stack.pop()

                            if (temp == "(") {
                                stack.push(temp)
                                stack.push(ch.toString())
                            } else if (temp == "+" || temp == "-") {
                                stack.push(temp)
                                stack.push(ch.toString())
                            } else if (temp == "*" || temp == "/") {
                                result.push(temp)
                                if (stack.isNotEmpty()) {
                                    while (stack.isNotEmpty()) {
                                        val temp = stack.pop()
                                        if (temp == "*" || temp == "/") {
                                            result.push(temp)
                                        } else if (temp == "+" || temp == "-"
                                                || temp == "(") {
                                            stack.push(temp)
                                            stack.push(ch.toString())
                                            break
                                        }
                                    }
                                }
                                stack.push(ch.toString())
                            }
                        }

                    } else if (inputSplit[i].length > 1 && ch == '-') {
                        var counter = 0
                        var operator = '-'
                        for(c in inputSplit[i].indices) {
                            if (inputSplit[i][c] == '-') {
                                counter ++
                            }
                        }
                        if (counter % 2 == 0) {
                            operator = '+'
                        }

                        if (stack.isEmpty()) {
                            stack.push(operator.toString())
                        } else {
                            val temp = stack.pop()

                            if (temp == "(") {
                                stack.push(temp)
                                stack.push(operator.toString())
                            } else if (temp == "*" || temp == "/" || temp == "+"
                                    || temp == "-") {
                                result.push(temp)
                                if(stack.size > 0) {
                                    for (i in 0..stack.size) {
                                        val temp = stack.pop()
                                        if (temp == "*" || temp == "/" || temp == "+"
                                                || temp == "-") {
                                            result.push(temp)
                                        } else if (temp == "(") {
                                            stack.push(temp)
                                            stack.push(operator.toString())
                                            break
                                        }
                                    }
                                } else {
                                    stack.push(operator.toString())
                                }

                            }
                        }
                        break
                    }  else if (inputSplit[i].length > 1 && ch == '+') {
                        val operator = '+'

                        if (stack.isEmpty()) {
                            stack.push(operator.toString())
                        } else {
                            val temp = stack.pop()

                            if (temp == "(") {
                                stack.push(temp)
                                stack.push(operator.toString())
                            } else if (temp == "*" || temp == "/" || temp == "+"
                                    || temp == "-") {
                                result.push(temp)
                                if(stack.size > 0) {
                                    for (i in 0..stack.size) {
                                        val temp = stack.pop()
                                        if (temp == "*" || temp == "/" || temp == "+"
                                                || temp == "-") {
                                            result.push(temp)
                                        } else if (temp == "(") {
                                            stack.push(temp)
                                            stack.push(operator.toString())
                                            break
                                        }
                                    }
                                } else {
                                    stack.push(operator.toString())
                                }

                            }
                        }
                        break
                    }
                    else {

                        if (stack.isEmpty()) {
                            stack.push(ch.toString())
                        } else {
                            var temp = stack.pop()

                            if (temp == "(") {
                                stack.push(temp)
                                stack.push(ch.toString())
                            } else if (temp == "*" || temp == "/" || temp == "+"
                                    || temp == "-") {
                                result.push(temp)
                                if (stack.isNotEmpty()) {
                                    temp = stack.pop()
                                }

                                while ((temp == "*" || temp == "/" || temp == "+"
                                        || temp == "-" ) && stack.isNotEmpty()) {
                                    result.push(temp)
                                    if (stack.isNotEmpty()) temp = stack.pop()
                                }
                                stack.push(ch.toString())

                                /*if(stack.size > 0) {
                                    for (i in 0..stack.size) {
                                        if(stack.size > 0) {
                                            temp = stack.pop()
                                        } else {
                                            if (temp == "*" || temp == "/" || temp == "+"
                                                    || temp == "-") {
                                                result.push(temp)
                                                temp = ""
                                            } else if (temp == "(") {
                                                stack.push(temp)
                                                stack.push(ch.toString())
                                                break
                                            }
                                        }
                                    }

                                } else {
                                    stack.push(ch.toString())
                                }*/

                            }
                        }
                    }
                }
            }
        }
        while (!stack.isEmpty()) {
            val temp = stack.pop()
            result.push(temp)
        }
    }
   return result
}
fun readVar (input: String, variables: MutableMap<String, String> ) : String {
    var value: String
    val input = input.trim()
    if (input[0].isDigit()){
        value = input
    } else if (variables.containsKey(input)){
        value = variables[input].toString()
    } else {
        value = "Unknown variable"
    }

    return value
}
fun checkOperation(input: String): String? {
    var operation: String? = null
    val input = input.trim()
    if (input.contains('=')) {
        operation = "varAs"
    } else if (input == "") {
        operation = "NoOper"
    } else if (input == "/help") {
        operation = "help"
    } else if (input == "/exit") {
        operation = "exit"
    } else if (input.split(" ").size == 1 && input[0] != '/'){
        operation = "readVar"
    }  else if (input.contains('+') || input.contains('-')
            || input.contains('*') || input.contains('/')) {
        operation = "count"
    } else {
        operation = "wrong"
    }

    return operation
}

fun checkVarAss (input: String, variables: MutableMap<String, String>) {
    var isOk = false
    var numVar: Boolean
    var varVar = true

    val input2 = input.split(" ")
    var input3:String = ""
    for(i in input2.indices){
        input3 += input2[i]
    }

    val pattern1 = Regex("([a-z]+)=(-?[0-9]+)")
    val pattern2 = Regex("([a-z]+)=([a-z]+)")
    /*val pattern3 = Regex("([a-z]+) =([0-9]+)")
    val pattern4 = Regex("([a-z]+) = ([0-9]+)")

    val pattern5 = Regex("([a-z]+)=([a-z]+)")
    val pattern6 = Regex("([a-z]+)= ([a-z]+)")
    val pattern7 = Regex("([a-z]+) =([a-z]+)")
    val pattern8 = Regex("([a-z]+) = ([a-z]+)")
    val pattern9 = Regex("([a-z]+)  = ([0-9]+)")*/

    numVar = pattern1.matches(input3)

    if (numVar) {
        val inputSplit = input.split("=")
        val key = inputSplit[0].trim()
        var value:String = inputSplit[1].trim()

        isOk = true

        if (variables.containsKey(key)) {
            variables [key] = value
        } else {
            variables.put(key, value)
        }
    }

    varVar = pattern2.matches(input3)

    if (varVar) {
        val inputSplit = input.split("=")
        val key = inputSplit[0].trim()
        val value = inputSplit[1].trim()

        isOk = true

        if (variables.containsKey(key) && variables.containsKey(value)) {
            variables[key] = variables[value]!!
        } else if (variables.containsKey(value)){
            variables.put(key, variables[value]!!)
        } else if (!variables.containsKey(value)) {
            isOk = false
            println("Unknown variable")
        }
    }

    if (!varVar && !numVar) {
        isOk = false
        println("Invalid assigment")
    }


}
fun add(input: String, variables: MutableMap<String, String>): Int {
    var isOk = chceckInputData(input)

    var input = input.split(" ")
    var operator: String? = null
    var a: String? = null
    var b: String? = null
    var a1 = 0
    var b1 = 0
    var suma = 0

    if (isOk) {
        for (i in input.indices){
            val current = input[i]

            if (current[0] == '+' || current[0] == '-') {
                if (current.length > 1) {
                    if (!current[1].isLetterOrDigit()) {
                        if (current[0] == '+') {
                            operator = "+"
                        } else if (current[0] == '-') {
                            if (current.length % 2 != 0) {
                                operator = "-"
                            } else {
                                operator = "+"
                            }
                        }
                    } else {
                        if (a == null) {
                            a = if(current[0].isLetter()){
                                variables[current].toString()
                            } else current

                        } else {
                            b = if(current[0].isLetter()){
                                variables[current].toString()
                            } else current
                        }
                    }

                } else if (current.length == 1) {
                    if (current[0] == '+') {
                        operator = "+"
                    } else if (current[0] == '-') {
                        if (current.length % 2 != 0) {
                            operator = "-"
                        } else {
                            operator = "+"
                        }
                    }
                }
            } else {
                if (a == null) {
                    a = if(current[0].isLetter()){
                        variables[current].toString()
                    } else current
                } else {
                    b = if(current[0].isLetter()){
                        variables[current].toString()
                    } else current
                }
            }

            if (a != null && b != null && operator != null) {
                if (a.length == 2 && !a[0].isDigit()) {
                    a1 = a[1].toString().toInt()
                    a1 *= (-1)
                } else {
                    a1 = a.toInt()
                }
                if (b.length == 2 && !b[0].isDigit()) {
                    b1 = b[1].toString().toInt()
                    b1 *= (-1)
                } else {
                    b1 = b.toInt()
                }
                if (operator == "+") {

                    val sum = a1 + b1
                    suma = sum

                    a = suma.toString()
                    b = null
                    operator = null
                } else if (operator == "-") {
                    val sum = a1 - b1
                    suma = sum

                    a = suma.toString()
                    b = null
                    operator = null
                }

            }
        }
    }



    return suma
}
fun chceckInputData(input: String): Boolean{
    var ok = true
    var isNotDigit = false

    val input = input.trim()

 
    if (input[0] == '/'){
        if (input != "/exit" && input != "/help"){
            ok = false
            println("Unknown command")
            return ok
        } else if (input == "/exit" || input == "/help") {
            ok = true
            return ok
        }
    }

        val inputSplit = input.split(" ")

        for (i in inputSplit.indices) {

            if (i % 2 != 0) {
                if (inputSplit[i][0] != '+' && inputSplit[i][0] != '-' && inputSplit[i][0] != '*'
                        && inputSplit[i][0] != '/') {
                    ok = false
                    println("Invalid expression")
                    return ok
                }
                if (inputSplit[i].length > 1 && inputSplit[i][0] == '*' && inputSplit[i][1] == '*') {
                    ok = false
                    println("Invalid expression")
                    return ok

                }
                if (inputSplit[i].length > 1 && inputSplit[i][0] == '/' && inputSplit[i][1] == '/') {
                    ok = false
                    println("Invalid expression")
                    return ok

                }
            } else if (inputSplit[i].length >= 2) {
                if (inputSplit[i][0] == '+' || inputSplit[i][0] == '-' || inputSplit[i][0].isDigit()
                        && inputSplit[i][1].isLetterOrDigit()) {
                    if (inputSplit[i].last() == '+' || inputSplit[i].last() == '-') {
                        ok = false
                        println("Invalid expression")
                        return ok
                    } else {
                        ok = true
                    }
                    /*else if (i % 2  == 0){
            if (inputSplit[i].length == 1){
                ok = inputSplit[i][0].isLetterOrDigit()

            } else if (inputSplit[i].length >= 2){
                if(inputSplit[i][0] == '+' || inputSplit[i][0] == '-' || inputSplit[i][0].isDigit()
                        && inputSplit[i][1].isLetterOrDigit()) {
                    if (inputSplit[i].last() == '+' || inputSplit[i].last() == '-') {
                        ok = false
                        println("Invalid expression")
                        return ok
                    } else {
                        ok = true
                    }

                } else {
                    ok = false
                    println("Invalid expression")
                    return ok
                }
            }

        }*/
                }
            }

        }

    if (checkParenthes(input) && ok) {
        ok = true
    } else {
        println("Invalid expression")
        ok = false
        return ok
    }

    return ok
}
fun checkParenthes(input: String): Boolean {

    var left = 0
    var right = 0
    var isOk = false
    for (ch in input.indices) {
        if (input[ch] == '('){
            left++
        }
        if (input[ch] == ')'){
            right++
        }
    }

    if (left == right) {
        isOk = true
    }

    return isOk
}