package com.example.devoir_jsf.dao;

import com.example.devoir_jsf.model.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class EmployeeDao {

    private EntityManager entityManager;

    public EmployeeDao() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("devoirPersistenceUnit");
        this.entityManager = emf.createEntityManager();
    }

    public List<Employee> findAll() {
        return entityManager.createQuery("SELECT e FROM Employee e", Employee.class).getResultList();
    }

    public Employee findById(Long id) {
        return entityManager.find(Employee.class, id);
    }

    // Implement save and delete as needed, following the pattern from ProjectContributionDao
    // Remember to manage transactions appropriately

    public void close() {
        if (entityManager != null) {
            entityManager.close();
        }
    }
}
