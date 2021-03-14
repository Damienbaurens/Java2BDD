package isen.Bdd.Daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
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
						Person person = new Person(resultSet.getString("lastname"),
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
	
	public Person addPerson(String lastName,String firstName, String surName, String phoneNumber, String address, String email, LocalDate birthDate) {
		try(Connection connection = dataSource.getConnection()){
			String sqlQuery = "INSERT INTO person(lastname, firstname, nickname, phone_number, address, email_address, birth_date)"+" VALUES(?,?,?,?,?,?,?)";
			try (PreparedStatement statement = connection.prepareStatement(sqlQuery,Statement.RETURN_GENERATED_KEYS)){
				statement.setString(1, lastName);
				statement.setString(2, firstName);
				statement.setString(3, surName);
				statement.setString(4, phoneNumber);
				statement.setString(5, address);
				statement.setString(6, email);
				statement.setDate(7, Date.valueOf(birthDate));
				statement.executeUpdate();
				ResultSet ids=statement.getGeneratedKeys();
				if(ids.next()) {
					return new Person(lastName,firstName,surName,phoneNumber,address,email,birthDate);
				}
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void editPerson(Integer idPerson,String lastName, String firstName, String surName, String phoneNumber, String address, String email, LocalDate birthDate)
	{
		try(Connection connection=dataSource.getConnection())
		{
			String sqlQuery = "UPDATE person set lastname = ?, firstname =?, nickname=?, phone_number=?, address=?, email_address=?, birth_date= ? WHERE idperson=?";
			try(PreparedStatement statement = connection.prepareStatement(sqlQuery))
			{
				statement.setString(1, lastName);
				statement.setString(2,firstName);
				statement.setString(3,surName);
				statement.setString(4,phoneNumber);
				statement.setString(5,address);
				statement.setString(6,email);
				statement.setDate(7,Date.valueOf(birthDate));
				statement.setInt(8,idPerson);
				statement.executeUpdate();
			}
			
		} catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	
	/*public Person getbyNickname(String nicknamePerson) {
		try(Connection connection=dataSource.getConnection()){
			try(PreparedStatement statement=connection.prepareStatement(")e)
		}
	}*/
	
}
