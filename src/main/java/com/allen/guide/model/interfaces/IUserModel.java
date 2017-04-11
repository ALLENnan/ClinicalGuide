package com.allen.guide.model.interfaces;

import com.allen.guide.module.listener.IBaseListener;
import com.allen.guide.module.listener.ILoginListener;

public interface IUserModel {

    void doLogin(String phoneNum, String password, ILoginListener loginListener);

    void doRegister(String phoneNum, String password, IBaseListener baseListener);

    /**
     * 修改用户信息
     *
     * @param username 用户id
     * @param username 用户名，即昵称
     * @param listener
     */
    void modifyUserInfo(int id, String username, final IBaseListener listener);
}
