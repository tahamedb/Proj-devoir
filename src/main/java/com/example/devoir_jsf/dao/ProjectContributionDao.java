package com.example.devoir_jsf.dao;

import com.example.devoir_jsf.model.ProjectContribution;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class ProjectContributionDao {

    private EntityManager entityManager;

    public ProjectContributionDao() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("devoirPersistenceUnit");
        this.entityManager = emf.createEntityManager();
    }

    public ProjectContribution findByProjectIdAndEmployeeId(Long projectId, Long employeeId) {
        List<ProjectContribution> results = entityManager.createQuery(
                        "SELECT pc FROM ProjectContribution pc WHERE pc.project.id = :projectId AND pc.employee.id = :employeeId",
                        ProjectContribution.class)
                .setParameter("projectId", projectId)
                .setParameter("employeeId", employeeId)
                .getResultList();

        if (!results.isEmpty()) {
            return results.get(0); // Assuming there's only one contribution per employee per project
        }
        return null;
    }

    public void save(ProjectContribution contribution) {
        entityManager.getTransaction().begin();
        entityManager.persist(contribution);
        entityManager.getTransaction().commit();
    }

    public void delete(ProjectContribution contribution) {
        entityManager.getTransaction().begin();
        if (!entityManager.contains(contribution)) {
            contribution = entityManager.merge(contribution);
        }
        entityManager.remove(contribution);
        entityManager.getTransaction().commit();
    }

    // Ensure you close both EntityManager and EntityManagerFactory when they're no longer needed
    public void close() {
        if (entityManager != null) {
            entityManager.close();
        }
        // Note: EntityManagerFactory should be closed at a higher level (application end) if shared
    }

    // Other methods...
}
