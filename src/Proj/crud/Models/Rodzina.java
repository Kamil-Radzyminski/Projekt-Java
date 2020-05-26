/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proj.crud.Models;

import Proj.Exceptions.ValidationException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.util.regex.Pattern;

/**
 *
 * @author Kamil
 */
public class Rodzina extends AbstractModel {

    private static final String SQL_DELETE = "DELETE FROM rodziny WHERE id=?";
    private static final String SQL_GET = "SELECT * FROM rodziny";
    private static final String SQL_GET_ONE = "SELECT * FROM rodziny WHERE id=?";
    private static final String SQL_UPDATE = "UPDATE rodziny SET gromada_id=?, nazwa=?, opis=? WHERE id=?";
    private static final String SQL_INSERT = "INSERT INTO rodziny(gromada_id, nazwa, opis) VALUES (?, ?, ?)";

    private Integer id;
    private Integer gromada_id = -1;
    private String nazwa;
    private String opis;

    public Rodzina(Integer id) {
        this.id = id;
    }

    public Rodzina(Integer gromada_id, String nazwa, String opis) throws SQLException {
        this.gromada_id = gromada_id;
        this.nazwa = nazwa;
        this.opis = opis;
    }

    public Rodzina(Integer gromada_id, String nazwa, String opis, Integer id) throws SQLException {
        this.gromada_id = gromada_id;
        this.nazwa = nazwa;
        this.opis = opis;
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public Integer getGromadaID() {
        return this.gromada_id;
    }

    public Rodzina setGromadaID(Integer gromada_id) {
        this.gromada_id = gromada_id;
        return this;
    }

    public String getNazwa() {
        return this.nazwa;
    }

    public Rodzina setNazwa(String nazwa) {
        this.nazwa = nazwa;
        return this;
    }

    public String getOpis() {
        return this.opis;
    }

    public Rodzina setOpis(String opis) {
        this.opis = opis;
        return this;
    }

    public static List<Rodzina> getList() throws SQLException {
        List<Rodzina> rodzinyList = new ArrayList<>();
        Statement stmt = AbstractModel.getConnection().createStatement();
        ResultSet result = stmt.executeQuery(Rodzina.SQL_GET);

        while (result.next()) {
            rodzinyList.add(
                    new Rodzina(
                            result.getInt("gromada_id"),
                            result.getString("nazwa"),
                            result.getString("opis"),
                            result.getInt("id")
                    )
            );
        }

        return rodzinyList;
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
        preparedStatement.setInt(1, this.gromada_id);
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
    public AbstractModel update() throws SQLException, ValidationException {
        if (!this.validate()) {
            throw new ValidationException("Nie wszystkie pola zostały wypełnione, lub zostały wypełnione niepoprawnie");
        }
        PreparedStatement preparedStatement = AbstractModel.getConnection().prepareStatement(SQL_UPDATE);
        preparedStatement.setInt(1, this.gromada_id);
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
            this.gromada_id = result.getInt("gromada_id");
            this.nazwa = result.getString("nazwa");
            this.opis = result.getString("opis");

        }

        return this;
    }

    @Override
    protected boolean validate() {
        Pattern LettersPattern = Patterns.Patterns.getLettersPattern();
        Pattern IloscPattern = Patterns.Patterns.getIloscPattern();

        return !(this.nazwa.trim().equals("")
                || this.opis.trim().equals("")
                || this.gromada_id == -1
                || !LettersPattern.matcher(this.nazwa).matches()
                || !IloscPattern.matcher(String.valueOf(this.ilosc)).matches());
    }
}
