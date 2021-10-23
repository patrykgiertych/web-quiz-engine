package engine.quiz;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class QuizController {

    private List<Quiz> quizzes = new ArrayList<>();
    private int counter = 1;

    @PostMapping("quizzes")
    public Quiz addQuiz(@RequestBody Quiz quiz) {
        quiz.setId(counter);
        counter++;
        quizzes.add(quiz);
        return quiz;
    }

    @GetMapping("quizzes/{id}")
    public Quiz getQuiz(@PathVariable int id) {
        Quiz quizById = null;
        for (Quiz q : quizzes) {
            if (q.getId() == id) {
                quizById = q;
            }
        }
        if (quizById == null)
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "entity not found"
            );
        return quizById;}

    @GetMapping("quizzes")
    public List<Quiz> getAllQuizzes() {
        return quizzes;
    }

    @PostMapping("quizzes/{id}/solve")
    public Answer solveQuiz(@RequestParam("answer") int answer, @PathVariable int id) {
        Quiz quiz = null;
        for (Quiz q : quizzes) {
            if (q.getId() == id) {
                quiz = q;
            }
        }
        if (quiz == null)
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "entity not found"
            );
        if (quiz.isCorrect(answer)) {
            return new Answer(true);
        }
        return new Answer(false);
    }

}
