package view;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import isen.Bdd.App;
import isen.Bdd.Daos.PersonDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class FormController implements Initializable {
	
	ObservableList<String> dayChoices = FXCollections.observableArrayList();
	ObservableList<String> monthChoices = FXCollections.observableArrayList("01","02","03","04","05","06","07","08","09","10","11","12");
	ObservableList<String> yearChoices = FXCollections.observableArrayList();
	
	@FXML
	TextField lastNameTF;
	@FXML
	TextField firstNameTF;
	@FXML
	TextField surNameTF;
	@FXML
	TextField phoneNumberTF;
	@FXML
	TextField addressTF;
	@FXML
	TextField emailTF;
		
	@FXML
	ChoiceBox<String> dayChoiceBox;
	
	@FXML
	ChoiceBox<String> monthChoiceBox;

	@FXML
	ChoiceBox<String> yearChoiceBox;
	
	@FXML
	Button validationButton;
	@FXML
	Button cancelButton;
	
	PersonDao personDao = new PersonDao();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadChoiceBoxes();	
	}
	
	@FXML
	void validation() throws IOException 
	{
		LocalDate birthDate = LocalDate.parse(yearChoiceBox.getValue() + "-" + monthChoiceBox.getValue()+"-" + dayChoiceBox.getValue());
		
		personDao.addPerson(lastNameTF.getText(), firstNameTF.getText(), surNameTF.getText(), "+33"+ phoneNumberTF.getText(), addressTF.getText(), emailTF.getText(), birthDate);
		App.setRoot("/isen/view/MainPage");
	}
	
	@FXML
	void cancel() throws IOException 
	{
		App.setRoot("/isen/view/MainPage");
	}
	
	private void loadChoiceBoxes() 
	{
		// initialise les valeurs possibles des choiceBoxes
		
		dayChoices.removeAll(dayChoices);
		yearChoices.removeAll(yearChoices);
		String value;
		
		for(Integer i = 0;i<32;i++)
		{
			if(i<10)
			{
				value = "0" + i.toString(); 
			}
			else {
				value = i.toString() ;
			}
			dayChoices.add(value);
		}
		dayChoiceBox.setItems(dayChoices);
		
		monthChoiceBox.setItems(monthChoices);
		
		for(Integer j = 1900; j<2021;j++) 
		{
			yearChoices.add(j.toString());
		}
		yearChoiceBox.setItems(yearChoices);
	}
}
