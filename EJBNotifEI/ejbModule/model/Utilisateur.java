package model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="Utilisateur")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="Utilisateur_Type")
public abstract class Utilisateur implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="Id")
	private Integer id;

	@Column(name="Login")
	private String login;

	@Column(name="Password")
	private String password;
	
	@Enumerated(EnumType.STRING)
	@Column
	private Role role;
	
	@OneToMany(mappedBy="utilisateur", fetch=FetchType.EAGER)
	private List<Declaration> declarations;

	public Utilisateur() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Declaration> getDeclarations() {
		return declarations;
	}

	public void setDeclarations(List<Declaration> declarations) {
		this.declarations = declarations;
	}

}