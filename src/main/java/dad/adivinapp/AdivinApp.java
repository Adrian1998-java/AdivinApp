package dad.adivinapp;

import java.util.InputMismatchException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdivinApp extends Application {

	Label mensaje = new Label();
	Button numButton = new Button();
	TextField numTextField = new TextField();

	double numDetectar = Math.random() * 100;
	int contador = 0;

	@Override
	public void start(Stage primaryStage) throws Exception {

		// Creamos la etiqueta que indica el mensaje

		mensaje.setText("Escribe un número del 1 al 100");
		mensaje.setLayoutX(20);
		mensaje.setLayoutY(20);

		// Creamos el textfiel
		numTextField.setPromptText("Escribe aqui");
		numTextField.setMaxWidth(150);

		// Creamos el boton
		numButton.setText("Comprobar");
		numButton.setDefaultButton(true);
		numButton.setOnAction(e -> onNumButtonPress(e));

		// Panel VBox
		VBox root = new VBox();
		root.setSpacing(5);
		root.setAlignment(Pos.CENTER);
		root.getChildren().addAll(mensaje, numTextField, numButton);

		Scene scene = new Scene(root, 320, 200);

		primaryStage.setTitle("AdivinApp");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	private void onNumButtonPress(ActionEvent e) {
		// Implementamos alerts
		try {
			if (Integer.valueOf(numTextField.getText()) == (int) numDetectar) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("AdivinApp");
				alert.setHeaderText("¡Has ganado!");
				alert.setContentText(
						"Solo has necesitado " + contador + " intentos. \n" + "Vuelve a jugar y hazlo mejor.");

				alert.showAndWait();
				numDetectar = Math.random() * 100;
				contador = 0;
			} else {
				if (Integer.valueOf(numTextField.getText()) != (int) numDetectar) {
					String resp = Integer.valueOf(numTextField.getText()) < (int) numDetectar ? "mayor" : "menor";
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("AdivinApp");
					alert.setHeaderText("¡Has fallado!");
					alert.setContentText("El número a adivinar es " + resp + " que el número dado. (" + numTextField.getText() + ")");

					alert.showAndWait();
					contador++;
				}
			}

		} catch (NumberFormatException error) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("AdivinApp");
			alert.setHeaderText("Error");
			alert.setContentText("El valor introducido no es válido...");

			alert.showAndWait();
		}

	}

	public static void main(String[] args) {
		launch(args);

	}

}
