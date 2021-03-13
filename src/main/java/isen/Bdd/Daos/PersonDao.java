package isen.Bdd.Daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;
import isen.Bdd.Entities.Person;


public class PersonDao {
	
	static DataSource dataSource = DataSourceFactory.getDataSource();
	
	public static List<Person> listPerson() {
		List<Person> listOfPerson= new ArrayList<>();
		try(Connection connection = dataSource.getConnection()){
			try (Statement statement = connection.createStatement()){
				try (ResultSet resultSet = statement.executeQuery("SELECT * FROM person")){
					while(resultSet.next()) {
						Person person = new Person(resultSet.getInt("idperson"), resultSet.getString("lastname"),
								resultSet.getString("firstname"),
								resultSet.getString("nickname"),
								resultSet.getString("phone_number"),
								resultSet.getString("address"),
								resultSet.getString("email_address"),
								resultSet.getDate("birth_date").toLocalDate());
						listOfPerson.add(person);
						}
					}
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		return listOfPerson;
	}
	
	public Person addPerson(Person addedPerson) {
		try(Connection connection = dataSource.getConnection()){
			String sqlQuery = "INSERT INTO person(lastname, firstname, nickname, phone_number, address, email_address, birth_date)"+" VALUES(?,?,?,?,?,?,?)";
			try (PreparedStatement statement = connection.prepareStatement(sqlQuery,Statement.RETURN_GENERATED_KEYS)){
				statement.setString(1, addedPerson.getLastname());
				statement.setString(2, addedPerson.getFirstname());
				statement.setString(3, addedPerson.getNickname());
				statement.setString(4, addedPerson.getPhone_number());
				statement.setString(5, addedPerson.getAdress());
				statement.setString(6, addedPerson.getEmail_adress());
				statement.setDate(7, Date.valueOf(addedPerson.getBirth_date()));
				statement.executeUpdate();
				ResultSet ids=statement.getGeneratedKeys();
				if(ids.next()) {
					return new Person(ids.getInt(1),addedPerson.getLastname(),addedPerson.getFirstname(),addedPerson.getNickname(),addedPerson.getPhone_number(),addedPerson.getAdress(),addedPerson.getEmail_adress(),addedPerson.getBirth_date());
				}
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void deletePerson(Person deletedPerson) {
		try(Connection connection=dataSource.getConnection()){
			try(PreparedStatement statement=connection.prepareStatement("DELETE FROM person WHERE phone_number=?")){
				statement.setString(1, deletedPerson.getPhone_number());
				statement.executeUpdate();
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
	
}
