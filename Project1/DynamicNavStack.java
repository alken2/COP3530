
@SuppressWarnings("unchecked")
public class DynamicNavStack<E> {
    //Data Fields
    private E[] navStack;
    private int undoI;
    private int redoI;
    private int initialCapacity;
    private int capacity;
    private static final int DEFAULT_CAPACITY = 2;

    //Constructors
    DynamicNavStack(int cap) {
        if (cap > 0) {
            capacity = initialCapacity = cap;
        }
        else {
            capacity = initialCapacity = DEFAULT_CAPACITY;
        }
        redoI = capacity - 1;
        navStack = (E[])new Object[initialCapacity];
    }

    DynamicNavStack() {
        capacity = initialCapacity = DEFAULT_CAPACITY;
        redoI = capacity - 1;
        navStack = (E[])new Object[initialCapacity];
    }

    //Methods
    public int size() {
        return undoI + (capacity - redoI - 1);
    }

    public boolean canUndo() {
        return undoI > 0;
    }

    public boolean canRedo() {
        return redoI < capacity - 1;
    }

    public E undoTop() {
        if (undoI == 0) {
            return null;
        }
        else {
            return navStack[0];
        }
    }

    public E redoTop() {
        if (redoI == capacity - 1) {
            return null;
        }
        else {
            return navStack[capacity - 1];
        }
    }

    public boolean isEmpty() {
        return undoI + (capacity - redoI - 1) == 0;
    }

    public int capacity() {
        return capacity;
    }

    public void push(E e) {
        for (redoI = undoI; redoI < capacity - 1; redoI++) {
            navStack[redoI] = null;
        }
        navStack[redoI] = null;
        navStack[undoI++] = e;
        if (size() == capacity) {
            resize(capacity * 2);
        }
        else if (size() <= capacity / 4) {
            resize(capacity / 2);
        }
    }

    public E undo() {
        if (undoI > 0) {
            navStack[redoI--] = navStack[--undoI];
            E undone = navStack[undoI];
            navStack[undoI] = null;
            return undone;
        }
        return null;
    }

    public E redo() {
        if (redoI < capacity - 1) {
            navStack[undoI++] = navStack[++redoI];
            E redone = navStack[redoI];
            navStack[redoI] = null;
            return redone;
        }
        return null;
    }

    private void resize(int newSize) {
        if (newSize < initialCapacity) {
            return;
        }
        capacity = newSize;
        redoI = capacity - 1;
        E[] temp = (E[]) new Object[capacity];
        if (navStack.length < temp.length) {
            System.arraycopy(navStack, 0, temp, 0, navStack.length);
        }
        else {
            System.arraycopy(navStack, 0, temp, 0, temp.length);
        }
        navStack = temp.clone();
    }

    //Provided Methods
    public String toString() {
        String ret = "Array Looks Like this: [";
        for (int i=0; i<capacity; i++)
            if (navStack[i] != null)
                ret += navStack[i].toString() + " ";
            else
                ret += "null ";
        ret += "]\n";
        ret += "undo stack: [";
        for (int i=0; i<undoI; i++)
            ret += navStack[i].toString() + " ";
        ret += "]\n";
        ret += "redo stack: [";
        for (int i=capacity-1; i>redoI; i--)
            ret += navStack[i].toString() + " ";
        ret += "]";
        return ret;
    }
}
