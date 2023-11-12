package com.example.hangman;

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
        revealedWord = encodeTheWord();
        System.out.println(secretWord);
    }

    public void pickARandomWord() {
        int number = r.nextInt(words.size());
        secretWord = words.get(number);
    }

    public String encodeTheWord() {
        return "_".repeat(secretWord.length());
    }
    @FXML
    public void buttonClicked() {
        String input = inputText.getText();
        if (!secretWord.contains(input)) {
            File imageFolder = new File("src/main/resources/Images");
            if (count < imageFolder.listFiles().length) {
                String phrase = "/Images/" + count + ".png";
                Image image1 = new Image(getClass().getResourceAsStream(phrase));
                image.setImage(image1);
                count++;
            }
        } else {
            int index = secretWord.indexOf(input.charAt(0));
            revealedWord = revealedWord.substring(0, index) + input.charAt(0) + revealedWord.substring(index + 1);
            textLabel.setText(revealedWord);
        }
    }
}
// did not fix two or more of the same letter
// victory / defeat menu
// only allow user to enter one letter
// make input not case sensitive
// make app nicer
// tests?