/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proj.crud.Models;

import Proj.Exceptions.ValidationException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kamil
 */
public class Sektor extends AbstractModel {

    private static final String SQL_DELETE = "DELETE FROM sektory WHERE id=?";
    static final String SQL_GET = "SELECT * FROM sektory";
    private static final String SQL_GET_ONE = "SELECT * FROM sektory WHERE id=?";
    private static final String SQL_UPDATE = "UPDATE sektory SET administrator_id=?, nazwa=?, stan=? WHERE id=?";
    private static final String SQL_INSERT = "INSERT INTO sektory(administrator_id, nazwa, stan) VALUES (?, ?, ?)";
    
    private Integer id;
    private Integer administrator_id;
    private String nazwa;
    private String stan;

    
    
    public Sektor(Integer id) {
        this.id = id;
    }

    
    public Sektor(Integer administrator_id, String nazwa, String stan) throws SQLException {
        this.administrator_id = administrator_id;
        this.nazwa = nazwa;
        this.stan = stan;
    }

   
    public Sektor(Integer administrator_id, String nazwa, String stan, Integer id) throws SQLException {
        this.administrator_id = administrator_id;
        this.nazwa = nazwa;
        this.stan = stan;
        this.id = id;
    }
    
    public Integer getId(){
        return this.id;
    }
    
    public Integer getAdministratorID(){
        return this.administrator_id;
    }
    
    public Sektor setAdministratorID(Integer administrator_id){
        this.administrator_id = administrator_id;
        return this;
    }
    
    public String getNazwa(){
        return this.nazwa;
    }
    
    public Sektor setNazwa(String nazwa){
        this.nazwa = nazwa;
        return this;
    }
    
    public String getStan(){
        return this.stan;
    }
    
    public Sektor setStan(String stan){
        this.stan = stan;
        return this;
    }
    
    
    
    public static List<Sektor> getList() throws SQLException {
        List<Sektor> sektoryList = new ArrayList<>();
        Statement stmt = AbstractModel.getConnection().createStatement();
        ResultSet result = stmt.executeQuery(Sektor.SQL_GET);

        while (result.next()) {
            sektoryList.add(
                    new Sektor(
                            result.getInt("administrator_id"),
                            result.getString("nazwa"),
                            result.getString("stan"),
                            result.getInt("id")
                    )
            );
        }

        return sektoryList;
    }
    
    
    
    @Override
    public boolean delete() throws SQLException {
        PreparedStatement preparedStatement = AbstractModel.getConnection().prepareStatement(SQL_DELETE);
        preparedStatement.setInt(1, this.id);
        preparedStatement.executeUpdate();
        return true;
    }

    @Override
    public AbstractModel create() throws SQLException, ValidationException {
        if (!this.validate()) {
            throw new ValidationException("Nie wszystkie pola zostały wypełnione, lub zostały wypełnione niepoprawnie");
        }
        PreparedStatement preparedStatement = AbstractModel.getConnection().prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setInt(1, this.administrator_id);
        preparedStatement.setString(2, this.nazwa);
        preparedStatement.setString(3, this.stan);
        preparedStatement.executeUpdate();

        ResultSet rs = preparedStatement.getGeneratedKeys();
        if (rs.next()) {
            this.id = rs.getInt(1);
        }

        return this;
    }

    @Override
    public AbstractModel update() throws SQLException, ValidationException {
        if (!this.validate()) {
            throw new ValidationException("Nie wszystkie pola zostały wypełnione, lub zostały wypełnione niepoprawnie");
        }
        PreparedStatement preparedStatement = AbstractModel.getConnection().prepareStatement(SQL_UPDATE);
        preparedStatement.setInt(1, this.administrator_id);
        preparedStatement.setString(2, this.nazwa);
        preparedStatement.setString(3, this.stan);
        preparedStatement.setInt(4, this.id);
        preparedStatement.executeUpdate();
        return this;
    }

    @Override
    public AbstractModel getOne() throws SQLException {
        PreparedStatement preparedStatement = AbstractModel.getConnection().prepareStatement(SQL_GET_ONE);
        preparedStatement.setInt(1, this.id);
        ResultSet result = preparedStatement.executeQuery();

        while (result.next()) {
            this.id = result.getInt("id");
            this.administrator_id = result.getInt("administrator_id");
            this.nazwa = result.getString("nazwa");
            this.stan = result.getString("stan");

        }

        return this;
    }

    @Override
    protected boolean validate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

