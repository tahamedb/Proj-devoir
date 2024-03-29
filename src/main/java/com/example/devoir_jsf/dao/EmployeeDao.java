package com.example.devoir_jsf.dao;

import com.example.devoir_jsf.model.Employee;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;
import java.util.List;
@Named
@RequestScoped
public class EmployeeDao {

    private EntityManager em;

    public EmployeeDao() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("devoirPersistenceUnit");
        this.em = emf.createEntityManager();
    }

    public void save(Employee employee) {
        em.persist(employee);
    }

    public Employee findById(Long id) {
        return em.find(Employee.class, id);
    }

    public List<Employee> findAll() {
        return em.createQuery("SELECT e FROM Employee e", Employee.class).getResultList();
    }

    public void delete(Employee employee) {
        em.remove(em.contains(employee) ? employee : em.merge(employee));
    }
}
