package engine.quiz;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/quiz")
public class QuizController {

    private final Quiz quiz = new Quiz(
            "The Java Logo",
            "What is depicted on the Java logo?",
            new String[]{"Robot", "Tea leaf", "Cup of coffee", "Bug"}
    );


    @GetMapping
    public Quiz getQuiz() {
        return quiz;
    }

    @PostMapping
    public Answer getAnswer(@RequestParam("answer") int answer) {
        if (answer == 2) {
            return new Answer(true);
        }
        return new Answer(false);
    }

}
