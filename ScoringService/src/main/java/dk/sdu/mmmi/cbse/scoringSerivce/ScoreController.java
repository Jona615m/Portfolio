package dk.sdu.mmmi.cbse.scoringSerivce;

import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/score")
public class ScoreController {

    private AtomicInteger score = new AtomicInteger(0);

    @GetMapping
    public Integer score() {
        return score.get();
    }

    @PostMapping("/add")
    public Integer add(@RequestParam("points") int points) {
        return score.addAndGet(points);
    }

    @PostMapping("/reset")
    public Integer reset() {
        score.set(0);
        return 0;
    }
}
