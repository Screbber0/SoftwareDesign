package screbber;

import screbber.annotations.JsonElement;
import screbber.annotations.JsonSerializable;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class JsonSerializer{
    private boolean isNullAccepted = false;
    public String convertToJson(Object object) throws IllegalAccessException {
        if (object == null) {
            if (!isNullAccepted) {
                throw new JsonSerializerException("Object is null");
            } else {
                return "{null}";
            }
        }

        //var a = object.getClass().getAnnotation(JsonSerializable.class);
        if (object.getClass().getAnnotation(JsonSerializable.class) == null) {
            throw new JsonSerializerException("Object hasn't JsonSerializable annotation");
        }

        HashMap<String, String> map = new HashMap<>();
        for (Field field : object.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            if (field.getAnnotation(JsonElement.class) != null) {
                String name = getNameFromField(field);
                String value = field.get(object).toString();
                map.put(name, value);
            }
        }

        return getJsonStringFromMap(map);
    }

    private String getJsonStringFromMap(Map<String, String> map) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            sb.append("\"").append(entry.getKey()).append("\"");
            sb.append(":");
            sb.append("\"").append(entry.getValue()).append("\"");
            sb.append(",");
        }
        if (sb.length() != 1) {
            sb.delete(sb.length() - 1, sb.length());
        }
        sb.append("}");
        return sb.toString();
    }

    private String getNameFromField(Field field) {
        if (field.getAnnotation(JsonElement.class).JsonName().equals("")) {
            return field.getName();
        } else {
            return field.getAnnotation(JsonElement.class).JsonName();
        }
    }

    public void setNullAccepted(boolean nullAccepted) {
        isNullAccepted = nullAccepted;
    }
}
