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
public class Gatunek extends AbstractModel {

    private static final String SQL_DELETE = "DELETE FROM gatunki WHERE id=?";
    static final String SQL_GET = "SELECT * FROM gatunki";
    private static final String SQL_GET_ONE = "SELECT * FROM gatunki WHERE id=?";
    private static final String SQL_UPDATE = "UPDATE gatunki SET rodzina_id=?, nazwa=?, opis=? WHERE id=?";
    private static final String SQL_INSERT = "INSERT INTO gatunki(rodzina_id, nazwa, opis) VALUES (?, ?, ?)";
    
    private Integer id;
    private Integer rodzina_id;
    private String nazwa;
    private String opis;
    
    
    public Gatunek(Integer id) {
        this.id = id;
    }

    
    public Gatunek(Integer rodzina_id, String nazwa, String opis) throws SQLException {
        this.rodzina_id = rodzina_id;
        this.nazwa = nazwa;
        this.opis = opis;
    }

   
    public Gatunek(Integer rodzina_id, String nazwa, String opis, Integer id) throws SQLException {
        this.rodzina_id = rodzina_id;
        this.nazwa = nazwa;
        this.opis = opis;
        this.id = id;
    }
    
    public Integer getId(){
        return this.id;
    }
    
    public Integer getRodzinaID(){
        return this.rodzina_id;
    }
    
    public Gatunek setRodzinaID(Integer rodzina_id){
        this.rodzina_id = rodzina_id;
        return this;
    }
    
    public String getNazwa(){
        return this.nazwa;
    }
    
    public Gatunek setNazwa(String nazwa){
        this.nazwa = nazwa;
        return this;
    }
    
    public String getOpis(){
        return this.opis;
    }
    
    public Gatunek setOpis(String opis){
        this.opis = opis;
        return this;
    }
    
    
    
    public static List<Gatunek> getList() throws SQLException {
        List<Gatunek> gatunkiList = new ArrayList<>();
        Statement stmt = AbstractModel.getConnection().createStatement();
        ResultSet result = stmt.executeQuery(Gatunek.SQL_GET);

        while (result.next()) {
            gatunkiList.add(
                    new Gatunek(
                            result.getInt("rodzina_id"),
                            result.getString("nazwa"),
                            result.getString("opis"),
                            result.getInt("id")
                    )
            );
        }

        return gatunkiList;
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
        preparedStatement.setInt(1, this.rodzina_id);
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
        preparedStatement.setInt(1, this.rodzina_id);
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
            this.rodzina_id = result.getInt("rodzina_id");
            this.nazwa = result.getString("nazwa");
            this.opis = result.getString("opis");

        }

        return this;
    }
    
}
