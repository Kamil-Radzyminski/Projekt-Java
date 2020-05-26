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
public class User extends AbstractModel {

    private static final String SQL_DELETE = "DELETE FROM uzytkownicy WHERE id=?";
    private static final String SQL_GET = "SELECT * FROM uzytkownicy";
    private static final String SQL_GET_ONE = "SELECT * FROM uzytkownicy WHERE id=?";
    private static final String SQL_UPDATE = "UPDATE uzytkownicy SET sektor_id=?, nazwisko=?, imie=?, rola=?, login=?, haslo=? WHERE id=?";
    private static final String SQL_INSERT = "INSERT INTO uzytkownicy(sektor_id, nazwisko, imie, rola, login, haslo) VALUES (?, ?, ?, ?, ?, ?)";

    private String firstname;
    private String lastname;
    private String role;
    private String login;
    private String password;
    private Integer id;
    private Integer sectorId = -1;

    /**
     * Create User
     *
     * @param firstname
     * @param lastname
     * @param role
     * @param login
     * @param password
     * @param sectorId
     * @throws SQLException
     */
    public User(String firstname, String lastname, String role, String login, String password, int sectorId) throws SQLException {
        this.firstname = firstname;
        this.lastname = lastname;
        this.role = role;
        this.login = login;
        this.password = password;
        this.sectorId = sectorId;
    }

    /**
     * Create user with id
     *
     * @param firstname
     * @param lastname
     * @param role
     * @param login
     * @param password
     * @param sectorId
     * @param id
     * @throws SQLException
     */
    public User(String firstname, String lastname, String role, String login, String password, int sectorId, Integer id) throws SQLException {
        this.firstname = firstname;
        this.lastname = lastname;
        this.role = role;
        this.login = login;
        this.password = password;
        this.sectorId = sectorId;
        this.id = id;
    }

    /**
     * Create user only by id
     *
     * @param id
     */
    public User(Integer id) {
        this.id = id;
    }

    /**
     * Set firstname
     *
     * @param firstname
     * @return
     */
    public User setFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    /**
     * Set lastname
     *
     * @param lastname
     * @return
     */
    public User setLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    /**
     * Set role
     *
     * @param role
     * @return
     */
    public User setRole(String role) {
        this.role = role;
        return this;
    }

    /**
     * Set login
     *
     * @param login
     * @return
     */
    public User setLogin(String login) {
        this.login = login;
        return this;
    }

    /**
     * Set password
     *
     * @param password
     * @return
     */
    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    /**
     * Set sectorId
     *
     * @param sectorId
     * @return
     */
    public User setSectorId(int sectorId) {
        this.sectorId = sectorId;
        return this;
    }

    /**
     * Get firstname
     *
     * @return
     */
    public String getFirstname() {
        return this.firstname;
    }

    /**
     * Get lastname
     *
     * @return
     */
    public String getLastname() {
        return this.lastname;
    }

    /**
     * Get role
     *
     * @return
     */
    public String getRole() {
        return this.role;
    }

    /**
     * Get login
     *
     * @return
     */
    public String getLogin() {
        return this.login;
    }

    /**
     * Get password
     *
     * @return
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Get id
     *
     * @return
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * Get sectorID
     * @return
     */
    public int getSectorId() {
        return this.sectorId;
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
        preparedStatement.setInt(1, this.sectorId);
        preparedStatement.setString(2, this.lastname);
        preparedStatement.setString(3, this.firstname);
        preparedStatement.setString(4, this.role);
        preparedStatement.setString(5, this.login);
        preparedStatement.setString(6, this.password);
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
        preparedStatement.setInt(1, this.sectorId);
        preparedStatement.setString(2, this.lastname);
        preparedStatement.setString(3, this.firstname);
        preparedStatement.setString(4, this.role);
        preparedStatement.setString(5, this.login);
        preparedStatement.setString(6, this.password);
        preparedStatement.setInt(7, this.id);
        preparedStatement.executeUpdate();
        return this;
    }

    public static List<User> getList() throws SQLException {
        List<User> usersList = new ArrayList<>();
        Statement stmt = AbstractModel.getConnection().createStatement();
        ResultSet result = stmt.executeQuery(User.SQL_GET);

        while (result.next()) {
            usersList.add(
                    new User(
                            result.getString("imie"),
                            result.getString("nazwisko"),
                            result.getString("rola"),
                            result.getString("login"),
                            result.getString("haslo"),
                            result.getInt("sektor_id"),
                            result.getInt("id")
                    )
            );
        }

        return usersList;
    }

    @Override
    public AbstractModel getOne() throws SQLException {
        PreparedStatement preparedStatement = AbstractModel.getConnection().prepareStatement(SQL_GET_ONE);
        preparedStatement.setInt(1, this.id);
        ResultSet result = preparedStatement.executeQuery();

        while (result.next()) {
            this.id = result.getInt("id");
            this.firstname = result.getString("imie");
            this.lastname = result.getString("nazwisko");
            this.login = result.getString("login");
            this.password = result.getString("haslo");
            this.role = result.getString("rola");
            this.sectorId = result.getInt("sektor_id");
        }

        return this;
    }

    @Override
    protected boolean validate() {
        Pattern ImieNazwiskoPattern = Pattern.compile("[A-Z]{1}[a-z]*");
        Pattern RolaPattern = Pattern.compile("(pracownik|kierownik){1}");

        return !(this.firstname.trim().equals("")
                || this.lastname.trim().equals("")
                || this.role.trim().equals("")
                || this.login.trim().equals("")
                || this.password.trim().equals("")
                || this.sectorId == -1
                || !ImieNazwiskoPattern.matcher(this.firstname).matches()
                || !ImieNazwiskoPattern.matcher(this.lastname).matches()
                || !RolaPattern.matcher(this.role).matches());
    }

}
