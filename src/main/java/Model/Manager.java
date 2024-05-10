package Model;

public class Manager {
    private int manager_id;
    private String name;
    private String image_path;

    public Manager(){

    }
    public Manager(int id, String name, String image_path) {
        this.image_path = image_path;
        this.manager_id = id;
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setManager_id(int id) {
        this.manager_id = id;
    }
    public void setImage_path(String path) {
        this.image_path = path;
    }
    public int getManager_id(){
        return this.manager_id;
    }
    public String getName(){
        return this.name;
    }
    public String getImage_path() {
        return this.image_path;
    }
}
