package lk.ijse.cropmonitoringsystembackend.dto;

import lk.ijse.cropmonitoringsystembackend.customObj.CropResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CropDTO implements CropResponse {
    private String cropCode;
    private String commonName;
    private String scientificName;
    private String category;
    private String cropSeason;
    private String cropImage;
}
