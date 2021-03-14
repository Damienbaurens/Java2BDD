package view;

import java.io.IOException;
import java.util.List;
import isen.Bdd.App;
import isen.Bdd.Daos.PersonDao;
import isen.Bdd.Entities.Person;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class DetailContactController {
	
	public static int idContactSelected;
	
	private Person personSelected;
	
	List<Person> personList=PersonDao.listPerson();
	
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
    
    @FXML
    private Text contactBirthDate;

    
    public void displayInformation() {
    	contactName.setText(personSelected.getLastname());
    	contactSurname.setText(personSelected.getFirstname());
    	contactNickName.setText(personSelected.getNickname());
    	contactPhoneNumber.setText(personSelected.getPhone_number());
    	contactAdress.setText(personSelected.getAdress());
    	contactMailAdress.setText(personSelected.getEmail_adress());
    	contactBirthDate.setText(DateUtil.format(personSelected.getBirth_date()));
    }
    
    public void initialize() {
    	for(Person person : personList) {
    		if(idContactSelected==personList.indexOf(person)) {
    			personSelected=person;
    		}
    	}
    	displayInformation();
	}
    
    @FXML
    void deleteContact(ActionEvent event) throws IOException {
		
		PersonDao.deletePerson(personSelected.getIdperson());
		App.setRoot("/isen/view/MainPage");
    }

    @FXML
    void backFunction(ActionEvent event) throws IOException {
    	App.setRoot("/isen/view/MainPage");
    }
    
    @FXML
    void goToEditView(ActionEvent event) throws IOException {
    	App.setRoot("/isen/view/EditContact");
    }

}
