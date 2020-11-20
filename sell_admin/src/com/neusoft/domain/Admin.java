package com.neusoft.domain;

public class Admin {
    private Integer adminId;
    private String adminName;
    private String password;

    public Admin() {
    }

    public Admin(Integer adminId, String adminName, String password) {
        this.adminId = adminId;
        this.adminName = adminName;
        this.password = password;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public String getAdminName() {
        return adminName;
    }

    public String getPassword() {
        return password;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return
                "\n用户编号:" + this.adminId +
                "\n用户名:" + this.adminName +
                "\n密码:" + this.password ;

    }
}
