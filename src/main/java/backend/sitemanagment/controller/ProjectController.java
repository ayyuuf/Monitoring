package backend.sitemanagment.controller;

import backend.sitemanagment.model.Project;
import backend.sitemanagment.service.impl.ProjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectServiceImpl projectService;

    @PostMapping("/save-project")
    public ResponseEntity<?> createProject(@RequestBody Project project) {

        projectService.addProject(project);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update-project")
    public ResponseEntity<?> updateProject(@RequestBody Project project) {

        projectService.updateProject(project);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete-project/{id}")
    public ResponseEntity<?> deleteProject(@PathVariable("id") int project_id) {

        projectService.deleteProject(project_id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<?> getProjects() {

        List<Project> projects = projectService.getAllProjects();
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findProjectByProject_id(@PathVariable("id") int project_id) {

        Project project = projectService.findProjectByProject_id(project_id);
        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<?> count(){

        int project= projectService.count();
        return new ResponseEntity<>(project, HttpStatus.OK);
    }


}
