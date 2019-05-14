package pakete;

import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;

public class SpielStarten extends PApplet {
	private Charakter held;
	private Platform hindernis;
	private Spike spike;
	private Editor editor;
	private SpielWelt welt;
	private Bullet bullet;
	private Floor floor;
	private CollisionHandler collisionHandler;

	private ArrayList<Spike> spikeListe;
	private ArrayList<Bullet> bulletList;
	private ArrayList<Bullet> bulletIDList = new ArrayList<>();
	private ArrayList<Floor> floorList;
	private static final float VELOCITY = 5;


	private boolean shoot;
	private boolean showgrid;
	private int scalevalue = 40;
	private ArrayList<Integer> lastMovement = new ArrayList<>();




	public boolean isShoot() {
		return shoot;
	}

	public void setShoot(boolean shoot) {
		this.shoot = shoot;
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
		erschaffeCollisionHandler();


	}

	public void erschaffeCollisionHandler(){
		System.out.println("test");
		collisionHandler = new CollisionHandler(held,spikeListe,floorList, welt);
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
		welt = new SpielWelt(held);
	}

	public void spielStarten() {


		held.setPositionX(100);
	}

	public void erschaffeFloor(){
		//PImage p = new PImage();
		//floor = new Floor(p = loadImage("resources/floor.png"),1,1);
		//floorList.add(floor);
	}

	public Bullet erschaffeBullet() {
		PImage bulletImage = loadImage("resources/bullet.png");
		if(lastMovement.get(0) == 0){
			bullet = new Bullet(bulletImage,held.getPositionX()-10,held.getPositionY()+10,2,-20,this);

		}else {
			bullet = new Bullet(bulletImage, held.getPositionX() + 10, held.getPositionY() + 10, 2, 20, this);
		}

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
		editor.createGrid(this,40);
		lastMovement.add(0,0);
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
		move();
		if(!held.isCooliding()) {
			welt.gravitation();
		}
		if(showgrid){
			editor.showGrid(this);
		}
		//kollisionFloor();
		held.springen(welt);
		displayMethods();

		removeBullet();

		collisionHandler.spikeCollision();
		collisionHandler.stopGrav();
		collisionHandler.kollisionFloor();

	}


	public void displayFloor(){
		for(Floor f : floorList){
			f.setImage(loadImage("resources/floor.png"));
			image(f.getImage(),f.getPositionX(),f.getPositionY());
		}
	}

	public void displayMethods(){
		displaySpike();
		displayFloor();
		displayBullet();

	}
	public void displaySpike(){
		for (Spike s : spikeListe) {
			triangle(s.getPositionX(), s.getPositionY(), s.getTriangleX2(), s.getTriangleY2(), s.getTriangleX3(),
					s.getTriangleY3());
		}
	}

	public void displayBullet(){
		if(isShoot()){
			bulletList.add(erschaffeBullet());
		}
		for (Bullet b : bulletList) {

			image(b.getImg(), b.getPositionX(), b.getPositionY());
			b.bulletMove(b);
			System.out.println(bulletList.size());
			if(b.getPositionX() > width || b.getPositionX() < (width - width)){

				bullet.getBulletIDList().add(b);


			}
		}
	}

	public void removeBullet() {
		if(bullet != null){
		if (bullet.getBulletIDList() != null) {
			for (Bullet b : bullet.getBulletIDList()) {
				bulletList.remove(b);
			}
			bulletIDList = new ArrayList<>();
		}
	}}

		public void erschaffeSpike () {
			spike = new Spike();
			spikeListe = new ArrayList<>();
		}



	public void move() {
		if (held.isMoveLeft() == true) {
			held.setPositionX(held.getPositionX() - VELOCITY);
		}
		if (held.isMoveRight() == true) {
			held.setPositionX(held.getPositionX() + VELOCITY);
		}
		//if (wKey == true) {
		//	held.setPositionY(held.getPositionY() - VELOCITY);
	//	}
		if (held.isMoveDown() == true) {
			held.setPositionY(held.getPositionY() + VELOCITY);
		}

	}


	@Override
	public void keyPressed() {
		if (keyPressed) {
			if (key == 'a') {
				held.setMoveLeft(true);
				lastMovement.add(0,0);

			}
			if (key == 'd') {
				held.setMoveRight(true);
				lastMovement.add(0,1);
			}
			if (key == 'w') {
				held.setJumping(true);
			}

			if (key == 's') {
				held.setMoveDown(true);
			}

			if (key == 'y') {

				setShoot(true);
			}
			if (key == 't') {
				if(showgrid){
					showgrid = false;

				}else {
					showgrid = true;
				}

			}
			if (key == 'r') {
				if(scalevalue >20) {

					scalevalue -= 20;

					editor.createGrid(this, scalevalue);
				}
			}
			if (key == 'z') {

					if(scalevalue <= 60) {
						scalevalue += 20;
					}
				System.out.println(scalevalue);
				editor.createGrid(this,scalevalue);
			}
		}
	}

	@Override
	public void keyReleased() {
		if (key == 'a') {
			held.setMoveLeft(false);
		}
		if (key == 'd') {
			held.setMoveRight(false);
		}
		if (key == 'w') {
			held.setJumping(false);
			held.setJumpTime(1 / 30F);
			held.setMaxJump(false);
			held.setJumpCount(held.getJumpCount() - 1);
		}
		if (key == 's') {
			held.setMoveDown(false);

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

			editor.createObjects((float) mouseX - (mouseX % scalevalue) ,(float) mouseY - (mouseY % scalevalue) ,spikeListe,floorList,this);
	}
		if(mouseButton == RIGHT) {
			System.out.println("Mouse Clicked right");
			editor.deleteSpike(spikeListe, (float) mouseX, (float) mouseY, floorList);
		}
	}
}


