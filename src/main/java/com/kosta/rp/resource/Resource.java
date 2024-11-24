package com.kosta.rp.resource;

import java.awt.*;
import java.util.ArrayList;

public abstract class Resource {

	protected ArrayList<String> datas = new ArrayList<String>();
	protected Point pivot;
	
	public Resource() {
		definedResource();
		
		int size = datas.get(0).length();
		for(String it : datas) {
			if(size != it.length()) {
				System.out.println("ERROR");
				System.out.println("0 LINE SIZE : " + size);
				System.out.println(datas.get(0));
				System.out.println(" LINE SIZE : " + it.length());
				System.out.println(it);
			}
		}
	}
	
	public void inData(String s) {
		datas.add(s);
	}

	public void setPivot(Point point) {
		pivot = point;
	}

	public Point getPivot() {
		return pivot;
	}
	
	public Point getResourceSize() {
	
		int width = datas.get(0).length();
		int height = datas.size();
		
		return new Point(width, height);
	}
	
	public char getPixelData(int x, int y) {
		String line = datas.get(y);
		return line.charAt(x);
	}
	
	abstract protected void definedResource();


}
