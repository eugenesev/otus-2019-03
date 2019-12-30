package ru.otus.hw09.jdbc.dao;

import ru.otus.hw09.api.dao.Id;
import ru.otus.hw09.api.dao.DBExecutor;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class DBExecutorImpl<T> implements DBExecutor<T> {
    private final Connection connection;
    private Class objectClass;
    private Field[] declaredField;
    private List<String> entityFields = new ArrayList<>();

    public DBExecutorImpl(Connection connection) {

        this.connection = connection;
    }

    @Override
    public void create(T object) throws SQLException {
        objectClass = object.getClass();
        getEntityFields();
        //"insert into user(name, age) values (?, ?)"
        var sqlStatement = new StringBuilder("insert into");
        sqlStatement.append(" ")
                .append(objectClass.getSimpleName().toLowerCase())
                .append("(").append(entityFields.get(0))
                .append(", ")
                .append(entityFields.get(1)).append(")")
                .append(" ").append("values (?, ?)");
        Savepoint savePoint = this.connection.setSavepoint("savePoint");
        try (PreparedStatement pst = connection.prepareStatement(sqlStatement.toString())) {
            if (primaryKeyIsPresent()) {
                setPST(object, pst);
            }
            pst.executeUpdate();
            this.connection.commit();
        } catch (SQLException | IllegalAccessException ex) {
            this.connection.rollback(savePoint);
            ex.getMessage();
        }
    }

    @Override
    public void update(T object) throws NoSuchFieldException, IllegalAccessException, SQLException {
        objectClass = object.getClass();
        getEntityFields();
        //"update user set name = ?, age = ? where id = ?"
        var sqlStatement = new StringBuilder("update");
        String primaryKey = getPrimaryKey();
        Field PKField = objectClass.getDeclaredField(primaryKey);
        PKField.setAccessible(true);
        long PKValue = (long)PKField.get(object);
        PKField.setAccessible(false);
        sqlStatement.append(" ")
                .append(objectClass.getSimpleName().toLowerCase())
                .append(" ").append("set").append(" ")
                .append(entityFields.get(0))
                .append("= ?, ")
                .append(entityFields.get(1))
                .append("= ? ").append("where id = ")
                .append(PKValue);
        Savepoint savePoint = this.connection.setSavepoint("savePoint");
        try (PreparedStatement pst = this.connection.prepareStatement(sqlStatement.toString())) {
            setPST(object, pst);

            pst.executeUpdate();
            this.connection.commit();
        } catch (SQLException ex) {
            this.connection.rollback(savePoint);
            ex.getMessage();
        }
    }

    private void setPST(T object, PreparedStatement pst) throws SQLException, IllegalAccessException {
        int idx = 0;
        for (Field field : declaredField) {
            if (!field.isAnnotationPresent(Id.class)) {
                if (field.getType() == String.class) {
                    field.setAccessible(true);
                    pst.setString(++idx, field.get(object).toString());
                    field.setAccessible(false);
                } else {
                    if (field.getType() == float.class || field.getType() == Float.class) {
                        field.setAccessible(true);
                        pst.setFloat(++idx, (float) field.get(object));
                        field.setAccessible(false);
                    }
                    if (field.getType() == Integer.class || field.getType() == int.class) {
                        field.setAccessible(true);
                        pst.setInt(++idx, (int) field.get(object));
                        field.setAccessible(false);
                    }
                }
            }
        }
    }

    @Override
    public Optional<T> load(String sql, long id, Function<ResultSet, T> rsHandler) {
        Optional<T> response = Optional.empty();

        try (PreparedStatement pst = this.connection.prepareStatement(sql)) {
            pst.setLong(1, id);

            try (ResultSet rs = pst.executeQuery()) {
                response = Optional.ofNullable(rsHandler.apply(rs));
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return response;
    }

    private String getPrimaryKey() {
        declaredField = objectClass.getDeclaredFields();
        for (Field field : declaredField) {
            if (field.isAnnotationPresent(Id.class)) {
                return field.getName().toLowerCase();
            }
        }
        return null;
    }

    private void getEntityFields() {
        declaredField = objectClass.getDeclaredFields();

        for (Field field : declaredField) {
            if (!field.isAnnotationPresent(Id.class)) {
                entityFields.add(field.getName().toLowerCase());
            }
        }
    }

    private boolean primaryKeyIsPresent() {
        return (this.getPrimaryKey() != null);
    }
}
