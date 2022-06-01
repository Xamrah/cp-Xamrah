package me.xamrah.cp.xamrah.store;

public class Vehicle {
    private String licencePlate; // рег. номер
    private String brand; // модель
    private String colour; // цвет
    private Integer releaseYear; // год выпуска
    private Boolean available; // наличие

    public Vehicle(String licencePlate, String brand, String colour, Integer releaseYear, Boolean available) {
        this.licencePlate = licencePlate;
        this.brand = brand;
        this.colour = colour;
        this.releaseYear = releaseYear;
        this.available = available;
    }

    public Vehicle() {
    }

    public String getLicencePlate() {
        return this.licencePlate;
    }

    public String getBrand() {
        return this.brand;
    }

    public String getColour() {
        return this.colour;
    }

    public Integer getReleaseYear() {
        return this.releaseYear;
    }

    public Boolean getAvailable() {
        return this.available;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public String toString() {
        return "ТС. Рег. номер: " + this.getLicencePlate() + ", Модель: " + this.getBrand() + ", Цвет: " + this.getColour() + ", Год выпуска: " + this.getReleaseYear() + ", Наличие: " + (this.getAvailable() ? "Доступно" : "Недоступно");
    }
}
