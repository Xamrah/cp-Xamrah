package me.xamrah.cp.xamrah.model.ciclelist;

import me.xamrah.cp.xamrah.store.LogBook;

public class CicleList {
    private Node head = null;
    private Node tail = null;

    public void addNode(LogBook note) {
        Node newNode = new Node(note);

        if (head == null) {
            head = newNode;
        } else {
            tail.nextNode = newNode;
        }

        tail = newNode;
        tail.nextNode = head;
    }

    public void selectionSort() {
        // TODO: Сделать сортировку объектов
    }

    public LogBook containsNode(String searchNote) {

        Node currentNode = head;

        if (head == null) {
            return new LogBook("-1","-1","-1","-1");
        } else {
            do {
                if (currentNode.note.getIdVehicle() == searchNote || currentNode.note.getIdCustomer() == searchNote) {
                    return currentNode.note;
                }
                currentNode = currentNode.nextNode;
            } while (currentNode != head);
            return new LogBook("-1","-1","-1","-1");
        }
    }

    public void deleteNode(String noteToDelete) {
        Node currentNode = head;
        if (head == null) {
            return;
        }
        do {
            Node nextNode = currentNode.nextNode;
            if (nextNode.note.getIdVehicle() == noteToDelete) {
                if (tail == head) {
                    head = null;
                    tail = null;
                } else {
                    currentNode.nextNode = nextNode.nextNode;
                    if (head == nextNode) {
                        head = head.nextNode;
                    }
                    if (tail == nextNode) {
                        tail = currentNode;
                    }
                }
                break;
            }
            currentNode = nextNode;
        } while (currentNode != head);
    }

    public void swap(Node one, Node two) {
        Node prev1, prev2, next1, next2, first;
        first = head;
        prev1 = first;
        prev2 = first;
        if (prev1 == one)
            prev1 = null;
        else {
            while (prev1.nextNode != one)
                prev1 = prev1.nextNode;
        }
        if (prev2 == two)
            prev2 = null;
        else {
            while (prev2.nextNode != two)
                prev2 = prev2.nextNode;
        }
        next1 = one.nextNode;
        next2 = two.nextNode;
        if(two == next1){
            two.nextNode = one;
            one.nextNode = next2;
            if(one != first)
                prev1.nextNode = two;
        } else if(one == next2){
            one.nextNode = two;
            two.nextNode = next1;
            if(two != first)
                prev2.nextNode = one;
        } else {
            if(one != first)
                prev1.nextNode = two;
            two.nextNode = next1;
            if(two != first)
                prev2.nextNode = one;
            one.nextNode = next2;
        }
        if(one == first)
            this.head = two;
        else if(two == head)
            this.head = one;
    }

    public void printList() {
        Node currentNode = head;
        if (head != null) {
            do {
                System.out.println((currentNode.note + " "));
                currentNode = currentNode.nextNode;
            } while (currentNode != head);
        }
    }
}

class Node {

    LogBook note;
    Node nextNode;
    Node prevNode;

    public Node(LogBook note) {
        this.note = note;
    }
}
