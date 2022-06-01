package me.xamrah.cp.xamrah.model.avltree;

import me.xamrah.cp.xamrah.store.Customer;
import java.util.ArrayList;

public class AvlTree {
    public Node root;

    // вставить объект
    private Node insert(Node node, Customer customer){
        if(node == null) return new Node(customer);
        String nodeName = node.curCustomer.getDriverLicence();
        String docName = customer.getDriverLicence();

        if(nodeName.compareTo(docName) > 0)
            node.prevNode = insert(node.prevNode, customer);
        else if(nodeName.compareTo(docName) < 0)
            node.nextNode = insert(node.nextNode, customer);
        else throw new RuntimeException("Дуплированние ключа");
        return balance(node);
    }

    // удалить объект
    private Node delete(Node node, Customer curCustomer){
        if(node == null) return null;
        String licenceOne = node.curCustomer.getDriverLicence();
        String licenceTwo = curCustomer.getDriverLicence();

        if(licenceOne.compareTo(licenceTwo) > 0)
            node.prevNode = delete(node.prevNode, curCustomer);
        else if(licenceOne.compareTo(licenceTwo) < 0)
            node.nextNode = delete(node.nextNode, curCustomer);
        else {
            if(node.prevNode == null || node.nextNode == null)
                node = (node.prevNode == null) ? node.nextNode : node.prevNode;
            else{
                Node leftLeaf = mostLeftLeaf(node.nextNode);
                node.curCustomer = leftLeaf.curCustomer;
                node.nextNode = delete(node.nextNode, node.curCustomer);
            }
        }
        if (node != null){
            node = balance(node);
        }
        return node;
    }

    // балансировка
    private Node balance(Node node){
        updateHeight(node);
        int currentBalance = getBalance(node);
        if(currentBalance > 1){
            if (height(node.nextNode.nextNode) <= height(node.nextNode.prevNode)) {
                node.nextNode = rotateRight(node.nextNode);
            }
            node = rotateLeft(node);
        } else if(currentBalance < -1){
            if (height(node.prevNode.prevNode) <= height(node.prevNode.nextNode)) {
                node.prevNode = rotateLeft(node.prevNode);
            }
            node = rotateRight(node);
        }
        return node;
    }

    // поиск самого левого листа
    private Node mostLeftLeaf(Node node){
        Node currentNode = node;
        while (currentNode.prevNode != null)
            currentNode = currentNode.prevNode;
        return currentNode;
    }

    // левый поворот
    private Node rotateLeft(Node node){
        Node tempRight = node.nextNode;
        Node tempLeft = tempRight.prevNode;
        tempRight.prevNode = node;
        node.nextNode = tempLeft;
        updateHeight(node);
        updateHeight(tempRight);
        return tempRight;
    }

    // правый поворот
    private Node rotateRight(Node node){
        Node tempLeft = node.prevNode;
        Node tempRight = tempLeft.nextNode;
        tempLeft.nextNode = node;
        node.prevNode = tempRight;
        updateHeight(node);
        updateHeight(tempLeft);
        return tempLeft;
    }

    // вывести дерево
    private void print(Node node, int level) {
        if (node != null) {
            print(node.nextNode,level+1);
            for (int i=0;i<level;i++) {
                System.out.print("\t");
            }
            System.out.println(node.curCustomer.getDriverLicence());
            print(node.prevNode,level+1);
        }
    }

    // высота
    private int height(Node node){
        return node == null ? -1 : node.height;
    }
    // обновление высоты
    private void updateHeight(Node node){
        node.height = 1 + Math.max(height(node.prevNode), height(node.nextNode));
    }

    // Публичные методы
    public ArrayList<Customer> customers = new ArrayList<>();

    // обратный обход дерева
    public ArrayList<Customer> backBypass(Node node){
        if ( node.prevNode != null) backBypass(node.prevNode);
        if ( node.nextNode != null ) backBypass(node.nextNode);
        customers.add(node.curCustomer);
        return customers;
    }

    // найти объект
    public Customer find(String key){
        Node current = root;
        if (root == null){
            return null;
        }
        while (current != null){
            String currentName = current.curCustomer.getDriverLicence();
            if(current.curCustomer.getDriverLicence().equals(key))
                break;
            current = currentName.compareTo(key) < 0 ? current.nextNode : current.prevNode;
        }
        if (current == null){
            return null;
        }
        if (current.curCustomer == null) {
            return null;
        }

        return current.curCustomer;
    }

    // вставить объект
    public void insert(Customer customer){
        root = insert(root, customer);
    }

    // удалить объект
    public void delete(Customer customer){
        root = delete(root, customer);
    }

    // очистить дерево
    public void clear(){ root = null;}

    public Node getRoot() {
        return root;
    }

    public int height(){
        return root == null ? - 1 : root.height;
    }

    public int getBalance(Node node){
        return (node == null) ? 0 : height(node.nextNode) - height(node.prevNode);
    }

    // поиск по фрагментам
    public ArrayList<Customer> contains(String match){
        match = match.toLowerCase();
        ArrayList<Customer> listCustomer = new ArrayList<>();

        for(Customer customer : backBypass(root) ){
            if(contains((customer.getName().toLowerCase() + customer.getAddress().toLowerCase()), match))
                listCustomer.add(customer);
        }
        return listCustomer;
    }

    private boolean contains(String pool, String match){
        char[] poolArray = pool.toCharArray();
        char[] matchArray = match.toCharArray();
        int firstEntry = -1;
        int j;
        for(int i = 0; i <= poolArray.length - matchArray.length; i++){
            j = 0;
            while (j < matchArray.length && (matchArray[j] == poolArray[i + j]))
                j++;
            if (j == matchArray.length) firstEntry = i;
        }
        return firstEntry != -1;
    }
}

class Node {

    Customer curCustomer;
    int height;
    Node nextNode;
    Node prevNode;

    public Node(Customer curCustomer) {
        this.curCustomer = curCustomer;
    }
}
