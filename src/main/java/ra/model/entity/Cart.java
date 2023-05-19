package ra.model.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Cart {
    private int cartId;
    private int userId;
    private String userService;
    private float total;
    private String phone;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date orderDate;
    private String address;
    private boolean status;
    private boolean style;

    public Cart(int cartId, int userId, String user, float total, String phone, String address) {
    }

    public Cart(int cartId, int userId, String userService, float total, String phone, Date orderDate, String address, boolean status, boolean style) {
        this.cartId = cartId;
        this.userId = userId;
        this.userService = userService;
        this.total = total;
        this.phone = phone;
        this.orderDate = orderDate;
        this.address = address;
        this.status = status;
        this.style = style;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserService() {
        return userService;
    }

    public void setUserService(String userService) {
        this.userService = userService;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isStyle() {
        return style;
    }

    public void setStyle(boolean style) {
        this.style = style;
    }
}
