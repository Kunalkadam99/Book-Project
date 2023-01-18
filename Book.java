package com.example.bookProject.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Book {
	
	private int id;
	private String name;
	private String cost;
	private String author;
	
}
