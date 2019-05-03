package pakete;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

import java.util.ArrayList;

public class SpielStarten extends PApplet {
	private Charakter held;
	private Platform hindernis;
	private Spike spike;
	private Editor editor;
	private SpielWelt welt;
	private Bullet bullet;
	private Floor floor;
	private PVector veca;
	private PVector vecb;
	private PVector vecc;
	private PVector vecp;
	private boolean isColiding;
	private ArrayList<Spike> spikeListe;
	private ArrayList<Bullet> bulletList;
	private ArrayList<Bullet> bulletIDList = new ArrayList<>();
	private ArrayList<Floor> floorList;
	private static final float VELOCITY = 5;
	private boolean aKey;
	private boolean dKey;
	private boolean sKey;
	private boolean wKey;
	private float gravity = 8F;
	private boolean shoot;
	private int testValue = 0;
	private boolean test;



	public boolean isShoot() {
		return shoot;
	}

	public void setShoot(boolean shoot) {
		this.shoot = shoot;
	}

	public float getGravity() {
		return gravity;
	}

	public void setGravity(float gravity) {
		this.gravity = gravity;
	}

	public ArrayList<Spike> getSpikeListe() {
		return spikeListe;
	}

	public void setSpikeListe(ArrayList<Spike> spikeListe) {
		this.spikeListe = spikeListe;
	}

	public static void main(String[] args) {
		System.out.println("hier ist die main klasse");
		PApplet.main(SpielStarten.class, args);
		System.out.println("hier ist die main klasse zuende");

	}

	public SpielStarten() {
		System.out.println("hier ist der construktor");
	}

	public void ersschaffeObjekte() {
		erschaffeHeld();
		// erschaffeHindernisse();
		erschaffeSpike();
		erschaffeWelt();
		erschaffeFloor();


	}

	public void erschaffeHeld() {
		System.out.println("hier ist erschaffe held");
		PImage heldImage = loadImage("resources/90tiny.png");
		held = new Charakter(heldImage, 1, 1);

	}

	public void respawn(PImage p) {
		held.setImg(p);
	}

	public void sterben(PImage p) {
		p = loadImage("resources/spike.png");
		held.setImg(p);
	}

	public void erschaffeWelt() {
		welt = new SpielWelt();
	}

	public void spielStarten() {
		System.out.println("Spielstarten erreicht");

		held.setPositionX(100);
	}

	public void erschaffeFloor(){
		//PImage p = new PImage();
		//floor = new Floor(p = loadImage("resources/floor.png"),1,1);
		//floorList.add(floor);
	}

