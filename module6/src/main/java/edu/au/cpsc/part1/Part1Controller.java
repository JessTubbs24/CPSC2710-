package edu.au.cpsc.part1;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class Part1Controller {

  @FXML
  private TextField messageTextField, echoTextField, firstBidirectionalTextField, secondBidirectionalTextField;

  @FXML
  private ImageView secretOverlayImageView;

  @FXML
  private Slider secretSlider;

  @FXML
  private CheckBox selectMeCheckBox;

  @FXML
private Label selectMeLabel;

@FXML
private TextField tweetTextField;

@FXML
private Label numberOfCharactersLabel, validityLabel;

public void initialize() {
  // binding to reflect text changes from messageTextField to echoTextField
        // Why won't text show in field when .bind??????
  messageTextField.textProperty().bindBidirectional(echoTextField.textProperty());

  //  bi-directional property binding between firstBidirectionalTextField and secondBidirectionalTextField
  firstBidirectionalTextField.textProperty().bindBidirectional(secondBidirectionalTextField.textProperty());

  // set the opacity of secretOverlapImageView based on secretSlider value
      //why can't I see the img.png??
  secretOverlayImageView.opacityProperty().bind(secretSlider.valueProperty().divide(100));

  // display true/false based on selectMeCheckBox state
  selectMeLabel.textProperty().bind(Bindings.when(selectMeCheckBox.selectedProperty()).then("true").otherwise("false"));

  //  number of letters in tweetTextField
  numberOfCharactersLabel.textProperty().bind(tweetTextField.textProperty().length().asString());

  // display "Valid" if tweetTextField has 10 or fewer characters, otherwise "Invalid"
  validityLabel.textProperty().bind(Bindings.when(tweetTextField.textProperty().length().lessThanOrEqualTo(10)).then("Valid").otherwise("Invalid"));
}


}
