package backend.sitemanagment.model;

import java.sql.Timestamp;

public class Monitoring {

    private int monitoring_id;
    private String username;
    private int project_id;
    private int server_id;
    private String name;
    private int type;
    private int port;
    private int haproxy;
    private int haproxy_port;
    private String path;
    private String git_url;
    private int running_on;
    private String running_command;
    private String project_name;

    public Monitoring(int monitoring_id, String username, int project_id, int server_id, String name, int type, int port, int haproxy, int haproxy_port, String path, String git_url, int running_on, String running_command, String project_name) {
        this.monitoring_id = monitoring_id;
        this.username = username;
        this.project_id = project_id;
        this.server_id = server_id;
        this.name = name;
        this.type = type;
        this.port = port;
        this.haproxy = haproxy;
        this.haproxy_port = haproxy_port;
        this.path = path;
        this.git_url = git_url;
        this.running_on = running_on;
        this.running_command = running_command;
        this.project_name = project_name;
    }

    public Monitoring() {
    }

    public int getMonitoring_id() {
        return monitoring_id;
    }

    public void setMonitoring_id(int monitoring_id) {
        this.monitoring_id = monitoring_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    public int getServer_id() {
        return server_id;
    }

    public void setServer_id(int server_id) {
        this.server_id = server_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getHaproxy() {
        return haproxy;
    }

    public void setHaproxy(int haproxy) {
        this.haproxy = haproxy;
    }

    public int getHaproxy_port() {
        return haproxy_port;
    }

    public void setHaproxy_port(int haproxy_port) {
        this.haproxy_port = haproxy_port;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getGit_url() {
        return git_url;
    }

    public void setGit_url(String git_url) {
        this.git_url = git_url;
    }

    public int getRunning_on() {
        return running_on;
    }

    public void setRunning_on(int running_on) {
        this.running_on = running_on;
    }

    public String getRunning_command() {
        return running_command;
    }

    public void setRunning_command(String running_command) {
        this.running_command = running_command;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }
}
