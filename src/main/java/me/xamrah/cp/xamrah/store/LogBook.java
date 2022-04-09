package me.xamrah.cp.xamrah.store;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LogBook {
    private String idCustomer;
    private String idVehicle;
    private String issued;
    private String returned;

    public String toString() {
        if (this.getIdCustomer().equals("-1")) {
            return "Не найдено";
        } else {
            return "LogBook(idCustomer=" + this.getIdCustomer() + ", idVehicle=" + this.getIdVehicle() + ", issued=" + this.getIssued() + ", returned=" + this.getReturned() + ")";
        }
    }
}
