package view;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

import org.assertj.core.util.DateUtil;

import isen.Bdd.Entities.Person;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class DetailContactController {
	@FXML
    private Button deleteButton;
	
	@FXML
    void DeleteContact(ActionEvent event) {
		//deletePerson()
    }
	
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

    
    private void DisplayInformation(Person contact) {
    	contactName.setText(contact.getLastname());
    	contactSurname.setText(contact.getFirstname());
    	contactNickName.setText(contact.getNickname());
    	contactPhoneNumber.setText(contact.getPhone_number());
    	contactAdress.setText(contact.getAdress());
    	contactMailAdress.setText(contact.getEmail_adress());

    }
    
    public void initialize() {
		
	}

}
