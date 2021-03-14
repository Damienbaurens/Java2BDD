package view;

import java.io.IOException;

import isen.Bdd.App;
import isen.Bdd.Daos.PersonDao;
import isen.Bdd.Entities.Person;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class DetailContactController {
	public static Person contactSelected;
	@FXML
    private Button deleteButton;
	
	/*@FXML
    void DeleteContact(ActionEvent event) {
		PersonDao.deletePerson(contactSelected);
    }*/
	
	@FXML
    private Text contactName;

    @FXML
    private Text contactSurname;

    @FXML
    private Text contactNickName;

    @FXML
    private Text contactPhoneNumber;

    @FXML
    private Text contactAdress;

    @FXML
    private Text contactMailAdress;

    
    public void DisplayInformation() {
    	contactName.setText(contactSelected.getLastname());
    	contactSurname.setText(contactSelected.getFirstname());
    	contactNickName.setText(contactSelected.getNickname());
    	contactPhoneNumber.setText(contactSelected.getPhone_number());
    	contactAdress.setText(contactSelected.getAdress());
    	contactMailAdress.setText(contactSelected.getEmail_adress());

    }
    
    public void initialize() {
    	DisplayInformation();
	}
    
    @FXML
    private Button backButton;

    @FXML
    void backFunction(ActionEvent event) throws IOException {
    	App.setRoot("/isen/view/MainPage");
    }

}