	public Bullet erschaffeBullet() {
		PImage bulletImage = loadImage("resources/bullet.png");
		bullet = new Bullet(bulletImage,held.getPositionX()+50,held.getPositionY()+50,2,20,this);
		System.out.println("test1234");
		return bullet;
	}
	@Override
	public void setup() {
		super.setup();
		System.out.println("hier ist setup");
		floorList = new ArrayList<>();
		ersschaffeObjekte();
		spike.erschaffeSpike(this, spikeListe);
		bulletList = new ArrayList<>();
		editor = new Editor();


		System.out.println(held);
		held.getNonTransparentPixel();
		background(0);
		frameRate(30);
		loop();


	}
	@Override
	public void settings() {
		super.settings();

		size(800, 800);
		System.out.println("hier wird settings aufgerufen");

	}
	@Override
	public void draw() {
		background(0);
		image(held.getImg(), held.getPositionX(), held.getPositionY());
		//gravity();
		move();
		if(!held.isCooliding()) {
			gravitation();
		}
		//held.setCooliding(false);
		kollisionFloor();
		held.springen(this);
		text("x" + held.getPositionX(),20,20);
		text("y" + held.getPositionY(),20,50);


		for(Floor f : floorList){
			f.setImage(loadImage("resources/floor.png"));
			image(f.getImage(),f.getPosiX(),f.getPosiY());
		}

			for (Bullet b : bulletList) {

				image(b.getImg(), b.getPositionX(), b.getPositionY());
				b.bulletMove(b);
				System.out.println(bulletList.size());
				if(b.getPositionX() > 300){

					bulletIDList.add(b);

				}
			}
		for(Bullet b : bulletIDList){
			bulletList.remove(b);
		}
		bulletIDList = new ArrayList<>();
		for (Spike s : spikeListe) {
			triangle(s.getTriangleX1(), s.getTriangleY1(), s.getTriangleX2(), s.getTriangleY2(), s.getTriangleX3(),
					s.getTriangleY3());
		}
		for (Spike c : getSpikeListe()) {
			int kollisionDistance = 90;

			if (held.getPositionX() - c.getTriangleX1() < kollisionDistance
					&& (held.getPositionX() - c.getTriangleX1() > -kollisionDistance
					&& held.getPositionY() - c.getTriangleY1() < kollisionDistance
					&& held.getPositionY() - c.getTriangleY1() > -kollisionDistance)
					|| (held.getPositionX() - c.getTriangleX2() < kollisionDistance
					&& held.getPositionX() - c.getTriangleX2() > -kollisionDistance
					&& held.getPositionY() - c.getTriangleY2() < kollisionDistance
					&& held.getPositionY() - c.getTriangleY2() > -kollisionDistance)
					|| (held.getPositionX() - c.getTriangleX3() < kollisionDistance
					&& held.getPositionX() - c.getTriangleX3() > -kollisionDistance
					&& held.getPositionY() - c.getTriangleY3() < kollisionDistance
					&& held.getPositionY() - c.getTriangleY3() > -kollisionDistance)) {

				if (kollisionSpike(c) == true) {
				}
			}

		}

		if (isColiding == true) {

			held.setPositionX(1);
			held.setPositionY(1);
			isColiding = false;

		}

	}
	public void kollisionFloor() {
		vecp = new PVector((int) held.getPositionX(),(int) held.getPositionY());

			// wenn w key gedrückt boolean setzen so das es nicht durchfällt!
		for(PVector p : held.getPixelListBottom()){



		for (Floor floortest : floorList) {

			if (floortest.getPosiY() - held.getPositionY() < 50 && floortest.getPosiY() - held.getPositionY() > 10 && floortest.getPosiX() - held.getPositionX() < 20 && (floortest.getPosiX() + floortest.getImage().width) - held.getPositionX() > -20) {
				if (((p.x + held.getPositionX()) >= floortest.getPosiX() && (p.x + held.getPositionX()) <= (floortest.getPosiX() + floortest.getImage().width)) && ((p.y + held.getPositionY()) >= floortest.getPosiY()) && (p.y + held.getPositionY()) <= (floortest.getPosiY() + 10)) {
					System.out.println("bla");
					held.setCooliding(true);
					held.setJumpCount(2);
					setGravity(1.5F);
					held.setPositionY(floortest.getPosiY() - held.getImg().height);

					//warum köst es das problem?
					if(held.isJumping()){
						held.setJumping(true);
					}



				} //else if (((p.x + held.getPositionX()) < floortest.getPosiX() || ((p.x + held.getPositionX()) > (floortest.getPosiX() + floortest.getImage().width)))) {
					//else if (( floortest.getPosiX() - (p.x + held.getPositionX()) < 10 || ((p.x + held.getPositionX()) > (floortest.getPosiX() + floortest.getImage().width)))) {
					else{
					held.setCooliding(false);
					System.out.println(held.getJumpTime());



				}

				}
			}
		}













		//for(PVector p : held.getPixelList()){
		//for (Floor floortest : floorList) {



			//System.out.println("posi x" +floortest.getPosiX());
			//System.out.println("posi y" + floortest.getPosiY());
			//System.out.println("floor width" + floortest.getImage().width);
			//System.out.println("floor height" + floortest.getImage().height);
			//System.out.println(floorList.get(floorList.size()));
			//if((held.getPositionX() >= floortest.getPosiX() &&  held.getPositionX() <= (floortest.getPosiX() +floortest.getImage().width)) && (held.getPositionY() >= floortest.getPosiY() && held.getPositionY() <= (floortest.getPosiY() + floortest.getImage().height))){
			//	System.out.println("bla");
			//}
			//System.out.println(floortest.getPosiY());

			// einen test mit y ausführen also den check mit der x und der y koordinate ausführen

			// test mit neuen arraylisen
	/*
			if (floortest.getPosiY() - held.getPositionY() < 50 && floortest.getPosiY() - held.getPositionY() > 10 && floortest.getPosiX() - held.getPositionX() < 20 && (floortest.getPosiX() + floortest.getImage().width) - held.getPositionX() >-20) {
				if (((p.x + held.getPositionX()) >= floortest.getPosiX() && (p.x + held.getPositionX()) <= (floortest.getPosiX() + floortest.getImage().width)) && ((p.y + held.getPositionY()) >= floortest.getPosiY()) && (p.y + held.getPositionY()) <= (floortest.getPosiY() + 10)) {
					System.out.println("bla");
					held.setCooliding(true);
					held.setJumpCount(2);
					setGravity(2);
					held.setPositionY(floortest.getPosiY() - held.getImg().height);
					held.setJumping(false);

				} else if (((p.x + held.getPositionX() + 12) < floortest.getPosiX() || ((p.x + held.getPositionX() - 12) > (floortest.getPosiX() + floortest.getImage().width)))) {
				//else if (( floortest.getPosiX() - (p.x + held.getPositionX()) < 10 || ((p.x + held.getPositionX()) > (floortest.getPosiX() + floortest.getImage().width)))) {

					held.setCooliding(false);
					System.out.println("test");
					System.out.println(held.getJumpCount());

				}
			}
	*/	}


