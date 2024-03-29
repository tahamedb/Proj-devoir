package com.example.devoir_jsf.bean;

import com.example.devoir_jsf.dao.EmployeeDao;
import com.example.devoir_jsf.dao.ProjectContributionDao;
import com.example.devoir_jsf.dao.ProjectDao;
import com.example.devoir_jsf.model.Employee;
import com.example.devoir_jsf.model.Project;
import com.example.devoir_jsf.model.ProjectContribution;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.util.List;

@Named
@ViewScoped
public class EmployeeProjectBean {

    private final EmployeeDao employeeDao = new EmployeeDao();
    private final ProjectDao projectDao = new ProjectDao();
    private final ProjectContributionDao projectContributionDao = new ProjectContributionDao();

    private List<Employee> employees;
    private List<Project> projects;
    private Long selectedEmployeeId;
    private Long selectedProjectId;
    private double contributionPercentage;

    @PostConstruct
    public void init() {
        this.employees = employeeDao.findAll();
        this.projects = projectDao.findAll();
    }

    public void assignEmployeeToProject() {
        Employee employee = employeeDao.findById(selectedEmployeeId);
        Project project = projectDao.findById(selectedProjectId);

        ProjectContribution contribution = new ProjectContribution();
        contribution.setEmployee(employee);
        contribution.setProject(project);
        contribution.setPercentage(contributionPercentage);

        projectContributionDao.save(contribution);
    }

    // Include removeEmployeeFromProject method here, utilizing projectContributionDao
    // to remove the specific contribution

    // Getters and Setters omitted for brevity
}
