package ru.netology

const val COMMISSION_VISA_MIR = 35
const val PERCENTAGE_VISA_MIR = 0.75
const val COMMISSION_MASTERCARD_MAESTRO = 20
const val PERCENTAGE_MASTERCARD_MAESTRO = 0.6

fun main() {

    print(
        "Выберите платежную систему. По умолчанию VK Pay." +
                "\n Доступно следующее: " +
                "\n 1. Visa Mir 0.75%, минимум 35 рублей " +
                "\n 2. Mastercard Maestro 0,6% + 20 рублей " +
                "\n 3. Продолжить по умолчанию" +
                "\n "
    )
    val choosingPaymentSystem = readLine()?.toInt() ?: return
    print("Введите сумму перевода в рублях:\n")
    val transferAmount = readLine()?.toInt() ?: return

    val totalAmountOfTransfersVisaMir = 0
    val totalAmountOfTransfersMastercardMaestro = 0
    val totalAmountOfVkPay = 0

    val TransfersVisaMirSumm = totalAmountOfTransfersVisaMir + transferAmount
    val TransfersMastercardMaestroSumm = totalAmountOfTransfersMastercardMaestro + transferAmount
    val TransfersVkPaySumm = totalAmountOfVkPay + transferAmount

    val resultVisaMirRKop = (calculationVisaMir(TransfersVisaMirSumm, transferAmount)
            * 100).toInt() % 100
    val resultMastercardMaestroKop = (calculationMastercardMaestro(TransfersMastercardMaestroSumm, transferAmount)
            * 100).toInt() % 100

    when (choosingPaymentSystem) {
        1 -> print(
            "За перевод суммы $transferAmount руб. " +
                    "Вы заплатите комиссию в размере ${
                        calculationVisaMir(
                            TransfersVisaMirSumm,
                            transferAmount
                        ).toInt()
                    } руб. " +
                    "$resultVisaMirRKop коп. "
        )
        2 -> print(
            "За перевод суммы $transferAmount руб. " +
                    "Вы заплатите комиссию в размере ${
                        calculationMastercardMaestro(
                            TransfersMastercardMaestroSumm,
                            transferAmount
                        ).toInt()
                    } руб. " +
                    "$resultMastercardMaestroKop коп. "
        )
        3 -> print(
            "За перевод суммы ${calculationVkPay(TransfersVkPaySumm, transferAmount)} руб. " +
                    "Комиссия в платежной системе VK Pay не взымаются."
        )
    }
}

fun calculationVisaMir(
    TransfersVisaMirSumm: Int,
    transferAmount: Int
): Double = when {
    TransfersVisaMirSumm > 4_670 && TransfersVisaMirSumm <= 600_000 ->
        (transferAmount * PERCENTAGE_VISA_MIR) / 100
    TransfersVisaMirSumm <= 4_670 -> COMMISSION_VISA_MIR.toDouble()
    else -> error("превышен лимит перевода.")
}

fun calculationMastercardMaestro(
    TransfersMastercardMaestroSumm: Int,
    transferAmount: Int
): Double = when {
    TransfersMastercardMaestroSumm > 0 && TransfersMastercardMaestroSumm <= 75_000 ->
        (transferAmount * PERCENTAGE_MASTERCARD_MAESTRO) / 100 + COMMISSION_MASTERCARD_MAESTRO
    else -> error("превышен лимит перевода.")
}

fun calculationVkPay(
    TransfersVkPaySumm: Int,
    transferAmount: Int
) = when {
    TransfersVkPaySumm > 0 && TransfersVkPaySumm <= 40_000 -> transferAmount
    else -> error("превышен лимит перевода.")
}