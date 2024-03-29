package com.example.devoir_jsf.bean;

import com.example.devoir_jsf.model.Employee;
import com.example.devoir_jsf.model.Project;
import com.example.devoir_jsf.model.ProjectContribution;
import com.example.devoir_jsf.dao.EmployeeDao;
import com.example.devoir_jsf.dao.ProjectDao;
import com.example.devoir_jsf.dao.ProjectContributionDao;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.util.List;

@Named
@ViewScoped
public class EmployeeProjectBean {

    private EmployeeDao employeeDao = new EmployeeDao();
    private ProjectDao projectDao = new ProjectDao();
    private ProjectContributionDao projectContributionDao = new ProjectContributionDao();

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

    public void removeEmployeeFromProject(Long projectId, Long employeeId) {
        // Assuming ProjectContributionDao has a method to find a contribution by projectId and employeeId
        ProjectContribution contribution = projectContributionDao.findByProjectIdAndEmployeeId(projectId, employeeId);
        if (contribution != null) {
            projectContributionDao.delete(contribution);
        }
    }

    public EmployeeDao getEmployeeDao() {
        return employeeDao;
    }

    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public ProjectDao getProjectDao() {
        return projectDao;
    }

    public void setProjectDao(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }

    public ProjectContributionDao getProjectContributionDao() {
        return projectContributionDao;
    }

    public void setProjectContributionDao(ProjectContributionDao projectContributionDao) {
        this.projectContributionDao = projectContributionDao;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public Long getSelectedEmployeeId() {
        return selectedEmployeeId;
    }

    public void setSelectedEmployeeId(Long selectedEmployeeId) {
        this.selectedEmployeeId = selectedEmployeeId;
    }

    public Long getSelectedProjectId() {
        return selectedProjectId;
    }

    public void setSelectedProjectId(Long selectedProjectId) {
        this.selectedProjectId = selectedProjectId;
    }

    public double getContributionPercentage() {
        return contributionPercentage;
    }

    public void setContributionPercentage(double contributionPercentage) {
        this.contributionPercentage = contributionPercentage;
    }
}
