package Behavioural

import java.util.*
import java.util.Observer

fun main() {
    val messageStream = TwitterStream()

    var client1 =  Client("Bryam")
    var client2 = Client("Mark")

    messageStream.addObserver(client1)
    messageStream.addObserver(client2)

    messageStream.someoneTweeted()
}

class TwitterStream : Observable(){
    fun someoneTweeted(){
        setChanged()
        notifyObservers()
    }
}

class Client(private val name: String) : Observer{
    override fun update(p0: Observable?, p1: Any?) {
        println("Update $name stream, someone tweeted something")
    }
}