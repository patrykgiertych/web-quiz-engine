package engine.quiz;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Arrays;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty
    private String title;
    @NotEmpty
    private String text;
    @NotNull
    @Size(min = 2)
    private String[] options;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int[] answer;

    public Quiz(String title, String text, String[] options, int[] answer) {
        this.title = title;
        this.text = text;
        this.options = options;
        this.answer = answer;
    }

    public Quiz(String title, String text, String[] options) {
        this.title = title;
        this.text = text;
        this.options = options;
        this.answer = new int[]{};
    }

    public boolean isCorrect(Answer userAnswer) {
        System.out.println(Arrays.toString(this.answer));
        System.out.println(Arrays.toString(userAnswer.getAnswer()));
        return Arrays.equals(this.answer, userAnswer.getAnswer());
    }
}