public class QHT<K, V> {

    public static class KVPair<K, V> {

    /*
    Generic key-value pair class
    */

        K k;
        V v;

        KVPair(K key, V val) {
            k = key;
            v = val;
        }

        public K key() {
            return k;
        }

        public V value() {
            return v;
        }
    }

  /*
    instance variables.
    DO NOT CHANGE, ADD OR REMOVE INSTANCE VARIABLES
  */

    KVPair[] htable;                                  //The Hash table which is an array of KVPairs
    int size;                                         //Number of elements in the hash table
    int initCap;                                      //Initial capacity of the hash table
    static final int DEFAULT_EXP = 2;                 //Default exponent if it's not specified
    final KVPair TOMBSTONE = new KVPair(null, null);  //The Tombstone to be used when deleting an element

    QHT() {
    /*
      ***TO-DO***
      Default constructor
      should initialize the hash table with default capacity
    */
    initCap = 2;
    for (int i = 1; i < DEFAULT_EXP; i++) {
        initCap *= 2;
    }
    htable = new KVPair[initCap];
    }

    QHT(int exp) {
    /*
      ***TO-DO***
      Single-parameter constructor. The capacity of the hash table
      should be 2^exp. if exp < 2, use default exponent.
      initialize size and initCap accordingly
    */
    if (exp < 2) {
        exp = DEFAULT_EXP;
    }
    initCap = 2;
    for (int i = 1; i < exp; i++) {
        initCap *= 2;
    }
    htable = new KVPair[initCap];
    }

    public int size() {
    /*
      ***TO-DO***
      return the number of elements currently stored in the
      hash table. Shouldn't include TOMBSTONES
      Should run in O(1)
    */
        return size;
    }

    public int capacity() {
    /*
      ***TO-DO***
      return the capacity of the hash table
      Should run in O(1)
    */
        return htable.length;
    }

    public boolean isEmpty() {
    /*
      ***TO-DO***
      return true if hash table is empty,
      false otherwise
      Should run in O(1)
    */
        return size == 0;
    }

    public double loadFactor() {
    /*
      ***TO-DO***
      return the load factor of this hash table.
      load factor is the ratio of size to capacity
      Should run in O(1). Note that the return type is double.
    */
        return (double)size / (double)htable.length;
    }

    private int h(K k) {
    /*
      The hash function. returns an integer for an arbitrary key
      Should run in O(1)
    */
        return (k.hashCode() + capacity()) % capacity();
    }

    private int p(K k, int i) {
    /*
      The probe function. returns an integer. i is
      the number of collisions seen so far for the key
      Should run in O(1)
    */
        return i/2 + (i*i)/2 + (i%2);
    }

    public void insert(K k, V v) {
    /*
      ***TO-DO***
      should insert the given key and value as a
      KVPair in the hash table.
      if load factor > 0.5, increase capacity by a factor of 2
    */
        //The main thing
        int i = h(k);
        int z = i;
        int j = 0;
        if (htable[i] != null) {
            if (find(k) != null) {
                return;
            }
        }
        while (htable[i] != null && htable[i] != TOMBSTONE) {
            i = (z + p(k, ++j)) % htable.length;
            if (j == htable.length) {
                throw new IllegalStateException();
            }
        }
        htable[i] = new KVPair(k, v);
        size++;
        //resize table
        if (loadFactor() > 0.5) {
            KVPair[] temp = new KVPair[htable.length * 2];
            for (int y = 0; y < htable.length; y++) {
                if (htable[y] == null || htable[y] == TOMBSTONE) {
                    continue;
                }
                i = (htable[y].key().hashCode() + temp.length) % temp.length;
                z = i;
                j = 0;
                while (temp[i] != null && temp[i] != TOMBSTONE) {
                    i = (z + p(k, ++j)) % temp.length;
                    if (j == temp.length) {
                        throw new IllegalStateException();
                    }
                }
                temp[i] = htable[y];
            }
            htable = temp.clone();
        }
    }

    public V remove(K k) {

    /*
      ***TO-DO***
      if k is found in the hash table, remove KVPair
      and return the value. Otherwise, return null.
      if load factor < 0.25 then reduce capacity in half.
    */
        //the main thing
        int i = h(k);
        int z = i;
        int j = 0;
        while ((K)htable[i].key() != k) {
            i = (z + p(k, ++j)) % htable.length;
            if (j == htable.length) {
                return null;
            }
            while (htable[i] == null) {
                i = (z + p(k, ++j)) % htable.length;
                if (j == htable.length) {
                    return null;
                }
            }
        }
        KVPair remove = htable[i];
        htable[i] = TOMBSTONE;
        size--;
        //resize table
        if (loadFactor() < 0.25 && (htable.length / 2) >= initCap) {
            KVPair[] temp = new KVPair[htable.length / 2];
            for (int y = 0; y < htable.length; y++) {
                if (htable[y] == null || htable[y] == TOMBSTONE) {
                    continue;
                }
                i = (htable[y].key().hashCode() + temp.length) % temp.length;
                z = i;
                j = 0;
                while (temp[i] != null && temp[i] != TOMBSTONE) {
                    i = (z + p(k, ++j)) % temp.length;
                    if (j == temp.length) {
                        throw new IllegalStateException();
                    }
                }
                temp[i] = htable[y];
            }
            htable = temp.clone();
        }
        return (V)remove.value();
    }

    public V find(K k) {
    /*
      ***TO-DO***
      if k is found in the hash table, return the value.
      Otherwise, return null.
    */
        int i = h(k);
        int z = i;
        int j = 0;
        while ((K)htable[i].key() != k) {
            i = (z + p(k, ++j)) % htable.length;
            if (j == htable.length) {
                return null;
            }
            while (htable[i] == null) {
                i = (z + p(k, ++j)) % htable.length;
                if (j == htable.length) {
                    return null;
                }
            }
        }
        return (V)htable[i].value();
    }

    public KVPair get(int i) {
    /*
      return the KVPair at index i of the hash table
    */

        if (i >= capacity())
            return null;

        return htable[i];
    }

    public String toString() {
    /*
      return a string representation of the hash table.
    */

        String ret = "\n\n";

        for (int i = 0; i < capacity(); i++) {
            if (get(i) != null) {
                if (get(i).key() != null)
                    ret += i + "\t" + get(i).key() + "\t->\t" + get(i).value() + "\n";
                else
                    ret += i + "\tTOMBSTONE\n";
            }
            else {
                ret += i + "\tnull\n";
            }
        }

        return ret;
    }
}