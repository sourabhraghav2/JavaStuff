package com;

public class Singoleton {

	public static void main(String[] args) {
		System.out.println(SingleObject.getInstance().getPlace()); 
		System.out.println(SingleObject.getInstance().getPlace());
		System.out.println(SingleObject.getInstance().getPlace());
		
	}
}

class SingleObject {

	static BookKeeper keeper = new BookKeeper();
	public static BookKeeper  getInstance(){
		return keeper;
	}
}

class BookKeeper {

	public BookKeeper(){
		System.out.println("BookKeeper Constructor");
		place="INDIA";
	}
	private String place;

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}
}