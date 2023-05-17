package backend.sitemanagment.service;


import backend.sitemanagment.model.Project;

import java.util.List;

public interface ProjectService {


    List<Project> getAllProjects();

    public Project findProjectByProject_id(int project_id);

    public void updateProject(Project project);

    public void addProject(Project project);

    public void deleteProject(int project_id);
    public int count();

}
