package Behavioural

fun main() {
    val airBus1 = AirBus()
    val miniAirBus1 = MiniAirBus()
    val nozhaTowr = NozhaTower()

    //register to Nozha Airport
    println("Registering AirCrafts ....")
    airBus1.setAirPortTower(nozhaTowr)
    miniAirBus1.setAirPortTower(nozhaTowr)

    println("Airbus1 Requests landing ....")
    airBus1.requestLanding()
    println("MiniAirbus1 Requests landing ....")
    miniAirBus1.requestLanding()

    println("Airbus1 Landed Successfully ....")
    airBus1.notifySuccess()

    println("MiniAirbus1 Requests Landing Again ....")
    miniAirBus1.requestLanding()

    println("MiniAirbus1 Landed Successfully ....")
    miniAirBus1.notifySuccess()
}

interface AirPortTower{
    fun registerAirCraft(airCraft: AirCraft)
    fun requestLanding(airCraft: AirCraft)
    fun notifySuccess()
}

interface AirCraft{
    fun notify(msg : String)
    fun requestLanding()
    fun notifySuccess()
}

class MiniAirBus : AirCraft{
    private lateinit var airPortTower: AirPortTower

    fun setAirPortTower(airPortTower: AirPortTower){
        this.airPortTower = airPortTower
        airPortTower.registerAirCraft(this)
    }

    override fun notify(msg: String) {
        println("$msg --> MiniAirBus")
    }

    override fun requestLanding() {
        airPortTower.requestLanding(this)
    }

    override fun notifySuccess() {
        airPortTower.notifySuccess()
    }
}

class AirBus : AirCraft{
    private lateinit var airPortTower: AirPortTower

    fun setAirPortTower(airPortTower: AirPortTower){
        this.airPortTower = airPortTower
        airPortTower.registerAirCraft(this)
    }

    override fun notify(msg: String) {
        println("$msg --> Airbus")
    }

    override fun requestLanding() {
        airPortTower.requestLanding(this)
    }

    override fun notifySuccess() {
        airPortTower.notifySuccess()
    }

}

class NozhaTower : AirPortTower{
    val registeredAirCrafts = arrayListOf<AirCraft>()
    var isTerminalEmpty = true
    val towerName = "Nozha AirTower"

    override fun registerAirCraft(airCraft: AirCraft) {
        registeredAirCrafts.add(airCraft)
        println("$towerName : Register Success --> ${airCraft.javaClass.name}")
    }

    override fun requestLanding(airCraft: AirCraft) {
        if (!isTerminalEmpty){
            airCraft.notify("$towerName : The terminal is busy, Please wait.")
        }else{
            isTerminalEmpty = false
            airCraft.notify("$towerName : Ok you can land")
            registeredAirCrafts.forEach {
                if (it != airCraft){
                    it.notify("$towerName : An aircraft will land soon")
                }
            }
        }
    }

    override fun notifySuccess() {
        isTerminalEmpty = true
        registeredAirCrafts.forEach {
            it.notify("Nozha AirTower : Terminal is now empty")
        }
    }
}

