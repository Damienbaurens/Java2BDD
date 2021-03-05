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
}
