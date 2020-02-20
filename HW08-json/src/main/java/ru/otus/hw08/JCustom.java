package ru.otus.hw08;

import javax.json.*;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class JCustom {
    Object object;

    public String toJson(Object object) throws IllegalAccessException {
        this.object = object;
        List<Field> declaredFields = new ArrayList<>();
        String json;

        StringBuilder field = new StringBuilder();
        Class clazz = object.getClass();
        Collections.addAll(declaredFields, clazz.getDeclaredFields());
        int size = declaredFields.size();
        for (int i = 0; i < size; i++) {
            if (i != 0)
                field.append(", ");
            if(!isArray(declaredFields.get(i))){
                field.append(addSimpleField(declaredFields.get(i)));
            }
            if(isArray(declaredFields.get(i))){
                field.append(addArray(declaredFields.get(i)));
            }
        }

        json = create(field).toString();

        return json;
    }

    private StringBuilder create(StringBuilder element) {
        StringBuilder json = new StringBuilder();
        json.append("{\n").append(element).append("\n}");
        return json;
    }

    private StringBuilder addSimpleField(Field field) throws IllegalAccessException {
        StringBuilder json = new StringBuilder();
        field.setAccessible(true);
        json.append(field.getName()).append(": ").append(field.get(object).toString());
        return json;
    }

    private boolean isSimpleField(Field field) {
        return field.getType().isPrimitive();
    }

    private boolean isArray(Field field) {
        return field.getType().isArray();
    }

    private StringBuilder addArray(Field field) throws IllegalAccessException {
        StringBuilder json = new StringBuilder();
        field.setAccessible(true);
        Object array = field.get(object);
        int length = Array.getLength(array);
        json.append(field.getName()).append(": ");
        json.append("[");
        for (int i = 0; i < length; i++) {
            if (i != 0)
                json.append(", ");
            json.append(Array.get(array, i));
        }
        json.append("]");
        return json;
    }

}
