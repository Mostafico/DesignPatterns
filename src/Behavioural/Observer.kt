package Behavioural

//fun main() {
//    val subject = MessageStream()
//
//    val phoneClient = PhoneClient(subject)
//    val tabletClient = TabletClient(subject)
//
//    phoneClient.addMessage("Here is a new message!")
//    tabletClient.addMessage("Another new message!")
//}

fun main() {
    val mohsen = ElliFeha()

    val mostafa = Player(mohsen, "Mostafa")
    val mohamed = Player(mohsen, "Mohamed")
    val bodi = Player(mohsen, "Bodi")

    mostafa.edrab()

    Thread.sleep(2000)
    println("Two Seconds Passed")

    bodi.edrab()
}

abstract class Subject{
    private var observers = ArrayList<Observer>()

    abstract fun setState(state: String)
    abstract fun getState() : String

    fun attach(observer: Observer){
        observers.add(observer)
    }

    fun detach(observer: Observer){
        observers.remove(observer)
    }

    fun notifyObservers(){
        observers.forEach{observer ->
            observer.update()
        }
    }
}

abstract class Observer{
    lateinit var subject: Subject
    abstract fun update()
}


class ElliFeha : Subject(){
    private var state = "Silent"

    override fun setState(state: String) {
        this.state = state
        notifyObservers()
    }

    override fun getState(): String {
        return state
    }

}

class Player(subject: Subject,val name: String) : Observer(){
    init {
        this.subject = subject
        subject.attach(this)
    }

    fun edrab(){
        subject.setState("Ayyy!")
    }

    override fun update() {
        println("$name heard Mohsen's Ayyy!")
    }
}



class MessageStream : Subject(){
    private var messageHistory = ArrayList<String>()

    override fun setState(message: String) {
        messageHistory.add(message)
        notifyObservers()
    }

    override fun getState(): String {
        return messageHistory.last()
    }

}

class PhoneClient(subject: Subject) : Observer() {
    init {
        this.subject = subject
        subject.attach(this)
    }

    fun addMessage(message: String) {
        subject.setState("$message - sent from phone")
    }

    override fun update() {
        println("Phone Stream: " + subject.getState())
    }
}

class TabletClient(subject: Subject) : Observer() {
    init {
        this.subject = subject
        subject.attach(this)
    }

    fun addMessage(message: String) {
        subject.setState("$message - sent from tablet")
    }

    override fun update() {
        println("Tablet Stream: " + subject.getState())
    }

}

