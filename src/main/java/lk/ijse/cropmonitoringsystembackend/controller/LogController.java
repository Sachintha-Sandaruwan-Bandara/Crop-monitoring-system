package lk.ijse.cropmonitoringsystembackend.controller;

import lk.ijse.cropmonitoringsystembackend.customObj.LogResponse;
import lk.ijse.cropmonitoringsystembackend.dto.LogDTO;
import lk.ijse.cropmonitoringsystembackend.exception.InvalidStaffDataException;
import lk.ijse.cropmonitoringsystembackend.exception.StaffNotFoundException;
import lk.ijse.cropmonitoringsystembackend.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/logs")
public class LogController {
    private final LogService logService;

    @Autowired
    public LogController (LogService logService){
        this.logService=logService;
    }
    @PostMapping
    public ResponseEntity<String> saveLog(@RequestBody LogDTO logDTO) {
        try {
            logService.saveLog(logDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Log saved successfully");
        } catch (InvalidStaffDataException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save log");
        }
    }


    @GetMapping("/{code}")
    public ResponseEntity<LogResponse> getLogByCode(@PathVariable String code) {
        try {
            LogResponse logByCode = logService.getLogByCode(code);
            return ResponseEntity.ok(logByCode);
        } catch (StaffNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    @GetMapping
    public ResponseEntity<List<LogDTO>> getAllLogs() {
        List<LogDTO> allLogs = logService.getAllLogs();
        return ResponseEntity.ok(allLogs);
    }

    @PreAuthorize("!hasRole('ADMINISTRATIVE')")
    @PutMapping("/{code}")
    public ResponseEntity<String> updateLog(@PathVariable String code, @RequestBody LogDTO updatedLog) {
        try {
            logService.updateLog(code,updatedLog);
            return ResponseEntity.ok("Log updated successfully");
        } catch (StaffNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update log");
        }
    }


    @DeleteMapping("/{code}")
    public ResponseEntity<String> deleteLog(@PathVariable String code) {
        try {
            logService.deleteLog(code);
            return ResponseEntity.ok("log deleted successfully");
        } catch (StaffNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete log");
        }
    }
}
