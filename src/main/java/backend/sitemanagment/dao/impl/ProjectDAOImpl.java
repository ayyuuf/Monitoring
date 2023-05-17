package backend.sitemanagment.dao.impl;

import backend.sitemanagment.dao.ProjectDAO;
import backend.sitemanagment.model.Project;
import backend.sitemanagment.model.mapper.ProjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class ProjectDAOImpl implements ProjectDAO {

    @Autowired
    private Environment env;

    @Autowired
    private JdbcTemplate jdbcTemplate;


    private Client client = Client.create();

    @Override
    public Project findProjectByProject_id(Integer project_id) {
        String query = "SELECT * FROM project WHERE project_id = ?";
        RowMapper<Project> rowMapper = new BeanPropertyRowMapper<Project>(Project.class);
        Project project= jdbcTemplate.queryForObject(query, rowMapper, project_id);
        return project;
    }

    @Override
    public void updateProject(Project project) {
        String query = "UPDATE project SET project_name=?, keterangan=?, username=?,status=? WHERE project_id=?";
        jdbcTemplate.update(query,project.getProject_name(),project.getKeterangan(),project.getUsername(), project.getStatus(),project.getProject_id());

    }

    @Override
    public void addProject(Project project) {
        String query = ("INSERT INTO project( project_name, keterangan, username,status) VALUES('"+project.getProject_name()+"','"+project.getKeterangan()+"','"+project.getUsername()+"','"+project.getStatus()+"')");
        jdbcTemplate.update(query);
        String url = env.getProperty("es.url") + env.getProperty("es.index.project")+"/_doc";
        WebResource webResource = client
                .resource(url);

        String input = "{\n" +
                "  \"project_name\": \""+project.getProject_name()+"\",\n" +
                "  \"keterangan\": \""+project.getKeterangan()+"\",\n" +
                "  \"project_id\": \""+project.getProject_id()+"\",\n" +
                "  \"username\": \""+project.getUsername()+"\"\n" +
                "}";

        ClientResponse response = webResource.type("application/json")
                .post(ClientResponse.class, input);

        if (response.getStatus() != 201) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + response.getStatus());
        }


    }


    @Override
    public void deleteProject(int project_id) {
        String query = "DELETE FROM project WHERE project_id=?";
        jdbcTemplate.update(query, project_id);
    }

    @Override
    public List<Project> getAllProjects() {
        String query = "SELECT * from project";
        RowMapper<Project> rowMapper = new ProjectMapper();
        List<Project> list = jdbcTemplate.query(query, rowMapper);
        return list;
    }

    @Override
    public int count() {
        String query = "SELECT COUNT(*) FROM project";
        return jdbcTemplate.queryForObject(query, Integer.class);
    }



}
