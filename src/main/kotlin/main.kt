package ru.netology

const val COMMISSION_VISA_MIR = 35
const val PERCENTAGE_VISA_MIR = 0.75
const val COMMISSION_MASTERCARD_MAESTRO = 20
const val PERCENTAGE_MASTERCARD_MAESTRO = 0.6

fun main() {
    total()

}

fun total() {
    print(
        "Выберите платежную систему. По умолчанию VK Pay." +
                "\n Доступно следующее: " +
                "\n 1. Visa Mir 0.75%, минимум 35 рублей " +
                "\n 2. Mastercard Maestro 0,6% + 20 рублей " +
                "\n 3. Продолжить по умолчанию" +
                "\n "
    )
    val choosingPaymentSystem = readLine()?.toInt() ?: return
    print("Введите сумму предыдущих переводов в этом месяце в рублях:\n")
    val amountOfPreviousTransfers = readLine()?.toInt() ?: return
    print("Введите сумму перевода в рублях:\n")
    val transferAmount = readLine()?.toInt() ?: return

    val resultVisaMirRKop = (calculationVisaMir(transferAmount)
            * 100).toInt() % 100
    val resultMastercardMaestroKop = (calculationMastercardMaestro(transferAmount)
            * 100).toInt() % 100

    when (choosingPaymentSystem) {
        1 -> print(
            "За перевод суммы $transferAmount руб. " +
                    "Вы заплатите комиссию в размере ${
                        calculationVisaMir(
                            transferAmount,
                            amountOfPreviousTransfers
                        ).toInt()
                    } руб. " +
                    "$resultVisaMirRKop коп. "
        )
        2 -> print(
            "За перевод суммы $transferAmount руб. " +
                    "Вы заплатите комиссию в размере ${
                        calculationMastercardMaestro(
                            transferAmount,
                            amountOfPreviousTransfers
                        ).toInt()
                    } руб. " +
                    "$resultMastercardMaestroKop коп. "
        )
        3 -> print(
            "За перевод суммы ${calculationVkPay(transferAmount, amountOfPreviousTransfers)} руб. " +
                    "Комиссия в платежной системе VK Pay не взымаются."
        )
    }
}

fun calculationVisaMir(
    transferAmount: Int,
    amountOfPreviousTransfers: Int = 0
): Double = when {
    amountOfPreviousTransfers + transferAmount > 4_670 && amountOfPreviousTransfers + transferAmount <= 600_000 ->
        (transferAmount * PERCENTAGE_VISA_MIR) / 100
    amountOfPreviousTransfers + transferAmount <= 4_670 -> COMMISSION_VISA_MIR.toDouble()
    else -> error("превышен лимит перевода.")
}

fun calculationMastercardMaestro(
    transferAmount: Int,
    amountOfPreviousTransfers: Int = 0

): Double = when {
    amountOfPreviousTransfers + transferAmount > 0 && amountOfPreviousTransfers + transferAmount <= 75_000 ->
        (transferAmount * PERCENTAGE_MASTERCARD_MAESTRO) / 100 + COMMISSION_MASTERCARD_MAESTRO
    else -> error("превышен лимит перевода.")
}

fun calculationVkPay(
    transferAmount: Int,
    amountOfPreviousTransfers: Int = 0

) = when {
    amountOfPreviousTransfers + transferAmount > 0 && amountOfPreviousTransfers + transferAmount <= 40_000 -> transferAmount
    else -> error("превышен лимит перевода.")
}