package TablesPackage;

import static HomePackage.MainClass.stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class TablesController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    
    @FXML
    void goToAddTable(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/AddTablePackage/AddTable.fxml"));

        Scene scene = new Scene(root, 700, 400);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void goToHome(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/HomePackage/Home.fxml"));

        Scene scene = new Scene(root, 700, 400);
        stage.setScene(scene);
        stage.show();
    }

}
