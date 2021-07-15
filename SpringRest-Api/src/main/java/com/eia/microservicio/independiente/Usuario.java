package com.eia.microservicio.independiente;

import org.springframework.stereotype.Component;

@Component
public class Usuario {
	
	private	String userName;
	private	String nickName;
	private	String password;
	
	public Usuario() {super();}
	

	public Usuario(String userName, String nickName, String password) {
		super();
		this.userName = userName;
		this.password = password;
		this.nickName = nickName;
	}



	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
	
}
