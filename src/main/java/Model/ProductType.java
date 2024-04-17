package Model;

public class ProductType {
    private int type_id; // khóa chính
    private String category;

    public ProductType(int type_id, String category) {
        this.type_id = type_id;
        this.category = category;
    }

    public ProductType() {
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }
}
