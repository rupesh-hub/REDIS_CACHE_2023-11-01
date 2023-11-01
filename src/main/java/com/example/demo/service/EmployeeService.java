package com.example.demo.service;

import com.example.demo.entity.Employee;
import com.example.demo.model.EmployeeDTO;
import com.example.demo.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Optional.of;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private static final String KEY = "employees";

    private final EmployeeRepository employeeRepository;
    private final HashOperations<String, Long, Object> operations;

    public EmployeeDTO save(final EmployeeDTO employeeDto) {

        return of(employeeRepository.save(mapTo(employeeDto)))
                .map(EmployeeDTO::new)
                .orElse(null);
    }

    public List<EmployeeDTO> getAll() {

        return operations.entries(KEY).values().stream()
                .filter(value -> value instanceof EmployeeDTO)
                .map(value -> (EmployeeDTO) value)
                .toList();

    }

    //@Cacheable(value = KEY, key = "#employeeId")
    //save into cache -- if already available don't save
    //if we request with same ID then return immediately from cache
    @Cacheable(value = KEY, key = "#employeeId")
    public EmployeeDTO get(final Long employeeId) {

        final EmployeeDTO employeeDTO = employeeRepository.findById(employeeId)
                .map(EmployeeDTO::new)
                .orElseThrow(() -> new RuntimeException("No employee found for employee id " + employeeId));

        //save employee record into redis
        //this will always get data from DB not from cache
        //if we want data from cache then required different logic
        //operations.put(KEY, employeeDTO.getId(), employeeDTO);
       /*var response = operations.get(KEY, employeeId);
       if(response instanceof EmployeeDTO)
           return ((EmployeeDTO)response);*/

        return employeeDTO;
    }

//    @CachePut(value = "employees", key = "#employeeId")
    @CacheEvict(value = "employees", key = "#employeeId")
    public Boolean delete(final Long employeeId) {

        final Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("No employee found for employee id " + employeeId));

        employeeRepository.delete(employee);

        return Boolean.TRUE;
    }


    private static Employee mapTo(final EmployeeDTO employeeDTO) {
        return new Employee(employeeDTO.getId(), employeeDTO.getName(), employeeDTO.getDesignation());
    }
}
