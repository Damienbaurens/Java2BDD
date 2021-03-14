package isen.Bdd.Daos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
						person.setIdperson(resultSet.getInt("idperson"));
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
	
	public Person addPerson(String lastName,String firstName, String surName, String phoneNumber, String address, String email, LocalDate birthDate) 
	//Ajoute une personne dans la database
	{
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
		//fontion d'édition qui change les informations d'une personne dans la database
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
	
	public static void deletePerson(Integer idPerson) {
		try(Connection connection=dataSource.getConnection()){
			try(PreparedStatement statement=connection.prepareStatement("DELETE FROM person WHERE idperson=?")){
				statement.setInt(1, idPerson);
				statement.executeUpdate();
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public void exportDataBase() throws IOException 
	{
		File vcardSaveFile = new File("../DBSave.vcf");
		FileOutputStream fop = new FileOutputStream(vcardSaveFile);
		if(vcardSaveFile.exists())
		{
			String save = "BEGIN:VCARD\n" + "VERSION:4.0\n" ;
			List<Person> Personlist = listPerson();
			for(Person person : Personlist)
			{
				save += "N:"+person.getLastname()+";"+person.getFirstname()+";;;\n"
						+"FN:"+person.getLastname()+person.getFirstname()+"\n"
						+"SN:"+person.getNickname()+"\n"
						+"TEL;VALUE=uri:tel:+33"+ person.getPhone_number()+"\n"
						+"ADR:"+ person.getAdress()+"\n"
						+"EMAIL:"+person.getEmail_adress()+"\n"
						+"BD:"+person.getBirth_date().toString()+"\n";
			}
			save += "END:VCARD";
			fop.write(save.getBytes());
			fop.flush();
			fop.close();
		}
		else {
			System.out.println("Ce fichier n'existe pas");
		}			
	}
	
}
