package backend.sitemanagment.model.mapper;

import backend.sitemanagment.model.Project;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProjectMapper implements RowMapper<Project> {

    @Override
    public Project mapRow(ResultSet rs, int rowNum) throws SQLException {

        Project project = new Project();
        project.setProject_id(rs.getInt("project_id"));
        project.setProject_name(rs.getString("project_name"));
        project.setKeterangan(rs.getString("keterangan"));
        project.setUsername(rs.getString("username"));
        project.setStatus(rs.getString("status"));
        return project;
    }
}
