package engine.quiz;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/")
@AllArgsConstructor
public class QuizController {

    private final QuizRepository quizRepository;

    @PostMapping("quizzes")
    public Quiz addQuiz(@Valid @RequestBody Quiz quiz) {
        if (quiz.getAnswer() == null) {
            quiz.setAnswer(new int[]{});
        }
        quizRepository.save(quiz);
        return quiz;
    }

    @GetMapping("quizzes/{id}")
    public Quiz getQuiz(@PathVariable Long id) {
        Quiz quiz = quizRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found")
        );
        return quiz;
    }

    @GetMapping("quizzes")
    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    @PostMapping("quizzes/{id}/solve")
    public Result solveQuiz(@PathVariable Long id, @RequestBody Answer answer) {
        Quiz quiz = quizRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found")
        );
        if (quiz.isCorrect(answer)) {
            return new Result(true);
        }
        return new Result(false);
    }

}
