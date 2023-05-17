package backend.sitemanagment.model.mapper;


import backend.sitemanagment.model.Server;
import com.zaxxer.hikari.pool.HikariProxyCallableStatement;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ServerMapper implements RowMapper<Server> {


    @Override
    public Server mapRow(ResultSet rs, int rowNum) throws SQLException {

        Server server = new Server();
        server.setServer_id(rs.getInt("server_id"));
        server.setServer_ip(rs.getString("server_ip"));
        server.setKeterangan(rs.getString("keterangan"));
        server.setProject_id(rs.getInt("project_id"));
        server.setUsername(rs.getString("username"));
        return server;
    }
}
