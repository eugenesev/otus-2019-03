package ru.otus.hw09.executor;

import ru.otus.hw09.Id;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.Optional;
import java.util.function.Function;

public class DBExecutorImpl<T> implements DBExecutor<T> {
    private final Connection connection;
    private Class objectClass;
    private Field[] declaredField;

    public DBExecutorImpl(Connection connection) {

        this.connection = connection;
    }

    @Override
    public <T> long create(String sql, T object) {
        objectClass = object.getClass();
        try (PreparedStatement pst = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            Savepoint savePoint = this.connection.setSavepoint("savePoint");
            if (primaryKeyIsPresent()) {
                int idx = 0;
                for (Field field : declaredField) {
                    if (!field.isAnnotationPresent(Id.class)) {
                        if (field.getType() == String.class) {
                            field.setAccessible(true);
                            String s = null;
                            try {
                                s = field.get(object).toString();
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                            pst.setString(++idx, s);
                            field.setAccessible(false);
                        } else {
                            field.setAccessible(true);
                            try {
                                pst.setInt(++idx, field.getInt(object));
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                            field.setAccessible(false);

                        }
                    }
                }
            }
            pst.executeUpdate();
            this.connection.commit();
            try (ResultSet rs = pst.getGeneratedKeys()) {
                rs.next();
                return rs.getInt(1);
            } catch (SQLException ex) {
                this.connection.rollback(savePoint);
                ex.getMessage();
                return -1;
            }
        } catch (SQLException ex) {
            ex.getMessage();
            return -1;
        }
    }

    @Override
    public <T> void update(String sql, long id, T param) {
        try (PreparedStatement pst = this.connection.prepareStatement(sql)) {
            Savepoint savePoint = this.connection.setSavepoint("savePoint");
            if (param.getClass() == String.class) {
                pst.setString(1, (String) param);
            }
            if (param.getClass() == Integer.class) {
                pst.setInt(1, (int) param);
            }
            pst.setLong(2, id);
            try {
                pst.executeUpdate();
                this.connection.commit();
            } catch (SQLException ex) {
                this.connection.rollback(savePoint);
                ex.getMessage();
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }

    public <T> int update(String sql, T object){
        objectClass = object.getClass();
        try (PreparedStatement pst = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            Savepoint savePoint = this.connection.setSavepoint("savePoint");
            if (primaryKeyIsPresent()) {
                int idx = 0;
                for (Field field : declaredField) {
                    if (!field.isAnnotationPresent(Id.class)) {
                        if (field.getType() == String.class) {
                            field.setAccessible(true);
                            String s = null;
                            try {
                                s = field.get(object).toString();
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                            pst.setString(++idx, s);
                            field.setAccessible(false);
                        } else {
                            field.setAccessible(true);
                            try {
                                pst.setInt(++idx, field.getInt(object));
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                            field.setAccessible(false);

                        }
                    }
                }
            }
            pst.executeUpdate();
            this.connection.commit();
            try (ResultSet rs = pst.getGeneratedKeys()) {
                rs.next();
                return rs.getInt(1);
            } catch (SQLException ex) {
                this.connection.rollback(savePoint);
                ex.getMessage();
                return -1;
            }
        } catch (SQLException ex) {
            ex.getMessage();
            return -1;
        }
    }

    @Override
    public Optional<T> load(String sql, long id, Function<ResultSet, T> rsHandler) {
        Optional<T> response = Optional.empty();
        try (PreparedStatement pst = this.connection.prepareStatement(sql)) {
            pst.setLong(1, id);

            try (ResultSet rs = pst.executeQuery()) {
                response =  Optional.ofNullable(rsHandler.apply(rs));
            }
        }catch (SQLException ex) {
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

    private boolean primaryKeyIsPresent() {
        return (this.getPrimaryKey() != null);
    }
}
