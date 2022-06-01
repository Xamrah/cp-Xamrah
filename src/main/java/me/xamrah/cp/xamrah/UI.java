package me.xamrah.cp.xamrah;

import lombok.SneakyThrows;
import me.xamrah.cp.xamrah.sevice.RentalService;
import me.xamrah.cp.xamrah.store.Vehicle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class UI {

    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    RentalService rentalService = new RentalService();

    public void mainMenu() throws IOException {
        while (true) {
            System.out.println("\t\tМеню");
            System.out.println("/---------------------------------\\");
            System.out.println("| 1 | Меню работы с Клиентами     |");
            System.out.println("| 2 | Меню работы с Автопарком    |");
            System.out.println("| 3 | Меню работы с Записями      |");
            System.out.println("| 0 | Выход                       |");
            System.out.println("\\---------------------------------/");
            switch (enterInteger()) {
                case 1 -> customerMenu();
                case 2 -> vehicleMenu();
                case 3 -> logBookMenu();
                case 0 -> System.exit(1);
                default -> System.out.println("Такой команды не сущесвует!");
            }
        }
    }

    private void customerMenu() throws IOException {
        Boolean work = true;
        while (work) {
            System.out.println("/-------------------------------------------------\\");
            System.out.println("| 1 | Зарегистрировать нового Клиента             |");
            System.out.println("| 2 | Удалить Клиента                             |");
            System.out.println("| 3 | Просмотреть всех зарегистрированных клиентов|");
            System.out.println("| 4 | Очистить базу клиентов                      |");
            System.out.println("| 5 | Найти по водительскому удостоверению        |");
            System.out.println("| 6 | Найти по ФИО или адресу                     |");
            System.out.println("| 0 | Вернуться в меню                            |");
            System.out.println("\\------------------------------------------------/");
            switch (enterInteger()) {
                case 1 -> createNewCustomerMenu();
                case 2 -> deleteCustomerMenu();
                case 3 -> showAllCustomerMenu();
                case 4 -> cleanAllCustomerMenu();
                case 5 -> findByDriverLicenceMenu();
                case 6 -> findByNameMenu();
                case 0 -> work = false;
                case 89 -> rentalService.createTestCustomers();
                default -> System.out.println("Такой команды не сущесвует!");
            }
        }
    }

    private void vehicleMenu() throws IOException {
        Boolean work = true;
        while (work) {
            System.out.println("/---------------------------------------------------\\");
            System.out.println("| 1 | Зарегистрировать новое ТС                     |");
            System.out.println("| 2 | Удалить ТС                                    |");
            System.out.println("| 3 | Просмотреть все зарегистрированные ТС         |");
            System.out.println("| 4 | Очистить базу автопарка                       |");
            System.out.println("| 5 | Найти по регистрационным номера               |");
            System.out.println("| 6 | Найти по марке ТС                             |");
            System.out.println("| 0 | Вернуться в меню                              |");
            System.out.println("\\--------------------------------------------------/");
            switch (enterInteger()) {
                case 1 -> createNewVehicleMenu();
                case 2 -> deleteVehicleMenu();
                case 3 -> showAllVehicleMenu();
                case 4 -> cleanAllVehicleMenu();
                case 5 -> findByLicencePlateMenu();
                case 6 -> findByBrandMenu();
                case 0 -> work = false;
                case 89 -> rentalService.createTestVehicles();
                default -> System.out.println("Такой команды не сущесвует!");
            }
        }
    }

    private void logBookMenu() throws IOException {
        Boolean work = true;
        while (work) {
            System.out.println("/-------------------------------------------------\\");
            System.out.println("| 1 | Отправить ТС в ремонт                       |");
            System.out.println("| 2 | Вернуть ТС из ремонта                       |");
            System.out.println("| 3 | Выдать ТС клиенту                           |");
            System.out.println("| 4 | Вернуть ТС в парк                           |");
            System.out.println("| 0 | Вернуться в меню                            |");
            System.out.println("\\------------------------------------------------/");
            System.out.println("Введите команду: ");
            switch (enterInteger()) {
                case 1 -> serviceSendVehicleMenu();
                case 2 -> serviceGetVehicleMenu();
                case 3 -> rentSendVehicleMenu();
                case 4 -> rentGetVehicleMenu();
                case 0 -> work = false;
                case 89 -> rentalService.checkLogBook();
                default -> System.out.println("Такой команды не сущесвует!");
            }
        }
    }

    private void createNewCustomerMenu() {
        System.out.println("/-------Регистрация нового клиента-------\\");
        System.out.print("Введите ФИО: ");
        String name = enterString();
        System.out.print("Введите номер паспорта: ");
        String passport = enterString();
        System.out.println("Напоминаем! Формат ввода: «RR AA NNNNNN», где RR – код региона (цифры); AA – серия (буквы из следующего множества: А, В, Е, К, М, Н, О, Р, С, Т, У, Х);\n NNNNNN – порядковый номер удостоверения (цифры). Код, серия и номер отделяются друг от друга пробелами");
        System.out.print("Введите номер водительского удостоверения: ");
        String drivingLicence = enterString();
        while (!Pattern.matches("^\\d{2}\\s[АВЕКМНОРСТУХавекмнорстух]{2}\\s\\d{6}", drivingLicence)){
            System.out.println("Неверный формат ввода! Формат ввода: «RR AA NNNNNN», где RR – код региона (цифры); AA – серия (буквы из следующего множества: А, В, Е, К, М, Н, О, Р, С, Т, У, Х);\\n NNNNNN – порядковый номер удостоверения (цифры). Код, серия и номер отделяются друг от друга пробелами");
            System.out.print("Повторите попытку: ");
            drivingLicence = enterString();
        }
        if(rentalService.findByDriverLicence(drivingLicence) == 0){
            System.out.println("Данный пользователь уже существует! Начните попытку заново");
            return;
        }

        System.out.println("Введите адрес регистрации/проживания: ");
        String address = enterString();
        switch (rentalService.createNewCustomer(drivingLicence,passport,name,address)){
            case 0 -> System.out.println(name + " " + drivingLicence + " - Добавлен успешно!");
            case 1 -> System.out.println("Что-то пошло не так, повторите попытку!");
        }

    }

    private void deleteCustomerMenu(){
        System.out.println("/-------Удаление пользователя-------\\");
        System.out.print("Введите вод. удостоверение: ");
        switch (rentalService.deleteCustomer(enterString()))
        {
            case 0 -> System.out.println("Удалено успешно!");
            case 1 -> System.out.println("Такого пользователя не существует!");
        };
    }

    private void showAllCustomerMenu(){
        switch (rentalService.showAllCustomer()){
            case 1 -> System.out.println("Нет записей :(");
        }

    }

    private void cleanAllCustomerMenu(){
        switch (rentalService.cleanAllCustomer()){
            case 0 -> System.out.println("Удалено успешно!");
        }
    }

    private void findByDriverLicenceMenu(){
        System.out.println("/-------Поиск пользователя-------\\");
        System.out.print("Введите вод. удостоверение: ");
        switch (rentalService.findByDriverLicence(enterString()))
        {
            case 0 -> {}
            case 1 -> System.out.println("Такого пользователя не существует!");
        };
    }

    private void findByNameMenu() {
        System.out.println("/-------Поиск пользователя-------\\");
        System.out.print("Введите фрагмент ФИО или Адреса: ");
        rentalService.findByName(enterString());
    }

    private void createNewVehicleMenu(){
        System.out.println("/-------Регистрация нового ТС-------\\");
        System.out.println("Напоминаем! Формат ввода: «ANNNAA-NN», где N –цифра; A – буква из следующего множества:\" + \"А, В, Е, К, М, Н, О, Р, С, Т, У, Х\"" );
        System.out.print("Введите регистрационный номер ТС: ");
        String licencePlate = enterString();
        while (!Pattern.matches("^[АВЕКМНОРСТУХавекмнорстух]{1}\\d{3}[АВЕКМНОРСТУХавекмнорстух]{2}-\\d{2}$", licencePlate)){
            System.out.println("Введённый номер не соотвествует формату: \n «ANNNAA-NN», где N –цифра; A – буква из следующего множества:" + "А, В, Е, К, М, Н, О, Р, С, Т, У, Х");
            System.out.print("Повторите попытку: ");
            licencePlate = enterString();
        }

        if(rentalService.findByLicencePlate(licencePlate) == 0){
            System.out.println("Данный ТС уже существует в базе! Повторите попытку с другими данными");
            return;
        }
        System.out.println("Введите марку ТС: ");
        String brand = enterString();
        System.out.println("Введите цвет ТС: ");
        String colour = enterString();
        System.out.println("Введите год выпуска: ");
        Integer releaseYear = enterInteger();
        switch (rentalService.createNewVehicle(licencePlate,brand,colour,releaseYear)){
            case 0 -> System.out.println(brand + " " + licencePlate  +" - Добавлен успешно!");
            case 1 -> System.out.println("Что-то пошло не так, повторите попытку!");
        }
    }

    private void deleteVehicleMenu(){
        System.out.println("/-------Удаление ТС-------\\");
        System.out.print("Введите рег. номер ТС: ");
        switch (rentalService.deleteVehicle(enterString()))
        {
            case 0 -> System.out.println("Удалено успешно!");
            case 1 -> System.out.println("Такого ТС не существует!");
        };
    }

    private void showAllVehicleMenu(){
        switch (rentalService.showAllVehicle()){
            case 1 -> System.out.println("В списке нет ТС");
        }
    }

    private void cleanAllVehicleMenu(){
        rentalService.cleanAllVehicle();
    }

    private void findByLicencePlateMenu() {
        System.out.println("/-------Поиск ТС по рег. номеру-------\\");
        System.out.print("Введите рег. номер ТС: ");
        if(rentalService.findByLicencePlate(enterString()) == 1)
            System.out.println("Не найдено.");
    }

    private void findByBrandMenu() {
        System.out.println("/-------Поиск ТС по модели-------\\");
        System.out.print("Введите модель: ");
        if(rentalService.findByBrand(enterString()) == 1)
            System.out.println("Нет ТС такой модели");
    }

    private void serviceSendVehicleMenu() {
        System.out.println("/-------Отправка ТС на сервис-------\\");
        System.out.println("Выберите ТС, которое хотите отправить на ремонт");
        rentalService.showAllVehicle();
        System.out.print("Введите рег. номер ТС: ");
        String licencePlate = enterString();
        Short currentVehicle = rentalService.findByLicencePlate(licencePlate);
        while (currentVehicle != 0){
            if (licencePlate.equals("0")) {return;}
            if (currentVehicle == 1) {
                System.out.println("Такое ТС не найдено! Попробуйте ещё раз! Или введите 0 для выхода");
            } else if (currentVehicle == 2){
                System.out.println("Данное ТС находится в аренде или на сервисе! Выберите другое ТС! Или введите 0 для выхода");
            }
            System.out.print("Введите рег. номер ТС: ");
            licencePlate = enterString();
            currentVehicle = rentalService.findByLicencePlate(licencePlate);
        }

        rentalService.serviceSendVehicle(licencePlate);
    }

    private void serviceGetVehicleMenu() {
        System.out.println("/-------Вернуть ТС из сервис-------\\");
        System.out.println("Выберите ТС, которое хотите вернуть из ремонта");
        if (rentalService.showAllServices() == 1){
            System.out.println("Нет доступных ТС!");
            return;
        }
        System.out.print("Введите рег. номер ТС: ");
        String licencePlate = enterString();
        Short currentVehicle = rentalService.findByLicencePlate(licencePlate);
        while (currentVehicle != 2 || !rentalService.isService(licencePlate)){
            if (licencePlate.equals("0")) {return;}
            if (currentVehicle == 1) {
                System.out.println("Такое ТС не найдено! Попробуйте ещё раз! Или введите 0 для выхода");
            }
            if (currentVehicle == 0 || !rentalService.isService(licencePlate)){
                System.out.println("Данное ТС не находится в сервисе! Выберите другое ТС! Или введите 0 для выхода");
            }
            System.out.print("Введите рег. номер ТС: ");
            licencePlate = enterString();
            currentVehicle = rentalService.findByLicencePlate(licencePlate);

        }

        rentalService.serviceGetVehicle(licencePlate);
    }

    private void rentSendVehicleMenu() {
        System.out.println("/-------Выдача ТС клиенту-------\\");
        System.out.println("Выберите клиента, которому хотите выдать ТС");
        rentalService.showAllCustomer();
        System.out.print("Введите номер его водительского удостоверения: ");
        String driverLicence = enterString();
        while (rentalService.findByDriverLicence(driverLicence) > 0) {
            if (driverLicence.equals("0")) {return;}
            System.out.println("Такой пользователь не найден! Попробуйте ещё раз! Или введите 0 для выхода");
            System.out.print("Введите номер его водительского удостоверения: ");
            driverLicence = enterString();

        }
        System.out.println("Выберите ТС, которое хотите выдать клиенту");
        rentalService.showAllVehicle();
        System.out.print("Введите рег. номер ТС: ");
        String licencePlate = enterString();
        Short currentVehicle = rentalService.findByLicencePlate(licencePlate);
        while (currentVehicle != 0){
            if (currentVehicle == 1) {
                System.out.println("Такое ТС не найдено! Попробуйте ещё раз! Или введите 0 для выхода");
            } else if (currentVehicle == 2){
                System.out.println("Данное ТС недоступно для аренды! Выберите другое ТС! Или введите 0 для выхода");
            }
            System.out.print("Введите рег. номер ТС: ");
            licencePlate = enterString();
            currentVehicle = rentalService.findByLicencePlate(licencePlate);
            if (licencePlate.equals("0")) {return;}
        }
        System.out.print("Внимание! Даты выдачи/получения лишь для внутренного использования пользователем!\nВведите дату выдачи: ");
        String issued = enterString();
        System.out.print("Введи дату возврата: ");
        String returned = enterString();

        rentalService.rentSendVehicle(driverLicence, licencePlate,issued,returned);
    }

    private void rentGetVehicleMenu(){
        System.out.println("/-------Вернуть ТС в автопарк-------\\");
        System.out.println("Выберите ТС, которое хотите вернуть из аренды");
        if(rentalService.showAllRental() == 1){
            System.out.println("Нет арендных ТС!");
            return;
        }
        System.out.print("Введите рег. номер ТС: ");
        String licencePlate = enterString();
        Short currentVehicle = rentalService.findByLicencePlate(licencePlate);
        while (currentVehicle != 2 || rentalService.isService(licencePlate)){
            if (licencePlate.equals("0")){return;}
            if (currentVehicle == 1) {
                System.out.println("Такое ТС не найдено! Попробуйте ещё раз! Или введите 0 для выхода");
            }
            if (rentalService.isService(licencePlate)){
                System.out.println("Данное ТС находится в сервисе! Выберите другое ТС! Или введите 0 для выхода");
            }
            System.out.print("Введите рег. номер ТС: ");
            licencePlate = enterString();
            currentVehicle = rentalService.findByLicencePlate(licencePlate);
        }
        rentalService.rentGetVehicle(licencePlate);
    }


    private String enterString(){
        try {
            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    @SneakyThrows
    private int enterInteger() {
        String button = reader.readLine();
        while (checkInt(button)) {
            System.out.println("Введите число");
            button = reader.readLine();
        }
        return Integer.parseInt(button);
    }

    public boolean checkInt(String data) {
        try {
            Integer.parseInt(data);
            if (Integer.parseInt(data) < 0)
                return true;
            return false;
        } catch (NumberFormatException e) {
        }
        return true;
    }

}
