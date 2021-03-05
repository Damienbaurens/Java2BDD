package isen.Bdd.Entities;

public class Person {
	private Integer idperson;
	private String lastname;
	private String firstname;
	private String nickname;
	private String phone_number;
	private String adress;
	private String email_adress;
	private String birth_date;
	
	
	public Person() {
	}

	public Person(Integer idperson, String lastname, String firstname, String nickname, String phone_number, String adress, String email_adress, String birth_date) {
		this.idperson = idperson;
		this.lastname = lastname;
		this.firstname = firstname;
		this.nickname = nickname;
		this.phone_number = phone_number;
		this.adress = adress;
		this.email_adress = email_adress;
		this.birth_date = birth_date;
	}

	public Integer getIdperson() {
		return idperson;
	}

	public void setIdperson(Integer idperson) {
		this.idperson = idperson;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getEmail_adress() {
		return email_adress;
	}

	public void setEmail_adress(String email_adress) {
		this.email_adress = email_adress;
	}

	public String getBirth_date() {
		return birth_date;
	}

	public void setBirth_date(String birth_date) {
		this.birth_date = birth_date;
	}

}
