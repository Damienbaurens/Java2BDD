package view;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import isen.Bdd.App;
import isen.Bdd.Daos.PersonDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import isen.Bdd.Entities.Person;

public class MainPageController implements Initializable {
	ObservableList<String> list= FXCollections.observableArrayList();
	
	List<Person> personList=PersonDao.listPerson();
	
	@FXML
    private ListView<String> repertoireList;
	
	//private ListViewItem contact;
	
	public void initialize(URL url, ResourceBundle rb) {
		loadData();
	}

	private void loadData() {
		list.removeAll(list);
		for(Person person : personList) {
			list.add(person.getNickname());
		}
		repertoireList.getItems().addAll(list);
		
	}
	
	@FXML
	private void DisplaySelected(MouseEvent event) throws IOException {
		App.setRoot("/isen/view/DetailContact");
	}
	
	@FXML
    private Button addButton;

    @FXML
    void addContact(ActionEvent event) throws IOException {
    	//addPerson()
    	App.setRoot("/isen/view/Formulaire");
    }
    
    /*
    @FXML
    void ContactSelection(MouseEvent event) throws IOException {
    	App.setRoot("isen/view/DetailContact");
    }*/
    
}

