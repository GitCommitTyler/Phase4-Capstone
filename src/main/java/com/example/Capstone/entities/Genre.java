package com.example.Capstone.entities;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Genre {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String Name;

    public Genre(){}
    
    public Genre(Long id, String Name){
    	this.id = id;
    	this.Name = Name;
    }
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

}
