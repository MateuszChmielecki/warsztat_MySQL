package pl.coderslab.entity;

import java.sql.*;
import java.util.Arrays;

public class UserDao{

    private static final String CREATE_USER_QUERY = "insert into users(username, email, password) values (?, ?, ?)";
    private static final String READ_QUERY = "select * from users where id = ?";
    private static final String UPDATE_QUERY = "update users set username = ?, email = ?, password = ? where id = ?";
    private static final String DELETE_QUERY = "delete from users where id = ?";
    private static final String FIND_ALL_QUERY = "select * from users;";



    public User create (User user){

        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(CREATE_USER_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());

            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            while(rs.next()) {
                user.setId(rs.getInt(1));
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String hashPassword(String password) {

        return JBCrypt.hashpw(password, JBCrypt.gensalt());

    }
    public static User read(int userId){
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(READ_QUERY);
            statement.setInt(1, userId);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                User user = new User (result.getString(2),result.getString(3),result.getString(4));
                user.setId(result.getInt(1));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }
    public static void delete (int userId) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(DELETE_QUERY);
            statement.setInt(1, userId);
            statement.executeUpdate();

        }catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public static void update(User user){
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(UPDATE_QUERY);
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getEmail());
            statement.setString(3, hashPassword(user.getPassword()));
            statement.setInt(4, user.getId());
            statement.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static User[] findAll() {

        try (Connection conn = DbUtil.getConnection()) {
            User[] users = new User[0];
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_QUERY);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new User(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4));
                users = addToArray(user, users);
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static User[] addToArray(User u, User[] users) {
        users = Arrays.copyOf(users, users.length + 1); // Tworzymy kopię tablicy powiększoną o 1.
        users[users.length -1] = u; // Dodajemy obiekt na ostatniej pozycji.
        return users;
    }
}

