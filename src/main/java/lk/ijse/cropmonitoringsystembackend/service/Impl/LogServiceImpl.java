package lk.ijse.cropmonitoringsystembackend.service.Impl;

import jakarta.transaction.Transactional;
import lk.ijse.cropmonitoringsystembackend.customObj.LogErrorResponse;
import lk.ijse.cropmonitoringsystembackend.customObj.LogResponse;
import lk.ijse.cropmonitoringsystembackend.dao.LogDAO;
import lk.ijse.cropmonitoringsystembackend.dto.LogDTO;
import lk.ijse.cropmonitoringsystembackend.entity.LogEntity;
import lk.ijse.cropmonitoringsystembackend.exception.*;
import lk.ijse.cropmonitoringsystembackend.service.LogService;
import lk.ijse.cropmonitoringsystembackend.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class LogServiceImpl implements LogService {
    private final LogDAO logDAO;
    private final Mapping mapping;
    @Autowired
    public LogServiceImpl(Mapping mapping,LogDAO logDAO){
        this.logDAO=logDAO;
        this.mapping=mapping;
    }
    @Override
    public void saveLog(LogDTO logDTO) {
        if (logDTO.getLogDetail() == null) {
            throw new InvalidFieldDataException("Log detail are required.");
        }
        LogEntity savedLog = logDAO.save(mapping.convertToLogEntity(logDTO));
        if(savedLog == null){
            throw new DataPersistFailedException("Cannot save Log");
        }
    }

    @Override
    public LogResponse getLogByCode(String code) {
        if(logDAO.existsById(code)){
            LogEntity logEntity = logDAO.getLogEntityByLogCode(code);
            return mapping.convertToLogDTO(logEntity);
        }else {
            return new LogErrorResponse(0, "Log not found");
        }
    }

    @Override
    public void deleteLog(String code) {
        if (!logDAO.existsById(code)) {
            throw new NotFoundException("Cannot delete: Log with code " + code + " not found.");
        }
        logDAO.deleteById(code);
    }

    @Override
    public void updateLog(String code, LogDTO updatedLog) {
        Optional<LogEntity> tmpLog = logDAO.findById(code);

        if (tmpLog.isEmpty()) {
            throw new StaffNotFoundException("Log not found");
        } else {
            LogEntity logEntity = mapping.convertToLogEntity(updatedLog);
            logEntity.setLogCode(code);
            logDAO.save(logEntity);
        }
    }

    @Override
    public List<LogDTO> getAllLogs() {
        return mapping.convertLogToDTOList(logDAO.findAll());
    }
}
