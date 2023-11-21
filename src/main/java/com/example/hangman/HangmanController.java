package com.example.hangman;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class HangmanController implements Initializable {
    @FXML
    private Label textLabel;
    private String secretWord;
    @FXML
    private ImageView image;
    private int count;
    @FXML
    private TextField inputText;
    private final List<String> words = new ArrayList<>();
    private Random r = new Random();
    private String revealedWord;

    public HangmanController() {
        words.add("Alligator");
        words.add("Bear");
        words.add("Cheetah");
        words.add("Chevrolet");
        words.add("Dodge");
        words.add("Honda");
        words.add("Football");
        pickARandomWord();
    }
    public void initialize(URL url, ResourceBundle resourceBundle) {
        textLabel.setText(encodeTheWord());
        if (getClass().getResourceAsStream("/images/0.png") != null) {
            Image image1 = new Image(getClass().getResourceAsStream("/Images/0.png"));
            image.setImage(image1);
            count++;
        }
        revealedWord = encodeTheWord(secretWord);
        System.out.println(secretWord);
        addTextLimiter(inputText, 1);
    }

    public void pickARandomWord() {
        int number = r.nextInt(words.size());
        secretWord = words.get(number).toLowerCase();
    }

    public String encodeTheWord() {
        return "_".repeat(secretWord.length());
    }
    @FXML
    public void buttonClicked() {

        String input = inputText.getText().toLowerCase();
        if (!secretWord.contains(input)) {
            File imageFolder = new File("src/main/resources/Images");
            if (count < imageFolder.listFiles().length) {
                String phrase = "/Images/" + count + ".png";
                Image image1 = new Image(getClass().getResourceAsStream(phrase));
                image.setImage(image1);
                count++;
            }
        } else {
            for (int i = 0; i < secretWord.length(); i++) {
                if (secretWord.charAt(i) == input.charAt(0)) {
                    revealedWord = revealedWord.substring(0, i) + input.charAt(0) + revealedWord.substring(i + 1);
                }
            }
            textLabel.setText(revealedWord);
        }
    }
    public static void addTextLimiter(final TextField tf, final int maxLength) {
        tf.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if (tf.getText().length() > maxLength) {
                    String s = tf.getText().substring(0, maxLength);
                    tf.setText(s);
                }
            }
        });
    }
}
// make app nicer
// tests?
// victory / defeat menu