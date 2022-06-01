package me.xamrah.cp.xamrah.model.hashtable;

import me.xamrah.cp.xamrah.store.Vehicle;
import java.util.ArrayList;

public class HashTable {
    private int HASH_TABLE_SIZE;
    private int size;
    private Node[] table;
    private int totalprimeSize;

    // иницализация
    public HashTable(int ts) {
        size = 0;
        HASH_TABLE_SIZE = ts;
        table = new Node[HASH_TABLE_SIZE];
        for (int i = 0; i < HASH_TABLE_SIZE; i++)
            table[i] = null;
        totalprimeSize = getPrime();
    }

    //первая хеш-функция
    private int myHashFirst(String y) {
        int myHashValFirst = y.hashCode();
        myHashValFirst %= HASH_TABLE_SIZE;
        if (myHashValFirst < 0)
            myHashValFirst += HASH_TABLE_SIZE;
        return myHashValFirst;
    }

    // вторая хеш-функция
    private int myHashSecond(String y) {
        int myHashValSecond = y.hashCode();
        myHashValSecond %= HASH_TABLE_SIZE;
        if (myHashValSecond < 0)
            myHashValSecond += HASH_TABLE_SIZE;
        return totalprimeSize - myHashValSecond % totalprimeSize;
    }

    // очистка таблицы
    public void clearHashTable() {
        size = 0;
        table = new Node[HASH_TABLE_SIZE];

        for (int i = 0; i < HASH_TABLE_SIZE; i++)
            table[i] = null;
        totalprimeSize = getPrime();
    }

    // поиск простых чисел
    public int getPrime() {
        for (int i = HASH_TABLE_SIZE - 1; i >= 1; i--) {
            int count = 0;
            for (int j = 2; j * j <= i; j++)
                if (i % j == 0)
                    count++;
            if (count == 0)
                return i;
        }
        return 3;
    }
    // получение размера
    public int getSize() { return size; }
    // проверка пустоты
    public boolean isEmpty() { return size == 0; }

    // получить объект
    public Vehicle get(String key) {
        int hashFirst = myHashFirst(key);
        int hashSecond = myHashSecond(key);

        while (table[hashFirst] != null
                && !table[hashFirst].key.equals(key)) {
            hashFirst += hashSecond;
            hashFirst %= HASH_TABLE_SIZE;
        }

        if (table[hashFirst] == null){
            return null;
        }

        return table[hashFirst].value;
    }

    // вставить объект
    public void insert(String key, Vehicle value) {
        if (size == HASH_TABLE_SIZE) {
            System.out.println("Таблица переполнена! ");
            return;
        }

        int hashFirst = myHashFirst(key);
        int hashSecond = myHashSecond(key);

        while (table[hashFirst] != null) {
            hashFirst += hashSecond;
            hashFirst %= HASH_TABLE_SIZE;
        }

        table[hashFirst] = new Node(key, value);
        size++;
    }

    // удалить объект
    public void remove(String key) {
        int hashFirst = myHashFirst(key);
        int hashSecond = myHashSecond(key);
        while (table[hashFirst] != null
                && !table[hashFirst].key.equals(key)) {
            hashFirst += hashSecond;
            hashFirst %= HASH_TABLE_SIZE;
        }

        table[hashFirst] = null;
        size--;
    }

    //установить доступность авто
    public void setAvailable(String key, boolean flag){
        int hashFirst = myHashFirst(key);
        int hashSecond = myHashSecond(key);
        while (table[hashFirst] != null
                && !table[hashFirst].key.equals(key)) {
            hashFirst += hashSecond;
            hashFirst %= HASH_TABLE_SIZE;
        }

        table[hashFirst].value.setAvailable(flag);
    }

    // получить всю хеш-таблицу
    public ArrayList<Vehicle> getHashTable() {
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        for (int i = 0; i < HASH_TABLE_SIZE; i++)
            if (table[i] != null)
                vehicles.add(table[i].value);
        return vehicles;
    }
}

class Node {
    String key;
    Vehicle value;

    Node(String key, Vehicle value) {
        this.key = key;
        this.value = value;
    }
}