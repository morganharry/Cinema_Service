package cinema

fun createCinemaList(): MutableList<MutableList<String>> {
    println("Enter the number of rows:")
    val rows = readln().toInt()
    println("Enter the number of seats in each row:")
    val seatsInRow = readln().toInt()
    println()
    val listSeats = MutableList (rows+1) {MutableList <String> (seatsInRow+1) {"S"}}
    for (i in 0..seatsInRow) {
        when {
            i == 0 -> listSeats[0][i] = " "
            else -> listSeats[0][i] = i.toString()
        }
    }
    for (n in 1..rows) {
        listSeats[n][0]=n.toString()
    }
    return listSeats
}

fun chooseProgram ():Int {
    println("1. Show the seats\n" +
            "2. Buy a ticket\n" +
            "3. Statistics\n" +
            "0. Exit")
    return readln().toInt()
}

fun showTheSeats (listSeats: MutableList<MutableList<String>>) {
    println("Cinema:")
    for (m in 0 .. listSeats.size-1) {
        print(listSeats[m].joinToString(" "))
        println()
    }
    println()
}

fun buyATicket (listSeats: MutableList<MutableList<String>>): MutableList<MutableList<String>>  {
    val yourSeat = MutableList (2) {0}
    try {
        do {
            println("Enter a row number:")
            yourSeat[0] = readln().toInt()
            println("Enter a seat number in that row:")
            yourSeat[1] = readln().toInt()
            if (listSeats[yourSeat[0]][yourSeat[1]] == "B") println("That ticket has already been purchased!")
        } while (listSeats[yourSeat[0]][yourSeat[1]] == "B")
        val yourPrice = cost(listSeats.size - 1, listSeats[0].size - 1, yourSeat[0])
        listSeats [yourSeat[0]][yourSeat[1]] = "B"
        println("Ticket price: \$${yourPrice}")
        println()
    }
    catch (e: Exception) {
        println("Wrong input!")
    }
    return listSeats
}

fun cost (rows:Int, seats:Int, row:Int):Int {
    var costOfSeat = 10
    if ((rows * seats) > 60 && (row > rows / 2)) costOfSeat = 8
    return costOfSeat
}

fun showStatistics (listSeats: MutableList<MutableList<String>>) {
    var countAllTickets = 0
    var countSoldTickets = 0
    var costAllTickets = 0
    var costSoldTickets = 0
    for (m in 1..listSeats.size - 1) {
        for (n in 1..listSeats[0].size - 1) {
            countAllTickets++
            costAllTickets += cost(listSeats.size - 1,listSeats[0].size - 1,m)
            if (listSeats[m][n] == "B") {
                countSoldTickets++
                costSoldTickets += cost(listSeats.size - 1,listSeats[0].size - 1,m)
            }
        }
    }
    val formatPercentage = "%.2f".format(countSoldTickets.toDouble()/countAllTickets.toDouble()*100)
    println("Number of purchased tickets: ${countSoldTickets}\n" +
            "Percentage: ${formatPercentage}%\n" +
            "Current income: \$${costSoldTickets}\n" +
            "Total income: \$${costAllTickets}")
    println()
}

fun menu (listSeats: MutableList<MutableList<String>>) {
    var program = 0

    do {
        try {
            program = chooseProgram()
            when (program) {
                1 -> showTheSeats(listSeats)
                2 -> buyATicket(listSeats)
                3 -> showStatistics(listSeats)
            }
        }
        catch (e: Exception) {
            println("Wrong input!")
        }
    } while (program != 0)
}

fun main() {
    val cinemaList = createCinemaList()
    menu (cinemaList)
}



