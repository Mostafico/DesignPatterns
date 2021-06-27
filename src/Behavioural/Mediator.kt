package Behavioural

fun main() {

    val mediator = ConcreteMediator()

    val jack = Friend1(mediator, "Jack")
    val john = Friend2(mediator, "John")
    val boss = Boss(mediator, "Boss")

    mediator.setFriend1(jack)
    mediator.setFriend2(john)
    mediator.setBoss(boss)

    jack.send("Hello My Friend !!")
    john.send("Hi how do you do")
    boss.send("Guys I can see your msgs!")
}

interface Mediator {
    fun send(friend: Friend, msg: String)
}

class ConcreteMediator() : Mediator {
    private lateinit var friend1 : Friend1
    private lateinit var friend2 : Friend2
    private lateinit var boss: Boss

    fun setFriend1(friend1: Friend1){
        this.friend1 = friend1
    }

    fun setFriend2(friend2: Friend2){
        this.friend2 = friend2
    }

    fun setBoss(boss: Boss){
        this.boss = boss
    }

    override fun send(friend: Friend, msg: String) {
        when (friend) {
            friend1 -> {
                friend2.notify(msg)
                boss.notify(friend1.getName() + " Sends Message to " + friend2.getName())
            }
            friend2 -> {
                friend1.notify(msg)
                boss.notify(friend2.getName() + " Sends Message to " + friend1.getName())
            }
            else -> {
                friend1.notify(msg)
                friend2.notify(msg)
            }
        }
    }

}

abstract class Friend(
    private val mediator: Mediator,
    private val name: String
) {
}

class Friend1(private val mediator: Mediator, private val name: String) : Friend(mediator, name) {
    fun send(msg: String) {
        mediator.send(this, msg)
    }

    fun notify(msg: String) {
        println("$name gets message: $msg")
    }

    fun getName(): String {
        return this.name
    }
}

class Friend2(private val mediator: Mediator, private val name: String) : Friend(mediator, name) {
    fun send(msg: String) {
        mediator.send(this, msg)
    }

    fun notify(msg: String) {
        println("$name gets message: $msg")
    }

    fun getName(): String {
        return this.name
    }
}

class Boss(private val mediator: Mediator, private val name: String) : Friend(mediator, name) {
    fun send(msg: String) {
        mediator.send(this, msg)
    }

    fun notify(msg: String) {
        println("$name sees message: $msg")
    }

    fun getName(): String {
        return this.name
    }
}