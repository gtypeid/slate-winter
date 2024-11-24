package com.kosta.rp;
import com.kosta.rp.resource.*;

import java.awt.Point;
import java.util.ArrayList;

public class Core {
	
	private Property property;
	private Scan scan;
	private ArrayList<Entity> entityStore = new ArrayList<>();
	private String drawLineStore;


	private static Core core;
	public static Core instance() {
		if( core == null )
			core = new Core();
		return core;
	}

	private Core() { 
		property = new Property();
		property.width = 100;
		property.height = 44;
		property.pivot = '*';
		property.blank = ' ';
		property.color = false;
		property.colorProperty = "\u001B[30m";

		scan = new Scan(this);
	};

	public void run() {
		objets();
		draw();
		scan.run();
	}

	public Property getProperty(){
		return property;
	}

	public ArrayList<Entity> getEntityStore(){
		return entityStore;
	}

	public void draw() {
		int pixelWidth = property.width;
		int pixelHeight = property.height;

		for(int h = 0; h < pixelHeight; h++) {
			StringBuilder stringBuilder = new StringBuilder();
			drawLineStore = "";
			for(int w = 0; w < pixelWidth; w++) {
				inPixel(w, h, stringBuilder);
			}
			if(property.color) {
				System.out.print(property.colorProperty);
			}

			System.out.println(drawLineStore);
		}

	}

	private void inPixel(int w, int h, StringBuilder stringBuilder) {
		boolean isAnyFound = false;
		char pivot = property.pivot;
		char blank = property.blank;

        for (Entity entity : entityStore) {
            Point point = new Point(w, h);
            if (entity.isInRect(point)) {
                char c = entity.getPixelData(point);
                if (entity.getPosition().equals(point)) c = pivot;

                drawLineStore = stringBuilder.append(c).toString();
				isAnyFound = true;
            }
        }
		if(!isAnyFound){
			drawLineStore = stringBuilder.append(blank).toString();
		}
	}

	private void newEntity(Point point, Resource resource) {
		Entity entity = new Entity( point, resource );
		entityStore.add(entity);
	}

	private void objets() {
		// cloud, rain
		newEntity(new Point(15, 0), new Cloud());
		newEntity(new Point(15, 3), new Rain());
		newEntity(new Point(50, 7), new Cloud2());
		newEntity(new Point(80, 7), new Cloud2());

		// tree
		for (int i = 0; i < 15; ++i) {
			int x = 5 + (i * 10);
			newEntity(new Point(x, 20), new Tree());
		}

		// cat
		newEntity(new Point(10, 24), new Cat());
		newEntity(new Point(25, 27), new Cat());
		newEntity(new Point(45, 26), new Cat());
		newEntity(new Point(85, 31), new Cat());

		// house
		newEntity(new Point(10, 38), new House());
		newEntity(new Point(30, 38), new House());

		// tree
		for (int i = 0; i < 15; ++i) {
			int x = 5 + (i * 10);
			newEntity(new Point(x, 46), new Tree());
		}
	}

}
