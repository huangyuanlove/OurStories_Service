package com.huangyuanlove.ourstories.entites;

/**
 * Created by huangyuan on 16-11-10.
 */
public class User {
    private int id;
    private String name;
    private String email;
    private int age;
    private Address address;
    private String password;
    private String rongIMToken;

    public String getRongIMToken() {
        return rongIMToken;
    }

    public void setRongIMToken(String rongIMToken) {
        this.rongIMToken = rongIMToken;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String username) {
        this.name = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + name + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", address=" + address +
                ", rongIMToken"+ rongIMToken +
                '}';
    }
}
