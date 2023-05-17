package backend.sitemanagment.model;

public class Server {

    @Override
    public String toString() {
        return  "Server[server_id="+server_id+", server_ip="+server_ip+", keterangan="+keterangan+", project_ip="+project_id+", username="+username+"]";
    }

    private int server_id;
    private String server_ip;
    private String keterangan;
    private int project_id;
    private String username;

    public Server(int server_id, String server_ip, String keterangan, int project_id, String username) {
        this.server_id = server_id;
        this.server_ip = server_ip;
        this.keterangan = keterangan;
        this.project_id = project_id;
        this.username = username;
    }

    public Server() {
    }

    public int getServer_id() {
        return server_id;
    }

    public void setServer_id(int server_id) {
        this.server_id = server_id;
    }

    public String getServer_ip() {
        return server_ip;
    }

    public void setServer_ip(String server_ip) {
        this.server_ip = server_ip;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
