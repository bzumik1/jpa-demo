package com.znamenacek.jakub.service;

import com.znamenacek.jakub.model.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class EmployeeService {
    EntityManager em;

    public EmployeeService(EntityManager em){
        this.em = em;
    }

    public void createEmployeeWithId(Employee employee){
        em.persist(employee); //don't have to be transactional
    }

    public Optional<Employee> getEmployeeById(Integer id){
        return Optional.ofNullable(em.find(Employee.class,id));
    }

    public List<Employee> getAllEmployees(){
        TypedQuery<Employee> query = em.createQuery("SELECT employee FROM Employee employee",Employee.class);
        return query.getResultList();
    }

    public void deleteEmployeeById(Integer id){
        getEmployeeById(id).ifPresent(employee -> em.remove(employee));
    }

    public void raiseEmployeeSalary(Integer id, Integer raise){
        getEmployeeById(id).ifPresent(employee -> employee.setSalary(employee.getSalary()+raise));
    }


}
