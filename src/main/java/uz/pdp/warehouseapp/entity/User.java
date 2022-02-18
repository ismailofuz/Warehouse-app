package uz.pdp.warehouseapp.entity;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false,unique = true)
    private String phoneNumber;

    @Column(nullable = false,unique = true)
    private String email;

    private UUID code=UUID.randomUUID();

    @Column(nullable = false)
    private String password;

    private boolean active=true;

}
