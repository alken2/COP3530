import java.util.*;

public class Assignment3 {
    public static void main(String[] args) {
        //Testing Q1
        LList<Integer> intList = new LList<>();
        intList.append(10);
        intList.append(20);
        intList.append(30);
        intList.append(40);
        intList.append(50);
        System.out.println(intList.second().element());

        //Testing Q2
        AList<Character> charList = new AList<>(20);
        charList.append('a');
        charList.append('b');
        charList.append('c');
        charList.append('d');
        charList.append('e');
        charList.append('f');
        charList.append('g');
        System.out.println(charList.find('d'));
    }
}

class Link<E> {         // Singly linked list node class
    private E e;          // Value for this node
    private Link<E> n;    // Point to next node in list

    // Constructors
    Link(E it, Link<E> inn) { e = it; n = inn; }
    Link(Link<E> inn) { e = null; n = inn; }

    E element() { return e; }                        // Return the value
    E setElement(E it) { return e = it; }            // Set element value
    Link<E> next() { return n; }                     // Return next link
    Link<E> setNext(Link<E> inn) { return n = inn; } // Set next link
}

// Linked list implementation
class LList<E> implements List<E> {
    private Link<E> head;      // Pointer to list header
    private Link<E> tail;      // Pointer to last element
    private Link<E> curr;      // Access to current element
    private int listSize;      // Size of list

    // Constructors
    LList(int size) { this(); }     // Constructor -- Ignore size
    LList() { clear(); }

    // Remove all elements
    public void clear() {
        curr = tail = new Link<E>(null); // Create trailer
        head = new Link<E>(tail);        // Create header
        listSize = 0;
    }

    //1 [QUESTION 1]
    //Write a Java member method for the singly linked list class (discussed in class and in openDSA) that returns the second-to-last node in a singly linked list. You can use all member data and methods from the SinglyLinkedList class. Note that your method shouldn't change the state of the linked list.
    //COPY AND PASTE THIS METHOD AND REMEMBER TO WRITE ITS TIME COMPLEXITY
    public Link<E> second() {
        Link<E> sec = new Link<E>(head);
        while (sec.next().next() != tail) sec = sec.next();
        return sec;
    }

    //Everything here is just used to allow this class to not be abstract

    @Override
    public E get(int index) {
        return null;
    }

    @Override
    public E set(int index, E element) {
        return null;
    }

    @Override
    public void add(int index, E element) {

    }

    @Override
    public E remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }

    // Insert "it" at current position
    public boolean insert(E it) {
        curr.setNext(new Link<E>(curr.element(), curr.next()));
        curr.setElement(it);
        if (tail == curr) tail = curr.next();  // New tail
        listSize++;
        return true;
    }

    // Append "it" to list
    public boolean append(E it) {
        tail.setNext(new Link<E>(null));
        tail.setElement(it);
        tail = tail.next();
        listSize++;
        return true;
    }

    // Remove and return current element
    public E remove () {
        if (curr == tail) return null;          // Nothing to remove
        E it = curr.element();                  // Remember value
        curr.setElement(curr.next().element()); // Pull forward the next element
        if (curr.next() == tail) tail = curr;   // Removed last, move tail
        curr.setNext(curr.next().next());       // Point around unneeded link
        listSize--;                             // Decrement element count
        return it;                              // Return value
    }

    public void moveToStart() { curr = head.next(); } // Set curr at list start
    public void moveToEnd() { curr = tail; }     // Set curr at list end

    // Move curr one step left; no change if now at front
    public void prev() {
        if (head.next() == curr) return; // No previous element
        Link<E> temp = head;
        // March down list until we find the previous element
        while (temp.next() != curr) temp = temp.next();
        curr = temp;
    }

    // Move curr one step right; no change if now at end
    public void next() { if (curr != tail) curr = curr.next(); }

    public int length() { return listSize; } // Return list length


    // Return the position of the current element
    public int currPos() {
        Link<E> temp = head.next();
        int i;
        for (i=0; curr != temp; i++)
            temp = temp.next();
        return i;
    }

    // Move down list to "pos" position
    public boolean moveToPos(int pos) {
        if ((pos < 0) || (pos > listSize)) return false;
        curr = head.next();
        for(int i=0; i<pos; i++) curr = curr.next();
        return true;
    }

    // Return true if current position is at end of the list
    public boolean isAtEnd() { return curr == tail; }

    // Return current element value. Note that null gets returned if curr is at the tail
    public E getValue() {
        return curr.element();
    }

    public String toString() {
        Link<E> temp = head.next();
        StringBuffer out = new StringBuffer((listSize + 1) * 4);

        out.append("< ");
        for (int i = 0; i < currPos(); i++) {
            out.append(temp.element());
            out.append(" ");
            temp = temp.next();
        }
        out.append("| ");
        for (int i = currPos(); i < listSize; i++) {
            out.append(temp.element());
            out.append(" ");
            temp = temp.next();
        }
        out.append(">");
        return out.toString();
    }

    @Override
    public int size() {
        return 0;
    }

    //Tell if the list is empty or not
    public boolean isEmpty() {
        return listSize == 0;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(E e) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }
}

