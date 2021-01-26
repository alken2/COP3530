
public class LinkedArrayQueue<E> {
    //Data Fields
    private SinglyLinkedList<E> sll;
    private int front;
    private int back;

    //Constructors
    public LinkedArrayQueue() {
        sll = new SinglyLinkedList<E>();
    }

    //Methods
    public int size() {
        return back - front;
    }

    //return the number of arrays currently storing elements
    public int numArrays() {
        return sll.length();
    }

    //test if the queue is empty
    public boolean isEmpty() {
        return back - front == 0;
    }

    //return the element at the front of the queue. return null if queue is empty
    public E first() {
        if (!isEmpty()) {
            return sll.getValue(front);
        }
        else {
            return null;
        }
    }

    //return the element at the back of the queue. return null if queue is empty
    public E last() {
        if (!isEmpty()) {
            return sll.getValue(back - 1);
        }
        else {
            return null;
        }
    }

    //push e to the back of the queue.
    public void enqueue(E e) {
        back++;
        sll.insert(e);
    }

    //pop and return the element at the front of the queue. return null if queue is empty
    public E dequeue() {
        if (!isEmpty()){
            front++;
            return sll.remove();
        }
        else {
            return null;
        }
    }
}
