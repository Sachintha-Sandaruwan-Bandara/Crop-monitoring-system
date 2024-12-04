package lk.ijse.cropmonitoringsystembackend.controller;

import lk.ijse.cropmonitoringsystembackend.customObj.StaffResponse;
import lk.ijse.cropmonitoringsystembackend.dto.StaffDTO;
import lk.ijse.cropmonitoringsystembackend.exception.InvalidStaffDataException;
import lk.ijse.cropmonitoringsystembackend.exception.StaffNotFoundException;
import lk.ijse.cropmonitoringsystembackend.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/staff")
public class StaffController {

    private final StaffService staffService;

    @Autowired
    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    // Create a new staff member
    @PostMapping
    public ResponseEntity<String> createStaff(@RequestBody StaffDTO staffDTO) {
        try {
            staffService.createStaff(staffDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Staff created successfully");
        } catch (InvalidStaffDataException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create staff");
        }
    }

    // Get a staff member by ID
    @GetMapping("/{id}")
    public ResponseEntity<StaffResponse> getStaffById(@PathVariable String id) {
        try {
            StaffResponse staffResponse = staffService.getStaffById(id);
            return ResponseEntity.ok(staffResponse);
        } catch (StaffNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Get all staff members
    @GetMapping
    public ResponseEntity<List<StaffDTO>> getAllStaff() {
        List<StaffDTO> allStaff = staffService.getAllStaff();
        return ResponseEntity.ok(allStaff);
    }

    // Update a staff member
    @PreAuthorize("!hasRole('SCIENTIST')")
    @PutMapping("/{id}")
    public ResponseEntity<String> updateStaff(@PathVariable String id, @RequestBody StaffDTO updatedStaff) {
        try {
            staffService.updateStaff(id, updatedStaff);
            return ResponseEntity.ok("Staff updated successfully");
        } catch (StaffNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update staff");
        }
    }

    // Delete a staff member
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStaff(@PathVariable String id) {
        try {
            staffService.deleteStaff(id);
            return ResponseEntity.ok("Staff deleted successfully");
        } catch (StaffNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete staff");
        }
    }
}
