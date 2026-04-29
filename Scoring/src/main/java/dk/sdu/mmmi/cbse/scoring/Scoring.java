package dk.sdu.mmmi.cbse.scoring;

import org.springframework.web.client.RestTemplate;

public class Scoring {
    private static final RestTemplate restTemplate = new RestTemplate();
    private static final String BASE_URL = "http://localhost:8080/api/score";
    private static int localScore = 0;

    private Scoring() {

    }

    public static int getScore() {
        try {
            Integer value = restTemplate.getForObject(BASE_URL, Integer.class);
            if (value != null) {
                localScore = value;
            }
        } catch (Exception ignored) {
           //If server is down, we will keep the localscore
        }
        return localScore;
    }

    public static int addPoints(int points) {
        if (points <= 0) {
            return localScore;
        }

        localScore += points;

        try {
            Integer value = restTemplate.postForObject(BASE_URL + "/add?points={points}", null, Integer.class, points);
            if (value != null) {
                localScore = value;
            }
        } catch (Exception ignored) {
        }
        return localScore;
    }


    public static int reset() {
        localScore = 0;
        try {
            Integer value = restTemplate.postForObject(BASE_URL + "/reset", null, Integer.class);
            if (value != null) {
                localScore = value;
            }
        }
        catch (Exception ignored) {}
        return localScore;
    }
}
