package Behavioural

fun main() {
    val names = HashSet<String>()

    names.add("Byram")
    names.add("Aaron")
    names.add("Jason")

    val namesItr = names.iterator()

    while (namesItr.hasNext()){
        val name = namesItr.next()
        println(name)
        namesItr.remove()
    }

    println(names.size)
}