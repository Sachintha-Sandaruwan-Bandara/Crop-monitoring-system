package lk.ijse.cropmonitoringsystembackend.controller;

import lk.ijse.cropmonitoringsystembackend.customObj.VehicleResponse;
import lk.ijse.cropmonitoringsystembackend.dto.VehicleDTO;
import lk.ijse.cropmonitoringsystembackend.exception.InvalidStaffDataException;
import lk.ijse.cropmonitoringsystembackend.exception.StaffNotFoundException;
import lk.ijse.cropmonitoringsystembackend.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/v1/vehicle")
public class VehicleController {
    private final VehicleService vehicleService;
    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService=vehicleService;
    }

    @PostMapping
    public ResponseEntity<String> saveVehicle(@RequestBody VehicleDTO vehicleDTO) {
        try {
            vehicleService.saveVehicle(vehicleDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("vehicle saved successfully");
        } catch (InvalidStaffDataException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save vehicle");
        }
    }

    // Get a staff member by ID
    @GetMapping("/{code}")
    public ResponseEntity<VehicleResponse> getVehicleByCode(@PathVariable String code) {
        try {
            VehicleResponse vehicleByCode = vehicleService.getVehicleByCode(code);
            return ResponseEntity.ok(vehicleByCode);
        } catch (StaffNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Get all staff members
    @GetMapping
    public ResponseEntity<List<VehicleDTO>> getAllVehicles() {
        List<VehicleDTO> allVehicles = vehicleService.getAllVehicles();
        return ResponseEntity.ok(allVehicles);
    }

    // Update a staff member
    @PreAuthorize("!hasRole('SCIENTIST')")
    @PutMapping("/{code}")
    public ResponseEntity<String> updateVehicle(@PathVariable String code, @RequestBody VehicleDTO vehicleDTO) {
        try {
            vehicleService.updateVehicle(code,vehicleDTO);
            return ResponseEntity.ok("Vehicle updated successfully");
        } catch (StaffNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update Vehicle");
        }
    }

    // Delete a staff member
    @DeleteMapping("/{code}")
    public ResponseEntity<String> deleteVehicle(@PathVariable String code) {
        try {
            vehicleService.deleteVehicle(code);
            return ResponseEntity.ok("Vehicle deleted successfully");
        } catch (StaffNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete vehicle");
        }
    }
}
