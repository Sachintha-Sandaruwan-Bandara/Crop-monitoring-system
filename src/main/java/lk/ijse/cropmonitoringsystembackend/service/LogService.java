package lk.ijse.cropmonitoringsystembackend.service;
import lk.ijse.cropmonitoringsystembackend.customObj.LogResponse;
import lk.ijse.cropmonitoringsystembackend.dto.LogDTO;

import java.util.List;

public interface LogService {
    public void saveLog(LogDTO logDTO);
    public LogResponse getLogByCode(String code);
    public void deleteLog(String code);
    public void updateLog(String code, LogDTO updatedLog);
    public List<LogDTO> getAllLogs();
}
