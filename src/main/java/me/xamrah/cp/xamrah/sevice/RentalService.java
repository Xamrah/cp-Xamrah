package me.xamrah.cp.xamrah.sevice;

import me.xamrah.cp.xamrah.model.avltree.AvlTree;
import me.xamrah.cp.xamrah.model.ciclelist.CicleList;
import me.xamrah.cp.xamrah.model.hashtable.HashTable;
import me.xamrah.cp.xamrah.store.Customer;
import me.xamrah.cp.xamrah.store.LogBook;
import me.xamrah.cp.xamrah.store.Vehicle;

import java.util.ArrayList;

public class RentalService {
    AvlTree customers = new AvlTree();
    CicleList logbooks = new CicleList();
    HashTable vehicles = new HashTable(1000);

    Customer superService = new Customer("superService","-1","-1","-1");

    public Short createNewCustomer(String drivingLicence, String passport, String name, String address){
        Customer customer = new Customer(drivingLicence, passport, name, address);
        customers.insert(customer);
        return 0;
    }

    public Short deleteCustomer(String drivingLicence){
        Customer customer = customers.find(drivingLicence);
        if (customer == null) {
            return 1;
        }
        if (logbooks.getSize() > 0) {
            for (int i = 0; i <= logbooks.getSize(); i++) {
                if (logbooks.get(i).getIdCustomer().equals(drivingLicence)) {
                    logbooks.deleteNode(drivingLicence);
                    vehicles.setAvailable(logbooks.containsNode(drivingLicence).getIdVehicle(), true);
                }
            }
        }
        customers.delete(customer);
        return 0;
    }

    public Short showAllCustomer(){
        if (customers.root == null){
            customers.customers.clear();
            return 1;
        }
        for (Customer customer: customers.backBypass(customers.root)) {
            System.out.println(customer.toString());
        }
        customers.customers.clear();
        return 0;
    }

    public Short cleanAllCustomer(){
        logbooks.clearCicle();
        customers.clear();
        return 0;
    }

    public Short findByDriverLicence(String driverLicence){
        Customer customer = customers.find(driverLicence);
        if (customer == null) {
            return 1;
        }
        System.out.println(customer.toString());
        System.out.println("/-------Аренды клиента-------\\");
        for (LogBook logbook: logbooks.printList()) {
            if(logbook.getIdCustomer().equals(driverLicence)){
                System.out.println(logbook.toString());
            }
        }
        System.out.println("\\----------------------------/");
        return 0;
    }

    public Short findByName(String search) {
        for (Customer customer: customers.contains(search)) {
            System.out.println(customer.toString());
        }
        customers.customers.clear();
        return 0;
    }

    public Short createNewVehicle(String licencePlate,String brand,String colour, Integer releaseYear){
        Vehicle vehicle = new Vehicle(licencePlate, brand, colour, releaseYear, true);
        vehicles.insert(vehicle.getLicencePlate(),vehicle);
        return 0;
    }

    public Short deleteVehicle(String licencePlate){
        if (vehicles.get(licencePlate) != null){
            logbooks.deleteNode(licencePlate);
            vehicles.remove(licencePlate);
            return 0;
        }
        return 1;
    }

    public Short showAllVehicle(){
        if (!vehicles.isEmpty()) {
            for (Vehicle vehicle:
                    vehicles.getHashTable()) {
                System.out.println(vehicle.toString());
            }
            return 0;
        }
        return 1;
    }

    public Short cleanAllVehicle(){
        logbooks.clearCicle();
        vehicles.clearHashTable();
        return null;
    }

    public Short findByLicencePlate(String licencePlate) {
        Vehicle current = vehicles.get(licencePlate);
        if (current != null){
            System.out.println(current.toString());
            if (!logbooks.containsNode(current.getLicencePlate()).getIdCustomer().equals("-1"))
                System.out.println((logbooks.containsNode(current.getLicencePlate()).getIdCustomer().equals("superService")? "ТС находится в сервисе": "Выдана:\n" + customers.find(logbooks.containsNode(current.getLicencePlate()).getIdCustomer()).toString()));
            if (current.getAvailable()) {
                return 0;
            }
                return 2;
        }
        return 1;
    }

    public Short findByBrand(String brand) {
        ArrayList<Vehicle> vehicleArrayList = vehicles.getHashTable();
        boolean flag = false;
        for (Vehicle vehicle: vehicleArrayList) {
            if(vehicle.getBrand().equals(brand)){
                System.out.println(vehicle.toString());
                flag = true;
            }
        }
        if(flag)
            return 0;
        return 1;
    }

    public Short serviceSendVehicle(String licencePlate) {
        logbooks.addNode(new LogBook(superService.getDriverLicence(), licencePlate, "Бессрочно", "Бессрочно"));
        vehicles.setAvailable(licencePlate, false);
        System.out.println("Вы отправили ТС - " + vehicles.get(licencePlate).getBrand()+ " " + vehicles.get(licencePlate).getLicencePlate() + " в сервис!");
       return 0;
    }

