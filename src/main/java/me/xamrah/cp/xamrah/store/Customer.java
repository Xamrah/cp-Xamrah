package me.xamrah.cp.xamrah.store;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Customer {
    private String drivingLicence;
    private String passport;
    private String name;
    private String address;
}
