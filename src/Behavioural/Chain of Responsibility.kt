package Behavioural

fun main(args: Array<String>) {
    var bryan = Director()
    var crystal = VP()
    var jeff = CEO()

    bryan.setSuccessor(crystal)
    crystal.setSuccessor(jeff)

    var request = Request(RequestType.CONFERENCE, 500.0)
    bryan.handleRequest(request)

    request = Request(RequestType.PURCHASE, 1000.0)
    bryan.handleRequest(request)

    request = Request(RequestType.PURCHASE, 2000.0)
    bryan.handleRequest(request)
}

abstract class Handler{
    internal var successor: Handler? = null

    fun setSuccessor(successor: Handler){
        this.successor = successor
    }

    public abstract fun handleRequest(request: Request)
}


enum class RequestType{
    CONFERENCE, PURCHASE
}

data class Request(val requestType: RequestType, val amount: Double)

class Director : Handler(){
    override fun handleRequest(request: Request) {
        if(request.requestType == RequestType.CONFERENCE){
            println("Director can Approve conferences")
        }else{
            successor?.handleRequest(request)
        }
    }

}

class VP : Handler(){
    override fun handleRequest(request: Request) {
        if (request.requestType == RequestType.PURCHASE){
            if (request.amount < 1500){
                println("VP can Approve budget < 1500")
            }else{
                successor?.handleRequest(request)
            }
        }
    }
}

class CEO : Handler(){
    override fun handleRequest(request: Request) {
        println("CEO can Approve Anything!")
    }
}

