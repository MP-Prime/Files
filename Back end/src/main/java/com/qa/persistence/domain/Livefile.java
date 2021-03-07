package com.qa.persistence.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;


@Entity
@Generated
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Livefile {
	// variables
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Auto incremented
	private Long record_id;
	
	@Column
	private String title; 
	@Column
	private String content; 
	@Column
	private String label; 
	
	
	// Constructor to generate record_id
	public Livefile(String title, String content, String label) {
		super();
		this.title = title;
		this.content = content;
		this.label = label;
	}
	
}
