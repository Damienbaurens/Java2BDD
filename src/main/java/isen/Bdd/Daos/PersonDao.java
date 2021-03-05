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
	
	DataSource dataSource = DataSourceFactory.getDataSource();
	
	public List<Person> listPerson() {
		List<Person> listofperson= new ArrayList<>();
		try(Connection connection = dataSource.getConnection()){
			try (Statement statement = connection.createStatement()){
				String sqlQuery="SELECT * FROM person";
				try (ResultSet resultSet = statement.executeQuery(sqlQuery);){
					while( resultSet.next()) {
						Person person = new Person(resultSet.getInt("idperson"), resultSet.getString("lastname"), resultSet.getString("firstname"), resultSet.getString("nickname"), resultSet.getString("phone_number"), resultSet.getString("adress"), resultSet.getString("email_adress"), resultSet.getDate("birth_date").toLocalDate());
						listofperson.add(person);
						}
					}
				statement.close();
				}
			connection.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		return listofperson;
	}
	
	public void addPerson(Person addedPerson) 
	{
		try(Connection connection = dataSource.getConnection())
		{
			String sqlQuery = "INSERT INTO person(lastname, firstname, nickname, phone_number, address, email_adress, birth_date) VALUES(?,?,?,?,?,?,?)";
			try (PreparedStatement statement = connection.prepareStatement(sqlQuery))
			{
				statement.setString(1, addedPerson.getLastname());
				statement.setString(2, addedPerson.getFirstname());
				statement.setString(3, addedPerson.getNickname());
				statement.setString(4, addedPerson.getPhone_number());
				statement.setString(5, addedPerson.getAdress());
				statement.setString(6, addedPerson.getEmail_adress());
				statement.setString(7, addedPerson.getBirth_date());
				statement.close();
			}
			connection.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
}
