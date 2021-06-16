package Behavioural

import java.util.regex.Pattern

fun main() {
    val input = "Lions, and tigers, and bears! Oh, my!"

    val p = Pattern.compile("(lion|cat|dog|wolf|bear|human|tiger|Lion)")
    val m = p.matcher(input)

    while (m.find()){
        println("Found a " + m.group())
    }
}