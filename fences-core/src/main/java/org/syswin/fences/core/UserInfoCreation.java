package org.syswin.fences.core;

public class UserInfoCreation extends UserInfo {

    private String password;
    private String permissionName;

    public UserInfoCreation () {}

    public String getPassword () {
        return password;
    }

    public void setPassword (String password) {
        this.password = password;
    }

    public String getPermissionName () {
        return permissionName;
    }

    public void setPermissionName (String permissionName) {
        this.permissionName = permissionName;
    }

    @Override public String toString () {
        return "UserInfoCreation{" +
                super.toString () +
                "password='" + password + '\'' +
                ", permissionName='" + permissionName + '\'' +
                '}';
    }
}
