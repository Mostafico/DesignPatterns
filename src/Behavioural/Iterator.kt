package Behavioural

fun main() {
    val repo = BikeRepository()

    repo.addBike("Cervelo")
    repo.addBike("Scott")
    repo.addBike("Fuji")

    val bikeIterator = repo.iterator()

    //while(bikeIterator.hasNext()) {
    //	System.out.println(bikeIterator.next());
    //}


    //while(bikeIterator.hasNext()) {
    //	System.out.println(bikeIterator.next());
    //}
    while (bikeIterator.hasNext()){
        val bike = bikeIterator.next()
        println(bike)
    }

    repo.forEach {
        println(it)
    }
}


class BikeRepository : Iterable<String>{
    private var bikes = arrayOfNulls<String>(10)
    private var index = 0

    fun addBike(bike: String){
        if(index == bikes.size){
            var largerBikes: Array<String?>? = arrayOfNulls<String>(bikes.size +5)
            System.arraycopy(bikes, 0, largerBikes, 0, bikes.size)
            bikes = largerBikes!!
            largerBikes = null
        }

        bikes[index] = bike
        index++
    }


    override fun iterator(): Iterator<String> {
        val iterator = object : Iterator<String>{
            private var currentIndex = 0

            override fun hasNext(): Boolean {
                return currentIndex < bikes.size && bikes[currentIndex] != null
            }

            override fun next(): String {
                return bikes[currentIndex++]!!
            }

        }
        return iterator
    }

}