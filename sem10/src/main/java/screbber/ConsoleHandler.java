package screbber;

import screbber.model.Category;

import java.util.List;
import java.util.Scanner;

public class ConsoleHandler {
    private final Scanner scanner = new Scanner(System.in);

    public int selectCategoryId(List<Category> categoryList) {
        int choice = 0;
        while (!(choice == -1 || (choice > 0 && choice <= categoryList.size())))
        {
            System.out.println("Введите номер категории вопросов из списка или наберите -1 для игры со всеми категориями");
            for (int i = 0; i < categoryList.size(); i++) {
                System.out.printf("%d. %s\n", i + 1, categoryList.get(i).getTitle());
            }
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
            }
            scanner.nextLine();
        }
        if (choice == -1) {
            return -1;
        } else {
            return categoryList.get( choice - 1).getId();
        }
    }

    public String getAnswer() {
        System.out.print("Enter your answer or /q to exit: ");
        return scanner.nextLine();
    }

    public void printEndOfGame(int numberOfCorrectAnswers, int numberOfIncorrectAnswers) {
        System.out.println("-------------------------------------");
        System.out.println("Игра завершена ваши результаты: ");
        System.out.println("Правильных ответов: " + numberOfCorrectAnswers);
        System.out.println("Неверных ответов: " + numberOfIncorrectAnswers);
        System.out.println("-------------------------------------");

    }

}
