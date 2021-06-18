package il.cshaifasweng.OCSFMediatorExample.client;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class OrderController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Button nextButton;

    @FXML
    private TabPane tabPane;

    @FXML
    private Tab tab1;

    @FXML
    private TableView<?> itemTable;

    @FXML
    private Tab tab2;

    @FXML
    private TextField firstNameVerField;

    @FXML
    private TextField lastNameVerField;

    @FXML
    private TextField idNumVerField;

    @FXML
    private TextField emailVerField;

    @FXML
    private TextField cardField;

    @FXML
    private Label firstNameVerEmptyLabel;

    @FXML
    private Label lastNameVerEmptyLabel;

    @FXML
    private Label idVerEmptyLabel;

    @FXML
    private Label emailVerEmptyLabel;

    @FXML
    private Label phoneVerEmptyLabel;

    @FXML
    private Label invalidFirstNameVerLabel;

    @FXML
    private Label invalidLastNameVerLabel;

    @FXML
    private Label invalidIdVerLabel;

    @FXML
    private Label invalidEmailVerLabel;

    @FXML
    private Label invalidPhoneVerLabel;

    @FXML
    private Button changeDetailsButton;

    @FXML
    void cancel(ActionEvent event) {

    }

    @FXML
    void changeDetails(ActionEvent event) {

    }

    @FXML
    void goBack(ActionEvent event) {

    }

    @FXML
    void next(ActionEvent event) {

    }

    @FXML
    void updateButtons(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert backButton != null : "fx:id=\"backButton\" was not injected: check your FXML file 'order.fxml'.";
        assert cancelButton != null : "fx:id=\"cancelButton\" was not injected: check your FXML file 'order.fxml'.";
        assert nextButton != null : "fx:id=\"nextButton\" was not injected: check your FXML file 'order.fxml'.";
        assert tabPane != null : "fx:id=\"tabPane\" was not injected: check your FXML file 'order.fxml'.";
        assert tab1 != null : "fx:id=\"tab1\" was not injected: check your FXML file 'order.fxml'.";
        assert itemTable != null : "fx:id=\"itemTable\" was not injected: check your FXML file 'order.fxml'.";
        assert tab2 != null : "fx:id=\"tab2\" was not injected: check your FXML file 'order.fxml'.";
        assert firstNameVerField != null : "fx:id=\"firstNameVerField\" was not injected: check your FXML file 'order.fxml'.";
        assert lastNameVerField != null : "fx:id=\"lastNameVerField\" was not injected: check your FXML file 'order.fxml'.";
        assert idNumVerField != null : "fx:id=\"idNumVerField\" was not injected: check your FXML file 'order.fxml'.";
        assert emailVerField != null : "fx:id=\"emailVerField\" was not injected: check your FXML file 'order.fxml'.";
        assert cardField != null : "fx:id=\"cardField\" was not injected: check your FXML file 'order.fxml'.";
        assert firstNameVerEmptyLabel != null : "fx:id=\"firstNameVerEmptyLabel\" was not injected: check your FXML file 'order.fxml'.";
        assert lastNameVerEmptyLabel != null : "fx:id=\"lastNameVerEmptyLabel\" was not injected: check your FXML file 'order.fxml'.";
        assert idVerEmptyLabel != null : "fx:id=\"idVerEmptyLabel\" was not injected: check your FXML file 'order.fxml'.";
        assert emailVerEmptyLabel != null : "fx:id=\"emailVerEmptyLabel\" was not injected: check your FXML file 'order.fxml'.";
        assert phoneVerEmptyLabel != null : "fx:id=\"phoneVerEmptyLabel\" was not injected: check your FXML file 'order.fxml'.";
        assert invalidFirstNameVerLabel != null : "fx:id=\"invalidFirstNameVerLabel\" was not injected: check your FXML file 'order.fxml'.";
        assert invalidLastNameVerLabel != null : "fx:id=\"invalidLastNameVerLabel\" was not injected: check your FXML file 'order.fxml'.";
        assert invalidIdVerLabel != null : "fx:id=\"invalidIdVerLabel\" was not injected: check your FXML file 'order.fxml'.";
        assert invalidEmailVerLabel != null : "fx:id=\"invalidEmailVerLabel\" was not injected: check your FXML file 'order.fxml'.";
        assert invalidPhoneVerLabel != null : "fx:id=\"invalidPhoneVerLabel\" was not injected: check your FXML file 'order.fxml'.";
        assert changeDetailsButton != null : "fx:id=\"changeDetailsButton\" was not injected: check your FXML file 'order.fxml'.";

    }
}
