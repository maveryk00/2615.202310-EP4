package xyz.maveryk.EP4.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Entity
@AllArgsConstructor
@Table(name = "tbl_clientes")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 8, unique = true)
    private String dni;
    @Column(name = "nombre", length = 100)
    private String name;
    @Column(name = "apellido", length = 100)
    private String lastname;

}