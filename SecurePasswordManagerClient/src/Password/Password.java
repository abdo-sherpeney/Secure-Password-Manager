/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Password;

import java.io.Serializable;

/**
 *
 * @author adelali
 */
public class Password implements IPassword ,Serializable {

    private int _id;
    private String _name;
    private String _login;
    private String _securedPassword;

    public void SetId(int Id){
        _id=Id;
    }
    
    @Override
    public int id() {
        return _id;
    }

    @Override
    public String name() {
        return _name;
    }

    @Override
    public String login() {
        return _login;
    }

    @Override
    public String securedPassword() {
        return _securedPassword;
    }

    public void name(String NewValue) {
        _name = NewValue;
    }

    public void login(String NewValue) {
        _login = NewValue;
    }

    public void securedPassword(String NewValue) {
        _securedPassword = NewValue;
    }

    public Password(String name_, String securedPassword_, String login_) {
        _name = name_;
        _securedPassword = securedPassword_;
        _login = login_;
    }

}
