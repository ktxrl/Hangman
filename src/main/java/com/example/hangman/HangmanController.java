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
    private Label answer;
    private String random;
    @FXML
    private ImageView image;
    private int count;
    @FXML
    private TextField inputText;
    private final List<String> words = new ArrayList<>();
    private Random r = new Random();

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
        answer.setText(random);
        if (getClass().getResourceAsStream("/images/0.png") != null) {
            Image image1 = new Image(getClass().getResourceAsStream("/Images/0.png"));
            image.setImage(image1);
            count++;
        }
    }

    public void pickARandomWord() {
        int number = r.nextInt(words.size());
        random = words.get(number);
    }

    public String encodeTheWord() {
        // alligator => _________
        // bear => ____

        //regex== rgular expression
        return "_ ".repeat(random.length());
    }
    @FXML
    public void buttonClicked() {
        String input = inputText.getText();
        if (!random.contains(input)) {
            File imageFolder = new File("src/main/resources/Images");
            if (count < imageFolder.listFiles().length) {
                String phrase = "/Images/" + count + ".png";
                Image image1 = new Image(getClass().getResourceAsStream(phrase));
                image.setImage(image1);
                count++;
            }
        } else {

        }
    }
}