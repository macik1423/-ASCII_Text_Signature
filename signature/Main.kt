package signature

import kotlin.math.max

fun main() {
    println("Enter name and surname: ")
    val name = readLine()!!
    println("Enter person's status:")
    val status = readLine()!!
    val roman = Converter(name).toRoman()
    val medium = Converter(status).toMedium()

    val frame = Frame(roman, medium)
    println(frame.show())
}

class Frame(private val name: List<Letter>, private val status: List<Letter>) {
    private fun longest(): Int {
        return max(nameLength(), statusLength())
    }

    private fun statusLength(): Int {
        return status.fold(0) { acc, letter -> acc + letter.fontWidth }
    }

    private fun nameLength(): Int {
        return name.fold(0) { acc, letter -> acc + letter.fontWidth }
    }

    fun show(): String {
        return top() + "\n" + body()
    }

    private fun body(): String {
        return nameSurname() + statusPrint() + bottom()
    }

    private fun bottom(): String {
        return "8888" + "8".repeat(longest()) + "8888"
    }

    private fun statusPrint(): String {
        var result = ""
        val spaces = if (nameLength() > statusLength()) {
            (nameLength() - statusLength() + 4) / 2
        } else {
            2
        }
        for (i in 0 until fontSize(status)) {
            result += "88" + " ".repeat(spaces)
            for (letter in status) {
                result += letter.text[i] + " ".repeat(letter.fontWidth - letter.text[i].length)
            }
            result += " ".repeat(longest() + 4 - statusLength() - spaces) + "88" + "\n"
        }
        return result
    }

    private fun nameSurname(): String {
        var result = ""
        val spaces = if (nameLength() > statusLength()) {
            2
        } else {
            (statusLength() - nameLength() + 4) / 2
        }
        for (i in 0 until fontSize(name)) {
            var line = "88" + " ".repeat(spaces)
            var lineLetters = ""
            for (letter in name) {
                lineLetters += letter.text[i] + " ".repeat(letter.fontWidth - letter.text[i].length)
            }
            line += lineLetters
            line += " ".repeat(longest() + 4 - nameLength() - spaces) + "88" + "\n"
            result += line
        }
        return result
    }

    private fun fontSize(name: List<Letter>): Int {
        return name[0].fontSize
    }

    private fun top(): String {
        return "8888" + "8".repeat(longest()) + "8888"
    }
}



