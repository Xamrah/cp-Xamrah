package me.xamrah.cp.xamrah.model.avltree;

import me.xamrah.cp.xamrah.store.Customer;

public class AvlTree {
    public Node root;

    private Node insert(Node node, Customer customer){
        if(node == null) return new Node(customer);
        String nodeName = node.curCustomer.getDrivingLicence();
        String docName = customer.getDrivingLicence();

        if(nodeName.compareTo(docName) > 0)
            node.prevNode = insert(node.prevNode, customer);
        else if(nodeName.compareTo(docName) < 0)
            node.nextNode = insert(node.nextNode, customer);
        else throw new RuntimeException("Дуплированние ключа");
        return balance(node);
    }

    private Node delete(Node node, Customer curCustomer){
        if(node == null) return null;
        String licenceOne = node.curCustomer.getDrivingLicence();
        String licenceTwo = curCustomer.getDrivingLicence();

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

    private Node mostLeftLeaf(Node node){
        Node currentNode = node;
        while (currentNode.prevNode != null)
            currentNode = currentNode.prevNode;
        return currentNode;
    }

    private Node rotateLeft(Node node){
        Node tempRight = node.nextNode;
        Node tempLeft = tempRight.prevNode;
        tempRight.prevNode = node;
        node.nextNode = tempLeft;
        updateHeight(node);
        updateHeight(tempRight);
        return tempRight;
    }

    private Node rotateRight(Node node){
        Node tempLeft = node.prevNode;
        Node tempRight = tempLeft.nextNode;
        tempLeft.nextNode = node;
        node.prevNode = tempRight;
        updateHeight(node);
        updateHeight(tempLeft);
        return tempLeft;
    }

    private void print(Node node, int level) {
        if (node != null) {
            print(node.nextNode,level+1);
            for (int i=0;i<level;i++) {
                System.out.print("\t");
            }
            System.out.println(node.curCustomer.getDrivingLicence());
            print(node.prevNode,level+1);
        }
    }

    private int height(Node node){
        return node == null ? -1 : node.height;
    }

    private void updateHeight(Node node){
        node.height = 1 + Math.max(height(node.prevNode), height(node.nextNode));
    }

    // Публичные методы
    public void backBypass(Node node){
        if ( node.prevNode != null) backBypass(node.prevNode);
        if ( node.nextNode != null ) backBypass(node.nextNode);
        System.out.print(node.curCustomer.getDrivingLicence() + " ");
    }

    public Customer find(String key){
        Node current = root;
        while (current != null){
            String currentName = current.curCustomer.getDrivingLicence();
            if(current.curCustomer.getDrivingLicence().equals(key))
                break;
            current = currentName.compareTo(key) < 0 ? current.nextNode : current.prevNode;
        }
        return current.curCustomer;
    }

    public void printBypass(){
        backBypass(root);
    }

    public void insert(Customer customer){
        root = insert(root, customer);
    }

    public void delete(Customer customer){
        root = delete(root, customer);
    }

    public Node getRoot() {
        return root;
    }

    public int height(){
        return root == null ? - 1 : root.height;
    }

    public void printTree() {
        print(root,0);
    }

    public int getBalance(Node node){
        return (node == null) ? 0 : height(node.nextNode) - height(node.prevNode);
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
