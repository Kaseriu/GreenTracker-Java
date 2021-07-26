package org.greentracker.scene.controllers.stateControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.greentracker.models.Session;
import org.greentracker.models.User;
import org.greentracker.requests.StateRequest;
import org.greentracker.scene.controllers.MenuController;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class DeleteStateController {

    @FXML
    private ImageView imageView;
    @FXML
    private Text stateToDeleteNameText;
    @FXML
    private TextField stateToDeleteNameTextField;
    @FXML
    private Text validateText;
    @FXML
    private Button returnButton;

    private Session session;
    private User user;
    private Stage stage;
    private Scene scene;

    public void setSessionAndUser(Session session, User user) {
        this.session = session;
        this.user = user;
    }

    public void validateButton() throws Exception {
        if (StateRequest.getStateByName(this.session, this.stateToDeleteNameTextField.getText()) != null) {
            StateRequest.deleteState(this.session, this.stateToDeleteNameTextField.getText());
            this.validateText.setVisible(true);
            this.stateToDeleteNameTextField.clear();
        } else {
            this.stateToDeleteNameTextField.clear();
            this.stateToDeleteNameTextField.setPromptText("Aucun état de ce nom");
        }
    }

    public void switchToMenuWindow(ActionEvent actionEvent) throws IOException {
        String resourcesPath = Objects.requireNonNull(getClass().getResource("/")).toString();
        FXMLLoader loader = new FXMLLoader(new URL(resourcesPath + "MenuWindow.fxml"));
        Parent menuWindow = loader.load();

        MenuController menuController = loader.getController();
        menuController.setSessionAndUser(this.session, this.user);

        this.stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        this.scene = new Scene(menuWindow);
        this.stage.setScene(this.scene);
        this.stage.show();
    }
}
