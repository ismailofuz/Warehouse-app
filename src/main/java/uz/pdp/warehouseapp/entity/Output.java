package uz.pdp.warehouseapp.entity;

import lombok.*;

import javax.persistence.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Output {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date date=Date.valueOf(LocalDate.now());
    private UUID code=UUID.randomUUID();
    private String factureNumber;
    @ManyToOne
    private Warehouse warehouse;
    @ManyToOne
    private Currency currency;
    @ManyToOne
    private Client client;

    public void setFactureNumber(String factureNumber) {
        this.factureNumber = this.warehouse+"_"+this.client+"_"+this.date;
    }
}
