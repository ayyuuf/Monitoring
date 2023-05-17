package backend.sitemanagment.model;

public class Project {

    private int project_id;
    private String project_name;
    private String keterangan;
    private String username;
    private String status;

    public Project(int project_id, String project_name, String keterangan, String username) {
        this.project_id = project_id;
        this.project_name = project_name;
        this.keterangan = keterangan;
        this.username = username;
        this.status = status;
    }

    public Project(){
    }

    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
