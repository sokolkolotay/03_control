package ru.netology

fun commissionCalculator(
    cardType: String = "Мир", //тип карты (по умолчанию Мир)
    amountThisMonth: Double = 0.0, //сумма предыдущих переводов в этом месяце (по умолчанию 0 рублей)
    amount: Double // сумма совершаемого перевода
): String {
    //введем лимиты
    val dailyLimit = 150_000 // лимит в сутки
    val monthlyLimit = 600_000 // лимит в месяц
    val mastercardLimit = 75_000 // лимит по карте MasterCard
    val minCommissionVisa = 35 // минимальная комиссия по карте Visa

    //блокирование операции при превышении дневного лимита
    if (amount > dailyLimit) {
        return "Превышена сумма лимита в день. Лимит = $dailyLimit"
    }

    //блокирование операции при превышении лимита за месяц
    if (amountThisMonth + amount > monthlyLimit) {
        return "Превышена сумма лимита за месяц. Лимит = $monthlyLimit"
    }

    //в зависимости от типа карты вычислим сумму комиссии
    when (cardType) {
//        За переводы с карты Mastercard комиссия не взимается,
//        пока не превышен месячный лимит в 75 000 руб.
//        Если лимит превышен, комиссия составит 0,6% + 20 руб.
        "Mastercard" -> {
            if (amountThisMonth < mastercardLimit) {
                val amountRemains = mastercardLimit - amountThisMonth
                if (amount <= amountRemains) {
                    return "Комиссия = 0 рублей"
                } else {
                    val amountCalculated = amount - amountRemains
                    val commission = amountCalculated * 0.006 + 20
                    return "Комиссия = $commission рублей"
                }
            } else {
                val commission = amount * 0.006 + 20
                return "Комиссия = $commission рублей"
            }
        }
//        За переводы с карты Visa комиссия составит 0,75%, минимальная сумма комиссии 35 руб.
        "Visa" -> {
            val comission = amount * 0.0075
            return if (comission < 35) {
                "Комиссия = 35 рублей"
            } else {
                "Комиссия = $comission рублей"
            }
        }
//        За переводы с карты Мир комиссия не взимается.
        "Мир" -> return "Комиссия = 0 рублей"
        else -> {
            return "Ошибка! Тип карты не найден"
        }
    }
}
