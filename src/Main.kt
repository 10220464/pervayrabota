import kotlin.random.Random

fun main() {
    while (true) {
        val computerChoice = getComputerChoice()
        val playerChoice = getPlayerChoice()

        println("Компьютер выбрал: $computerChoice")
        println("Вы выбрали: $playerChoice")

        val result = determineWinner(computerChoice, playerChoice)

        when (result) {
            "Вы победили!" -> println(result)
            "Компьютер победил!" -> println(result)
            "Ничья!" -> println(result)
        }

        if (result != "Ничья!") {
            break
        }
    }
}

fun getComputerChoice(): String {
    val choices = listOf("Камень", "Ножницы", "Бумага")
    return choices[Random.nextInt(choices.size)]
}

fun getPlayerChoice(): String {
    println("Введите свой выбор:")
    println("1 - Камень")
    println("2 - Ножницы")
    println("3 - Бумага")

    while (true) {
        val choice = readLine()?.toIntOrNull()
        if (choice != null && choice in 1..3) {
            return when (choice) {
                1 -> "Камень"
                2 -> "Ножницы"
                3 -> "Бумага"
                else -> ""
            }
        }
        println("Неверный ввод. Пожалуйста, введите число от 1 до 3.")
    }
}

fun determineWinner(computerChoice: String, playerChoice: String): String {
    return when {
        computerChoice == playerChoice -> "Ничья!"
        (computerChoice == "Камень" && playerChoice == "Ножницы") ||
                (computerChoice == "Ножницы" && playerChoice == "Бумага") ||
                (computerChoice == "Бумага" && playerChoice == "Камень") -> "Компьютер победил!"
        else -> "Вы победили!"
    }
}