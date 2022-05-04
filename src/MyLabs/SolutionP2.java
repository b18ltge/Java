package MyLabs;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;


public class SolutionP2 extends Application {
	 private static Double[] values = new Double[3]; // 0 - R, 1 - X, 2 - Y
	 private static Label[] labels = new Label[5]; // 0 - info, 1 - radius, 2 - x, 3 - y, 4 - result
	 private static TextField[] fields = new TextField[3]; // 0 - R, 1 - X, 2 - Y
	 
	 private static double ParseDouble(final String text) {
		 if (text == null || !text.matches("^-{0,1}[0-9]{1,}[.]{0,1}[0-9]{0,}\\b$"))
			 return Double.NaN;
		 return Double.parseDouble(text);
	 }
	 
	 private static String CalcResult() {
		 for(byte i = 0; i < values.length; ++i) {
			 values[i] = ParseDouble(fields[i].getText());
			 if (Double.isNaN(values[i]))
				 return "Вхідні дані задано некореткно;";
		 }
		 
		 return values[1] * values[1] + values[2] * values[2] < values[0] * values[0] ? "Точка з заданими координатами потрапляє в коло"
				 : values[1] * values[1] + values[2] * values[2] > values[0] * values[0] ? "Точка з заданими координатами НЕ потрапляє в коло"
						 : "Точка з заданими координатами лежить на колі";
	 }
	
	 public static void main (String [] args) { 
		 Application.launch(args); 
     }
	 
	 
	 @Override
	 public void start (Stage stage) {		 		 
		 for(byte i = 0; i < labels.length; ++i) {
			 labels[i] = new Label();
			 labels[i].fontProperty().set(new Font(16));
			 labels[i].setLayoutX(40);
		 }
			 
		 labels[0].setLayoutY(40);
		 labels[0].setText("Програма перевіряє чи точка із заданими координатами Х та У\n"
		 		+ "потрапляє всередину радіуса кола, центр якого знаходиться в точці (0;0)");
		 labels[1].setLayoutY(120);
		 labels[1].setText("Введіть радіус кола R:\t");
		 labels[2].setLayoutY(160);
		 labels[2].setText("Введіть значення координати X:\t");
		 labels[3].setLayoutY(200);
		 labels[3].setText("Введіть значення координати Y:\t");
		 labels[4].setLayoutY(240);
		 
		 for(byte i = 0; i < fields.length; ++i) {
			 fields[i] = new TextField();
			 fields[i].setLayoutX(300);
			 fields[i].setLayoutY(labels[i + 1].getLayoutY());
			 fields[i].textProperty().addListener(new ChangeListener<String>() {
			        @Override
			        public void changed(final ObservableValue<? extends String> observable, final String oldValue, final String newValue) {
			   		 labels[4].setText(CalcResult());
			        }
			 });
		 }
		 
		 Group root = new Group(labels[0], labels[1], labels[2], labels[3], labels[4], 
				 fields[0], fields[1], fields[2]);
		 Scene scene = new Scene(root);
		 stage.setScene(scene);
		 stage.setTitle("--== Практична робота №2 ==--");
		 stage.setMinHeight(325);
		 stage.setMinWidth(625);
		 stage.setResizable(false);
		 stage.show();
	 }
}