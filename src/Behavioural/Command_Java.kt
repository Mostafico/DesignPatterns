package Behavioural

fun main() {
    var task1 = Task(5,10)
    var task2 = Task(20,30)

    var thread1 = Thread(task1)
    thread1.start()

    var thread2 = Thread(task2)
    thread2.start()
}


class Task(val num1: Int, val num2: Int) : Runnable{
    override fun run() {
        println(num1 * num2)
    }
}