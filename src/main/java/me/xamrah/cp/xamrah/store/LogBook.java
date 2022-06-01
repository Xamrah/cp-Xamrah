package me.xamrah.cp.xamrah.store;

public class LogBook {
    private String idCustomer; // вод. удостоверение
    private String idVehicle; // рег. номер авто
    private String issued; // дата выдачи
    private String returned; // дата возврата

    public LogBook(String idCustomer, String idVehicle, String issued, String returned) {
        this.idCustomer = idCustomer;
        this.idVehicle = idVehicle;
        this.issued = issued;
        this.returned = returned;
    }

    public LogBook() {
    }

    public String toString() {
        if (this.getIdCustomer().equals("-1")) {
            return "Неa найдено";
        } else {
            return "Аренда. Клиент: " + this.getIdCustomer() + ", Рег. номер ТС: " + this.getIdVehicle() + ", Дата выдачи: " + this.getIssued() + ", Дата возврата: " + this.getReturned();
        }
    }

    public String getIdCustomer() {
        return this.idCustomer;
    }

    public String getIdVehicle() {
        return this.idVehicle;
    }

    public String getIssued() {
        return this.issued;
    }

    public String getReturned() {
        return this.returned;
    }

    public void setIdCustomer(String idCustomer) {
        this.idCustomer = idCustomer;
    }

    public void setIdVehicle(String idVehicle) {
        this.idVehicle = idVehicle;
    }

    public void setIssued(String issued) {
        this.issued = issued;
    }

    public void setReturned(String returned) {
        this.returned = returned;
    }
}
