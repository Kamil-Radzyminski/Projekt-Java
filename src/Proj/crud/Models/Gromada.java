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
public class Gromada extends AbstractModel {

    private static final String SQL_DELETE = "DELETE FROM gromady WHERE id=?";
    static final String SQL_GET = "SELECT * FROM gromady";
    private static final String SQL_GET_ONE = "SELECT * FROM gromady WHERE id=?";
    private static final String SQL_UPDATE = "UPDATE gromady SET sektor_id=?, nazwa=?, opis=? WHERE id=?";
    private static final String SQL_INSERT = "INSERT INTO gromady(sektor_id, nazwa, opis) VALUES (?, ?, ?)";
    
    private Integer id;
    private Integer sektor_id;
    private String nazwa;
    private String opis;

    
    
    public Gromada(Integer id) {
        this.id = id;
    }

    
    public Gromada(Integer sektor_id, String nazwa, String opis) throws SQLException {
        this.sektor_id = sektor_id;
        this.nazwa = nazwa;
        this.opis = opis;
    }

   
    public Gromada(Integer sektor_id, String nazwa, String opis, Integer id) throws SQLException {
        this.sektor_id = sektor_id;
        this.nazwa = nazwa;
        this.opis = opis;
        this.id = id;
    }
    
    public Integer getId(){
        return this.id;
    }
    
    public Integer getSektorID(){
        return this.sektor_id;
    }
    
    public Gromada setSektorID(Integer sektor_id){
        this.sektor_id = sektor_id;
        return this;
    }
    
    public String getNazwa(){
        return this.nazwa;
    }
    
    public Gromada setNazwa(String nazwa){
        this.nazwa = nazwa;
        return this;
    }
    
    public String getOpis(){
        return this.opis;
    }
    
    public Gromada setOpis(String opis){
        this.opis = opis;
        return this;
    }
    
    
    
    public static List<Gromada> getList() throws SQLException {
        List<Gromada> gromadyList = new ArrayList<>();
        Statement stmt = AbstractModel.getConnection().createStatement();
        ResultSet result = stmt.executeQuery(Gromada.SQL_GET);

        while (result.next()) {
            gromadyList.add(
                    new Gromada(
                            result.getInt("sektor_id"),
                            result.getString("nazwa"),
                            result.getString("opis"),
                            result.getInt("id")
                    )
            );
        }

        return gromadyList;
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
        preparedStatement.setInt(1, this.sektor_id);
        preparedStatement.setString(2, this.nazwa);
        preparedStatement.setString(3, this.opis);
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
        preparedStatement.setInt(1, this.sektor_id);
        preparedStatement.setString(2, this.nazwa);
        preparedStatement.setString(3, this.opis);
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
            this.sektor_id = result.getInt("sektor_id");
            this.nazwa = result.getString("nazwa");
            this.opis = result.getString("opis");

        }

        return this;
    }
    
}
