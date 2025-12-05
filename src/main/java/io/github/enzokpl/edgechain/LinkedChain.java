package io.github.enzokpl.edgechain;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedChain<T, R> implements Chain<T, R> {

    private static class InternalNode<T, R> implements Chain.Node<T, R> {
        private final T data;
        private R relationToNext;
        private InternalNode<T, R> next;

        public InternalNode(T data) {
            this.data = data;
        }

        @Override
        public T getData() {
            return data;
        }

        @Override
        public R getRelationToNext() {
            return relationToNext;
        }

        @Override
        public boolean hasNext() {
            return next != null;
        }

        @Override
        public T getNextData() {
            return (next != null) ? next.data : null;
        }
    }

    private InternalNode<T, R> head;
    private InternalNode<T, R> tail;
    private int size = 0;

    @Override
    public void addFirst(T data) {
        if (head != null) {
            throw new IllegalStateException("Chain already started. Use addNext.");
        }
        InternalNode<T, R> n = new InternalNode<>(data);
        this.head = n;
        this.tail = n;
        this.size++;
    }

    @Override
    public void addNext(R relation, T data) {
        if (head == null) {
            throw new IllegalStateException("Chain is empty. Use addFirst.");
        }
        InternalNode<T, R> n = new InternalNode<>(data);
        this.tail.relationToNext = relation;
        this.tail.next = n;
        this.tail = n;
        this.size++;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isValid(ChainValidator<T, R> validator) {
        if (head == null) {
            return true;
        }
        InternalNode<T, R> current = head;
        while (current.next != null) {
            if (!validator.validate(current.data, current.relationToNext, current.next.data)) {
                return false;
            }
            current = current.next;
        }
        return true;
    }

    @Override
    public Iterator<Chain.Node<T, R>> iterator() {
        return new Iterator<>() {
            private InternalNode<T, R> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public Chain.Node<T, R> next() {
                if (current == null) {
                    throw new NoSuchElementException();
                }
                InternalNode<T, R> temp = current;
                current = current.next;
                return temp;
            }
        };
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        for (Chain.Node<T, R> node : this) {
            sb.append(node.getData());
            if (node.hasNext()) {
                sb.append(" --[").append(node.getRelationToNext()).append("]--> ");
            }
        }
        return sb.toString();
    }
}