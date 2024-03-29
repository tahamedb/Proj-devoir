package com.example.devoir_jsf.bean;

import com.example.devoir_jsf.dao.EmployeeDao;
import com.example.devoir_jsf.dao.ProjectContributionDao;
import com.example.devoir_jsf.dao.ProjectDao;
import com.example.devoir_jsf.model.Employee;
import com.example.devoir_jsf.model.Project;
import com.example.devoir_jsf.model.ProjectContribution;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class AssignmentBean {

    private EmployeeDao employeeDao = new EmployeeDao();
    private ProjectDao projectDao = new ProjectDao();
    private ProjectContributionDao contributionDao = new ProjectContributionDao();

    private List<Employee> employees;
    private List<Project> projects;
    private Long selectedEmployeeId;
    private Long selectedProjectId;
    private double contributionPercentage;

    @PostConstruct
    public void init() {
        employees = employeeDao.findAll();
        projects = projectDao.findAll();
    }

    // Getters and setters omitted for brevity

    public String assignEmployeeToProject() {
        Employee employee = employeeDao.findById(selectedEmployeeId);
        Project project = projectDao.findById(selectedProjectId);
        ProjectContribution contribution = new ProjectContribution();
        contribution.setEmployee(employee);
        contribution.setProject(project);
        contribution.setPercentage(contributionPercentage);
        contributionDao.save(contribution);
        return "assignmentSuccess"; // Navigate to a success page or refresh
    }
}
