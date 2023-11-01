package com.example.demo.model;

import com.example.demo.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("employees")
public class EmployeeDTO implements Serializable {

    private Long id;
    private String name;
    private String designation;

    public EmployeeDTO(Employee employee){
        this.id = employee.getId();
        this.name = employee.getName();
        this.designation = employee.getDesignation();
    }

}
