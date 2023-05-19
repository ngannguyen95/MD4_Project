package ra.model.entity;

public class CartDetail {
    private int cdtId;
    private int proId;
    private int cartId;
    private int quantity;
    private String proName;
    private String image;
    private float price;

    public CartDetail() {
    }

    public CartDetail(int cdtId, int proId, int cartId, int quantity, String proName, String image, float price) {
        this.cdtId = cdtId;
        this.proId = proId;
        this.cartId = cartId;
        this.quantity = quantity;
        this.proName = proName;
        this.image = image;
        this.price = price;
    }

    public CartDetail(int cdtId, int quantity) {
        this.cdtId = cdtId;
        this.quantity = quantity;
    }

    public CartDetail(int proId, int cartId, int quantity) {
        this.proId = proId;
        this.cartId = cartId;
        this.quantity = quantity;
    }

    public int getCdtId() {
        return cdtId;
    }

    public void setCdtId(int cdtId) {
        this.cdtId = cdtId;
    }

    public int getProId() {
        return proId;
    }

    public void setProId(int proId) {
        this.proId = proId;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
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
}
