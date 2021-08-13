package com.designpattern;

public class Builder {
	public static void main(String[] args) {
		Pen o=new Pen("red",19);
		PenBuilder p=new PenBuilder().setColor("Red").setPrice(10);
		p.build();
	}

}

class PenBuilder{
	
	private String color;
	private int price;
	public String getColor() {
		return color;
	}
	public PenBuilder setColor(String color) {
		this.color = color;
		return this;
	}
	public int getPrice() {
		return price;
	}
	public PenBuilder setPrice(int price) {
		this.price = price;
		return this;
	}
	public Pen build(){
		
		return new Pen(this.getColor(),this.getPrice());
	}
	
}
class Pen {
	
	private String color;
	private int price;
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public Pen(String color, int price) {
		super();
		this.color = color;
		this.price = price;
	}
	
}