fun main() {
    // write your code here
    val vovels = listOf<String>("a", "e", "i", "o", "u", "y")
    var s = readLine()!!
    var new:String = ""
    var counterVow = 0
    var counterCon = 0
    var listVowsPos = mutableListOf<Int>()
    var listConPos = mutableListOf<Int>()
    var min = 0
    var j = 0

    var c = 0
    var v = 0

    for (ch in s){
        if (vovels.contains(ch.toString().toLowerCase())){
            if (c >= 3) {
                if (c % 2 == 0){
                    min += (c / 2) - 1
                } else {
                    min += c / 2
                }
            }
            v++
            c = 0

        } else {
            if (v >= 3) {
                if (v % 2 == 0){
                    min += (v / 2) - 1
                } else {
                    min += v / 2
                }
            }
            c ++
            v = 0
        }

    }
    if (c >= 3) {
        if (c % 2 == 0){
            min += (c / 2) - 1
        } else {
            min += c / 2
        }
    }
    if (v >= 3) {
        if (v % 2 == 0){
            min += (v / 2) - 1
        } else {
            min += v / 2
        }
    }

   /* val isStill = true
    for (i in 0..s.length - 2){

        if(vovels.contains(s[i].toString().toLowerCase()) && vovels.contains(s[i + 1].toString().toLowerCase())){
            counterVow ++
        }
        if (!vovels.contains(s[i].toString().toLowerCase()) && !vovels.contains(s[i + 1].toString().toLowerCase())) {
            counterCon++
        }


        *//*if (counterVow == 2) {
            min ++
            listVowsPos.add(i)
            counterVow = 0
        }*//*

    }*/


   /*while(j != listVowsPos.size){
       val pos = listVowsPos[j]
       val old = s.substring(0, pos + 1 )
       val rest = s.substring(pos + 1 , s.length)
       val add = "k"
       s = old + add + rest
       var i = 0
       while(i != listVowsPos.size){
           listVowsPos[i] += 1
           i++
       }
       j++
   }*/
    for (i in 0..s.length - 2) {

        if (!vovels.contains(s[i].toString().toLowerCase()) && !vovels.contains(s[i + 1].toString().toLowerCase())) {
            counterCon++
        }

        /*if (counterCon == 2) {
            min++
            listConPos.add(i)
            counterCon = 0
        }*/
    }
   /* j = 0
    while(j != listConPos.size){
        val pos = listConPos[j]
        val old = s.substring(0, pos + 1 )
        val rest = s.substring(pos + 1, s.length)
        val add = "a"
        s = old + add + rest
        var i = 0
        while(i != listConPos.size){
            listConPos[i] += 1
            i++
        }
        j++
    }*/

   /* if (counterCon != 0  ) {
        val temp = counterCon + 2
        var dziel = 0
        if (counterCon > 2) {
             dziel = temp / 3
        } else {
            dziel = 0
        }
        counterCon += dziel - 1
    }
    if (counterVow != 0  ) {
        val temp = counterVow + 2
        var dziel = 0
        if (counterVow > 2) {
            dziel = temp / 3
        } else {
            dziel = 0
        }
        counterVow += dziel - 1
    }

    val dzialaj = if (counterCon % 2 == 0 && counterCon != 0) counterCon / 3 + 1 else if (counterCon == 0) 0 else counterCon / 3
    val kurla = if (counterVow % 2 == 0 && counterVow != 0) counterVow / 3 + 1 else if (counterVow == 0) 0 else counterVow / 3

    min = dzialaj + kurla*/
    println(min)
   //println(s)

}