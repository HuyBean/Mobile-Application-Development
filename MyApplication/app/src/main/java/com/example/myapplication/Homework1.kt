package com.example.myapplication

import kotlin.math.sqrt
import kotlin.math.roundToInt

fun main() {
    println("======== For and Array =========")
    // Bài 7
    val array1 = arrayOf(5, 2, 8, 1, 6)
    val sortedArray1 = sortArray(array1, true)
    println("\nQuestion 7: \n Array: ${array1.contentToString()}")
    println("Sorted Array (ascending): ${sortedArray1.contentToString()}")

    // Bài 8
    val array2 = arrayOf(5, 2, 8, 1, 6)
    val sortedArray2 = sortArray(array2, false)
    println("\nQuestion 8: \n Array: ${array2.contentToString()}")
    println("Sorted Array (descending): ${sortedArray2.contentToString()}")

    // Bài 9
    val array3 = arrayOf(5, 2, 8, 1, 6)
    val k1 = 1
    val sortedArray3 = sortArray(array3, k1 == 1)
    println("\nQuestion 9: \n Array: ${array3.contentToString()}")
    println("Sorted Array (based on k=$k1): ${sortedArray3.contentToString()}")

    // Bài 10
    val n1 = 121
    println("\nQuestion 10: \n n1 = $n1 ")
    println("$n1 is palindrome: ${isPalindrome(n1)}")

    // Bài 11
    val numPrime = 13
    val nonPrime = 12
    val resultQ11A = isPrime(numPrime)
    val resultQ11B = isPrime(nonPrime)
    println("\nQuestion 11: \n Num A: $numPrime \n Num B: $nonPrime")
    println("Is A a Prime: $resultQ11A")
    println("Is B a Prime: $resultQ11B")

    // Bài 12
    val array5 = arrayOf(2, 5, 8, 12, 3, 17, 20, 7, 13, 4)
    val result = findValues(array5)
    println("\nQuestion 12: \n Array: ${array5.contentToString()}")
    println("Giá trị lớn nhất trong mảng: ${result.first}")
    println("Giá trị số chẵn lớn nhất trong mảng: ${result.second}")
    println("Giá trị số lẽ lớn nhất trong mảng: ${result.third}")
    println("Giá trị số nguyên tố lớn nhất trong mảng: ${result.fourth}")

    println("\n======== Other =========")

    // Bài 1
    val km = 150
    val totalMoney = calculateTaxiMoney(km)
    println("Question 1:\n km: $km")
    println("Total taxi money: $totalMoney VND")

    // Bài 2
    val bookPrice = 50000
    val quantity = 5
    val delivery = true
    val totalCost = calculateBookCost(bookPrice, quantity, delivery)
    println("\nQuestion 2: \n Price: $bookPrice, Quantity: $quantity, Delivery: $delivery")
    println("Total book cost: $totalCost VND")

    // Bài 3
    val usdAmount = 50
    val vndAmount = 54000
    val vndConvert = convertToVND(usdAmount)
    val usdConvert = convertToUSD(vndAmount)
    println("\nQuestion 3: \n USD: $usdAmount")
    println("$usdAmount USD is equal to $vndConvert VND")
    println("$vndAmount VND is equal to $usdConvert USD")

    // Bài 4
    val n = 20
    val k = findSmallestK(n)
    println("\nQuestion 4: \n N: $n")
    println("Smallest k for 1+2+...+k > $n: $k")
}

fun sortArray(array: Array<Int>, ascending: Boolean): Array<Int> {
    return if (ascending) {
        array.sorted().toTypedArray()
    } else {
        array.sortedDescending().toTypedArray()
    }
}

fun isPalindrome(n: Int): Boolean {
    val strN = n.toString()
    return strN === strN.reversed()
}

fun isPrime(n: Int): Boolean {
    if (n <= 1) return false
    for (i in 2..sqrt(n.toDouble()).toInt()) {
        if (n % i == 0) return false
    }
    return true
}

data class Quadruple<A, B, C, D>(val first: A, val second: B, val third: C, val fourth: D)

fun findValues(array: Array<Int>): Quadruple<Int, Int, Int, Int> {
    val maxValue = array.maxOrNull() ?: 0
    val maxEven = array.filter { it % 2 == 0 }.maxOrNull() ?: 0
    val maxOdd = array.filter { it % 2 != 0 }.maxOrNull() ?: 0
    val maxPrime = array.filter { isPrime(it) }.maxOrNull() ?: 0
    return Quadruple(maxValue, maxEven, maxOdd, maxPrime)
}

fun calculateTaxiMoney(km: Int): Int {
    var money = 0
    money += minOf(km, 1) * 15000
    money += minOf(km - 1, 4) * 13500
    money += maxOf(km - 5, 0) * 11000
    if (km > 120) {
        money -= money / 10
    }
    return money
}

fun calculateBookCost(bookPrice: Int, quantity: Int, delivery: Boolean): Int {
    var cost = bookPrice * quantity
    if (delivery) {
        cost += 20000
    }
    if (cost > 100000) {
        cost -= cost / 10
    }
    return cost
}

fun convertToVND(usdAmount: Int): Int {
    val exchangeRate = 22700
    return usdAmount * exchangeRate
}

fun convertToUSD(vndAmount: Int): Int {
    val exchangeRate = 22700
    return (vndAmount.toDouble() / exchangeRate).roundToInt()
}

fun findSmallestK(n: Int): Int {
    var sum = 0
    var k = 0
    while (sum < n) {
        k++
        sum += k
    }
    return k-1
}

