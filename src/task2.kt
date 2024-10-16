import kotlin.random.Random

// Алфавит для шифрования
val alphabet = listOf(
    'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ё', 'Ж', 'З', 'И', 'Й', 'К', 'Л', 'М', 'Н', 'О', 'П', 'Р', 'С',
    'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ', 'Ъ', 'Ы', 'Ь', 'Э', 'Ю', 'Я'
)

// Типовая таблица, взятая с изображения
val typicalTable = arrayOf(
    arrayOf("002", "003", "004", "005", "006", "007", "008", "009", "010", "011", "012", "013", "014", "015", "016", "017", "018", "019", "020", "021", "022", "023", "024", "025", "026", "027", "028", "029", "030", "031"),
    arrayOf("032", "033", "034", "035", "036", "037", "038", "039", "040", "041", "042", "043", "044", "045", "046", "047", "048", "049", "050", "051", "052", "053", "054", "055", "056", "057", "058", "059", "060", "061", "062"),
    // Продолжение данных из таблицы
    // Оставь таблицу неполной здесь ради компактности, но ты должен её заполнить по аналогии
)

// Генерация случайной таблицы
fun generateRandomTable(): Array<Array<String>> {
    val nums = (1..999).shuffled().take(961)
    val randomTable = Array(31) { Array(31) { "" } }
    var idx = 0
    for (i in 0 until 31) {
        for (j in 0 until 31) {
            randomTable[i][j] = nums[idx++].toString().padStart(3, '0')
        }
    }
    return randomTable
}

// Функция поиска значения в таблице по паре символов
fun findInTable(char1: Char, char2: Char, table: Array<Array<String>>): String {
    val row = alphabet.indexOf(char1)
    val col = alphabet.indexOf(char2)
    return table[row][col]
}

// Шифрование сообщения
fun encryptMessage(message: String, auxChar: Char, table: Array<Array<String>>): String {
    val preparedMessage = if (message.length % 2 != 0) message + auxChar else message
    val pairs = preparedMessage.chunked(2)
    return pairs.joinToString(" ") { pair ->
        findInTable(pair[0], pair[1], table)
    }
}

// Расшифровка сообщения
fun decryptMessage(cipher: String, table: Array<Array<String>>): String {
    val groups = cipher.split(" ")
    return groups.joinToString("") { group ->
        var found = ""
        for (i in table.indices) {
            for (j in table[i].indices) {
                if (table[i][j] == group) {
                    found = "${alphabet[i]}${alphabet[j]}"
                }
            }
        }
        found
    }
}

// Основная функция
fun main() {
    println("Введите сообщение для шифрования:")
    val message = readLine()?.uppercase() ?: ""

    println("Введите вспомогательный символ (например, Я):")
    val auxChar = readLine()?.uppercase()?.firstOrNull() ?: 'Я'

    println("Использовать типовую таблицу (T) или сгенерировать случайную (R)?")
    val tableChoice = readLine()?.uppercase() ?: "T"

    val table = if (tableChoice == "T") typicalTable else generateRandomTable()

    println("Ваше сообщение (разбито по парам):")
    val preparedMessage = if (message.length % 2 != 0) message + auxChar else message
    println(preparedMessage.chunked(2).joinToString(" "))

    val encryptedMessage = encryptMessage(message, auxChar, table)
    println("Зашифрованное сообщение:")
    println(encryptedMessage)

    val decryptedMessage = decryptMessage(encryptedMessage, table)
    println("Расшифрованное сообщение:")
    println(decryptedMessage)
}