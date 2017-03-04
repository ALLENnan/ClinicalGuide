package com.allen.guide.model.entities;

public class UserBean {
    private int id;
    private String phone_num;
    private String password;
    private String username;

    public UserBean() {
        super();
    }

    public UserBean(int id, String phone_num, String password, String username) {
        super();
        this.id = id;
        this.phone_num = phone_num;
        this.password = password;
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "UserBean [id=" + id + ", phone_num=" + phone_num
                + ", password=" + password + ", username=" + username + "]";
    }

}
