package backend.sitemanagment.dao.impl;

import backend.sitemanagment.dao.ServerDAO;
import backend.sitemanagment.model.Server;
import backend.sitemanagment.model.mapper.ServerMapper;
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

@Transactional
@Repository
public class ServerDAOImpl implements ServerDAO {

    @Autowired
    private Environment env;

    @Autowired
    private JdbcTemplate jdbcTemplate;


    private Client client = Client.create();

    @Override
    public List<Server> getAllServers() {
        String query = "SELECT * from server";
        RowMapper<Server> rowMapper = new ServerMapper();
        List<Server> list = jdbcTemplate.query(query, rowMapper);
        return list;
    }

    @Override
    public Server findServerByServer_id(Integer server_id) {
        String query = "SELECT * FROM server WHERE server_id = ?";
        RowMapper<Server> rowMapper = new BeanPropertyRowMapper<Server >(Server.class);
        Server server= jdbcTemplate.queryForObject(query, rowMapper, server_id);
        return server;
    }

    @Override
    public void updateServer( Server server) {
        String query = "UPDATE server SET server_ip=?, keterangan=?, project_id=?, username=? WHERE server_id=?";
        jdbcTemplate.update(query,server.getServer_ip(),server.getKeterangan(),server.getProject_id(),server.getUsername(),server.getServer_id());

    }

    @Override
    public void addServer(Server server) {
        String query = ("INSERT INTO server( server_ip, keterangan, project_id, username) VALUES('"+server.getServer_ip()+"','"+server.getKeterangan()+"','"+server.getProject_id()+"','"+server.getUsername()+"')");
        jdbcTemplate.update(query);
        String url = env.getProperty("es.url") + env.getProperty("es.index.server")+"/_doc";
        WebResource webResource = client
                .resource(url);

        String input = "{\n" +
                "  \"server_ip\": \""+server.getServer_ip()+"\",\n" +
                "  \"keterangan\": \""+server.getKeterangan()+"\",\n" +
                "  \"project_id\": \""+server.getProject_id()+"\",\n" +
                "  \"username\": \""+server.getUsername()+"\"\n" +
                "}\n";

        System.out.println(input);

        ClientResponse response = webResource.type("application/json")
                .post(ClientResponse.class, input);

        if (response.getStatus() != 201) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + response.getStatus());
        }

    }

    @Override
    public void deleteServer(int server_id) {
        String query = "DELETE FROM server WHERE server_id=?";
        jdbcTemplate.update(query, server_id);
    }

    @Override
    public int count() {
        String query = "SELECT COUNT(*) FROM server";
        return jdbcTemplate.queryForObject(query, Integer.class);
    }

}
