package backend.sitemanagment.dao.impl;

import backend.sitemanagment.dao.MonitoringDAO;
import backend.sitemanagment.model.Monitoring;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class MonitoringDAOImpl implements MonitoringDAO {

    @Autowired
    private Environment env;

    @Autowired
    private JdbcTemplate jdbcTemplate;


    private Client client =Client.create();

    @Override
    public List<Map<String, Object>> getAllMonitorings() {
        String query = "SELECT * from monitoring";
        return jdbcTemplate.queryForList(query);
    }

    @Override
    public Monitoring findById(int monitoring_id) {
        String query = "SELECT * FROM monitoring WHERE monitoring_id = ?";
        RowMapper<Monitoring> rowMapper = new BeanPropertyRowMapper<Monitoring>(Monitoring.class);
        Monitoring monitoring= jdbcTemplate.queryForObject(query, rowMapper, monitoring_id);
        return monitoring;
    }

    @Override
    public int countService() {
        String query = "SELECT COUNT(type) FROM monitoring where type = 1 ";
        return jdbcTemplate.queryForObject(query, Integer.class);
    }

    @Override
    public int countEngine() {
        String query = "SELECT COUNT(type) FROM monitoring where type = 2";
        return jdbcTemplate.queryForObject(query, Integer.class);
    }


    @Override
    public void addMonitoring(Monitoring monitoring) {
        String query = ("INSERT INTO monitoring (username, project_id, server_id, name, type, port, haproxy, haproxy_port, path, git_url, running_on, running_command, project_name) VALUES ('"+monitoring.getUsername()+"','"+monitoring.getProject_id()+"', '"+monitoring.getServer_id()+"','"+monitoring.getName()+"','"+monitoring.getType()+"', '"+monitoring.getPort()+"', '"+monitoring.getHaproxy()+"', '"+monitoring.getHaproxy_port()+"','"+monitoring.getPath()+"', '"+monitoring.getGit_url()+"', '"+monitoring.getRunning_on()+"', '"+monitoring.getRunning_command()+"', '"+monitoring.getProject_name()+"')");
        System.out.println(query);
        jdbcTemplate.update(query);
        String url = env.getProperty("es.url") + env.getProperty("es.index.monitoring")+"/_doc";
        System.out.println(url);
        WebResource webResource = client
                .resource(url);

        String input = "{\n" +
                "  \"server_id\": \""+monitoring.getServer_id()+"\",\n" +
                "  \"project_id\": \""+monitoring.getProject_id()+"\",\n" +
                "  \"username\": \""+monitoring.getUsername()+"\",\n" +
                "  \"name\":\""+    monitoring.getName()+"\",\n" +
                "  \"type\":\""+monitoring.getType()+"\",\n" +
                "  \"port\":\""+monitoring.getPort()+"\",\n" +
                "  \"haproxy\":\""+monitoring.getHaproxy()+"\",\n" +
                "  \"haproxy_port\":\""+monitoring.getHaproxy_port()+"\",\n" +
                "  \"path\":\""+monitoring.getPath()+"\",\n" +
                "  \"git_url\":\""+monitoring.getGit_url()+"\",\n" +
                "  \"running_on\":\""+monitoring.getRunning_on()+"\",\n" +
                "  \"running_command\":\""+monitoring.getRunning_command()+"\",\n" +
                "  \"project_name\":\""+monitoring.getProject_name()+"\"\n" +

                "}";

        ClientResponse response = webResource.type("application/json")
                .post(ClientResponse.class, input);

        if (response.getStatus() != 201) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + response.getStatus());
        }


    }

    @Override
    public void updateMonitoring(Monitoring monitoring) {
        String query =("UPDATE monitoring SET username=?, project_id=?, server_id=?, name=?, type=?, port=?,haproxy=?, haproxy_port=?, path=?, git_url=?, running_on=?, running_command=?WHERE monitoring_id=?");
        jdbcTemplate.update(query, monitoring.getUsername(),monitoring.getProject_id(), monitoring.getServer_id(), monitoring.getName(), monitoring.getType(),monitoring.getPort(), monitoring.getHaproxy(), monitoring.getHaproxy_port(),monitoring.getPath(),monitoring.getGit_url(), monitoring.getRunning_on(), monitoring.getRunning_command(),monitoring.getMonitoring_id());
    }

    @Override
    public void deleteMonitoring(int monitoring_id) {
        String query = "DELETE FROM monitoring WHERE monitoring_id=?";
        jdbcTemplate.update(query, monitoring_id);
    }
}
