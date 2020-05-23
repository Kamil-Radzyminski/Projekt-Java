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
public class Towar extends AbstractModel {

    private static final String SQL_DELETE = "DELETE FROM towary WHERE id=?";
    static final String SQL_GET = "SELECT * FROM towary";
    private static final String SQL_GET_ONE = "SELECT * FROM towary WHERE id=?";
    private static final String SQL_UPDATE = "UPDATE towary SET magazyn_id=?, nazwa=?, ilosc=? WHERE id=?";
    private static final String SQL_INSERT = "INSERT INTO towary(magazyn_id, nazwa, ilosc) VALUES (?, ?, ?)";
    
    private Integer id;
    private Integer magazyn_id;
    private String nazwa;
    private Double ilosc;

    
    
    public Towar(Integer id) {
        this.id = id;
    }

    
    public Towar(Integer magazyn_id, String nazwa, Double ilosc) throws SQLException {
        this.magazyn_id = magazyn_id;
        this.nazwa = nazwa;
        this.ilosc = ilosc;
    }

   
    public Towar(Integer magazyn_id, String nazwa, Double ilosc, Integer id) throws SQLException {
        this.magazyn_id = magazyn_id;
        this.nazwa = nazwa;
        this.ilosc = ilosc;
        this.id = id;
    }
    
    
    public Integer getMagazynID(){
        return this.magazyn_id;
    }
    
    public Towar setMagazynID(Integer magazyn_id){
        this.magazyn_id = magazyn_id;
        return this;
    }
    
    public String getNazwa(){
        return this.nazwa;
    }
    
    public Towar setNazwa(String nazwa){
        this.nazwa = nazwa;
        return this;
    }
    
    public Double getIlosc(){
        return this.ilosc;
    }
    
    public Towar setIlosc(Double ilosc){
        this.ilosc = ilosc;
        return this;
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
        preparedStatement.setInt(1, this.magazyn_id);
        preparedStatement.setString(2, this.nazwa);
        preparedStatement.setDouble(3, this.ilosc);
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
        preparedStatement.setInt(1, this.magazyn_id);
        preparedStatement.setString(2, this.nazwa);
        preparedStatement.setDouble(3, this.ilosc);
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
            this.magazyn_id = result.getInt("magazyn_id");
            this.nazwa = result.getString("nazwa");
            this.ilosc = result.getDouble("ilosc");

        }

        return this;
    }
        public static List<Towar> getList() throws SQLException {
        List<Towar> towaryList = new ArrayList<>();
        Statement stmt = AbstractModel.getConnection().createStatement();
        ResultSet result = stmt.executeQuery(Towar.SQL_GET);

        while (result.next()) {
            towaryList.add(
                    new Towar(
                            result.getInt("magazyn_id"),
                            result.getString("nazwa"),
                            result.getDouble("ilosc"),
                            result.getInt("id")
                    )
            );
        }

        return towaryList;
    }
}
