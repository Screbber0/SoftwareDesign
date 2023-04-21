package screbber;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import screbber.model.Question;

import java.io.IOException;
import java.net.URISyntaxException;

public class Game {
    private final HttpClient httpClient;
    private final ConsoleHandler consoleHandler;
    private final Logger logger = LogManager.getLogger(Game.class);
    private int numberOfCorrectAnswers = 0;
    private int numberOfIncorrectAnswers = 0;

    public Game(HttpClient httpClient, ConsoleHandler consoleHandler) {
        this.httpClient = httpClient;
        this.consoleHandler = consoleHandler;
    }

    private boolean isRightAnswer(String playerAnswer, Question question) {
        return (playerAnswer.trim().equalsIgnoreCase(question.getAnswer()));
    }

    public void start() {
        try {
            int idQuestion = consoleHandler.selectCategoryId(httpClient.getCategories());
            while (true) {
                Question question;
                if (idQuestion != -1) {
                    question = httpClient.getRandomQuestionByCategory(idQuestion);
                } else {
                    question = httpClient.getRandomQuestion();
                }

                logger.info("Asking new question: {}", question.getQuestion());
                System.out.println("Question: " + question.getQuestion());

                String answer = consoleHandler.getAnswer();
                if (answer.trim().equalsIgnoreCase("/q")) {
                    consoleHandler.printEndOfGame(numberOfCorrectAnswers, numberOfIncorrectAnswers);
                    break;
                }

                if (isRightAnswer(answer, question)) {
                    ++numberOfCorrectAnswers;
                    System.out.println("Верно!");
                } else {
                    ++numberOfIncorrectAnswers;
                    System.out.println("Неверно!");
                    System.out.println("Правильный ответ: " + question.getAnswer());
                }
            }
        } catch (IOException | URISyntaxException e) {
            System.out.println("Извините, произошла ошибка, попробуйте зайти в игру позже");
            logger.error("An error occurred while running the game: {}", e.getMessage(), e);
        }

    }
}
