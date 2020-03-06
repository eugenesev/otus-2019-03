package ru.otus.hw08;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class JCustomImpl implements JCustom {

    public String toJson(Object object) throws IllegalAccessException {
        if (object == null) {
            return "null";
        }

        StringBuilder jFields = new StringBuilder();
        Class clazz = object.getClass();

        if (isSimpleType(clazz)) {
            jFields.append(addSimple(object));
        } else {
            if (isArray(clazz)) {
                jFields.append(addListOrArray(object));
            } else {
                jFields.append(addInstance(object));
            }
        }
        return jFields.toString();
    }

    private boolean isSimpleType(Field field) {
        Class clazz = field.getType();
        return isSimpleType(clazz);
    }

    private boolean isSimpleType(Class clazz) {

        return clazz.isPrimitive() ||
                clazz.equals(String.class) ||
                clazz.equals(Byte.class) ||
                clazz.equals(Short.class) ||
                clazz.equals(Integer.class) ||
                clazz.equals(Long.class) ||
                clazz.equals(Float.class) ||
                clazz.equals(Double.class) ||
                clazz.equals(Character.class) ||
                clazz.equals(Boolean.class);
    }

    private StringBuilder addSimple(Field field, Object object) throws IllegalAccessException {
        StringBuilder json = new StringBuilder();
        field.setAccessible(true);
        json.append("\"").append(field.getName()).append("\"").append(":")
                .append(addSimple(field.get(object)));
        return json;
    }

    private StringBuilder addSimple(Object object) {
        StringBuilder json = new StringBuilder();
        Class clazz = object.getClass();
        if (clazz.equals(String.class) || clazz.equals(Character.class)) {
            return json.append("\"").append(object).append("\"");
        }
        return json.append(object);
    }


    private boolean isArray(Field field) {
        Class clazz = field.getType();
        return isArray(clazz);
    }

    private boolean isArray(Class clazz) {
        return clazz.isArray() || List.class.isAssignableFrom(clazz);
    }

    private StringBuilder addArray(Field field, Object object) throws IllegalAccessException {
        StringBuilder json = new StringBuilder();
        field.setAccessible(true);
        Object array;
        if (List.class.isAssignableFrom(field.getType())) {
            List list = (List) field.get(object);
            array = list.toArray();
        } else {
            array = field.get(object);
        }
        return json.append("\"").append(field.getName()).append("\"").append(":")
                .append(addArray(array));
    }

    private StringBuilder addListOrArray(Object object) throws IllegalAccessException {
        StringBuilder json = new StringBuilder();
        Object array;
        if (List.class.isAssignableFrom(object.getClass())) {
            List list = (List) object;
            array = list.toArray();
            return json.append(addArray(array));
        }
        return json.append(addArray(object));
    }

    private StringBuilder addArray(Object array) throws IllegalAccessException {
        StringBuilder json = new StringBuilder();

        int length = Array.getLength(array);
        json.append("[");
        for (int i = 0; i < length; i++) {
            if (i != 0)
                json.append(",");
            if (!isArray(Array.get(array, i).getClass())) {
                if (isSimpleType(Array.get(array, i).getClass())) {
                    json.append(addSimple(Array.get(array, i)));
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

    private StringBuilder addInstance(Object object) throws IllegalAccessException {
        StringBuilder json = new StringBuilder();
        List<Field> declaredFields = new ArrayList<>();
        Class clazz = object.getClass();
        Collections.addAll(declaredFields, clazz.getDeclaredFields());
        int size = declaredFields.size();
        for (int i = 0; i < size; i++) {
            Field field = declaredFields.get(i);
            field.setAccessible(true);
            if (field.get(object) != null && !Modifier.isStatic(field.getModifiers())) {
                if (i != 0)
                    json.append(",");
                if (isArray(field)) {
                    json.append(addArray(field, object));
                } else {
                    if (isSimpleType(field)) {
                        json.append(addSimple(field, object));
                    } else {
                        json.append("\"").append(field.getName()).append("\"").append(":")
                                .append(addInstance(field.get(object)));
                    }
                }
            }
        }
        return create(json);
    }

    private StringBuilder create(StringBuilder element) {
        StringBuilder json = new StringBuilder();
        json.append("{").append(element).append("}");
        return json;
    }

}
