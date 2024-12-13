package flipperfly;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class MenuController {

    @FXML
    private ImageView exitbutton;
    @FXML
    private ImageView playbutton;
    @FXML
    private void goToScene2(MouseEvent event) {
            try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Flipperfly.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();

            stage.setScene(new Scene(root));
            stage.setTitle("Flipperfly");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void exitApp(MouseEvent event) {
        Stage stage = (Stage) exitbutton.getScene().getWindow();
        stage.close();
    }
    
}