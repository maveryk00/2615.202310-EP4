package xyz.maveryk.EP4.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.sql.Time;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@Table(name = "tbl_vitas")
public class Visit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100)
    private String local;
    @Column(length = 8, unique = true)
    private String dni;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha")
    private Date date;

    @Temporal(TemporalType.TIME)
    @Column(name = "hora")
    private Time time;

}