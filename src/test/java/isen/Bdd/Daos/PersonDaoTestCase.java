package isen.Bdd.Daos;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import isen.Bdd.Daos.DataSourceFactory;
import isen.Bdd.Entities.Person;
import isen.Bdd.Daos.PersonDao;

public class PersonDaoTestCase {
	private PersonDao personDao = new PersonDao();
	@Before
	public void initDb() throws Exception {
		Connection connection = DataSourceFactory.getDataSource().getConnection();
		Statement stmt = connection.createStatement();
		stmt.executeUpdate(
				"CREATE TABLE IF NOT EXISTS person (\r\n"
						+ "  idperson INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\r\n" + "  lastname VARCHAR(45) NOT NULL,\r\n"
						+ "  firstname VARCHAR(45) NOT NULL,\r\n" + "  nickname VARCHAR(45) NOT NULL,\r\n" + "  phone_number VARCHAR(15) NULL,\r\n"
						+ "  address VARCHAR(200) NULL,\r\n" + "  email_address VARCHAR(150) NULL,\r\n"
						+ "   birth_date DATE NULL);");
		stmt.executeUpdate("DELETE FROM person");
		stmt.executeUpdate("INSERT INTO person(idperson, lastname, firstname, nickname, phone_number, adress, email_adress, birth_date) "
				+ "VALUES (1, 'CHOPINEAU', 'Maxence','Chopi','0011223344','Lille', 'maxence.chopineau@gmail.com', '1999-04-17 18:00.000')");
		stmt.executeUpdate("INSERT INTO person(idperson, lastname, firstname, nickname, phone_number, adress, email_adress, birth_date) "
				+ "VALUES (2, 'LEFEU', 'Joseph','LE POMPIER','5566778899','Paumer City', 'joseph.aeteintlefeu@gmail.com', '1994-11-03 09:08.076')");
		stmt.close();
		connection.close();
	}
	@Test
	 public void shouldListPerson() {
		 //WHEN
		 List<Person> personnes = personDao.listPerson();
		 //THEN
		 assertThat(personnes).hasSize(2);
		 assertThat(personnes).extracting("idperson","firstname", "Lastname","adress").containsOnly(tuple(1, "Maxence", "CHOPINEAU", "Lille"), tuple(2, "Joseph", "LEFEU", "Paumer city"));
	 }
	
	
}
