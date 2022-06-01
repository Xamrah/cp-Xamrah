package me.xamrah.cp.xamrah.model.ciclelist;

import me.xamrah.cp.xamrah.store.LogBook;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class CicleList {
    private Node head = null;
    private Node tail = null;
    private Integer size = 0;

    // очистить список
    public void clearCicle(){
        head = null;
    }

    // добавить объект
    public void addNode(LogBook note) {
        Node newNode = new Node(note);

        if (head == null) {
            head = newNode;
        } else {
            tail.nextNode = newNode;
            tail.prevNode = newNode;
        }

        tail = newNode;
        tail.nextNode = head;
        tail.prevNode = head;
        size++;
    }

    // получить объект
    public Node getNode(Integer index) {
        Node currentNode = head;
        for (int i = 0; i <= index; i++) {
            currentNode = currentNode.nextNode;
        }
        return currentNode;
    }

    public LogBook get(Integer index) {
        Node currentNode = head;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.nextNode;
        }
        return currentNode.note;
    }

    // получить размер
    public Integer getSize () {return this.size;}

    // сортировка извлечением
    public void selectionSort() {
        for (int i = 0; i < getSize(); i++) {
            Node min = getNode(i);
            for (int j = i + 1; j < getSize(); j++) {
                if (getNode(j).note.getIdCustomer().compareTo(min.note.getIdCustomer()) < 0) {
                    min = getNode(j);
                }
            }
            swap(getNode(i), min);
        }
    }

    // получить объект
    public LogBook containsNode(String searchNote) {
        Node currentNode = head;

        if (head == null) {
            return new LogBook("-1","-1","-1","-1");
        } else {
            do {
                if (currentNode.note.getIdVehicle().equals(searchNote)  || currentNode.note.getIdCustomer().equals(searchNote)) {
                    return currentNode.note;
                }
                currentNode = currentNode.nextNode;
            } while (currentNode != head);
            return new LogBook("-1","-1","-1","-1");
        }
    }

    // удалить объект
    public void deleteNode(String noteToDelete) {
        Node currentNode = head;
        if (head == null) {
            return;
        }
        do {
            Node nextNode = currentNode.nextNode;
            if (nextNode.note.getIdVehicle().equals(noteToDelete) || nextNode.note.getIdCustomer().equals(noteToDelete)) {
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
                size--;
                break;
            }
            currentNode = nextNode;
        } while (currentNode != head);
    }

    // поменять местами объекты
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

    // вывести объекты
    public ArrayList<LogBook> printList() {
        ArrayList<LogBook> logBooks = new ArrayList<>();
        Node currentNode = head;
        if (head != null) {
            do {
                logBooks.add(currentNode.note);
                currentNode = currentNode.nextNode;
            } while (currentNode != head);
        }
        return logBooks;
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
