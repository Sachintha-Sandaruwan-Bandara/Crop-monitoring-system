package lk.ijse.cropmonitoringsystembackend.dto;

import lk.ijse.cropmonitoringsystembackend.customObj.LogResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LogDTO implements LogResponse {
    private String logCode;
    private LocalDate logDate;
    private String logDetail;
    private String observedImage;
}
