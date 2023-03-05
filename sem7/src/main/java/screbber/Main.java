package screbber;

public class Main {
    public static void main(String[] args) throws IllegalAccessException {
        Person person = new Person("Sergey", "Chesnokov", 19);
        JsonSerializer jsonSerializer = new JsonSerializer();
        System.out.println(jsonSerializer.convertToJson(person));
    }
}