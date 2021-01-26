public class SinglyLinkedList<E> {
    //Nested Class
    private static class Node<E> {
        //Data Fields
        private E[] arr = (E[])new Object[8];
        private Node<E> n;

        //Constructors
        Node(E it, Node<E> inn) {
            arr[0] = it;
            n = inn;
        }
        Node(Node<E> inn) {
            n = inn;
        }

        //Methods
        E[] getArray() {
            return arr;
        }
        E getElement(int i) {
            return arr[i];
        }
        Node<E> getNext() {
            return n;
        }
        E setElement(E it, int i) {
            arr[i] = it;
            return it;
        }
        Node<E> setNext(Node<E> inn) {
            return n = inn;
        }
    }

    //Data Fields
    private Node<E> head;
    private Node<E> tail;
    private Node<E> curr;
    private int arrCounter;
    private int listSize;

    //Constructors
    SinglyLinkedList(int size) { this(); }
    SinglyLinkedList() { clear(); }

    //Methods
    public void clear() {
        curr = tail = new Node<>(null);
        head = new Node<>(tail);
        listSize = 0;
        arrCounter = 0;
    }

    public boolean insert(E it) {
        if (arrCounter == curr.getArray().length) {
            arrCounter = 0;
            curr.setNext(new Node<>(it, curr.getNext()));
            if (tail == curr) tail = curr.getNext();
            next();
            listSize++;
        }
        else {
            curr.setElement(it, arrCounter);
        }
        arrCounter++;
        return true;
    }

    public E remove () {
        if (head == tail) {
            return null;
        }
        if (arrCounter == curr.getArray().length) {
            next();
        }
        moveToStart();
        arrCounter = 0;
        while (curr.getElement(arrCounter) == null) {
            arrCounter++;
        }
        E it = curr.getElement(arrCounter);
        if (arrCounter == curr.getArray().length - 1) {
            head = head.getNext();
            moveToStart();
            listSize--;
        }
        else {
            curr.setElement(null, arrCounter);
        }
        arrCounter++;
        return it;
    }

    public void moveToStart() { curr = head.getNext(); } // Set curr at list start

    public void next() { if (curr != tail) curr = curr.getNext(); }

    public int length() {
        if (arrCounter == 0 && listSize == 0) {
            return 0;
        }
        else {
            return listSize + 1;
        }
    }

    public boolean moveToPos(int pos) {
        if ((pos < 0) || (pos > listSize)) return false;
        curr = head.getNext();
        for(int i = 0; i < pos; i++) curr = curr.getNext();
        return true;
    }

    public E getValue(int num) {
        int i = num % curr.getArray().length;
        moveToPos(num / curr.getArray().length);
        return curr.getElement(i);
    }
}