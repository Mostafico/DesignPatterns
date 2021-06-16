package Behavioural

import java.util.*

//Zaky
fun main() {
    val jack = Interpreter().buildInterpreterTree()

    val context = "Tigers Bears"

    println(context + " is " + jack.interpret(context))
}

class Interpreter{
    companion object fun buildInterpreterTree(): Expression {
        val terminal1 = TerminalExpression("Lions")
        val terminal2 = TerminalExpression("Tigers")
        val terminal3 = TerminalExpression("Bears")

        //Tigers and Bears
        val alternation1 = AndExpression(terminal2, terminal3)

        //Lions or (Tigers and Bears)
        val alternation2 = OrExpression(terminal1, alternation1)

        //Bears and (Lions or (Tigers and Bears) )
        return AndExpression(terminal3, alternation2)
    }
}

interface Expression {
    fun interpret(context: String): Boolean
}

class TerminalExpression(private val data: String) : Expression {
    override fun interpret(context: String): Boolean {
        val st = StringTokenizer(context)
        while (st.hasMoreTokens()) {
            val test = st.nextToken()
            if (test.equals(data)) {
                return true
            }
        }
        return false
    }
}

class OrExpression(private val expr1: Expression, private val expr2: Expression) : Expression {
    override fun interpret(context: String): Boolean {
        return expr1.interpret(context) || expr2.interpret(context)
    }
}

class AndExpression(private val expr1: Expression, private val expr2: Expression) : Expression {
    override fun interpret(context: String): Boolean {
        return expr1.interpret(context) && expr2.interpret(context)
    }
}