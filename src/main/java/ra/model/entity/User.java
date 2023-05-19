package ra.model.entity;

public class User {
    private int userId;
    private String userName;
    private String fullName;
    private int role;
    private int age;
    private boolean sex;
    private String phone;
    private String address;
    private String password;
    private boolean status;

    public User() {
    }

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public User(int userId, String userName, String fullName, int role, int age, boolean sex, String phone, String address, String password) {
        this.userId = userId;
        this.userName = userName;
        this.fullName = fullName;
        this.role = role;
        this.age = age;
        this.sex = sex;
        this.phone = phone;
        this.address = address;
        this.password = password;
        this.status = true;
    }

    public User(int userId, String userName, String fullName, int age, boolean sex, String phone, String address, String password) {
        this.userId = userId;
        this.userName = userName;
        this.fullName = fullName;
        this.age = age;
        this.sex = sex;
        this.phone = phone;
        this.address = address;
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
