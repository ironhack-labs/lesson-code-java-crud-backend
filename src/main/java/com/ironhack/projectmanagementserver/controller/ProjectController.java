package com.ironhack.projectmanagementserver.controller;

import com.ironhack.projectmanagementserver.model.Project;
import com.ironhack.projectmanagementserver.repository.ProjectRepository;
import com.ironhack.projectmanagementserver.service.impl.ProjectService;
import com.ironhack.projectmanagementserver.service.interfaces.ProjectServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProjectController {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectServiceInterface projectService;

    @GetMapping("/projects/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Project getProjectById(@PathVariable(name = "id") Long projectId) {
        return projectService.findById(projectId);
    }

    @GetMapping("/projects")
    @ResponseStatus(HttpStatus.OK)
    public List<Project> getProjects() {
        return projectRepository.findAll();
    }

    @PostMapping("/projects")
    @ResponseStatus(HttpStatus.CREATED)
    public void addProject(@RequestBody @Valid Project project){
        projectService.saveProject(project);
    }

    @PutMapping("/projects/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateProject(@PathVariable Long id,@RequestBody @Valid Project project){
        projectService.update(id, project);
    }

    @DeleteMapping("/projects/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProject(@PathVariable Long id){
        projectService.deleteProject(id);
    }
}
