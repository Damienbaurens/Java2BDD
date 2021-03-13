import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import App.java;
import Person.java;


public class MainPageController {
ObservableList<E> list=FXCollections.observableArrayList();
	
	@FXML
    private ListView<String> repertoireList;
	
	private ListViewItem contact;
	
	public void initialize(URL url, ResourceBundle rb) {
		loadData();
	}

	private void loadData() {
		list.removeAll(list);
		String a="mon premier contact";
		String b="Mon deuxieme contact";
		String c="Mon troisieme contact";
		String d="Mon quatrieme contact";
		list.addAll(ab,c,d);
		repertoireList.getItems().addAll(list);
	}
	
	@FXML
	private void DisplaySelected(MouseEvent event) {
		App.setRoot("/isen/view/DetailContact");
	}
	
	@FXML
    private Button addButton;

    @FXML
    void addContact(ActionEvent event) {
    	//addPerson()
    	App.setRoot("/isen/view/Formulaire");
    }
}
