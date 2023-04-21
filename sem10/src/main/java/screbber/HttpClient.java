package screbber;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import screbber.model.Category;
import screbber.model.Question;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HttpClient {
    private static final String RANDOM_URL = "https://jservice.io/api/random";
    private static final String CLUES_URL = "https://jservice.io/api/clues";
    private static final String CATEGORIES_URL = "https://jservice.io/api/categories?count=5";
    private static final Logger logger = LogManager.getLogger(HttpClient.class);
    private final Random random = new Random();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public Question getRandomQuestion() throws IOException {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(RANDOM_URL);
            logger.debug("Sending GET request to " + RANDOM_URL);

            try (CloseableHttpResponse response = httpClient.execute(request)) {
                String stringBody = EntityUtils.toString(response.getEntity());
                logger.debug("Received response with status code " + response.getStatusLine().getStatusCode()
                        + " and body " + stringBody);

                JsonNode firstJsonNode = objectMapper.readTree(stringBody).get(0);

                String question = firstJsonNode.get("question").asText();
                String answer = firstJsonNode.get("answer").asText();
                return new Question(question, answer);
            }
        }
    }

    public Question getRandomQuestionByCategory(int categoryId) throws IOException, URISyntaxException {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            URI uri = new URIBuilder(CLUES_URL)
                    .addParameter("category", Integer.toString(categoryId))
                    .build();
            HttpGet request = new HttpGet(uri);
            logger.debug("Sending GET request to " + uri);

            try (CloseableHttpResponse response = httpClient.execute(request)) {
                String stringBody = EntityUtils.toString(response.getEntity());
                logger.debug("Received response with status code " + response.getStatusLine().getStatusCode()
                        + " and body " + stringBody);

                JsonNode jsonNodesByCategory = objectMapper.readTree(stringBody);
                JsonNode randomJsonNode = jsonNodesByCategory.get(random.nextInt(jsonNodesByCategory.size()));

                String question = randomJsonNode.get("question").asText();
                String answer = randomJsonNode.get("answer").asText();
                return new Question(question, answer);
            }
        }
    }

    public List<Category> getCategories() throws IOException {
        List<Category> categoryList = new ArrayList<>();

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(CATEGORIES_URL);

            try (CloseableHttpResponse response = httpClient.execute(request)) {
                String stringBody = EntityUtils.toString(response.getEntity());

                JsonNode AllJsonNodes = objectMapper.readTree(stringBody);
                for (JsonNode jsonNode : AllJsonNodes) {
                    int id = jsonNode.get("id").asInt();
                    String title = jsonNode.get("title").asText();
                    categoryList.add(new Category(id, title));
                }
                return categoryList;
            }
        }
    }
}
