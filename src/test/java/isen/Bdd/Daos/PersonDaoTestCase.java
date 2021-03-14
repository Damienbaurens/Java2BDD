package isen.Bdd.Daos;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
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
		stmt.executeUpdate("INSERT INTO person(idperson, lastname, firstname, nickname, phone_number, address, email_address, birth_date) "
				+ "VALUES (1, 'CHOPINEAU', 'Maxence','Chopi','0011223344','Lille', 'maxence.chopineau@gmail.com', '1999-04-17 18:00:00.000')");
		stmt.executeUpdate("INSERT INTO person(idperson, lastname, firstname, nickname, phone_number, address, email_address, birth_date) "
				+ "VALUES (2, 'LEFEU', 'Joseph','LE POMPIER','5566778899','Paumer City', 'joseph.aeteintlefeu@gmail.com', '1994-11-03 09:08:00.000')");
		stmt.close();
		connection.close();
	}
	@Test
	 public void shouldListPerson() throws Exception{
		 //WHEN
		 List<Person> personnes = personDao.listPerson();
		 //THEN
		 assertThat(personnes).hasSize(2).doesNotContainNull();
		 assertThat(personnes).extracting("idperson","lastname", "firstname","nickname","phone_number","adress","email_adress","birth_date").containsOnly(
				 tuple(1, "CHOPINEAU", "Maxence","Chopi","0011223344","Lille", "maxence.chopineau@gmail.com", LocalDate.of(1999, 4, 17)),
				 tuple(2, "LEFEU", "Joseph","LE POMPIER","5566778899","Paumer City", "joseph.aeteintlefeu@gmail.com", LocalDate.of(1994, 11, 3)));
	 }
	
	@Test
	public void shouldAddPerson() throws Exception {
		//WHEN
		personDao.addPerson("DUPONT","Jean","Dudu","547675466","adresse de jean","jean.dupont@gmail.com",LocalDate.of(1971,6,29));
		//THEN
		Connection connection=DataSourceFactory.getDataSource().getConnection();
		Statement statement=connection.createStatement();
		ResultSet resultSet=statement.executeQuery("SELECT * FROM person WHERE phone_number=547675466");
		assertThat(resultSet.next()).isTrue();
		assertThat(resultSet.getInt("idperson")).isNotNull();
		assertThat(resultSet.getString("lastname")).isEqualTo("DUPONT");
		assertThat(resultSet.getString("firstname")).isEqualTo("Jean");
		assertThat(resultSet.getString("nickname")).isEqualTo("Dudu");	
		assertThat(resultSet.getString("phone_number")).isEqualTo("547675466");
		assertThat(resultSet.getString("address")).isEqualTo("adresse de jean");
		assertThat(resultSet.getString("email_address")).isEqualTo("jean.dupont@gmail.com");
		assertThat(resultSet.getDate("birth_date")).isEqualTo(Date.valueOf(LocalDate.of(1971,6,29)));
		assertThat(resultSet.next()).isFalse();
	 	resultSet.close(); 
	    statement.close();
		connection.close();
		}
	
	@Test
	public void shouldDeletePerson() throws Exception{
		//GIVEN
		Person personToAddedAndDeleted=new Person("MARTIN","Paul","Popo","9549696","adresse de paul","paul.martin@gmail.com",LocalDate.of(1976,12,5));
		//WHEN
		personDao.addPerson("MARTIN","Paul","Popo","9549696","adresse de paul","paul.martin@gmail.com",LocalDate.of(1976,12,5));
		personDao.deletePerson(personToAddedAndDeleted);
		//THEN
		Connection connection=DataSourceFactory.getDataSource().getConnection();
		Statement statement=connection.createStatement();
		ResultSet resultSet=statement.executeQuery("SELECT * FROM person WHERE phone_number=9549696");
		assertThat(resultSet.next()).isFalse();
		resultSet.close(); 
	    statement.close();
		connection.close();
	}
	
}
