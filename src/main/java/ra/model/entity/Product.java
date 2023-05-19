package ra.model.entity;

public class Product {
    private int proId;
    private String proName;
    private String image;
    private float price;
    private int quantity;
    private int cataId;
    private boolean proStatus;

    public Product() {
    }

    public Product(String proName, String image, float price, int quantity, int cataId) {
        this.proName = proName;
        this.image = image;
        this.price = price;
        this.quantity = quantity;
        this.cataId = cataId;
    }

    public Product(int proId, String proName, String image, float price, int quantity, int cataId, boolean proStatus) {
        this.proId = proId;
        this.proName = proName;
        this.image = image;
        this.price = price;
        this.quantity = quantity;
        this.cataId = cataId;
        this.proStatus = proStatus;
    }

    public Product(int proId, String proName, float price, int quantity, int cataId,boolean proStatus) {
        this.proId = proId;
        this.proName = proName;
        this.price = price;
        this.quantity = quantity;
        this.cataId = cataId;
        this.proStatus=proStatus;
    }

    public int getProId() {
        return proId;
    }

    public void setProId(int proId) {
        this.proId = proId;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public int getCataId() {
        return cataId;
    }

    public void setCataId(int cataId) {
        this.cataId = cataId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isProStatus() {
        return proStatus;
    }

    public void setProStatus(boolean proStatus) {
        this.proStatus = proStatus;
    }
}
