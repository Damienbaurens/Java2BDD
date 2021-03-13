package view;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

import com.sun.glass.events.MouseEvent;

import isen.Bdd.App;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class MainPageController implements Initializable{
ObservableList list= FXCollections.observableArrayList();
	
	@FXML
    private ListView<String> repertoireList;
	
	//private ListViewItem contact;
	
	public void initialize(URL url, ResourceBundle rb) {
		loadData();
	}

	private void loadData() {
		list.removeAll(list);
		String a="mon premier contact";
		String b="Mon deuxieme contact";
		String c="Mon troisieme contact";
		String d="Mon quatrieme contact";
		list.addAll(a,b,c,d);
		repertoireList.getItems().addAll(list);
	}
	
	/*@FXML
	private void DisplaySelected(MouseEvent event) {
		App.setRoot("/isen/view/DetailContact");
	}*/
	
	@FXML
    private Button addButton;

    /*@FXML
    void addContact(ActionEvent event) {
    	//addPerson()
    	App.setRoot("/isen/view/Formulaire");
    }*/
    
}

