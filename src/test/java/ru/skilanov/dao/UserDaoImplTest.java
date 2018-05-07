package ru.skilanov.dao;

import org.apache.commons.dbcp.BasicDataSource;
import org.h2.tools.RunScript;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.skilanov.model.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.h2.engine.Constants.UTF8;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class UserDaoImplTest {
    /**
     * Дао пользователя.
     */
    private UserDao userDao;

    /**
     * Перед стартом тестов создаем базу данных.
     *
     * @throws Exception Exception
     */
    @BeforeClass
    public static void createSchema() throws Exception {
        String db = "src\\test\\resources\\schema.sql";
        RunScript.execute("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1",
                "sa", "", db, UTF8, false);
    }

    /**
     * Перед стартом теста инициализирем Dao.
     */
    @Before
    public void setUp() {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        userDao = new UserDaoImpl(connectionFactory);
    }

    /**
     * После завершения теста.
     *
     * @throws Exception Exception
     */
    @After
    public void tearDown() throws Exception {
        try (Connection connection = new ConnectionFactory().getConnection()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate("ALTER TABLE users ALTER COLUMN id RESTART WITH 1");
        }
    }

    /**
     * Тестирует получение всех пользователей.
     */
    @Test
    public void whenGetAll_ThenReturnNotEmptyListOfUsers() {
        User userSam = new User("Sam Kilanoff", "Sam", "1", User.ROLE.ADMIN, "sam@email.com");
        User userMike = new User("Mike Johns", "Mike", "2", User.ROLE.USER, "mike@email.com");

        userDao.insert(userSam);
        userDao.insert(userMike);

        List<User> expectedUsers = userDao.getAll();

        assertNotNull(expectedUsers);
    }

    /**
     * Тестирует создание пользователя.
     */
    @Test
    public void whenInsertUser_ThenUserInserted() {
        User userSam = new User("Sam Kilanoff", "Sam", "1", User.ROLE.ADMIN, "sam@email.com");

        userDao.insert(userSam);

        String userName = userDao.getAll().get(0).getName();

        assertThat(userName, is("Sam Kilanoff"));
    }

    /**
     * Тестирует удаление пользователя по id.
     */
    @Test
    public void whenDeleteById_ThenUserDeleted() {
        User userSam = new User("Sam Kilanoff", "Sam", "1", User.ROLE.ADMIN, "sam@email.com");

        userDao.insert(userSam);

        userDao.deleteById(1);

        assertNull(userDao.findById(1));
    }

    /**
     * Тестирует изменение пользователя.
     */
    @Test
    public void whenUpdateUser_ThenUserUpdated() {
        User userSam = new User("Sam Kilanoff", "Sam", "1", User.ROLE.ADMIN, "sam@email.com");

        userDao.insert(userSam);

        User updatedUser = userDao.findById(1);
        updatedUser.setName("Alex");
        userDao.update(updatedUser);

        assertThat(userDao.getAll().get(0).getName(), is("Alex"));
    }

    /**
     * Тестирует поиск по id.
     */
    @Test
    public void whenFindById_ThenUserFound() {
        User userSam = new User("Sam Kilanoff", "Sam", "1", User.ROLE.ADMIN, "sam@email.com");

        userDao.insert(userSam);

        assertNotNull(userDao.findById(1));
    }

    /**
     * Тест проверяет существует ли пользователь
     */
    @Test
    public void whenUserIsExist_ThenReturnTrue() {
        User userSam = new User("Sam Kilanoff", "Sam", "1", User.ROLE.ADMIN, "sam@email.com");

        userDao.insert(userSam);

        boolean result = userDao.userIsExist("Sam", "1");

        assertThat(result, is(true));
    }

    /**
     * Тестирует поиск роли по логину и паролю.
     */
    @Test
    public void whenGetRoleByLoginAndPassword_ThenReturnUsersRole() {
        User userSam = new User("Sam Kilanoff", "Sam", "1", User.ROLE.ADMIN, "sam@email.com");

        userDao.insert(userSam);

        User.ROLE expectedRole = userDao.getRoleByLoginPassword("Sam", "1");

        assertThat(expectedRole, is(User.ROLE.ADMIN));
    }

    /**
     * Тестирует поиск id по логину и паролю.
     */
    @Test
    public void whenFindByLogin_ThenReturnRightUser() {
        User userSam = new User("Sam Kilanoff", "Sam", "1", User.ROLE.ADMIN, "sam@email.com");

        userDao.insert(userSam);

        int expectedId = userDao.findByLogin("Sam", "1");

        assertThat(expectedId, is(1));
    }
}

/**
 * Тестовая фабрика соединений.
 */
class ConnectionFactory {
    private static final String DRIVER = "org.h2.Driver";
    private static final String URL = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
    private static final String LOGIN = "sa";
    private static final String PASSWORD = "";

    private static ConnectionFactory connectionFactory;
    private BasicDataSource ds;

    /**
     * Конструктор.
     */
    protected ConnectionFactory() {
        ds = new BasicDataSource();
        ds.setDriverClassName(DRIVER);
        ds.setUsername(LOGIN);
        ds.setPassword(PASSWORD);
        ds.setUrl(URL);

        ds.setMinIdle(5);
        ds.setMaxIdle(20);
        ds.setMaxOpenPreparedStatements(180);

    }

    /**
     * Создаем соединение.
     *
     * @return ConnectionFactory
     */
    public static ConnectionFactory getInstance() {
        if (connectionFactory == null) {
            connectionFactory = new ConnectionFactory();
            return connectionFactory;
        } else {
            return connectionFactory;
        }
    }

    /**
     * Подключаемся.
     *
     * @return Connection
     */
    public Connection getConnection() {
        try {
            return this.ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

