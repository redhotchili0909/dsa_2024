fun main(){
    /*
    Implementation of Problem 3
     */
    val original = MyStack<Int>()
    val temp = MyStack<Int>()
    original.push(3)
    original.push(2)
    original.push(1)

    println("Top of the Original Stack")
    println(original.peek())

    while (!original.isEmpty()) {
        temp.push(original.pop()!!)
    }

    println("Order is reversed!")
    println(temp.peek())
}