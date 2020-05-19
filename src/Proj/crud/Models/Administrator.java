
package Proj.crud.Models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;


public class Administrator extends AbstractModel {

    private static final String SQL_DELETE = "DELETE FROM administratorzy WHERE id=?";
    private static final String SQL_GET = "SELECT * FROM administratorzy";
    private static final String SQL_GET_ONE = "SELECT * FROM administratorzy WHERE id=?";
    private static final String SQL_UPDATE = "UPDATE administratorzy SET nazwa=?, login=?, haslo=? WHERE id=?";
    private static final String SQL_INSERT = "INSERT INTO administratorzy(nazwa, login, haslo) VALUES (?, ?, ?)";

    private String nazwa;
    private String login;
    private String password;
    private Integer id;


    public Administrator(String nazwa, String login, String password) throws SQLException {
        this.nazwa = nazwa;
        this.login = login;
        this.password = password;
    }


    public Administrator(String nazwa, String login, String password, Integer id) throws SQLException {
        this.nazwa = nazwa;
        this.login = login;
        this.password = password;
        this.id = id;
    }


    public Administrator(Integer id) {
        this.id = id;
    }


    public Administrator setNazwa(String firstname) {
        this.nazwa = firstname;
        return this;
    }


    public Administrator setLogin(String login) {
        this.login = login;
        return this;
    }

    public Administrator setPassword(String password) {
        this.password = password;
        return this;
    }

    
    public String getNazwa(){
        return this.nazwa;
    }

    public String getLogin() {
        return this.login;
    }


    public String getPassword() {
        return this.password;
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
    public AbstractModel create() throws SQLException {
        PreparedStatement preparedStatement = AbstractModel.getConnection().prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, this.nazwa);
        preparedStatement.setString(2, this.login);
        preparedStatement.setString(3, this.password);
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
        preparedStatement.setString(1, this.nazwa);
        preparedStatement.setString(2, this.login);
        preparedStatement.setString(3, this.password);
        preparedStatement.setInt(4, this.id);
        preparedStatement.executeUpdate();
        return this;
    }

    public static List<Administrator> getList() throws SQLException {
        List<Administrator> administratorsList = new ArrayList<>();
        Statement stmt = AbstractModel.getConnection().createStatement();
        ResultSet result = stmt.executeQuery(Administrator.SQL_GET);

        while (result.next()) {
            administratorsList.add(
                    new Administrator(
                            result.getString("nazwa"),
                            result.getString("login"),
                            result.getString("haslo"),
                            result.getInt("id")
                    )
            );
        }

        return administratorsList;
    }

    @Override
    public AbstractModel getOne() throws SQLException {
        PreparedStatement preparedStatement = AbstractModel.getConnection().prepareStatement(SQL_GET_ONE);
        preparedStatement.setInt(1, this.id);
        ResultSet result = preparedStatement.executeQuery();

        while (result.next()) {
            this.id = result.getInt("id");
            this.nazwa = result.getString("nazwa");
            this.login = result.getString("login");
            this.password = result.getString("haslo");
        }

        return this;
    }

    
    
}
