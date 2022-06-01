package me.xamrah.cp.xamrah.store;

public class Customer {
    private String driverLicence; // вод.удостоверение
    private String passport; // паспортные данные
    private String name; // ФИО клиента
    private String address; // Адрес клиента

    public Customer(String driverLicence, String passport, String name, String address) {
        this.driverLicence = driverLicence;
        this.passport = passport;
        this.name = name;
        this.address = address;
    }

    public Customer() {
    }

    public String getDriverLicence() {
        return this.driverLicence;
    }

    public String getPassport() {
        return this.passport;
    }

    public String getName() {
        return this.name;
    }

    public String getAddress() {
        return this.address;
    }

    public void setDriverLicence(String driverLicence) {
        this.driverLicence = driverLicence;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String toString() {
        return "Клиент. ФИО: " + this.getName() + ", Вод. удостоверение: " + this.getDriverLicence() + ", Паспортные данные: " + this.getPassport() + ", Адрес регистрации: " + this.getAddress();
    }
}
