package productos.crud.utils;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class SceneManager {

    public static void cambiarEscena(Event event, String rutaFxml, String titulo) throws IOException {
        Parent root = FXMLLoader.load(SceneManager.class.getResource(rutaFxml));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene escena = new Scene(root);
        stage.setScene(escena);
        stage.setTitle(titulo);
        stage.show();
    }

    public static void cambiarEscena(Stage stage, String rutaFxml, String titulo) throws IOException {
        Parent root = FXMLLoader.load(SceneManager.class.getResource(rutaFxml));
        Scene escena = new Scene(root);
        stage.setScene(escena);
        stage.setTitle(titulo);
        stage.show();
    }
}
