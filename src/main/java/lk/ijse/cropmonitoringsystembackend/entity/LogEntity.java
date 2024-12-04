package lk.ijse.cropmonitoringsystembackend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LogEntity {

    @Id
    private String logCode;

    private LocalDate logDate;
    private String logDetail;
    private String observedImage;
}
