package dk.sdu.mmmi.cbse.scoring;

import org.springframework.web.client.RestTemplate;

public class Scoring {
    private static final RestTemplate restTemplate = new RestTemplate();
    private static final String BASE_URL = "http://localhost:8080/api/score";

    private Scoring() {

    }

    public static int getScore() {
        try {
            Integer value = restTemplate.getForObject(BASE_URL, Integer.class);
            return value != null ? value : 0;
        } catch (Exception ignored) {
            return 0;
        }
    }

    public static int addPoints(int points) {
        try {
            Integer value = restTemplate.postForObject(BASE_URL + "/add?points={points}", null, Integer.class, points);
            return value != null ? value : getScore();
        } catch (Exception ignored) {
            return getScore();
        }
    }
    public static int reset() {
        try {
            Integer value = restTemplate.postForObject(BASE_URL + "/reset", null, Integer.class);
            return value != null ? value : 0;
        } catch (Exception ignored) {
            return 0;
        }
    }
}
