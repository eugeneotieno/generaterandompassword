fun main() {
    getRandomPassword()
}

fun getRandomPassword() {
    val requirements = readln().split("\\s".toRegex()).toTypedArray()

    val numA = requirements[0].toInt()
    val numB = requirements[1].toInt()
    val numC = requirements[2].toInt()
    val total = requirements[3].toInt()

    val password = generatePassword(numA, numB, numC, total)
    println(password)
}

fun generatePassword(A: Int, B: Int, C: Int, N: Int): String {
    val password = StringBuilder()
    val chars = mutableListOf<Char>()

    // Add A uppercase letters
    repeat(A) {
        chars.add(getRandomUppercaseLetter())
    }

    // Add B lowercase letters
    repeat(B) {
        chars.add(getRandomLowercaseLetter())
    }

    // Add C digits
    repeat(C) {
        chars.add(getRandomDigit())
    }

    // If the password is not long enough, add random characters until it reaches the required length
    val remaining = N - chars.size
    if (remaining != 0) {
        repeat(remaining) {
            chars.add(getRandomChar())
        }
    }

    // Shuffle the list of characters
    chars.shuffle()

    // Add the characters to the password, checking for repeated characters AAA
    var lastChar: Char? = null
    for (c in chars) {
        if (c != lastChar) {
            password.append(c)
            lastChar = c
        } else {
            if (lastChar.isUpperCase()) {
                var newChar: Char
                do {
                    newChar = getRandomUppercaseLetter()
                } while (lastChar == newChar)
                password.append(newChar)
                lastChar = newChar
            } else if (lastChar.isLowerCase()) {
                var newChar: Char
                do {
                    newChar = getRandomLowercaseLetter()
                } while (lastChar == newChar)
                password.append(newChar)
                lastChar = newChar
            } else {
                var newChar: Char
                do {
                    newChar = getRandomDigit()
                } while (lastChar == newChar)
                password.append(newChar)
                lastChar = newChar
            }
        }
    }

    return password.toString()
}

fun getRandomUppercaseLetter(): Char {
    return ('A'..'Z').random()
}

fun getRandomLowercaseLetter(): Char {
    return ('a'..'z').random()
}

fun getRandomDigit(): Char {
    return ('0'..'9').random()
}

fun getRandomChar(): Char {
    val chars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
    return chars.random()
}