	//	for (Floor floortest : floorList) {
	//		System.out.println(floortest.getPixelList());
	//		if (floortest.getPixelList().contains(vecp)) {
	//			System.out.println("bla");
	//		}
	//	}


	public boolean kollisionSpike(Spike spike) {

		veca = new PVector(spike.getTriangleX1(), spike.getTriangleY1());

		vecb = new PVector(spike.getTriangleX2(), spike.getTriangleY2());

		vecc = new PVector(spike.getTriangleX3(), spike.getTriangleY3());

		vecp = new PVector(held.getPositionX(), held.getPositionY());

		for (PVector p : held.getPixelList()) {
			float w1 = (veca.x * (vecc.y - veca.y) + ((vecp.y + p.y) - veca.y) * (vecc.x - veca.x)
					- (vecp.x + p.x) * (vecc.y - veca.y))
					/ ((vecb.y - veca.y) * (vecc.x - veca.x) - (vecb.x - veca.x) * (vecc.y - veca.y));

			float w2 = ((vecp.y + p.y) - veca.y - w1 * (vecb.y - veca.y)) / (vecc.y - veca.y);


			if (w1 >= 0 && w2 >= 0 && (w1 + w2) <= 1) {
				isColiding = true;
			} else {

			}
		}

		return isColiding;

	}

	public void gravitation() {
		if (held.getTest() == 1 && held.isJumping() == false && held.isMaxJump() == false) {
			gravity = 0;
			held.setTest(0);
		}
		if (gravity != 8) {
			gravity += 0.5F;
			if (gravity > 8) {
				gravity = 8;
			}
		}
		if (held.getPositionY() < 300) {
			// System.out.println(getHeldY());
			held.setPositionY(held.getPositionY() + gravity);


		}
		if (held.getPositionY() > 300) {
			held.setJumpCount(2);
		}

	}

	public void erschaffeSpike() {
		spike = new Spike();
		spikeListe = new ArrayList<>();
	}


	public void move() {
		if (aKey == true) {
			held.setPositionX(held.getPositionX() - VELOCITY);
		}
		if (dKey == true) {
			held.setPositionX(held.getPositionX() + VELOCITY);
		}
		if (wKey == true) {
			held.setPositionY(held.getPositionY() - VELOCITY);
		}
		if (sKey == true) {
			held.setPositionY(held.getPositionY() + VELOCITY);
		}

	}


	@Override
	public void keyPressed() {
		if (keyPressed) {
			if (key == 'a') {
				aKey = true;
			}
			if (key == 'd') {
				dKey = true;
			}
			if (key == 'w') {
				held.setJumping(true);
			}

			if (key == 's') {
				sKey = true;
			}
			if (key == 'y') {
			bulletList.add(erschaffeBullet());
				setShoot(true);
			}
		}
	}

	@Override
	public void keyReleased() {
		if (key == 'a') {
			aKey = false;
		}
		if (key == 'd') {
			dKey = false;
		}
		if (key == 'w') {
			held.setJumping(false);
			held.setJumpTime(1 / 30F);
			held.setMaxJump(false);
			held.setJumpCount(held.getJumpCount() - 1);
		}
		if (key == 's') {
			sKey = false;

		}
		if (key == 'y') {
			setShoot(false);

		}
		if (key == 'q') {
			editor.changeIndex(key);

		}
		if (key == 'e') {
			editor.changeIndex(key);

		}
	}

	//boolean für is editor mode
	@Override
	public void mouseClicked(){
		if(mouseButton == LEFT) {
			System.out.println("Mouse Clicked left");

			editor.createObjects((float) mouseX,(float) mouseY,spikeListe,floorList,this);
		}
		if(mouseButton == RIGHT) {
			System.out.println("Mouse Clicked right");
			editor.deleteSpike(spikeListe, (float) mouseX, (float) mouseY);
		}
	}
}


