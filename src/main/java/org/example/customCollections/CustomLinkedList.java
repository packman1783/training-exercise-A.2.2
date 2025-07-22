package org.example.customCollections;

public class CustomLinkedList<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size = 0;

    private static class Node<E> {
        E data;
        Node<E> prev;
        Node<E> next;

        Node(E data) {
            this.data = data;
        }
    }

    public boolean add(E value) {
        Node<E> newNode = new Node<>(value);

        if(head == null) {
            tail = head = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;

        return true;
    }

    public boolean addAll(CustomLinkedList<E> other) {
        for (int i = 0; i < other.size(); i++) {
            this.add(other.get(i));
        }

        return true;
    }

    public E get(int index) {
        checkIndex(index);

        Node<E> current = getNode(index);

        return current.data;
    }

    public boolean remove(int index) {
        checkIndex(index);
        Node<E> toRemove = getNode(index);

        if (toRemove.prev != null) {
            toRemove.prev.next = toRemove.next;
        } else {
            head = toRemove.next;
        }

        if (toRemove.next != null) {
            toRemove.next.prev = toRemove.prev;
        } else {
           tail = toRemove.prev;
        }
        size--;

        return true;
    }

    private Node<E> getNode(int index) {
        Node<E> current;

        current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        return current;
    }

    public int size() {
        return size;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
    }
}
