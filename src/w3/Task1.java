package w3;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.ListIterator;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Task1 extends Application   {
	OnePane num1;
	int index;
	Stage first;
	int howMany;
	ArrayList<AddressBook> adlist = new ArrayList<AddressBook>();
	private ListIterator<AddressBook> itr=adlist.listIterator();  
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);

	}
	public void load() throws IOException{ //load already saved object
		 howMany=AddressBook.size();
		
		int i=0;
		if(howMany==0) {
			adlist.add(new AddressBook(i));
		}
		while(i<howMany) {
			
			adlist.add(new AddressBook(i));
			i++;
		}
	
	}
	public void moveScene(Stage first, OnePane pane) {
		num1 =pane;
buttonClick();
		first.getScene().setRoot(num1);
	}
	public void addScene(Stage first) throws IOException {
	
		if(adlist.get(adlist.size()-1).isEmpty()) { //if last one is empty
			index = adlist.size()-1;
			num1 = new OnePane(adlist.get(adlist.size()-1));
		}else {
			index = adlist.size();
			adlist.add(new AddressBook(index));
			num1 = new OnePane(adlist.get(index));
			num1.update.setOnAction(e->{
				howMany++;
			});
		}
		buttonClick();
		first.getScene().setRoot(num1);
	}
	
				
public void buttonClick() {
	
	 num1.add.setOnAction(e->{
			try {
				addScene(first);
			} catch (IOException e1) {
				System.out.println("Can't open add");
			}
		
		 });
		num1.previous.setOnAction(e->{
			try {
				System.out.println("previ");
				if(index==0) {}else {
					num1 = null;
					num1 = new OnePane(adlist.get(--index));
					moveScene(first,num1);	
				}
			
			}catch(Exception ex){
			
			}
		});
		num1.firstbb.setOnAction(e->{
			try {
				index=0;
				num1 = null;
				num1 = new OnePane(adlist.get(index));
				moveScene(first,num1);	
			}catch(Exception ex){}
		});
		num1.last.setOnAction(e->{
			try {
				index=adlist.size()-1;
				num1 = null;
				num1 = new OnePane(adlist.get(index));
				moveScene(first,num1);	
			}catch(Exception ex){}
		});
		num1.next.setOnAction(e->{
			try {
				System.out.println("next");
				
				if(index==adlist.size()-1) {}else {
					num1 = null;
					num1 = new OnePane(adlist.get(++index));
					moveScene(first,num1);	
				}
			}catch(Exception ex){
			
			}	
		});
}
public void mainStage(Stage first, AddressBook object)  throws IOException { // use making pane
		
		index = 0;
		num1 = new OnePane(object);
		
		Scene scene = new Scene(num1);
		buttonClick();
		first.setTitle("Address Book");
		first.setScene(scene);
		first.show();
	}
	public void start(Stage primaryStage) throws InvocationTargetException {
	try {
		load();
		first=primaryStage;
		mainStage(primaryStage, adlist.get(0));
		
	}catch(IOException e) {
		System.out.println("Can not read this file");
	}
		
	}

}
// pane 안에 이름 어드레스 고정시켜야함