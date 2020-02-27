package ru.otus.hw08;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JCustomImpl implements JCustom {
    private Object object;

    public String toJson(Object object) throws IllegalAccessException {
        this.object = object;
        List<Field> declaredFields = new ArrayList<>();

        StringBuilder jFields = new StringBuilder();
        Class clazz = object.getClass();
        Collections.addAll(declaredFields, clazz.getDeclaredFields());
        int size = declaredFields.size();
        for (int i = 0; i < size; i++) {
            Field field = declaredFields.get(i);
            if (i != 0)
                jFields.append(",");
            if (!isArray(field)) {
                if (isSimpleField(field)) {
                    jFields.append(addSimpleField(field));
                } else {
                    jFields.append(field.getName()).append(":").append(toJson(field.get(object)));
                }
            }
            if (isArray(field)) {
                jFields.append(addArray(field));
            }
        }

        String json = create(jFields).toString();

        return json;
    }

    private StringBuilder create(StringBuilder element) {
        StringBuilder json = new StringBuilder();
        json.append("{").append(element).append("}");
        return json;
    }

    private StringBuilder addSimpleField(Field field) throws IllegalAccessException {
        StringBuilder json = new StringBuilder();
        field.setAccessible(true);
        json.append(field.getName()).append(":").append(field.get(object).toString());
        return json;
    }

    private boolean isSimpleField(Field field) {
        Class clazz = field.getType();
        return isSimpleField(clazz);
    }

    private boolean isSimpleField(Class clazz) {
        if (clazz.isPrimitive()) {
            return true;
        } else {
            switch (clazz.getSimpleName()) {
                case "String":
                case "Integer":
                case "Double":
                case "Long":
                case "Boolean":
                    return true;
            }
        }
        return false;
    }

    private boolean isArray(Field field) {
        Class clazz = field.getType();
        return isArray(clazz);
    }

    private boolean isArray(Class clazz) {
        return clazz.isArray() || clazz.getSimpleName().equals("List");
    }

    private StringBuilder addArray(Field field) throws IllegalAccessException {
        StringBuilder json = new StringBuilder();
        field.setAccessible(true);
        Object array;
        if (field.getType().getSimpleName().equals("List")) {
            List list = (List) field.get(object);
            array = list.toArray();
        } else {
            array = field.get(object);
        }
        return json.append(field.getName()).append(":").append(addArray(array));
    }

    private StringBuilder addArray(Object array) throws IllegalAccessException {
        StringBuilder json = new StringBuilder();
        int length = Array.getLength(array);
        json.append("[");
        for (int i = 0; i < length; i++) {
            if (i != 0)
                json.append(",");
            if (!isArray(Array.get(array, i).getClass())) {
                if (isSimpleField(Array.get(array, i).getClass())) {
                    json.append(Array.get(array, i));
                } else {
                    json.append(toJson(Array.get(array, i)));
                }
            }
            if (isArray(Array.get(array, i).getClass())) {
                json.append(addArray(Array.get(array, i)));
            }
        }
        json.append("]");
        return json;
    }
}
