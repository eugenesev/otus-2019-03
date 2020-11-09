package ru.otus.hw10;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.hw10.api.model.PhoneDataSet;
import ru.otus.hw10.api.model.User;
import ru.otus.hw10.api.model.HomeAddress;

import ru.otus.hw10.hibernate.HibernateUtils;
import ru.otus.hw10.hibernate.dao.UserDaoHibernate;
import ru.otus.hw10.hibernate.sessionmanager.SessionManagerHibernate;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Dao для работы с пользователями должно ")
public class UserDaoHibernateTest {

    private static final String HIBERNATE_CFG_XML_FILE_RESOURCE = "test-hibernate.cfg.xml";

    protected static final String TEST_USER_NAME = "Вася";

    private SessionManagerHibernate sessionManagerHibernate;
    private UserDaoHibernate userDaoHibernate;
    private SessionFactory sessionFactory;

    @BeforeEach
    public void setUp() {
        sessionFactory = HibernateUtils.buildSessionFactory(HIBERNATE_CFG_XML_FILE_RESOURCE, User.class, HomeAddress.class, PhoneDataSet.class);
        sessionManagerHibernate = new SessionManagerHibernate(sessionFactory);
        userDaoHibernate = new UserDaoHibernate(sessionManagerHibernate);
    }

    @AfterEach
    void tearDown() {
        sessionFactory.close();
    }

    @Test
    @DisplayName(" корректно загружать пользователя по заданному id")
    void shouldFindCorrectUserById() {
        User expectedUser = buildDefaultUserWithRelations();
        saveUser(expectedUser);
        assertThat(expectedUser.getId()).isGreaterThan(0);

        sessionManagerHibernate.beginSession();
        Optional<User> mayBeUser = userDaoHibernate.findById(expectedUser.getId());
        assertThat(mayBeUser.get()).isEqualTo(expectedUser);
        sessionManagerHibernate.commitSession();

        assertThat(mayBeUser.get()).isNotNull().hasFieldOrPropertyWithValue("id", expectedUser.getId());
        assertThat(mayBeUser.get()).isNotNull().hasFieldOrPropertyWithValue("name", expectedUser.getName());
        assertThat(mayBeUser.get()).isNotNull().hasFieldOrPropertyWithValue("age", expectedUser.getAge());
        assertThat(mayBeUser.get().getHomeAddress()).isNotNull().hasFieldOrPropertyWithValue("street", expectedUser.getHomeAddress().getStreet());
//      Can throw LazyInitializationException
        assertThat(mayBeUser.get().getPhone().get(0).getNumber()).isNotNull().isEqualTo("112-22-33");
    }

    @DisplayName(" корректно сохранять пользователя")
    @Test
    void shouldCorrectSaveUser() {
        User expectedUser = buildDefaultUserWithRelations();
        sessionManagerHibernate.beginSession();
        long id = userDaoHibernate.saveUser(expectedUser);
        sessionManagerHibernate.commitSession();

        assertThat(id).isGreaterThan(0);

        User actualUser = loadUserWithRelations(id);
        assertThat(actualUser).isNotNull().hasFieldOrPropertyWithValue("id", expectedUser.getId());
        assertThat(actualUser).isNotNull().hasFieldOrPropertyWithValue("name", expectedUser.getName());
        assertThat(actualUser).isNotNull().hasFieldOrPropertyWithValue("age", expectedUser.getAge());
        assertThat(actualUser.getHomeAddress()).isNotNull().hasFieldOrPropertyWithValue("street", expectedUser.getHomeAddress().getStreet());
//      Can throw LazyInitializationException
        assertThat(actualUser.getPhone().get(0).getNumber()).isNotNull();
    }

    @DisplayName(" возвращать менеджер сессий")
    @Test
    void getSessionManager() {
        assertThat(userDaoHibernate.getSessionManager()).isNotNull().isEqualTo(sessionManagerHibernate);
    }


    protected User buildDefaultUserWithRelations() {
        User defaultUser = new User(TEST_USER_NAME, 25);
        HomeAddress address = new HomeAddress("Садовая");
        defaultUser.setHomeAddress(address);
        PhoneDataSet phone1 = new PhoneDataSet("112-22-33");
        defaultUser.addPhone(phone1);
        return defaultUser;
    }

    protected void saveUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            saveUser(session, user);
        }
    }

    protected void saveUser(Session session, User user) {
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
    }

    protected User loadUserWithRelations(long id) {
        try (Session session = sessionFactory.openSession()) {
            User user = session.find(User.class, id);
            user.getPhone().isEmpty();
            return user;
        }
    }
}
