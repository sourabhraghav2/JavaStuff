package com.designpattern;

public class Factory {

	public static OS getInstance(String name) {
		if ("ANDROID".equalsIgnoreCase(name)) {
			return new Android();
		} else if ("IPHONE".equalsIgnoreCase(name)) {
			return new Mac();
		} else if ("NOKIA".equalsIgnoreCase(name)) {
			return new MS();
		}
		return null;
	}

	public static void main(String[] args) {
		OS o = Factory.getInstance("ANDROID");
		System.out.println(o.getSystemName());
	}
}

interface OS {

	public String getSystemName();
}

class Android implements OS {

	@Override
	public String getSystemName() {

		return "Android";
	}

}

class MS implements OS {

	@Override
	public String getSystemName() {

		return "MS";
	}

}

class Mac implements OS {

	@Override
	public String getSystemName() {

		return "Mac";
	}

}