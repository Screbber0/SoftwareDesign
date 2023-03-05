package screbber;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.api.Test;
import screbber.annotations.JsonElement;
import screbber.annotations.JsonSerializable;

public class JsonSerializerTest {
    @Test
    @DisplayName("Тест сериализации объекта Person в Json строчку, " +
            "где пары поле-значение может идти в любом порядке, должен корректную Json строку")
    public void test_serialize_Person_object_ensure_correct_json() throws IllegalAccessException {
        Person person = new Person("sergey", "chesnokov", 19);
        JsonSerializer jsonSerializer = new JsonSerializer();
        var result = jsonSerializer.convertToJson(person);
        String possible1 = "{\"name\":\"sergey\",\"surname\":\"chesnokov\",\"age\":\"19\"}";
        String possible2 = "{\"name\":\"sergey\",\"age\":\"19\",\"surname\":\"chesnokov\"}";
        String possible3 = "{\"surname\":\"chesnokov\",\"name\":\"sergey\",\"age\":\"19\"}";
        String possible4 = "{\"surname\":\"chesnokov\",\"age\":\"19\",\"name\":\"sergey\"}";
        String possible5 = "{\"age\":\"19\",\"name\":\"sergey\",\"surname\":\"chesnokov\"}";
        String possible6 = "{\"age\":\"19\",\"surname\":\"chesnokov\",\"name\":\"sergey\"}";
        Assertions.assertTrue(
                possible1.equals(result) ||
                possible2.equals(result) ||
                possible3.equals(result) ||
                possible4.equals(result) ||
                possible5.equals(result) ||
                possible6.equals(result)
        );
    }

    @Test
    @DisplayName("Преобразование объекта без JsonSerializable аннотации, должен бросить JsonSerializerException")
    public void test_serialize_object_without_JsonSerializable_annotation_should_throw_JsonSerializerException() {
        Integer object = 777;
        JsonSerializer serializer = new JsonSerializer();
        Assertions.assertThrows(JsonSerializerException.class, () -> serializer.convertToJson(object));
    }

    @Test
    @DisplayName("Сериализация null должен бросить JsonSerializerException")
    public void test_serialize_null_without_isNullAccepted_should_throw_JsonSerializerException() {
        JsonSerializer serializer = new JsonSerializer();
        Assertions.assertThrows(JsonSerializerException.class, () -> serializer.convertToJson(null));
    }

    @Test
    @DisplayName("Сериализация null должен бросить JsonSerializerException")
    public void test_serialize_null_with_isNullAccepted_should_true_return() throws IllegalAccessException {
        JsonSerializer serializer = new JsonSerializer();
        serializer.setNullAccepted(true);
        String expected = "{null}";
        Assertions.assertEquals(expected, serializer.convertToJson(null));
    }

    @JsonSerializable
    private static class TestClass {
        @JsonElement(JsonName = "newName")
        private final String oldName = "name";
    }

    @Test
    @DisplayName("Сериализация класса TestClass с полем, название которого изменено" +
            " из-за антации @JsonElement(JsonName = \"newName\"), должно вернуть корректный Json")
    public void test_serialize_TestClass_object_with_changed_field_by_JsonElement() throws IllegalAccessException {
        TestClass testClass = new TestClass();
        JsonSerializer jsonSerializable = new JsonSerializer();
        String expected = "{\"newName\":\"name\"}";
        Assertions.assertEquals(expected, jsonSerializable.convertToJson(testClass));
    }
}
