package com.znamenacek.jakub;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //create instance of Employee
        Employee employee01 = new Employee(1,"Jakub",10000);
        Employee employee02 = new Employee(2,"Petr",10000);

        //create entity manager and entity manager factory
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EmployeeService"); // connect with persistence.xml
        EntityManager em = emf.createEntityManager(); //create entityManager

        //persist entity
        em.getTransaction().begin(); //starts transaction
        em.persist(employee01);
//        em.persist(employee02);
//        em.persist(new Employee(3,"Lukas",1));
//        em.persist(new Employee(4,"Honza",100));
        em.getTransaction().commit(); //ends transaction


        //load from database
        // we are passing in the class of the entity and ID (primary key)
        // returns null if not found
        Employee employee03 = em.find(Employee.class, 1); //doesn't have to be processed as transaction because it only reads from DB
        System.out.println(employee03);

        //remove from database
        em.getTransaction().begin(); //starts transaction
        var employee04 = em.find(Employee.class,1); // I have to look ob the employee with given id is in DB, otherwise throws an error
        if(employee04!=null){
            em.remove(employee04); // remove employee from DB
        }
        em.getTransaction().commit(); //ends transaction

        //updating value in database
        em.getTransaction().begin(); //starts transaction
        var employee05 = em.find(Employee.class,2);
        if(employee05!=null){
            employee05.setSalary(employee05.getSalary()+1000); //entity is managed instance, so it doesn't have to be saved back to DB
        }
        em.getTransaction().commit(); //ends transaction

        //query example
        TypedQuery<String> query = em.createQuery("SELECT employee.name FROM Employee employee", String.class); // employee is variable
        //List<Employee> employees = query.getResultList();
        System.out.println("QUERY");
        query.getResultStream().forEach(System.out::println);

    }
}
