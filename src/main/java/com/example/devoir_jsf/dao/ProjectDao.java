package com.example.devoir_jsf.dao;

import com.example.devoir_jsf.model.Project;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

public class ProjectDao {

    @PersistenceContext
    private EntityManager em;

    public void save(Project project) {
        em.persist(project);
    }

    public Project findById(Long id) {
        return em.find(Project.class, id);
    }

    public List<Project> findAll() {
        return em.createQuery("SELECT p FROM Project p", Project.class).getResultList();
    }

    public void delete(Project project) {
        em.remove(em.contains(project) ? project : em.merge(project));
    }
}
