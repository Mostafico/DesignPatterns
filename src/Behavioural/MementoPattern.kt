package Behavioural

import java.util.*

fun main() {
    val employee = Employee("Mostafa Ragab", "01113300680")
    val caretaker = Caretaker()

    //save
    caretaker.save(employee)

    //change in phone
    employee.phone = "01556770680"

    println("Before undo --> ${employee.phone}")

    //undo
    caretaker.revert(employee)
    println("After undo --> ${employee.phone}")

}

class Employee(var name: String, var phone: String){
    fun save() : EmployeeMemento{
        return EmployeeMemento(this.name, this.phone)
    }

    fun revert(revEmp : EmployeeMemento){
        this.name = revEmp.getName()
        this.phone = revEmp.getPhone()
    }


}

class EmployeeMemento(private val name: String, private val phone: String){
    fun getName() : String{
        return this.name
    }

    fun getPhone() : String{
        return this.phone
    }
}

class Caretaker(){
    private val history = Stack<EmployeeMemento>()

    fun save(emp : Employee){
        history.push(emp.save())
    }

    fun revert(emp: Employee){
        emp.revert(history.pop())
    }
}
