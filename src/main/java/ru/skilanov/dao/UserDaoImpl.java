package ru.skilanov.dao;

import ru.skilanov.database.ConnectionFactory;
import ru.skilanov.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private static final String SELECT_ALL = "SELECT * FROM users";
    private static final String INSERT = "INSERT INTO users (name, login, password, role, email) VALUES (?,?,?,?,?)";
    private static final String DELETE = "DELETE FROM users WHERE id=?";
    private static final String UPDATE = "UPDATE users SET name=?, login=?, password=?, role=?, email=? WHERE id=?";
    private static final String FIND_BY_ID = "SELECT * FROM users WHERE id=?";
    private static final String FIND_BY_LOGIN_AND_PASS = "SELECT * FROM users WHERE login=? AND password=?";
    private static final String FIND_ROLE_BY_LOGIN_AND_PASSWORD = "SELECT role FROM users WHERE login=? AND password=?";

    private ConnectionFactory connectionFactory = ConnectionFactory.getInstance();

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try (Connection connection = connectionFactory.getConnection()) {
            PreparedStatement st = connection.prepareStatement(SELECT_ALL);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                users.add(new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("login"),
                        rs.getString("password"),
                        User.ROLE.valueOf(rs.getString("role")),
                        rs.getString("email")));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return users;
    }

    @Override
    public void insert(User user) {
        try (Connection connection = connectionFactory.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(INSERT);
            ps.setString(1, user.getName());
            ps.setString(2, user.getLogin());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getRole().toString());
            ps.setString(5, user.getEmail());
            ps.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    @Override
    public void deleteById(int id) {
        try (Connection connection = connectionFactory.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(DELETE);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    @Override
    public void update(User user) {
        try (Connection connection = connectionFactory.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(UPDATE);
            ps.setString(1, user.getName());
            ps.setString(2, user.getLogin());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getRole().toString());
            ps.setString(5, user.getEmail());
            ps.setInt(6, user.getId());
            ps.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    @Override
    public User findById(int id) {
        try (Connection connection = connectionFactory.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(FIND_BY_ID);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                return new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("login"),
                        rs.getString("password"),
                        User.ROLE.valueOf(rs.getString("role")),
                        rs.getString("email"));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean userIsExist(String login, String password) {
        boolean result = false;
        try (Connection connection = connectionFactory.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(FIND_BY_LOGIN_AND_PASS);
            ps.setString(1, login);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                result=true;
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return result;
    }

    @Override
    public User.ROLE getRoleByLoginPassword(String login, String password) {
        User.ROLE result = User.ROLE.UNKNOWN;
        try (Connection connection = connectionFactory.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(FIND_ROLE_BY_LOGIN_AND_PASSWORD);
            ps.setString(1, login);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
               result= User.ROLE.valueOf(rs.getString("role"));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return result;
    }
}
