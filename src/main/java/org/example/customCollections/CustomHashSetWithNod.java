package org.example.customCollections;

public class CustomHashSetWithNod<T> {
    private static class Node<T> {
        T key;
        Node<T> next;

        Node(T key) {
            this.key = key;
        }
    }

    private Node<T>[] buckets;
    private int size = 0;
    private int capacity = 16;
    private final double loadFactor = 0.75;

    public CustomHashSetWithNod() {
        this.buckets = (Node<T>[]) new Node[capacity];
    }

    private int hash(T key) {
        return key == null ? 0 : Math.abs(key.hashCode()) % capacity;
    }

    public boolean add(T key) {
        if(contains(key)) {
            return false;
        }

        if ((double) size / capacity >= loadFactor) {
            resize();
        }

        int index = hash(key);
        Node<T> newNode = new Node<>(key);
        newNode.next = buckets[index];
        buckets[index] = newNode;
        size++;

        return true;
    }

    public boolean remove(T key) {
        int index = hash(key);
        Node<T> current = buckets[index];
        Node<T> prev = null;

        while(current != null) {
            if ((key == null && current.key == null) || (key != null && key.equals(current.key))) {
                if (prev == null) {
                    buckets[index] = current.next;
                } else {
                    prev.next = current.next;
                }
                size--;
                return true;
            }
            prev = current;
            current = current.next;
        }

        return false;
    }

    public boolean contains(T key) {
        int index = hash(key);
        Node<T> current = buckets[index];
        while(current != null) {
            if ((key == null && current.key == null) || (key != null && key.equals(current.key))) {
                return true;
            }
            current = current.next;
        }

        return false;
    }

    private void resize() {
        capacity = capacity * 2;
        Node<T>[] oldBuckets = buckets;
        buckets = (Node<T>[]) new Node[capacity];
        size = 0;

        for(Node<T> node : oldBuckets) {
            while(node != null) {
                add(node.key);
                node = node.next;
            }
        }
    }
}
