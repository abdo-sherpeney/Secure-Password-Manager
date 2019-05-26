/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User;

import Password.IPassword;
import Password.Password;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author adelali
 */
public class User implements IUser, Serializable {

    private int _id;
    private String _firstName;
    private String _userName;
    private String _Password;

    @Override
    public int id() {
        return _id;
    }

    @Override
    public String firstName() {
        return _firstName;
    }

    @Override
    public String userName() {
        return _userName;
    }

    @Override
    public String Password() {
        return _Password;
    }

    public void firstName(String NewValue) {
        _firstName = NewValue;
    }

    public void userName(String NewValue) {
        _userName = NewValue;
    }

    public void Password(String NewValue) {
        _Password = NewValue;
    }

    public User(int id_, String name_, String userName_, String Password_) {
        _id = id_;
        _firstName = name_;
        _userName = userName_;
        _Password = Password_;
    }

}
