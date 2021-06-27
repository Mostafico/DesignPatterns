package Behavioural

//Manager
fun main() {
    val companyARepository = CompanyARepository()
    val companyBRepository = CompanyBRepository()

    companyARepository.forEach { employee ->
        println(employee)
    }
    companyBRepository.forEach { employee ->
        println(employee)
    }
}

class CompanyBRepository : Iterable<String>{
    private var employees = arrayListOf<String>("Mohamed Mahmoud", "Ahmed Khaled", "Amira Ismael", "Omar Adel")

    fun addEmployee(employee: String){
        employees.add(employee)
    }

    //implementation of loop is internally, not exposed to the client
    override fun iterator(): Iterator<String> {
        return object : Iterator<String> {
            var index = 0
            override fun hasNext(): Boolean {
                if (index < employees.size)
                    return true
                return false
            }

            override fun next(): String {
                return employees[index++]
            }

        }
    }
}


class CompanyARepository : Iterable<String>{
    private var employees = arrayOfNulls<String>(10)
    private var index = 0

    init {
        addEmployee("Mohamed Ibrahim")
        addEmployee("Malak Ragab")
        addEmployee("Habiba Mohamed")
    }

    fun addEmployee(employee: String){
        if(index == employees.size){
            var largerListOfEmployees: Array<String?>? = arrayOfNulls<String>(employees.size +5)
            System.arraycopy(employees, 0, largerListOfEmployees, 0, employees.size)
            employees = largerListOfEmployees!!
            largerListOfEmployees = null
        }

        employees[index] = employee
        index++
    }

    //implementation of loop is internally, not exposed to the client
    override fun iterator(): Iterator<String> {
        return object : Iterator<String> {
            private var currentIndex = 0

            override fun hasNext(): Boolean {
                return currentIndex < employees.size && employees[currentIndex] != null
            }

            override fun next(): String {
                return employees[currentIndex++]!!
            }

            //we can add more functionality like add, remove, previous, hasPrevious
            //we can find mentioned extra features in MutableListIterator
        }
    }

}