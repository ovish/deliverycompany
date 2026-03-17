package com.solvd.deliverycompany.utilities;

public class MyLinkedList<T> {

    private Node<T> head;
    private Node<T> tail;
    private int size;

    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
        }
    }

    public void add(T data) {
        Node<T> newNode = new Node<>(data);

        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }

        size++;
    }

    public void addFirst(T data) {
        Node<T> newNode = new Node<>(data);

        newNode.next = head;
        head = newNode;

        if (tail == null) {
            tail = newNode;
        }

        size++;
    }

    public void insert(int index, T data) {

        checkIndex(index);

        if (index == 0) {
            addFirst(data);
            return;
        }

        if  (index == size) {
            add(data);
            return;
        }

        Node<T> current = head;

        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }

        Node<T> newNode = new Node<>(data);
        newNode.next = current.next;
        current.next = newNode;

        size++;
    }

    public T get(int index) {
        checkIndex(index);

        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        return current.data;
    }

    public void remove(int index) {
        checkIndex(index);

        if (index == 0) {
            head = head.next;
            if (head == null) {
                tail = null;
            }
        } else {
            Node<T> current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }

            current.next = current.next.next;

            if (current.next == null) {
                tail = current;
            }
        }

        size--;
    }

    public int size() {
        return size;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index);
        }
    }

    public void print() {
        Node<T> current = head;
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }

}
