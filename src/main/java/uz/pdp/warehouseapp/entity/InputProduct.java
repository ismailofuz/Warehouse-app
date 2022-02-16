package uz.pdp.warehouseapp.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class InputProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date expireDate;
    @Column(nullable = false)
    private Integer amount;
    @Column(nullable = false)
    private double price;
    @ManyToOne
    private Input input;
    @ManyToOne
    private Product product;

}
