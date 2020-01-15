package com.znamenacek.jakub.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
@Entity
public class Employee {
    @Id
    private Integer id;
    private String name;
    private Integer salary;
}
