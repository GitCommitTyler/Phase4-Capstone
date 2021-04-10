package com.example.Capstone.entities;


import lombok.Data;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Album {

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

    @ManyToOne
    private Genre genre;

    private String artist;

    private Date releaseDate;

    private BigDecimal price;

    public Album() {}
    
    public Album(String Name, Genre genre, String artist, Date releaseDate, BigDecimal price) {
    	this.Name = Name;
    	this.genre = genre;
    	this.artist = artist;
    	this.releaseDate = releaseDate;
    	this.price = price;
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

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}


}
