package com.example.Capstone.entities;


import lombok.Data;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import java.util.Date;

@Entity
@Data
public class Genre {
	@Id
	@GeneratedValue(
	    strategy= GenerationType.AUTO,
	    generator="native"
	)
	@GenericGenerator(
	    name = "native",
	    strategy = "native"
	)
    private Long id;

    private String Name;
    
    public String imageURL;

    public Genre(){}
    
    public Genre(Long id, String Name, String url){
    	this.id = id;
    	this.Name = Name;
    	this.imageURL = url;
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
	
	public String getImageUrl() {
		return this.imageURL;
	}

	public void setImageURL(String url) {
		this.imageURL = url;
	}

}