    public Short serviceGetVehicle(String licencePlate) {
        logbooks.deleteNode(licencePlate);
        vehicles.setAvailable(licencePlate, true);
        System.out.println("Вы вернули ТС - " + vehicles.get(licencePlate).getBrand()+ " " + vehicles.get(licencePlate).getLicencePlate() + " из сервсиса!");
        return 0;
    }

    public Short rentSendVehicle(String driverLicence, String licencePlate, String issued, String returned) {
        Customer currentCustomer = customers.find(driverLicence);
        Vehicle currentVehicle = vehicles.get(licencePlate);
        logbooks.addNode(new LogBook(driverLicence,licencePlate,issued, returned));
        vehicles.setAvailable(licencePlate, false);
        System.out.println("Оформлено! Клиенту " + currentCustomer.getName() + " " + currentCustomer.getDriverLicence() + " транспортное средство " + currentVehicle.getBrand() + " " + currentVehicle.getLicencePlate() + "\n Дата выдачи: " + issued + " Дата возврата: " + returned);
        return 0;
    }

    public Short rentGetVehicle(String licencePlate){
        logbooks.deleteNode(licencePlate);
        vehicles.setAvailable(licencePlate, true);
        System.out.println("Вы вернули ТС - " + vehicles.get(licencePlate).getBrand()+ " " + vehicles.get(licencePlate).getLicencePlate() + " из аренды!");
        return 0;
    }

    public Boolean isService(String licencePlate){
        if (logbooks.containsNode(licencePlate).getIdCustomer().equals("superService")){
            return true;
        }
        return false;
    }

    public void createTestCustomers(){
        customers.insert(new Customer("93 УР 100000", "31 31 31331", "Чинчоппа Боб Олегович", "Жукова 24"));
        customers.insert(new Customer("93 УР 100001", "31 31 31332", "Чинчоппа Олег Коджукетович", "Жукова 24"));
        customers.insert(new Customer("93 УР 100002", "31 31 31333", "Чинчоппа Бип Бобович", "Жукова 24"));
        customers.insert(new Customer("93 УР 100003", "31 31 31334", "Чинчоппа Анжелла Оганесовна", "Жукова 24"));
        customers.insert(new Customer("93 УР 100004", "31 31 31335", "Чинчоппа Катя Бобовна", "Жукова 24"));
        customers.insert(new Customer("93 УР 100005", "31 31 31336", "Хамрачев Евгений Евгеньевич", "Жукова 24"));
        customers.insert(new Customer("35 УР 100006", "31 31 31337", "Кононов Андрей Олегович", "Жукова 24"));
        customers.insert(new Customer("98 УР 100004", "31 31 31338", "Конегов Руся Курицович", "Жукова 24"));
    }

    public void createTestVehicles(){
        vehicles.insert("Т336НХ-93", new Vehicle("Т336НХ-93", "Мицубиси Лансер 9", "Светло-серо-зелёный", 2006, true));
        vehicles.insert("Т337НХ-93", new Vehicle("Т337НХ-93", "Мазда 3", "Чёрный", 2007, true));
        vehicles.insert("Т338НХ-93", new Vehicle("Т338НХ-93", "БМВ 318i", "Красный", 2018, true));
        vehicles.insert("Т339НХ-93", new Vehicle("Т339НХ-93", "Мерседес S500", "Чёрный", 2022, true));
        vehicles.insert("Т340НХ-93", new Vehicle("Т340НХ-93", "Т-150", "Металлический", 1984, true));
        vehicles.insert("Т341НХ-93", new Vehicle("Т341НХ-93", "Абобус", "Смешной", 2002, true));
        vehicles.insert("Т342НХ-93", new Vehicle("Т342НХ-93", "Мияги", "Владикавказный", 1993, true));
    }

    public Short showAllServices(){
        boolean flag = false;
        for (LogBook logbook: logbooks.printList()) {
            if(logbook.getIdCustomer().equals("superService")){
                System.out.println(vehicles.get(logbook.getIdVehicle()).toString());
                flag = true;
            }
        }
        if (flag)
            return 0;
        return 1;
    }

    public Short showAllRental(){
        boolean flag = false;
        for (LogBook logbook: logbooks.printList()) {
            if(!logbook.getIdCustomer().equals("superService")){
                System.out.println(vehicles.get(logbook.getIdVehicle()).toString());
                flag = true;
            }
        }
        if(flag)
            return 0;
        return 1;
    }

    public void checkLogBook() {
        System.out.println("/-------Текущий список: -------\\");
        logbooks.selectionSort(1);
        for (LogBook logbook: logbooks.printList()) {
            System.out.println(logbook.toString());
        }
    }

}
