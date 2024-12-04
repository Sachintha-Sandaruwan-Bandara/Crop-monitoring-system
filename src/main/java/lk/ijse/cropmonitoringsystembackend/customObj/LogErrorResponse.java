package lk.ijse.cropmonitoringsystembackend.customObj;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LogErrorResponse implements LogResponse{
    private int errorCode;
    private String errorMessage;
}
