package algorithmPatterns.linkedListReversal

fun main() {

    // здесь у меня большие проблемы (стоит поучить получше)
    val node1 = ListNode(4)
    val node2 = ListNode(2)
    val node3 = ListNode(5)
    val node4 = ListNode(6)
    val node5 = ListNode(8)
    val node6 = ListNode(10)

    node1.next = node2
    node2.next = node3
    node3.next = node4
    node4.next = node5
    node5.next = node6

//    val reverseList = reverseList(node1)

    reverseBetween(node1, 2, 5)


    val node11 = ListNode(1)
    val node12 = ListNode(2)
    val node13 = ListNode(3)

    node11.next = node12
    node12.next = node13

    swapPairs(node11)
}

/**
 * Given the head of a singly linked list,
 * reverse the list, and return the reversed list.
 */

class ListNode(var `val`: Int) {
    var next: ListNode? = null
}

fun reverseList(head: ListNode?): ListNode? {

    var prev: ListNode? = null
    var curr = head

    while (curr != null) {
        val nextNode = curr.next
        curr.next = prev
        prev = curr
        curr = nextNode
    }

    return prev
}

/**
 * Given the head of a singly linked list and two integers
 * left and right where left <= right, reverse the nodes
 * of the list from position left to position right, and return the reversed list.
 */

fun reverseBetween(head: ListNode?, left: Int, right: Int): ListNode? {
    // Создаем фиктивный (dummy) узел со значением 0
    // Он нужен, чтобы упростить обработку случая, когда left = 1
    // Теперь у нас всегда есть узел перед головой списка
    val dummy = ListNode(0)
    dummy.next = head

    // Шаг 1: Находим узел ПЕРЕД началом подсписка (prev)
    // prev - это узел, который стоит перед узлом с позицией left
    var prev: ListNode? = dummy

    // Делаем (left - 1) шагов от dummy до узла перед началом подсписка
    // Пример: left = 2, нужно сделать 1 шаг: dummy(0) -> 1 (prev)
    for (i in 1 until left) {
        prev = prev?.next
    }

    // Шаг 2: Начинаем разворот подсписка
    // current - первый узел подсписка, который нужно развернуть
    var current: ListNode? = prev?.next

    // Разворачиваем подсписок длиной (right - left + 1) узлов
    // Классический алгоритм разворота связного списка, но ограниченный
    for (i in 1..(right - left)) {
        // 1. Сохраняем ссылку на следующий узел после current
        val nextNode = current?.next

        // 2. Переставляем указатели:
        // - current.next теперь указывает на следующий узел после nextNode
        // - это нужно, чтобы "вынуть" nextNode из списка
        current?.next = nextNode?.next

        // 3. Вставляем nextNode в начало подсписка (после prev)
        nextNode?.next = prev?.next
        prev?.next = nextNode

        // current остаётся тем же, но теперь он уже не первый в подсписке
        // nextNode стал первым узлом в подсписке
    }

    // Возвращаем реальную голову списка
    // Если left = 1, то dummy.next будет указывать на новый первый узел
    // Если left > 1, то dummy.next по-прежнему указывает на старую голову
    return dummy.next
}


fun swapPairs(head: ListNode?): ListNode? {

    if (head?.next == null) {
        return head
    }

    val dummy = ListNode(0)
    dummy.next = head

    var prev: ListNode? = dummy
    var current = head

    while (current != null && current.next != null) {
        // Сохраняем указатели
        val nextPair = current.next?.next
        val second = current.next


        // Меняем узлы местами
        prev?.next = second
        second?.next = current
        current.next = nextPair

        // Переходим к следующей паре
        prev = current
        current = nextPair
    }

    return dummy.next

}

