package engine.quiz;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Quiz {

    private int id;
    private String title;
    private String text;
    private String[] options;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int answer;

    public Quiz(String title, String text, String[] options, int answer) {
        this.title = title;
        this.text = text;
        this.options = options;
        this.answer = answer;
    }

    public boolean isCorrect(int userAnswer) {
        return this.answer == userAnswer;
    }
}