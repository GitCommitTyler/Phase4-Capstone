package com.example.Capstone.entities;


import lombok.Data;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
public class Music {
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

	private String name;

	@ManyToOne
    private Genre genre;
	
    @ManyToOne
    private Album album;

    private int trackNumber;

    private BigDecimal price;
    
    public Music() {}
    
    public Music(Long ID) {
    	this.id = ID;
    }
    
    public Music(String name, Genre genre, Album album, int trackNumber, BigDecimal price)
    {
    	this.name = name;
    	this.genre = genre;
    	this.album = album;
    	this.trackNumber = trackNumber;
    	this.price = price;
    }
    
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	public int getTrackNumber() {
		return trackNumber;
	}

	public void setTrackNumber(int trackNumber) {
		this.trackNumber = trackNumber;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}


}
