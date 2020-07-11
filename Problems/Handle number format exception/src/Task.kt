
fun parseCardNumber(cardNumber: String): Long {
    val card = cardNumber.split(" ")
    var longNumber: Long = 0

    if (card.size != 4) {
       throw Exception("Wrong format!")
    } else {

        for (ch in cardNumber) {
            if (!ch.isDigit() && ch != ' ') {
                throw Exception("Card number must contains only numbers!")
            }
        }
        var newNumber = card[0] + card[1] + card[2] + card[3]

        if (newNumber.length != 16) {
            throw Exception("Card number must be 16 numbers!")
        }

        /*for (i in card.indices){
            newNumber += card[i]
        }
        */

        longNumber = newNumber.toLong()
    }
    return longNumber
}
