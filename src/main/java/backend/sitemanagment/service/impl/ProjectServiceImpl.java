package backend.sitemanagment.service.impl;

import backend.sitemanagment.dao.impl.ProjectDAOImpl;
import backend.sitemanagment.model.Project;
import backend.sitemanagment.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectDAOImpl projectDAO;

    @Override
    public List<Project> getAllProjects() {
        return projectDAO.getAllProjects();
    }

    @Override
    public Project findProjectByProject_id(int project_id) {
        return projectDAO.findProjectByProject_id(project_id);
    }

    @Override
    public void updateProject(Project project) {
        projectDAO.updateProject(project);

    }

    @Override
    public void addProject(Project project) {
        projectDAO.addProject(project);

    }

    @Override
    public void deleteProject(int project_id) {
        projectDAO.deleteProject(project_id);

    }

    @Override
    public int count() {
        return projectDAO.count();
    }


}
