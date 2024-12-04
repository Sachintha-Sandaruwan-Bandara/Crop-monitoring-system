package lk.ijse.cropmonitoringsystembackend.controller;

import lk.ijse.cropmonitoringsystembackend.customObj.FieldResponse;
import lk.ijse.cropmonitoringsystembackend.dto.FieldDTO;
import lk.ijse.cropmonitoringsystembackend.exception.InvalidStaffDataException;
import lk.ijse.cropmonitoringsystembackend.exception.StaffNotFoundException;
import lk.ijse.cropmonitoringsystembackend.service.FieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/field")
public class FieldController {
    private final FieldService fieldService;
    @Autowired
    public FieldController(FieldService fieldService) {
        this.fieldService = fieldService;
    }

    @PostMapping
    public ResponseEntity<String> createField(@RequestBody FieldDTO fieldDTO) {
        try {
            fieldService.createField(fieldDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Field created successfully");
        } catch (InvalidStaffDataException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create field");
        }
    }

    // Get a staff member by ID
    @GetMapping("/{code}")
    public ResponseEntity<FieldResponse> getFieldByCode(@PathVariable String code) {
        try {
            FieldResponse fieldByCode = fieldService.getFieldByCode(code);
            return ResponseEntity.ok(fieldByCode);
        } catch (StaffNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Get all staff members
    @GetMapping
    public ResponseEntity<List<FieldDTO>> getAllFields() {
        List<FieldDTO> allFields = fieldService.getAllFields();
        return ResponseEntity.ok(allFields);
    }

    // Update a staff member
    @PreAuthorize("!hasRole('ADMINISTRATIVE')")
    @PutMapping("/{code}")
    public ResponseEntity<String> updateField(@PathVariable String code, @RequestBody FieldDTO updatedField) {
        try {
            fieldService.updateField(code,updatedField);
            return ResponseEntity.ok("Field updated successfully");
        } catch (StaffNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update field");
        }
    }

    // Delete a staff member
    @DeleteMapping("/{code}")
    public ResponseEntity<String> deleteField(@PathVariable String code) {
        try {
            fieldService.deleteField(code);
            return ResponseEntity.ok("field deleted successfully");
        } catch (StaffNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete field");
        }
    }
}
