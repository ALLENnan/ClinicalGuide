package com.allen.guide.model.entities;

public class JLogin {

    private boolean isUserExist;
    private UserBean user;

    public JLogin(boolean isUserExist, UserBean user) {
        super();
        this.isUserExist = isUserExist;
        this.user = user;
    }

    public boolean isUserExist() {
        return isUserExist;
    }

    public void setUserExist(boolean userExist) {
        isUserExist = userExist;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

}
