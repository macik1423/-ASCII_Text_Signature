package signature

import java.io.File

class Medium {
    companion object {
        fun get(): MutableList<Letter> {
            val mediumPath = "/home/maciej/Publiczny/programowanie/scala/progfun1-example/ASCII Text Signature/ASCII Text Signature/task/src/signature/medium.txt"
            val letters = FileReader.file(mediumPath)
            val spaces = 5
            val space = Letter(' ',3, 5,
                mutableListOf(" ".repeat(spaces), " ".repeat(spaces), " ".repeat(spaces), " ".repeat(spaces), " ".repeat(spaces), " ".repeat(spaces), " ".repeat(spaces), " ".repeat(spaces), " ".repeat(spaces), " ".repeat(spaces)))
            letters.add(space)
            return letters
        }
    }
}

class Roman {
    companion object {
        fun get(): MutableList<Letter> {
            val romanPath = "/home/maciej/Publiczny/programowanie/scala/progfun1-example/ASCII Text Signature/ASCII Text Signature/task/src/signature/roman.txt"
            val letters = FileReader.file(romanPath)
            val spaces = 10
            val space = Letter(' ',10, 10,
                mutableListOf(" ".repeat(spaces), " ".repeat(spaces), " ".repeat(spaces), " ".repeat(spaces), " ".repeat(spaces), " ".repeat(spaces), " ".repeat(spaces), " ".repeat(spaces), " ".repeat(spaces), " ".repeat(spaces)))
            letters.add(space)
            return letters
        }
    }
}

class Converter(private val word: String) {
    fun toRoman(): List<Letter> {
        return word.toCharArray().map { Roman.get().find { letter -> letter.charRepresentative == it }!! }
    }

    fun toMedium(): List<Letter> {
        return word.toCharArray().map { Medium.get().find { letter -> letter.charRepresentative == it }!! }
    }
}

private class FileReader {
    companion object {
        fun file(path: String): MutableList<Letter> {
            val lines = File(path).bufferedReader().readLines()
            val fontSize = lines[0].split(" ")[0].toInt()
            val letters: MutableList<Letter> = mutableListOf()
            for (i in 1 until lines.size step (fontSize + 1)) {
                val charRepresentative = lines[i].split(" ")[0][0]
                val charWidth = lines[i].split(" ")[1].toInt()
                val textLines = lines.subList(i + 1, i + 1 + fontSize)
                val letter = Letter(
                    charRepresentative = charRepresentative,
                    fontSize = fontSize,
                    fontWidth = charWidth,
                    text = textLines)
                letters.add(letter)
            }
            return letters
        }
    }
}

data class Letter(
    val charRepresentative: Char,
    val fontSize: Int,
    val fontWidth: Int,
    val text: List<String>
)


