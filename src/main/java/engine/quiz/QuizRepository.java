package engine.quiz;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {

    @Query("select q from Quiz q where q.author = ?1 and q.id = ?2")
    Optional<Quiz> findByUserId(Long userId, Long quizId);
}
