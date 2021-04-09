package com.example.Capstone.entities;

import javax.persistence.*;

//import com.example.Capstone.entities.User;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    private int id;
    @Column(name = "role")
    private String role;
    
//    @ManyToOne(fetch =FetchType.LAZY)
//	@JoinColumn(name="user_id", nullable=true)
//	//referencedColumnName = "id"
//	private User user;
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
    
}