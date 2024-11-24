package com.kosta.rp;

import com.kosta.rp.resource.Resource;

import java.awt.*;

public class Entity {

	protected Point position;
	protected Resource resource;
	
	public Entity(Point point, Resource resource) {
		position = point;
		this.resource = resource;
	}

	public boolean isInRect(Point screenPoint) {
		
		Point dsp = getDrawStartPoint();
		Point dep = getDrawEndPoint();

		return ( (dsp.x <= screenPoint.x ) &&
				(screenPoint.x <= dep.x ) &&
				(dsp.y <= screenPoint.y ) &&
				(screenPoint.y <= dep.y ) );
	}
	
	public char getPixelData(Point screenPoint){
		Point resourcePivot = resource.getPivot();

		int x = Math.abs( (position.x - screenPoint.x) - (resourcePivot.x - 1) );
		int y = Math.abs( (position.y - screenPoint.y) - (resourcePivot.y - 1) );
		return resource.getPixelData(x, y);
	}

	public Point getDrawStartPoint(){
		Point resourcePivot = resource.getPivot();

		int drawStartX = position.x - (resourcePivot.x - 1);
		int drawStartY = position.y - (resourcePivot.y - 1);
		return new Point(drawStartX, drawStartY);
	}

	public Point getDrawEndPoint() {
		Point startPoint = getDrawStartPoint();
		Point resourceSize = resource.getResourceSize();

		int drawEndX = startPoint.x + (resourceSize.x - 1);
		int drawEndY = startPoint.y + (resourceSize.y - 1);
		return new Point(drawEndX, drawEndY);
	}

	public  Point getPosition(){
		return position;
	}

	public void left(){
		position.x += 5;
	}
	
	public void right(){
		position.x -= 5;
	}	
	
}
