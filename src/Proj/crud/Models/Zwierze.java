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
 * User model
 *
 * @author Kamil
 */
public class Zwierze extends AbstractModel {

    private static final String SQL_DELETE = "DELETE FROM zwierzeta WHERE id=?";
    private static final String SQL_GET = "SELECT * FROM zwierzeta";
    private static final String SQL_GET_ONE = "SELECT * FROM zwierzeta WHERE id=?";
    private static final String SQL_UPDATE = "UPDATE zwierzeta SET  plec=?, imie=?, wiek_lata=?, waga_kg=?, gatunek_id=? WHERE id=?";
    private static final String SQL_INSERT = "INSERT INTO zwierzeta(gatunek_id, plec, imie, wiek_lata, waga_kg) VALUES (?, ?, ?, ?, ?)";

    private Integer gatunek_id = -1;
    private String imie;
    private String plec;
    private Integer id;
    private Integer waga_kg;
    private Integer wiek_lata;

    public Zwierze(String plec, String imie, int wiek_lata, int waga_kg, Integer gatunek_id) throws SQLException {
        this.plec = plec;
        this.imie = imie;
        this.wiek_lata = wiek_lata;
        this.waga_kg = waga_kg;
        this.gatunek_id = gatunek_id;
    }

    public Zwierze(String plec, String imie, int wiek_lata, int waga_kg, Integer gatunek_id, Integer id) throws SQLException {
        this.plec = plec;
        this.imie = imie;
        this.wiek_lata = wiek_lata;
        this.waga_kg = waga_kg;
        this.gatunek_id = gatunek_id;
        this.id = id;
    }

    public Zwierze(Integer id) {
        this.id = id;
    }

    public Zwierze setImie(String imie) {
        this.imie = imie;
        return this;
    }

    public Zwierze setGatunekID(int gatunek_id) {
        this.gatunek_id = gatunek_id;
        return this;
    }

    public Zwierze setPlec(String plec) {
        this.plec = plec;
        return this;
    }

    public Zwierze setWaga(int waga_kg) {
        this.waga_kg = waga_kg;
        return this;
    }

    public Zwierze setWiek(int wiek_lata) {
        this.wiek_lata = wiek_lata;
        return this;
    }

    public String getImie() {
        return this.imie;
    }

    public String getPlec() {
        return this.plec;
    }

    public Integer getWaga() {
        return this.waga_kg;
    }

    public Integer getWiek() {
        return this.wiek_lata;
    }

    public Integer getGatunekID() {
        return this.gatunek_id;
    }

    public Integer getId() {
        return this.id;
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
        preparedStatement.setInt(1, this.gatunek_id);
        preparedStatement.setString(2, this.plec);
        preparedStatement.setString(3, this.imie);
        preparedStatement.setInt(4, this.wiek_lata);
        preparedStatement.setInt(5, this.waga_kg);
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
        preparedStatement.setString(1, this.plec);
        preparedStatement.setString(2, this.imie);
        preparedStatement.setInt(3, this.wiek_lata);
        preparedStatement.setInt(4, this.waga_kg);
        preparedStatement.setInt(5, this.gatunek_id);
        preparedStatement.setInt(6, this.id);
        preparedStatement.executeUpdate();
        return this;
    }

    public static List<Zwierze> getList() throws SQLException {
        List<Zwierze> zwierzetaList = new ArrayList<>();
        Statement stmt = AbstractModel.getConnection().createStatement();
        ResultSet result = stmt.executeQuery(Zwierze.SQL_GET);

        while (result.next()) {
            zwierzetaList.add(
                    new Zwierze(
                            result.getString("plec"),
                            result.getString("imie"),
                            result.getInt("wiek_lata"),
                            result.getInt("waga_kg"),
                            result.getInt("gatunek_id"),
                            result.getInt("id")
                    )
            );
        }

        return zwierzetaList;
    }

    @Override
    public AbstractModel getOne() throws SQLException {
        PreparedStatement preparedStatement = AbstractModel.getConnection().prepareStatement(SQL_GET_ONE);
        preparedStatement.setInt(1, this.id);
        ResultSet result = preparedStatement.executeQuery();

        while (result.next()) {
            this.plec = result.getString("plec");
            this.imie = result.getString("imie");
            this.wiek_lata = result.getInt("wiek_lata");
            this.waga_kg = result.getInt("waga_kg");
            this.gatunek_id = result.getInt("gatunek_id");
            this.id = result.getInt("id");
        }

        return this;
    }

    @Override
    protected boolean validate() {
        Pattern LettersPattern = Patterns.Patterns.getLettersPattern();
        Pattern SexPattern = Patterns.Patterns.getSexPattern();

        return !(this.imie.trim().equals("")
                || this.plec.trim().equals("")
                || this.wiek_lata < 0
                || this.waga_kg < 0
                || this.gatunek_id == -1
                || !SexPattern.matcher(this.plec).matches()
                || !LettersPattern.matcher(this.imie).matches());
    }

}
