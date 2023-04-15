package screbber;

public class Main {
    public static void main(String[] args) {
        int port = 8080;
        if (args.length > 0) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.out.println("Invalid port number, using default " + port);
            }
        }
        Server server = new Server(port);
        server.start();
    }
}
