package cz.kamproductions.scp_wars.Game.UI.FacilitiesManagement;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class FacilitiesManagementBaseController extends Stage implements Initializable {
    public FacilitiesManagementBaseController(Parent parent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/cz/kamproductions/scp_wars/Game/UI/HumanResources/HumanResources-BaseUI.fxml"));
        loader.setController(this);

        try{
            Parent parentLoc = (Parent)loader.load();
            Scene scene = new Scene(parentLoc);
            setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("HR INIT INIT [OK]");

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
