package com.worktwo.HW2.Service;


import com.worktwo.HW2.Dto.DepartmentDto;
import com.worktwo.HW2.Entity.Department;
import com.worktwo.HW2.Repository.DepartmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository, ModelMapper modelMapper){
        this.departmentRepository = departmentRepository;

        this.modelMapper = modelMapper;
    }


    public DepartmentDto createDepartment( DepartmentDto departmentDto) {
        departmentDto.setCreatedAt(LocalDateTime.now());
        departmentRepository.save(modelMapper.map(departmentDto, Department.class));
        return departmentDto;
    }

    public List<DepartmentDto> getAllDepartments() {
        return departmentRepository.findAll().stream().map(x -> modelMapper.map(x, DepartmentDto.class)).toList();

    }

    public Optional<DepartmentDto> getDepartmentById(Long id) {
        return departmentRepository.findById(id).map(x -> modelMapper.map(x, DepartmentDto.class));
    }

    public Optional<DepartmentDto> updateDepartment(Long id, DepartmentDto departmentDto) {
        if(!isExistsById(id)){
            return Optional.empty();
        }
        Department dept = departmentRepository.save(modelMapper.map(departmentDto, Department.class));
        return Optional.ofNullable(modelMapper.map(dept, DepartmentDto.class));
    }

    private boolean isExistsById(Long id) {
        return departmentRepository.existsById(id);
    }

    public Optional<DepartmentDto> patchDepartment(Long id, Map<String, Object> patches) {
        if(!isExistsById(id)){
            return Optional.empty();
        }
        Department departmentEntity = departmentRepository.findById(id).get();
        for(Map.Entry<String, Object> entry : patches.entrySet()){
            Field field = ReflectionUtils.findField(Department.class, entry.getKey());
            if(field == null){
                return Optional.empty();
            }
            ReflectionUtils.makeAccessible(field);
            ReflectionUtils.setField(field, departmentEntity, entry.getValue());
        }
        return Optional.ofNullable(modelMapper.map(departmentEntity,DepartmentDto.class));

    }

    public boolean deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
        return true;
    }
}
