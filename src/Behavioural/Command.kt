package Behavioural
//
//fun main() {
//    var bedroomLight = Light()
//    var kitchenLight = Light()
//
//    var switch = Switch()
//
//    var toggleCommand = ToggleCommand(bedroomLight)
//    var allLightsOnCommand = AllLightsOnCommand(mutableListOf(bedroomLight, kitchenLight))
//
////    switch.storeAndExecute(toggleCommand)
//    switch.storeAndExecute(allLightsOnCommand)
//}

interface Command{
    fun execute()
}

class SendMoneyCommand(private val receiver: Receiver) : Command{
    override fun execute() {
        receiver.sendMoney(500)
    }
}

class SendMoneyToAllCommand(private val listOfReceivers: ArrayList<Receiver>) : Command{
    override fun execute() {
        listOfReceivers.forEach {
            receiver -> receiver.sendMoney(500)
        }
    }
}

//3am Ahmed
fun main() {
    val osama = Invoker()

    val amAly = Receiver(0)

    val sendMoneyCommand = SendMoneyCommand(amAly)
    osama.execute(sendMoneyCommand)

    println("Second Scenario --->")

    val amMohsen = Receiver(1)
    val amIbrahim = Receiver(2)

    val sendMoneyToAllCommand = SendMoneyToAllCommand(arrayListOf(amAly, amIbrahim, amMohsen))

    osama.execute(sendMoneyToAllCommand)
}

//osama
class Invoker{
    fun execute(command: Command){
        command.execute()
    }
}

// 3am Aly
class Receiver(private val id: Int){
    private var money = 0

    fun sendMoney(money: Int){
        this.money += money
        println("Receiver $id Total money = ${this.money}")
    }
}

class Switch{
    fun storeAndExecute(command: Command){
        command.execute()
    }
}

class OnCommand(private val light: Light) : Command{
    override fun execute() {
        light.on()
    }
}

class ToggleCommand(private val light: Light) : Command{
    override fun execute() {
        light.toggle()
    }
}

class AllLightsOnCommand(private val listOfLight: List<Light>) : Command{
    override fun execute() {
        listOfLight.forEach{ light ->
            if(!light.isOn())
                light.on()
        }
    }
}

class Light{
    private var isOn = false

    public fun isOn() : Boolean{
        return isOn
    }

    public fun toggle(){
        if(isOn){
            off()
            isOn = false
        }else{
            on()
            isOn = true
        }
    }

    fun on(){
        println("$this is on")
    }

    fun off(){
        println("$this is off")
    }

}