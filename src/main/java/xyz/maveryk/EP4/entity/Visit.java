package xyz.maveryk.EP4.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_vitas")
public class Visit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100)
    private String local;

    @ManyToOne
    @JoinColumn(name = "dni", referencedColumnName = "dni")
    private Affiliate affiliate;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha")
    private Date date;

    @Column(name = "hora", columnDefinition = "TIME")
    private LocalTime time;

}
