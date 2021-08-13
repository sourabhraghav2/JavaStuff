package com.designpattern;

public class Adapter {

	public static void main(String[] args) {
		PenImp p = new PenImp();
		System.out.println(p.write());
		;
	}
}

interface PenInterface {
	public String write();

}

class PenImp implements PenInterface {

	@Override
	public String write() {

		return new PilotPen().write();
	}

}

class PilotPen {
	public String write() {
		return "Smoot writing with pilotpen ";
	}
}
