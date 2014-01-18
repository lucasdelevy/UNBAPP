package com.example.hellogridview;

public class Contact {
	private String title;
	private String numbers;
	private int numOfNumbers;
	private String separator= " \\| ";
	
	public Contact(String title, String numbers){
		super();
		this.title = title;
		this.numbers = numbers;
		this.numOfNumbers = this.calculateNumOfNumbers(numbers);
	}

	public int calculateNumOfNumbers(String numbers) {
		return numbers.split(this.separator).length;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getNumbers() {
		return numbers;
	}
	
	public String[] getNumbersArray() {
		return numbers.split(this.separator);
	}

	public int getNumOfNumbers() {
		return numOfNumbers;
	}
}
