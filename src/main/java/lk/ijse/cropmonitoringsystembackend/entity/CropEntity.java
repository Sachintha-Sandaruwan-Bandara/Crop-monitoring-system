package lk.ijse.cropmonitoringsystembackend.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class CropEntity {
    @Id
    private String cropCode;

    private String commonName;
    private String scientificName;
    private String cropImage;
    private String category;
    private String cropSeason;

}
