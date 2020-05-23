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
import java.sql.PreparedStatement;


/**
 *
 * @author Kamil
 */
public class Magazyn extends AbstractModel {

    private static final String SQL_DELETE = "DELETE FROM magazyny WHERE id=?";
    static final String SQL_GET = "SELECT * FROM magazyny";
    private static final String SQL_GET_ONE = "SELECT * FROM magazyny WHERE id=?";
    private static final String SQL_UPDATE = "UPDATE magazyny SET uzytkownik_id=?, nazwa=?, przeznaczenie=? WHERE id=?";
    private static final String SQL_INSERT = "INSERT INTO magazyny(uzytkownik_id, nazwa, przeznaczenie) VALUES (?, ?, ?)";
    
    private Integer id;
    private Integer uzytkownik_id;
    private String nazwa;
    private String przeznaczenie;

    
    
    public Magazyn(Integer id) {
        this.id = id;
    }

    
    public Magazyn(Integer uzytkownik_id, String nazwa, String przeznaczenie) throws SQLException {
        this.uzytkownik_id = uzytkownik_id;
        this.nazwa = nazwa;
        this.przeznaczenie = przeznaczenie;
    }

   
    public Magazyn(Integer uzytkownik_id, String nazwa, String przeznaczenie, Integer id) throws SQLException {
        this.uzytkownik_id = uzytkownik_id;
        this.nazwa = nazwa;
        this.przeznaczenie = przeznaczenie;
        this.id = id;
    }
    
    
    public Integer getUzytkownikID(){
        return this.uzytkownik_id;
    }
    
    public Magazyn setUzytkownikID(Integer uzytkownik_id){
        this.uzytkownik_id = uzytkownik_id;
        return this;
    }
    
    public String getNazwa(){
        return this.nazwa;
    }
    
    public Magazyn setNazwa(String nazwa){
        this.nazwa = nazwa;
        return this;
    }
    
    public String getPrzeznaczenie(){
        return this.przeznaczenie;
    }
    
    public Magazyn setPrzeznaczenie(String przeznaczenie){
        this.przeznaczenie = przeznaczenie;
        return this;
    }
    
    
    
    public static List<Magazyn> getList() throws SQLException {
        List<Magazyn> magazynyList = new ArrayList<>();
        Statement stmt = AbstractModel.getConnection().createStatement();
        ResultSet result = stmt.executeQuery(Magazyn.SQL_GET);

        while (result.next()) {
            magazynyList.add(
                    new Magazyn(
                            result.getInt("uzytkownik_id"),
                            result.getString("nazwa"),
                            result.getString("przeznaczenie"),
                            result.getInt("id")
                    )
            );
        }

        return magazynyList;
    }
    
    
    
    @Override
    public boolean delete() throws SQLException {
        PreparedStatement preparedStatement = AbstractModel.getConnection().prepareStatement(SQL_DELETE);
        preparedStatement.setInt(1, this.id);
        preparedStatement.executeUpdate();
        return true;
    }

    @Override
    public AbstractModel create() throws SQLException {
        PreparedStatement preparedStatement = AbstractModel.getConnection().prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setInt(1, this.uzytkownik_id);
        preparedStatement.setString(2, this.nazwa);
        preparedStatement.setString(3, this.przeznaczenie);
        preparedStatement.executeUpdate();

        ResultSet rs = preparedStatement.getGeneratedKeys();
        if (rs.next()) {
            this.id = rs.getInt(1);
        }

        return this;
    }

    @Override
    public AbstractModel update() throws SQLException {
        PreparedStatement preparedStatement = AbstractModel.getConnection().prepareStatement(SQL_UPDATE);
        preparedStatement.setInt(1, this.uzytkownik_id);
        preparedStatement.setString(2, this.nazwa);
        preparedStatement.setString(3, this.przeznaczenie);
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
            this.uzytkownik_id = result.getInt("uzytkownik_id");
            this.nazwa = result.getString("nazwa");
            this.przeznaczenie = result.getString("przeznaczenie");

        }

        return this;
    }
    
}
