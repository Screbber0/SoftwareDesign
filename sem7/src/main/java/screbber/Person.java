package screbber;

import screbber.annotations.JsonElement;
import screbber.annotations.JsonSerializable;

@JsonSerializable
public class Person {
    @JsonElement
    private final String name;

    @JsonElement
    private final String surname;

    @JsonElement(JsonName = "age")
    private final int count;

    public Person(String name, String surname, int count) {
        this.name = name;
        this.surname = surname;
        this.count = count;
    }
}
