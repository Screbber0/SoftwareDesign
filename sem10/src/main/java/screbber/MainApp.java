package screbber;

public class MainApp {
    public static void main(String[] args) {
        HttpClient httpClient = new HttpClient();
        ConsoleHandler consoleHandler = new ConsoleHandler();
        Game game = new Game(httpClient, consoleHandler);
        game.start();
    }
}
