package w3;

import java.io.IOException;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class OnePane extends GridPane {
	AddressBook object;
	public	Button add;
	public Button next;
	public Button previous;
	public 	Button firstbb;
	public 	Button last;
	public Button update;
	TextField nameField;
	TextField streetField;
	TextField cityField;
	TextField stateField;
	TextField zipField ;
	OnePane(AddressBook newOb) {
		object =newOb;
		setPadding(new Insets(10,10,10,10));
		setVgap(10);
		setHgap(10);
		//name
		Label name = new Label("Name");
		nameField = new TextField(object.Name);
		add(name, 1, 1);
		setColumnSpan(nameField,6);
		add(nameField, 2, 1);
		
		///street
		Label street = new Label("Street");
		 streetField = new TextField(object.Street);
		
		add(street, 1, 2);
		setColumnSpan(streetField,6);
		add(streetField, 2, 2);
		//City
		Label city = new Label("City");
		 cityField = new TextField(object.City);
		cityField.setPrefWidth(70);
		add(city, 1, 3);
		add(cityField, 2, 3);
		//state
		Label state = new Label("State");
		stateField = new TextField(object.State);
		stateField.setPrefWidth(30);
		add(state, 3, 3);
		add(stateField, 4, 3);
		//zip
		Label zip = new Label("Zip");
		 zipField = new TextField(object.Zip);
		zipField.setPrefWidth(60);
		add(zip, 5, 3);
		add(zipField, 6, 3);
		HBox buttonAll = new HBox();
		////////////////////////////////////////////////////////////
		setColumnSpan(buttonAll,6);
		add(buttonAll, 1, 4);
		buttonAll.setAlignment(Pos.BASELINE_CENTER);
		buttonAll.setSpacing(10);
		//add
		 add = new Button("Add");
		
		buttonAll.getChildren().add(add);
		//first
		
		 firstbb = new Button("First");
		buttonAll.getChildren().add(firstbb);
		//next
		 next = new Button("Next");
		buttonAll.getChildren().add(next);
		//Previous
		 previous = new Button("Previous");
		buttonAll.getChildren().add(previous);
		//last
		 last = new Button("Last");
		buttonAll.getChildren().add(last);
		//Update
		 update = new Button("Update");
		buttonAll.getChildren().add(update);
		update.setOnAction(e->{
				
			 try {
				 object.Name = nameField.getText();
				 object.State =stateField.getText();
				 object.Street=	 streetField.getText();
				object.City=cityField.getText();
				object.Zip= zipField.getText();
				 object.save(nameField.getText(), streetField.getText(), cityField.getText(), stateField.getText(), zipField.getText());
			 }catch(IOException ex) {
				 System.out.println("Can't get field");
			 }
		
		 });
		
		
	}
	
	
}
