package MyLabs;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.text.Font;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class SolutionL9 extends Application {
	 private Label[] labels = new Label[3]; // 0 - info, 1 - number, 2 - result
	 private TextField field = new TextField();
	 private boolean selectedTask = false;	// false - task1, true - task2
	 
	 private short ParseShort(final String text) {
		 if (text == null || (selectedTask == false && !text.matches("^[0-9]{4}\\b$")) 
				 || (selectedTask == true && !text.matches("^[0-9]{1,2}\\b$")))
			 return Short.MIN_VALUE;
		 return Short.parseShort(text);
	 }
	 
	 private String CalcResult() {
		 short number = ParseShort(field.getText());
		 
		 if (selectedTask == false) {
			 if (number < 1000 || number > 9999)
				 return "Число задано некоректно"; 
			 
			 byte sum = 0;
			 short prod = 1;
			 
			 while(number > 0) {
				 byte tmp = (byte) (number % 10);
				 sum += tmp; prod *= tmp;
				 number /= 10;
			 }
			 
			 return "Сума цифр: " + sum + "\tДобуток цифр: " + prod;
		 }
		 
		 
		 if (number > 24 || number < 1)
				 return "Число задано некоректно"; 
		 String tmp = "";
	     switch(number) {
	     case 1: tmp = "Перша";
	     break;
	     case 2: tmp = "Друга";
	     break;
	     case 3: tmp = "Третя";
	     break;
	     case 4: tmp = "Четверта";
	     break;
	     case 5: tmp = "П'ята";
	     break;
	     case 6: tmp = "Шоста";
	     break;
	     case 7: tmp = "Сьома";
	     break;
	     case 8: tmp = "Восьма";
	     break;
	     case 9: tmp = "Дев'ята";
	     break;
	     case 10: tmp = "Десята";
	     break;
	     case 11: tmp = "Одинадцята";
	     break;
	     case 12: tmp = "Дванадцята";
	     break;
	     case 13: tmp = "Тринадцята";
	     break;
	     case 14: tmp = "Чотирнадцята";
	     break;
	     case 15: tmp = "П'ятнадцята";
	     break;
	     case 16: tmp = "Шістнадцята";
	     break;
	     case 17: tmp = "Сімнадцята";
	     break;
	     case 18: tmp = "Вісімнадцята";
	     break;
	     case 19: tmp = "Дев'ятнадцята";
	     break;
	     case 20: tmp = "Двадцята";
	     break;
	     case 21: tmp = "Двадцять перша";
	     break;
	     case 22: tmp = "Двадцять друга";
	     break;
	     case 23: tmp = "Двадцять третя";
	     break;
	     default: tmp = "Двадцять четверта";
	     }
		 return tmp + " година";
	 }

	 
	 public static void main (String [] args) { 
		 Application.launch(args); 
    }
	 
	 
	 @Override
	 public void start (Stage stage) {		
		 Menu menu = new Menu("Menu");
		 MenuItem menuTask1 = new MenuItem("Завдання 1");
		 MenuItem menuTask2 = new MenuItem("Завдання 2");
		 menu.getItems().add(menuTask1);
		 menu.getItems().add(menuTask2);
		 
		 EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
	            public void handle(ActionEvent e)
	            {
	            	selectedTask = !selectedTask;
	            	field.setText("");
	            	if (selectedTask == false)
	            		labels[0].setText("Програма обчислює суму та добуток цифр введеного чотиризначного числа.");
	            	else labels[0].setText("Програма виводить назву години за її введеним порядковим номером.");
	            	labels[2].setText("");
	            }
	        };
	     menuTask1.setOnAction(event);
	     menuTask2.setOnAction(event);
	        
		 MenuBar menuBar = new MenuBar();
		 menuBar.getMenus().add(menu);
		 
		 for(byte i = 0; i < labels.length; ++i) {
			 labels[i] = new Label();
			 labels[i].fontProperty().set(new Font(16));
			 labels[i].setLayoutX(25);
		 }
			 
		 labels[0].setLayoutY(60);
		 labels[0].setText("Програма обчислює суму та добуток цифр введеного чотиризначного числа.");
		 labels[1].setLayoutY(100);
		 labels[1].setText("Введіть число:\t");
		 labels[2].setLayoutY(140);
		 
		 field = new TextField();
		 field.setLayoutX(200);
		 field.setLayoutY(labels[1].getLayoutY());
		 field.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(final ObservableValue<? extends String> observable, final String oldValue, final String newValue) {
			labels[2].setText(CalcResult());
			}
		 });
		 
		 Group root = new Group(labels[0], labels[1], labels[2], field, menuBar);
		 Scene scene = new Scene(root);
		 stage.setScene(scene);
		 stage.setTitle("--== Лабораторна робота №9 ==--");
		 stage.setMinHeight(325);
		 stage.setMinWidth(625);
		 stage.setResizable(false);
		 stage.show();
	 }
}
