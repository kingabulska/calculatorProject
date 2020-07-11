fun main() {
    // write your code here
    val input = readLine()!!.split(" ")

    val s = input[0]
    val n = input[1].toInt()

    if (n < s.length){
        val new1 = s.substring(n, s.length )
        val new2 = s.substring(0, n)
        val new = new1 + new2
        println(new)
    } else {
        println(s)
    }
}