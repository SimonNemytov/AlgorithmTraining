package yandex_algo

fun main() {
//    val l11 = ListNode(1)
//    val l12 = ListNode(2)
//    l11.next = l12
//    val l13 = ListNode(3)
//    l12.next = l13
//    val l14 = ListNode(4)
//    l13.next = l14
//    val l15 = ListNode(5)
//    l14.next = l15
//    val l16 = ListNode(6)
//    l15.next = l16
//
//    val l21 = ListNode(1)
//    val l22 = ListNode(2)
//    l21.next = l22
//    val l23 = ListNode(3)
//    l22.next = l23
//    val l24 = ListNode(4)
//    l23.next = l24
//    val l25 = ListNode(5)
//    l24.next = l25
//    val l26 = ListNode(6)
//    l25.next = l26

    //--------//
//    val l11 = ListNode(2)
//    val l12 = ListNode(4)
//    l11.next = l12
//    val l13 = ListNode(6)
//    l12.next = l13
//
//    val l21 = ListNode(5)
//    val l22 = ListNode(6)
//    l21.next = l22
//    val l23 = ListNode(7)
//    l22.next = l23

    //------//
    val l11 = ListNode(9)
    val l12 = ListNode(9)
    l11.next = l12
    val l13 = ListNode(9)
    l12.next = l13
    val l14 = ListNode(9)
    l13.next = l14


    val l21 = ListNode(9)
    val l22 = ListNode(9)
    l21.next = l22
    val l23 = ListNode(9)
    l22.next = l23
    val l24 = ListNode(9)
    l23.next = l24
    val l25 = ListNode(9)
    l24.next = l25
    val l26 = ListNode(9)
    l25.next = l26
    val l27 = ListNode(9)
    l26.next = l27
    addTwoNumbers(l11, l21)?.print()

}


fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
    var resultNode = ListNode(0)
    var temp: ListNode? = resultNode

    var temp1 = l1
    var temp2 = l2
    var tempDiff = 0
    while (temp1 != null && temp2 != null) {
        val sum = temp1.`val` + temp2.`val` + tempDiff
        tempDiff = sum / 10
        val diff = (sum) % 10
        temp?.next = ListNode(diff)
        temp = temp?.next
        temp1 = temp1.next
        temp2 = temp2.next
    }
    // 2. Если temp1 еще не закончился (длиннее, чем l2)
    // Исправлено: проверяем temp1 != null, а не temp1?.next
    var remaining = if (temp1 != null) temp1 else temp2

    while (remaining != null) {
        val sum = remaining.`val` + tempDiff
        tempDiff = sum / 10
        val diff = sum % 10

        temp?.next = ListNode(diff)
        temp = temp?.next
        remaining = remaining.next
    }

    // 3. Если после всех сложений остался перенос (например, 99 + 1)
    if (tempDiff != 0) {
        temp?.next = ListNode(tempDiff)
    }

    return resultNode.next
}

class ListNode(var `val`: Int) {
    var next: ListNode? = null

    fun print() {
        var temp = this
        println(this.`val`)
        while (temp.next != null) {
            println(temp.`val`)
            temp = temp.next!!
        }
    }
}