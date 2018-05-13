package utils;

/**
 * Created by magic on 12/05/2018.
 */

public class MyPair<K, V> {

    private K k;

    private V v;

    public MyPair(K k, V v) {
        this.k = k;
        this.v = v;
    }

    public K getKey() {
        return k;
    }

    public V getValue() {
        return v;
    }

}
