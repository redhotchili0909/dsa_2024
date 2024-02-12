import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
class Assignment2Test {

    @Test
    fun testDLL(){
        val s = DoublyLinkedList<String>()
        s.pushFront("hello")
        s.pushBack("world")
        assertEquals(s.peekFront(),"hello")
        assertEquals(s.peekBack(),"world")
        assertEquals(s.isEmpty(),false)
        s.popFront()
        assertEquals(s.peekFront(),"world")
        s.popBack()
        assertEquals(s.peekBack(),null)
        assertEquals(s.isEmpty(),true)
    }

    @Test
    fun testStack(){
        val stack =  MyStack<Int>()
        stack.push(1)
        stack.push(2)
        stack.push(3)
        assertEquals(stack.peek(),3)
        assertEquals(stack.isEmpty(),false)
        stack.pop()
        assertEquals(stack.peek(),2)
        stack.pop()
        stack.pop()
        assertEquals(stack.isEmpty(),true)
    }

    @Test
    fun testQueue(){
        val queue =  MyQueue<Int>()
        queue.enqueue(3)
        queue.enqueue(2)
        queue.enqueue(1)
        assertEquals(queue.peek(),1)
        assertEquals(queue.isEmpty(),false)
        queue.dequeue()
        assertEquals(queue.peek(),2)
        queue.dequeue()
        queue.dequeue()
        assertEquals(queue.isEmpty(),true)
    }
}