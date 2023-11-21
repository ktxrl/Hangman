import com.example.hangman.HangmanController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class test1 {
    @Test
    @DisplayName("Encode encodes")
    void encodeWorksFine() {
        // given
        HangmanController hangman = new HangmanController();

        // when
        String result = hangman.encodeTheWord("Bear");

        // then
        Assertions.assertEquals("____", result);
    }
}
