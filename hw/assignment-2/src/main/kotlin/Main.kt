fun main(){
    val s = DoublyLinkedList<String>()
    s.pushFront("hello")
    s.pushBack("bye")
    s.pushFront("my")
    println(s.isEmpty())
    println(s.peekFront())
    println(s.peekBack())
}