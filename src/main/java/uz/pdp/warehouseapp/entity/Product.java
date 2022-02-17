package uz.pdp.warehouseapp.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false,unique = true)
    private String name;
    @Column(unique = true)
    private String code = String.valueOf(UUID.randomUUID());
    private boolean active=true;
    @ManyToOne
    private Category category;
    @ManyToOne
    private Measurement measurement;
    @OneToMany
    @ToString.Exclude
    private List<Attachment>attachments;
}
