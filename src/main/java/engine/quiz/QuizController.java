package engine.quiz;

import engine.user.User;
import engine.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/quizzes")
@AllArgsConstructor
public class QuizController {

    private final QuizRepository quizRepository;
    private final UserService userService;

    @PostMapping
    public Quiz addQuiz(@Valid @RequestBody Quiz quiz) {
        if (quiz.getAnswer() == null) {
            quiz.setAnswer(new int[]{});
        }
        quizRepository.save(quiz);
        return quiz;
    }

    @GetMapping("/{id}")
    public Quiz getQuiz(@PathVariable Long id) {
        Quiz quiz = quizRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found")
        );
        return quiz;
    }

    @GetMapping
    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    @PostMapping("/{id}/solve")
    public Result solveQuiz(@PathVariable Long id, @RequestBody Answer answer) {
        Quiz quiz = quizRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found")
        );
        if (quiz.isCorrect(answer)) {
            return new Result(true);
        }
        return new Result(false);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteQuiz(@PathVariable Long id, @AuthenticationPrincipal User user) {
        if (!quizRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        boolean isAllowed = quizRepository.findByUserId(user.getId(), id).isPresent();
        if (!isAllowed) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        quizRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}




























