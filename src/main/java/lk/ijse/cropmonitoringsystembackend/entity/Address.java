package lk.ijse.cropmonitoringsystembackend.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Data

public class Address {
    private String line1;
    private String line2;
    private String line3;
    private String line4;
    private String line5;
}
