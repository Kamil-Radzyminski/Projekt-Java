/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proj.crud.Models;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Kamil
 */
abstract public class AbstractModel {
    
    protected static Connection con;
    
    public static Connection getConnection() throws SQLException{
        con = DriverManager.getConnection("jdbc:sqlserver://"
                    + "localhost:1433;databaseName=projekt;" +
                    "user=kamiljano99;password=haslo123;");
        return con;
    }
    
    abstract public boolean delete(Integer id);
    abstract public AbstractModel create();
    abstract public AbstractModel update(Integer id);
}
