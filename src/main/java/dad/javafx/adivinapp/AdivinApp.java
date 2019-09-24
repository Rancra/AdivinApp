package dad.javafx.adivinapp;

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

	private TextField nombreText;
	private Button saludarButton;
	private Label saludoLabel;
	int numeroRandom = (int) (Math.random() * 100) + 1;
	int contador = 1;
	String mayoromenor = "";

	@Override
	public void start(Stage primaryStage) throws Exception {

		nombreText = new TextField();
		nombreText.setPromptText("0");
		nombreText.setMaxWidth(150);

		saludoLabel = new Label("Introduce un número del 1 al 100");

		saludarButton = new Button("Comprobar");
		saludarButton.setDefaultButton(true);
		saludarButton.setOnAction(evento -> onsaludarButtonAction(evento));

		VBox root = new VBox();
		root.setSpacing(5);
		root.setAlignment(Pos.CENTER);
		root.getChildren().addAll(saludoLabel, nombreText, saludarButton);

		Scene scene = new Scene(root, 320, 200);

		primaryStage.setTitle("CheckPalindrome");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	private void onsaludarButtonAction(ActionEvent evento) {

		String numeroIntroducido = nombreText.getText();

		if (!esNumero(nombreText.getText())) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("AdivinApp");
			alert.setHeaderText("Error");
			alert.setContentText("El número introducido no es válido.");

			alert.showAndWait();
		} else {

			if (Integer.parseInt(nombreText.getText()) == numeroRandom) {

				contador++;
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("AdivinApp");
				alert.setHeaderText("¡Has ganado!");
				alert.setContentText(
						"Solo has necesitado " + contador + " intentos. \n\n Vuelve a jugar y hazlo mejor.");

				alert.showAndWait();
				contador = 0;
				numeroRandom = (int) (Math.random() * 100) + 1;
			} else {

				if (Integer.parseInt(nombreText.getText()) < numeroRandom) {
					mayoromenor = "mayor";
				} else {
					mayoromenor = "menor";
				}

				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("AdivinApp");
				alert.setHeaderText("¡Has fallado!");
				alert.setContentText("El número a adivinar es " + mayoromenor + " que " + numeroIntroducido
						+ ". \n\n Vuelve a intentarlo.");

				alert.showAndWait();
				contador++;

			}

		}

	}

	public static boolean esNumero(String cadena) {

		boolean resultado;

		try {
			Integer.parseInt(cadena);
			resultado = true;
		} catch (NumberFormatException excepcion) {
			resultado = false;
		}

		return resultado;
	}

	public static void main(String[] args) {

		launch(args);

	}

}
