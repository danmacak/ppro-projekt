package cz.uhk.restaurace.model;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

import javax.persistence.*;

@Entity
public class Role implements GrantedAuthority{

	private static final long serialVersionUID = 42L;

	@Id
	@Enumerated(EnumType.STRING)
	private RoleType roleType;
	@ManyToMany(mappedBy = "roles", cascade={CascadeType.ALL})
	private Collection<User> users;
	public enum RoleType{ROLE_ADMIN, ROLE_EMPLOYEE, ROLE_USER}

	public Role(){}

	public Role(RoleType roleType){
		this.roleType = roleType;
	}

	public RoleType getRoleType() {
		return roleType;
	}

	public void setRoleType(RoleType roleType) {
		this.roleType = roleType;
	}

	public Collection<User> getUsers() {
		return users;
	}

	public void setUsers(Collection<User> users) {
		this.users = users;
	}

	@Override
	public String getAuthority() {
		return roleType.name();
	}
}
