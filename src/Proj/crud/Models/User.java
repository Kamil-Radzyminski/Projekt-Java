/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proj.crud.Models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kamil
 */
public class User extends AbstractModel {

    private final String firstname;
    private final String lastname;
    private final String role;
    private final String login;
    private final String password;
    private Integer id;

    public User(String firstname, String lastname, String role, String login, String password) throws SQLException {
        this.firstname = firstname;
        this.lastname = lastname;
        this.role = role;
        this.login = login;
        this.password = password;
    }

    public User(String firstname, String lastname, String role, String login, String password, Integer id) throws SQLException {
        this.firstname = firstname;
        this.lastname = lastname;
        this.role = role;
        this.login = login;
        this.password = password;
        this.id = id;
    }
    
    public String getFirstname(){
        return this.firstname;
    }
    
    public String getLastname(){
        return this.lastname;
    }
    
    public String getRole(){
        return this.role;
    }
    
    public String getLogin(){
        return this.login;
    }
    
    public String getPassword(){
        return this.password;
    }
    
    public Integer getId(){
        return this.id;
    }

    @Override
    public boolean delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AbstractModel create() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AbstractModel update(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static List<User> getList() throws SQLException {
        List<User> usersList = new ArrayList<>();
        Statement stmt = AbstractModel.getConnection().createStatement();
        String query = "select * from uzytkownicy";
        ResultSet result = stmt.executeQuery(query);

        while (result.next()) {
            usersList.add(new User(result.getString("imie"), result.getString("nazwisko"), result.getString("rola"), result.getString("login"), result.getString("haslo"), result.getInt("id")));
        }

        return usersList;
    }
}
