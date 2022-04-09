package me.xamrah.cp.xamrah;

import me.xamrah.cp.xamrah.model.avltree.AvlTree;
import me.xamrah.cp.xamrah.model.ciclelist.CicleList;
import me.xamrah.cp.xamrah.store.Customer;
import me.xamrah.cp.xamrah.store.LogBook;

public class Main {
    public static void main(String[] args) {

        LogBook logBook = new LogBook("NNN21MM", "2231341", "0","2");
        LogBook logBook1 = new LogBook("NN2MM", "221341", "0","2");
        LogBook logBook2 = new LogBook("NN21MM", "223141", "0","2");
        LogBook logBook3 = new LogBook("NNN21M", "23141", "0","2");
        CicleList cicleList = new CicleList();
        cicleList.addNode(logBook);
        cicleList.addNode(logBook1);
        cicleList.addNode(logBook2);
        cicleList.addNode(logBook3);
        System.out.println(cicleList.containsNode("221341").toString());
        cicleList.printList();

        Customer customer1 = new Customer("1","II","BOB","Chinchoppnaya11");
        Customer customer2 = new Customer("2","II","BOB","Chinchoppnaya11");
        Customer customer3 = new Customer("3","II","BOB","Chinchoppnaya11");
        Customer customer4 = new Customer("4","II","BOB","Chinchoppnaya11");
        Customer customer5 = new Customer("5","II","BOB","Chinchoppnaya11");
        Customer customer6 = new Customer("6","II","BOB","Chinchoppnaya11");
        Customer customer7 = new Customer("7","II","BOB","Chinchoppnaya11");

        AvlTree avlTree = new AvlTree();
        avlTree.insert(customer1);
        avlTree.insert(customer2);
        avlTree.insert(customer3);
        avlTree.insert(customer4);
        avlTree.insert(customer5);
        avlTree.insert(customer6);
        avlTree.insert(customer7);
        System.out.println(avlTree.find("1").toString());
        avlTree.printBypass();
        System.out.println("");
        avlTree.printTree();
        avlTree.insert(new Customer("9", "II", "Beq", "ddad"));
        System.out.println("******************");
        avlTree.printTree();
        System.out.println("******************");
        avlTree.printTree();
    }
}
