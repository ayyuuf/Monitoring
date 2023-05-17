package backend.sitemanagment.model.mapper;

import backend.sitemanagment.model.Monitoring;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MonitoringMapper implements RowMapper<Monitoring> {

    @Override
    public Monitoring mapRow(ResultSet rs, int rowNum) throws SQLException {
        Monitoring monitoring = new Monitoring();
        monitoring.setMonitoring_id(rs.getInt("monitoring_id"));
        monitoring.setUsername(rs.getString("username"));
        monitoring.setProject_id(rs.getInt("project_id"));
        monitoring.setServer_id((rs.getInt("server_id")));
        monitoring.setName(rs.getString("name"));
        monitoring.setType(rs.getInt("type"));
        monitoring.setPort(rs.getInt("port"));
        monitoring.setHaproxy(rs.getInt("haproxy"));
        monitoring.setHaproxy_port(rs.getInt("haproxy_port"));
        monitoring.setPath(rs.getString("path"));
        monitoring.setGit_url(rs.getString("git_url"));
        monitoring.setRunning_on(rs.getInt("running_on"));
        monitoring.setRunning_command(rs.getString("running_command"));
        monitoring.setProject_name(rs.getString("project_name"));
        return monitoring;
    }
}
