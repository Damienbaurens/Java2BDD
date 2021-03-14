package view;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import isen.Bdd.App;
import isen.Bdd.Daos.PersonDao;
import isen.Bdd.Entities.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class EditContactController {
private Person personSelected;

	ObservableList<String> dayChoices = FXCollections.observableArrayList();
	ObservableList<String> monthChoices = FXCollections.observableArrayList("01","02","03","04","05","06","07","08","09","10","11","12");
	ObservableList<String> yearChoices = FXCollections.observableArrayList();
	List<Person> personList=PersonDao.listPerson();
	PersonDao personDao = new PersonDao(); 
	
	@FXML
    private TextField editLastNameContact;

    @FXML
    private TextField editFirstNameContact;

    @FXML
    private TextField editNickNameContact;

    @FXML
    private TextField editPhoneNumberContact;

    @FXML
    private TextField editAdressContact;

    @FXML
    private TextField editMailAdressContact;

    @FXML
	ChoiceBox<String> dayChoiceBox;
	
	@FXML
	ChoiceBox<String> monthChoiceBox;

	@FXML
	ChoiceBox<String> yearChoiceBox;

    @FXML
    void backFunction(ActionEvent event) throws IOException {
    	App.setRoot("/isen/view/MainPage");
    }

    @FXML
    void validateEditContact(ActionEvent event) throws IOException {
    	LocalDate birthDate = LocalDate.parse(yearChoiceBox.getValue() + "-" + monthChoiceBox.getValue()+"-" + dayChoiceBox.getValue());
    	personDao.editPerson(personSelected.getIdperson(),editLastNameContact.getText(), editFirstNameContact.getText(), editNickNameContact.getText(), editPhoneNumberContact.getText(),editAdressContact.getText(), editMailAdressContact.getText(), birthDate);
    	App.setRoot("/isen/view/MainPage");
    }
    
    public void displayInformation() {
    	editLastNameContact.setText(personSelected.getLastname());
    	editFirstNameContact.setText(personSelected.getFirstname());
    	editNickNameContact.setText(personSelected.getNickname());
    	editPhoneNumberContact.setText(personSelected.getPhone_number());
    	editAdressContact.setText(personSelected.getAdress());
    	editMailAdressContact.setText(personSelected.getEmail_adress());
    	
    	String birthDate = personSelected.getBirth_date().toString();
    	String year = birthDate.substring(0,birthDate.indexOf("-"));
    	String month = birthDate.substring(birthDate.indexOf("-")+1,birthDate.lastIndexOf("-"));
    	String day = birthDate.substring(birthDate.lastIndexOf("-")+1,birthDate.length());
    	
    	dayChoiceBox.setValue(day);
    	monthChoiceBox.setValue(month);
    	yearChoiceBox.setValue(year);
    }
    
    public void initialize() {
    	for(Person person : personList) {
    		if(DetailContactController.idContactSelected==personList.indexOf(person)) {
    			personSelected=person;
    		}
    	}
    	loadChoiceBoxes();
    	displayInformation();
	}
    
    private void loadChoiceBoxes() 
	{
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
