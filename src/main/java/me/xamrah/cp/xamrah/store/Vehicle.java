package me.xamrah.cp.xamrah.store;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Vehicle {
    private String licencePlate;
    private String brand;
    private String colour;
    private Integer releaseYear;
    private Boolean available;
}
