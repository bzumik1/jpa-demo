package com.znamenacek.jakub;

import com.znamenacek.jakub.model.Employee;
import com.znamenacek.jakub.service.EmployeeService;
import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToFile;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EmployeeService");
        EntityManager em = emf.createEntityManager();
        EmployeeService employeeService= new EmployeeService(em);

        System.out.println("CREATE");
//        em.getTransaction().begin();
//        employeeService.createEmployeeWithId(new Employee(22,"Jakub",28000));
//        employeeService.createEmployeeWithId(new Employee(23,"Honza",13000));
//        employeeService.createEmployeeWithId(new Employee(24,"Petr",58000));
//        employeeService.createEmployeeWithId(new Employee(25,"Frantisek",125000));
//        em.getTransaction().commit();

        System.out.println("PRINT");
        System.out.println("bylo nalezeno "+employeeService.getAllEmployees().size()+" zamestnancu");
        employeeService.getAllEmployees().stream().forEach(System.out::println);

        System.out.println("DELETE"); // working
        em.getTransaction().begin();
        employeeService.deleteEmployeeById(4);
        em.getTransaction().commit();

        System.out.println("PRINT");
        employeeService.getAllEmployees().stream().forEach(System.out::println);

        System.out.println("RAISE SALARY");
        em.getTransaction().begin();
        employeeService.raiseEmployeeSalary(2,10000);
        em.getTransaction().commit();

        System.out.println("FIND BY ID"); // working
        employeeService.getEmployeeById(2).ifPresent(System.out::println);

        em.close();
        emf.close();


    }
}