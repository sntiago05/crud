package productos.crud.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextInputDialog;

import java.util.Optional;


public class Alertas {

    private static void aplicarEstilo(DialogPane dialogPane, String claseCss) {
        try {
            dialogPane.getStylesheets().add(
                    Alertas.class.getResource("src/main/productos/crud/alertas.css").toExternalForm()
            );
            dialogPane.getStyleClass().add(claseCss);
        } catch (Exception e) {

        }
    }

    public static void mostrarInfo(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        aplicarEstilo(alert.getDialogPane(), "alerta-info");
        alert.showAndWait();
    }

    public static void mostrarError(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        aplicarEstilo(alert.getDialogPane(), "alerta-error");
        alert.showAndWait();
    }


    public static void mostrarAdvertencia(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        aplicarEstilo(alert.getDialogPane(), "alerta-warning");
        alert.showAndWait();
    }

    public static boolean confirmar(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        aplicarEstilo(alert.getDialogPane(), "alerta-confirmacion");
        Optional<ButtonType> resultado = alert.showAndWait();
        return resultado.isPresent() && resultado.get() == ButtonType.OK;
    }


    public static Optional<String> pedirTexto(String titulo, String mensaje, String valorPorDefecto) {
        TextInputDialog dialog = new TextInputDialog(valorPorDefecto);
        dialog.setTitle(titulo);
        dialog.setHeaderText(null);
        dialog.setContentText(mensaje);
        aplicarEstilo(dialog.getDialogPane(), "alerta-info");
        return dialog.showAndWait();
    }
}
