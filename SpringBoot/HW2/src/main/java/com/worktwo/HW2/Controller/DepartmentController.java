package com.worktwo.HW2.Controller;


import com.worktwo.HW2.Dto.DepartmentDto;

import com.worktwo.HW2.Exception.ResourceNotFoundException;
import com.worktwo.HW2.Service.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/Department")
public class DepartmentController {

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService){
        this.departmentService = departmentService;
    }

    @PostMapping
    public ResponseEntity<DepartmentDto> createDepartment(@RequestBody @Valid  DepartmentDto departmentDto) {
            return ResponseEntity.status(HttpStatus.CREATED).body(departmentService.createDepartment(departmentDto));
    }

    @GetMapping
    public ResponseEntity<List<DepartmentDto>> getAllDepartments(){
        return ResponseEntity.status(HttpStatus.OK).body(departmentService.getAllDepartments());

    }

    @GetMapping(path = "/{departmentId}")
    public ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable(name = "departmentId") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(departmentService.getDepartmentById(id).orElseThrow(()->new ResourceNotFoundException("Department not found")));
    }

    @PutMapping(path = "/{departmentId}")
    public ResponseEntity<DepartmentDto> updateDepartment(@PathVariable(name = "departmentId") Long id, @RequestBody DepartmentDto departmentDto){
        return ResponseEntity.status(HttpStatus.OK).body(departmentService.updateDepartment(id,departmentDto).orElseThrow(()->new ResourceNotFoundException("Department not found!")));
    }

    @DeleteMapping(path = "/{departmentId}")
    public ResponseEntity<Boolean> deleteDepartment(@PathVariable(name = "departmentId") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(departmentService.deleteDepartment(id));
    }

    @PatchMapping(path = "/{departmentId}")
    public ResponseEntity<DepartmentDto> patchDepartment(@PathVariable(name = "departmentId") Long id, @RequestBody Map<String,Object> patches){
        return ResponseEntity.ok(departmentService.patchDepartment(id,patches).orElseThrow(()->new ResourceNotFoundException("Some Error in Updates")));
    }

}
