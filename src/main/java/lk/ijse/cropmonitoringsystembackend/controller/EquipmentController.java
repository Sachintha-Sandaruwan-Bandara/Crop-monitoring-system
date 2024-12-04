package lk.ijse.cropmonitoringsystembackend.controller;

import lk.ijse.cropmonitoringsystembackend.customObj.EquipmentResponse;
import lk.ijse.cropmonitoringsystembackend.dto.EquipmentDTO;
import lk.ijse.cropmonitoringsystembackend.exception.InvalidStaffDataException;
import lk.ijse.cropmonitoringsystembackend.exception.StaffNotFoundException;
import lk.ijse.cropmonitoringsystembackend.service.EquipmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/equipment")
public class EquipmentController {
    private final EquipmentService equipmentService;

    public EquipmentController(EquipmentService equipmentService){
        this.equipmentService=equipmentService;
    }
    @PostMapping
    public ResponseEntity<String> saveEquipment(@RequestBody EquipmentDTO equipmentDTO) {
        try {
            equipmentService.saveEquipment(equipmentDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Equipment saved successfully");
        } catch (InvalidStaffDataException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save Equipment");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<EquipmentResponse> getFieldByCode(@PathVariable String id) {
        try {
            EquipmentResponse equipmentById=equipmentService.getEquipmentById(id);
            return ResponseEntity.ok(equipmentById);
        } catch (StaffNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<EquipmentDTO>> getEquipments() {
        List<EquipmentDTO> allEquipments = equipmentService.getAllEquipments();
        return ResponseEntity.ok(allEquipments);
    }
    @PreAuthorize("!hasRole('SCIENTIST')")
    @PutMapping("/{id}")
    public ResponseEntity<String> updateEquipment(@PathVariable String id, @RequestBody EquipmentDTO updatedEquipment) {
        try {
            equipmentService.updatEquipment(id,updatedEquipment);
            return ResponseEntity.ok("Equipment updated successfully");
        } catch (StaffNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update Equipment");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEquipment(@PathVariable String id) {
        try {
            equipmentService.deleteEquipment(id);
            return ResponseEntity.ok("Equipment deleted successfully");
        } catch (StaffNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete equipment");
        }
    }
}
