package backend.sitemanagment.dao;

import backend.sitemanagment.model.Project;

import java.util.List;

public interface ProjectDAO {

    public Project findProjectByProject_id(Integer Project_id);

    public void updateProject(Project project);

    public void addProject(Project project);

    public void deleteProject(int project_id);

    List<Project> getAllProjects();
    public int count();

}
