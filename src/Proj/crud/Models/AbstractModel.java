package Proj.crud.Models;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Abstract Model
 * @author Kamil
 */
abstract public class AbstractModel {

    protected static Connection con;

    public static Connection getConnection() throws SQLException {
        con = DriverManager.getConnection("jdbc:sqlserver://"
                + "localhost:1433;databaseName=projekt;"
                + "user=kamiljano99;password=haslo123;");
        return con;
    }

    /**
     * Delete model from DB
     * @return
     * @throws SQLException 
     */
    abstract public boolean delete() throws SQLException;

    /**
     * Save model to DB
     * @return
     * @throws SQLException 
     */
    abstract public AbstractModel create() throws SQLException;

    /**
     * Update model in DB
     * @return
     * @throws SQLException 
     */
    abstract public AbstractModel update() throws SQLException;
    
    /**
     * Get Single model from DB
     * @return
     * @throws SQLException 
     */
    abstract public AbstractModel getOne() throws SQLException;
}
