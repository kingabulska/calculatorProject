fun main() {
    // put your code here
    val one = readLine()!!.toLowerCase()
    val two = readLine()!!.toLowerCase()

    val bool: Boolean = if (one.compareTo(two) == 0) true else false
    println(bool)
}