package ru.netology

val secondInOneMinutes = 60
val secondInOneHours = 60 * 60
val secondInOneDay: Int = secondInOneHours * 24
val secondInTwoDay: Int = secondInOneDay * 2
val secondInThreeDay: Int = secondInOneDay * 3

// функция учитывающая сколько времени прошло с последнего визита пользователя и выдает
// «был(а) только что», «был(а) 3 часа назад» и т. п.
fun agoToText(second: Int) {
    val result = when {
        (second <= secondInOneMinutes) -> "был(а) только что"
        (second > secondInOneMinutes && second < secondInOneHours) -> "был(а) ${howMuchTime(second)} ${
            formOfWordsMinutes(
                howMuchTime(second), "минуту", "минуты", "минут"
            )
        } назад"

        (second >= secondInOneHours && second <= secondInOneDay) -> "был(а) ${howMuchTime(second)} ${
            formOfWordsHours(howMuchTime(second), "час", "часа", "часов")
        } назад"

        (second > secondInOneDay && second <= secondInTwoDay) -> "был(а) вчера"
        (second > secondInTwoDay && second <= secondInThreeDay) -> "был(а) позавчера"
        else -> "был(а) давно"
    }
    println(result)
}

//функция подсчета пройденного времени
fun howMuchTime(second: Int): Int {
    return when {
        (second < secondInOneHours) -> second / secondInOneMinutes //минуты (если меньше 1 часа)
        else -> second / secondInOneHours //часы (если больше или равно одному часу)
    }
}

//функция определения правильной формы слов "минута" и "час"
fun formOfWordsMinutes(time: Int, form1: String, form2: String, form3: String): String {
    val lastDigit = time % 10
    return when {
        (lastDigit in 5..9) -> form3
        (lastDigit == 1 && time != 11) -> form1
        (lastDigit in 2..4 && time !in 12..14) -> form2
        (time in 11..14) -> form3
        else -> form3
    }
}

fun formOfWordsHours(time: Int, form1: String, form2: String, form3: String): String {
    val lastDigit = time % 10
    return when {
        (time in 5..20) -> form3
        (lastDigit == 1) -> form1
        (time in 22..24 || time in 2..4) -> form2
        else -> form3
    }
}