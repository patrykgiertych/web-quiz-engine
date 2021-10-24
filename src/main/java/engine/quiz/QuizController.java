package engine.quiz;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class QuizController {

    private List<Quiz> quizzes = new ArrayList<>();
    private int counter = 1;

    @PostMapping("quizzes")
    public Quiz addQuiz(@Valid @RequestBody Quiz quiz) {
        quiz.setId(counter);
        counter++;
        if (quiz.getAnswer() == null) {
            quiz.setAnswer(new int[]{});
        }
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
    public Result solveQuiz(@PathVariable int id, @RequestBody Answer answer) {
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
            return new Result(true);
        }
        return new Result(false);
    }

}