class AList<E> implements List<E> {
    private E listArray[];                  // Array holding list elements
    private static final int DEFAULT_SIZE = 10; // Default size
    private int maxSize;                    // Maximum size of list
    private int listSize;                   // Current # of list items
    private int curr;                       // Position of current element

    // Constructors
    // Create a new list object with maximum size "size"
    @SuppressWarnings("unchecked") // Generic array allocation
    AList(int size) {
        maxSize = size;
        listSize = curr = 0;
        listArray = (E[])new Object[size];         // Create listArray
    }
    // Create a list with the default capacity
    AList() { this(DEFAULT_SIZE); }          // Just call the other constructor

    public void clear()                     // Reinitialize the list
    { listSize = curr = 0; }              // Simply reinitialize values

    @Override
    public E get(int index) {
        return null;
    }

    @Override
    public E set(int index, E element) {
        return null;
    }

    @Override
    public void add(int index, E element) {

    }

    @Override
    public E remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }

    // Insert "it" at current position
    public boolean insert(E it) {
        if (listSize >= maxSize) return false;
        for (int i=listSize; i>curr; i--)  // Shift elements up
            listArray[i] = listArray[i-1];   //   to make room
        listArray[curr] = it;
        listSize++;                        // Increment list size
        return true;
    }

    // Append "it" to list
    public boolean append(E it) {
        if (listSize >= maxSize) return false;
        listArray[listSize++] = it;
        return true;
    }

    // Remove and return the current element
    public E remove() {
        if ((curr<0) || (curr>=listSize))  // No current element
            return null;
        E it = listArray[curr];            // Copy the element
        for(int i=curr; i<listSize-1; i++) // Shift them down
            listArray[i] = listArray[i+1];
        listSize--;                        // Decrement size
        return it;
    }

    public void moveToStart() { curr = 0; }       // Set to front
    public void moveToEnd() { curr = listSize; }  // Set at end
    public void prev() { if (curr != 0) curr--; } // Move left
    public void next() { if (curr < listSize) curr++; } // Move right
    public int length() { return listSize; }      // Return list size
    public int currPos() { return curr; }         // Return current position

    // Set current list position to "pos"
    public boolean moveToPos(int pos) {
        if ((pos < 0) || (pos > listSize)) return false;
        curr = pos;
        return true;
    }

    // Return true if current position is at end of the list
    public boolean isAtEnd() { return curr == listSize; }

    // Return the current element
    public E getValue() {
        if ((curr < 0) || (curr >= listSize)) // No current element
            return null;
        return listArray[curr];
    }

    public String toString() {
        StringBuffer out = new StringBuffer((listSize + 1) * 4);

        out.append("< ");
        for (int i = 0; i < curr; i++) {
            out.append(listArray[i]);
            out.append(" ");
        }
        out.append("| ");
        for (int i = curr; i < listSize; i++) {
            out.append(listArray[i]);
            out.append(" ");
        }
        out.append(">");
        return out.toString();
    }

    //2 [QUESTION 2]
    //Write a Java member method for the Array-based list class (discussed in class and in openDSA) that finds the index of a given value in the list. You can use all member data and methods from the array-based list class. Note that your method shouldn't change the state of the list.
    //REMEMBER TO REMOVE STATIC KEYWORD AND WRITE THE TIME COMPLEXITY WHEN TURNING IN ASSIGNMENT
    public int find(E e) {
        for (int i = 0; i < listSize; i++) if (e == listArray[i]) return i;
        return -1;
    }

    //Blah blah blah not an abstract class blah blah blah

    @Override
    public int size() {
        return 0;
    }

    //Tell if the list is empty or not
    public boolean isEmpty() {
        return listSize == 0;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(E e) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }
}
