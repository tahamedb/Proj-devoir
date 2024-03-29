package com.example.devoir_jsf.dao;

import com.example.devoir_jsf.model.Project;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class ProjectDao {

    private EntityManager entityManager;

    public ProjectDao() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("devoirPersistenceUnit");
        this.entityManager = emf.createEntityManager();
    }

    public List<Project> findAll() {
        return entityManager.createQuery("SELECT p FROM Project p", Project.class).getResultList();
    }

    public Project findById(Long id) {
        return entityManager.find(Project.class, id);
    }

    // Implement save and delete as needed, following the pattern from ProjectContributionDao
    // Remember to manage transactions appropriately

    public void close() {
        if (entityManager != null) {
            entityManager.close();
        }
    }
}
