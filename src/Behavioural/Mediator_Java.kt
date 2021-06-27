package Behavioural

import java.util.*

fun main() {
    var mediator = MediatorEveryDayDemo(3)
}

class MediatorEveryDayDemo(private val seconds: Long){
    private var timer: Timer? = null

    init {
        timer = Timer()
        timer!!.schedule(RemindTask(), seconds * 1000)
        timer!!.schedule(RemindTaskWithoutBeeb(), seconds * 2000)
    }

    class RemindTask : TimerTask(){
        override fun run() {
            println("Time is up")
        }
    }

    class RemindTaskWithoutBeeb : TimerTask(){
        override fun run() {
            println("Time is up without beeb")
        }
    }

}