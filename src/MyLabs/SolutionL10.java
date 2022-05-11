package MyLabs;

import java.time.LocalDate;

import Misc.L10.CarPart;
import Misc.L10.MyDataBase;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ComboBox;
import javafx.scene.control.cell.PropertyValueFactory;

public class SolutionL10 extends Application {
	private MyDataBase db = new MyDataBase();
	
	private MenuBar menuBar = new MenuBar();
	private Menu menu = new Menu("Menu");
	private MenuItem menuItems[] = new MenuItem[]{
			 new MenuItem("Перегляд"), new MenuItem("Додавання"), new MenuItem("Редагування"), 
			 new MenuItem("Вилучення")
	 };
	
	private Label labels[] = new Label[6]; // 0 - name, 1 - date, 2 - price, 3 - count, 4 - info, 5 - result
	private TextField fields[] = new TextField[4]; // 0 - name, 1 - date, 2 - price, 3 - count
	private Button button = new Button();	
	private ComboBox<String> comboBox = new ComboBox<String>();
	
	private byte selectedMenuItem = 0;	// 0 - View, 1 - Add, 2 - Edit, 3 - Remove, 4 - Search
	private EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
        public void handle(ActionEvent e)
        {
        	if (!(e.getSource() instanceof MenuItem))
        		return;
        	
        	switch (((MenuItem) e.getSource()).getText()) {
        	case "Перегляд":{
        		selectedMenuItem = 0;
        		break;
        	}
        	case "Додавання":{
        		selectedMenuItem = 1;
        		break;
        	}
        	case "Редагування":{
        		selectedMenuItem = 2;
        		break;
        	}
        	case "Вилучення":{
        		selectedMenuItem = 3;
        		break;
        	}
        	default: selectedMenuItem = 4;
        	}
        	
        	switch(selectedMenuItem) {
        	case 0: {
        		labels[4].setText("Перегляд інформації про автозапчастини:");
        		table.setVisible(true);  comboBox.setVisible(false);
        		button.setVisible(false);
        		labels[5].setText("");

        		for(byte i = 1; i < labels.length - 2; ++i) {
        			labels[i].setVisible(false);
        			fields[i].setVisible(false);
        		}
        		fields[0].setVisible(true);	 labels[0].setVisible(true);
        		fields[0].setText("");
        		labels[0].setText("Пошук:");
        		table.setItems(db.getData());
        		break;
        	}
        	case 1: {
        		labels[4].setText("Додавання записів:");
        		labels[0].setText("Введіть назву автозапчастини:\t");
       		 	labels[1].setText("Введіть дату виготовлення:\t");
       		 	labels[2].setText("Введіть ціну запчастини:\t");
       		 	labels[3].setText("Введіть кількість запчастин:\t");
       		    labels[5].setText("");
       		 	
        		table.setVisible(false); comboBox.setVisible(false);
        		for(byte i = 0; i < fields.length; ++i) {
        			fields[i].setVisible(true);
        			labels[i].setVisible(true);
        			fields[i].setText("");
        		}
        		button.setText("Додати"); button.setVisible(true);
        		fields[0].setLayoutX(290); fields[0].setLayoutY(labels[0].getLayoutY());
        		labels[0].setLayoutX(labels[1].getLayoutX()); labels[0].setLayoutY(100);
        		break;
        	}
        	case 2: {
        		labels[4].setText("Редагування записів:");
        		labels[0].setText("Введіть порядковий № запису:\t");
        		labels[1].setText("Оберіть параметр для редагування:\t");
        		labels[2].setText("Введіть значення:\t");
        		labels[5].setText("");
        		
        		table.setVisible(false); comboBox.setVisible(true);
        		comboBox.setLayoutX(320); comboBox.setLayoutY(140);
        
        		for(byte i = 1; i < fields.length; ++i) {
        			fields[i].setVisible(false);
        			labels[i].setVisible(false);
        			fields[i].setText("");
        		}
        		labels[0].setVisible(true); labels[1].setVisible(true); labels[2].setVisible(true);
        		fields[0].setVisible(true); fields[2].setVisible(true);
        		button.setVisible(true); button.setText("Редагувати");
        		break;
        	}
        	case 3: {
        		labels[4].setText("Вилучення записів:");
        		labels[0].setText("Введіть порядковий № запису:");
        		labels[0].setVisible(true); fields[0].setVisible(true);
        		labels[5].setText("");
        		table.setVisible(false); comboBox.setVisible(false);
        		for(byte i = 1; i < fields.length; ++i) {
        			fields[i].setVisible(false);
        			labels[i].setVisible(false);
        			fields[i].setText("");
        		}
        		button.setText("Вилучити"); button.setVisible(true);
        		break;
        	}
        	}
        }
    };
	 
	private TableView<CarPart> table = new TableView<CarPart>();
	@SuppressWarnings("rawtypes")
	private TableColumn tableColumns[] = new TableColumn[] {
			 new TableColumn<CarPart,Integer>("Id:"), new TableColumn<CarPart,String>("Name:"),
			 new TableColumn<CarPart,LocalDate>("Date:"), new TableColumn<CarPart,Float>("Price:"),
			 new TableColumn<CarPart,Short>("Count:")
		 };

	
	public static void main (String [] args) { 
		 Application.launch(args); 
    }
	 
	@SuppressWarnings("unchecked")
	private void setupComponents() {
		for(final var i : menuItems)
			 i.setOnAction(event);
		 menu.getItems().addAll(menuItems);
		 menuBar.getMenus().add(menu);
		 
		 for(final var i : tableColumns) {
			 i.setMinWidth(80);
			 i.setResizable(false);
		 }
			 
		 tableColumns[0].setCellValueFactory(new PropertyValueFactory<CarPart, Integer>("id"));
		 tableColumns[1].setCellValueFactory(new PropertyValueFactory<CarPart, String>("name"));
		 tableColumns[2].setCellValueFactory(new PropertyValueFactory<CarPart, LocalDate>("date"));
		 tableColumns[3].setCellValueFactory(new PropertyValueFactory<CarPart, Float>("price"));
		 tableColumns[4].setCellValueFactory(new PropertyValueFactory<CarPart, Short>("count"));
	     table.getColumns().addAll(tableColumns);
	     table.setItems(db.getData());
	     table.setLayoutX(450);
	     table.setLayoutY(70);
		 
		 
		 for(byte i = 0; i < labels.length; ++i) {
			 labels[i] = new Label();
			 labels[i].fontProperty().set(new Font(16));
			 labels[i].setLayoutX(50);
		 }
		 
		 labels[4].setLayoutY(60);
		 labels[4].setText("Перегляд інформації про автозапчастини");
		 labels[5].setLayoutY(260); 
		 
		 button.setLayoutX(350); button.setLayoutY(250);
		 
		 comboBox.setVisible(false); button.setVisible(false);
		 
		 for(int i = 0; i < labels.length - 2; ++i) {
			 labels[i].setLayoutY(60 + 40 * (i + 1));
			 fields[i] = new TextField();
			 fields[i].setLayoutX(290); fields[i].setLayoutY(labels[i].getLayoutY());
			 labels[i].setVisible(false); fields[i].setVisible(false);
		 }
		 
		 fields[0].setVisible(true);	 labels[0].setVisible(true);
		 labels[0].setText("Пошук:");
		 comboBox.getItems().addAll("Назва запчастини", "Дата виготовлення", 
				 "Ціна запчастини", "Кількість запчастин");
	}
	private void addRecord() {
		if (fields[0].getText() == "" || fields[1].getText() == "" || fields[2].getText() == ""
				 || fields[3].getText() == "") {
			 labels[5].setText("Дані введені некоректно");
			 return;
		 }
			 
		 try {
			 CarPart testPart = new CarPart(db.getMaxId() + 1, fields[0].getText(), LocalDate.parse(fields[1].getText()), 
					 Float.parseFloat(fields[2].getText()), Short.parseShort(fields[3].getText()));
			 if (db.insert(testPart.getName(), testPart.getDate(), testPart.getPrice(), testPart.getCount())) {
				 labels[5].setText("Новий запис успішно додано!");
				 for(var i : fields)
					 i.setText("");
				 return;
			 }
			 else {
				 labels[5].setText("Дані введені некоректно");
				 return;
			 }
			 
		 } catch(Exception e) { 
			 labels[5].setText("Дані введені некоректно");
			 return;
		 }
	}
	private void editRecord() {
		if (fields[0].getText() == "" || fields[2].getText() == "" 
				|| comboBox.getValue() == null || comboBox.getValue() == "") {
			labels[5].setText("Дані введені некоректно");
			return;
		}
		Object value;
		try {
			switch(comboBox.getValue()) {
			case "Дата виготовлення": {
				value = LocalDate.parse(fields[2].getText());
				break;
			}
			case "Ціна запчастини":{
				value = Float.parseFloat(fields[2].getText());
				break;
			}
			case "Кількість запчастин":{
				value = Short.parseShort(fields[2].getText());
				break;
			}
			default : value = fields[2].getText();
			}
			int id = Integer.parseInt(fields[0].getText());
			if (db.update(id, value)) {
				labels[5].setText("Запис №" + id + " змінено успішно");
				fields[0].setText("");
				fields[2].setText("");
				comboBox.setValue("");
			}
			else labels[5].setText("Дані введені некоректно");
		}
		catch(Exception e) {
			labels[5].setText("Дані введені некоректно");
			return;
		}
	}
	private void removeRecord() {
		try {
			int id = Integer.parseInt(fields[0].getText());
			if (db.delete(id)) {
				labels[5].setText("Запис №" + id + " вилучено успішно");
				fields[0].setText("");
			}
			else labels[5].setText("Дані введені некоректно");
		}
		catch(Exception e) {
			labels[5].setText("Дані введені некоректно");
			return;
		}
		
	}
	@Override
	public void start (Stage stage) {	
		setupComponents();
		 
		 fields[0].textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(final ObservableValue<? extends String> observable, final String oldValue, final String newValue) {
				if (selectedMenuItem != 0)
					return;
				table.setItems(db.find(newValue));
			}
		 });
		 
		 button.setOnAction(new EventHandler <ActionEvent>() {
			 @Override
			 public void handle(ActionEvent event) {
				 if (selectedMenuItem == 1) {
					 addRecord();
				 }
				 else if (selectedMenuItem == 2) {
					 editRecord();
				 }
				 else if (selectedMenuItem == 3) {
					 removeRecord();
				 }
			 }
		 });

		 Group root = new Group(labels[0], labels[1], labels[2], labels[3], labels[4], labels[5],
				 fields[0], fields[1], fields[2], fields[3], button, menuBar, table, comboBox);
		 Scene scene = new Scene(root);
		 stage.setScene(scene);
		 stage.setTitle("--== Лабораторна робота №10 ==--");
		 stage.setMinHeight(600); stage.setMinWidth(900);
		 stage.setResizable(false);
		 stage.show();
	 }
}
