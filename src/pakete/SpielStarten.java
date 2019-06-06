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
	private Placeable placeable;
	private boolean respawn;
	private SaveLevel savelevel;
	private ArrayList<Charakter> charakterArrayList;
	private Enemy enemy;


	private ArrayList<Spike> spikeListe;
	private ArrayList<Bullet> bulletList;
	private ArrayList<Bullet> bulletIDList = new ArrayList<>();
	private ArrayList<Placeable> placebleList = new ArrayList<>();
	private ArrayList<Floor> floorList;



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
		//system.out.println("hier ist die main klasse");
		PApplet.main(SpielStarten.class, args);
		//system.out.println("hier ist die main klasse zuende");

	}

	public SpielStarten() {
		//system.out.println("hier ist der construktor");
	}

	public void ersschaffeObjekte() {
		erschaffeHeld();
		// erschaffeHindernisse();
		erschaffeSpike();
		erschaffeWelt();
		erschaffeFloor();
		erschaffeCollisionHandler();
        erschaffePlacable();
        erschaffeEditor();
        erschaffeSaveLevel();
        erschaffeEnemy();

	}
    public void erschaffeEditor(){
        editor = new Editor(this,placebleList,welt,this, held, savelevel);
    }
	public void erschaffeSaveLevel(){
        savelevel = new SaveLevel(this,editor);
        editor.setSaveLevel(savelevel);

    }

	public void erschaffePlacable(){
	  //  placeable = new Placeable();
    }

	public void erschaffeCollisionHandler(){
		//system.out.println("test");
		collisionHandler = new CollisionHandler(held,spikeListe,floorList, welt,placebleList, charakterArrayList, bullet, bulletList);
	}

	public void erschaffeHeld() {
		//system.out.println("hier ist erschaffe held");
		PImage heldImage = loadImage("resources/90tiny.png");
		held = new Charakter(heldImage, 1, 1);
		charakterArrayList.add(held);

	}

	public void erschaffeEnemy(){

		PImage enemyImage = loadImage("resources/enemy.png");
		enemy = new Enemy(enemyImage,50,50);
		charakterArrayList.add(enemy);

	}

	public void respawn(PImage p) {
		held.setImg(p);
	}

	public void sterben(PImage p) {
		p = loadImage("resources/spike.png");
		held.setImg(p);
	}

	public void erschaffeWelt() {
		welt = new SpielWelt(held,this, charakterArrayList, charakterArrayList);
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
			bullet = new Bullet(bulletImage,held.getPositionX()-10,held.getPositionY()+10,2,-10,this);
			collisionHandler.getBulletArrayList().add(bullet);


		}else {
			bullet = new Bullet(bulletImage, held.getPositionX() + 10, held.getPositionY() + 10, 2, 10, this);
			collisionHandler.getBulletArrayList().add(bullet);


		}

		return bullet;
	}
	@Override
	public void setup() {
		super.setup();
		//system.out.println("hier ist setup");
		floorList = new ArrayList<>();
		charakterArrayList = new ArrayList<>();
		bulletList = new ArrayList<>();
		ersschaffeObjekte();
		spike.erschaffeSpike(this, spikeListe);

		//editor = new Editor(this,placebleList,welt,this, held, savelevel);
		editor.createGrid(40);
		lastMovement.add(0,0);
		//system.out.println(held);
		held.getNonTransparentPixel();
		background(0);
		frameRate(30);

		loop();


	}
	@Override
	public void settings() {
		super.settings();

		size(800, 800);
		//system.out.println("hier wird settings aufgerufen");

	}
	@Override
	public void draw() {

		background(0);

		this.heromove();
		this.enemyMove();


		displayMethods();

		removeBullet();

		collisionHandler.spikeCollision();

		collisionHandler.checkEnemyHit();

		collisionHandler.stopGrav();
		collisionHandler.kollisionFloor();

		editor.testdraw((float) mouseX,(float) mouseY);


	}




	public void heromove(){
		held.move();
		if(!held.isCooliding()) {
			welt.gravitation();

			// TODO: 05.06.2019 warum gravi nicht mehr greift 
		}
		held.springen(welt);
		//enemy.move();
		//enemy.movePattern();

	}

	public void enemyMove(){
		for(Charakter e : charakterArrayList) {
			if(e instanceof Enemy) {


				Enemy c = (Enemy) e;

				//c.movePattern();
				e.move();
			}

		}
	}




	public void displayCharakters(){
		for(Charakter e : charakterArrayList) {

			image(e.getImg(), e.getPositionX(), e.getPositionY());



		//image(held.getImg(), held.getPositionX(), held.getPositionY());
		//image(enemy.getImg(), enemy.getPositionX(), enemy.getPositionX());
		}
	}

	public void displayFloor(){
//		for(Floor f : floorList){
//			//f.setImage(loadImage("resources/floor.png"));
//			image(f.getImage(),f.getPositionX(),f.getPositionY());
//		}
		for(Placeable c : placebleList){

		    if(c instanceof Floor ){

		        image(c.getImage(),c.getPositionX(),c.getPositionY());

            }
        }
	}

	public void displayMethods(){
		displayCharakters();
		displaySpike();
		displayFloor();
		displayBullet();
		//if(editor.isRespawn()) {
            respawn();
		if(showgrid){
			editor.showGrid();
		}
      //  }
	}

	public void respawn(){
        for(Placeable restart : placebleList) {
            if(restart instanceof Restart) {

                this.text("R", restart.getPositionX(), restart.getPositionY());
                textSize(20);
            }
        }
    }
	public void displaySpike(){
		for (Placeable s : placebleList) {
            if (s instanceof Spike) {

                triangle(s.getPositionX(), s.getPositionY(), ((Spike) s).getTriangleX2(), ((Spike) s).getTriangleY2(), ((Spike) s).getTriangleX3(),
                        ((Spike) s).getTriangleY3());
               // triangle(s.getPositionX(), s.getPositionY(), s.getPositionX() - 20, s.getPositionY() + 30, s.getPositionX() + 20, s.getPositionY() + 30);
            }
        }

	}

	public void displayBullet(){
		if(isShoot()){
			bulletList.add(erschaffeBullet());
		}
		for (Bullet b : bulletList) {

			image(b.getImg(), b.getPositionX(), b.getPositionY());
			b.bulletMove(b);
			//system.out.println(bulletList.size());
			//if(b.getPositionX() > width || b.getPositionX() < (width - width)){
			if(b.isHit() || b.getPositionX() > width || b.getPositionX() < (width - width)){

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
			held.setPositionX(held.getPositionX() - held.getVelocityJump());
		}
		if (held.isMoveRight() == true) {
			held.setPositionX(held.getPositionX() + held.getVelocityJump());
		}
		//if (wKey == true) {
		//	held.setPositionY(held.getPositionY() - VELOCITY);
	//	}
		if (held.isMoveDown() == true) {
			held.setPositionY(held.getPositionY() + held.getVelocityJump());
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
			    if(!collisionHandler.isTestJump()) {

                    held.setJumping(true);
                }

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
					editor.setEditorMode(false);

				}else {
					showgrid = true;
					editor.setEditorMode(true);
				}

			}
			if (key == 'r') {
				if(scalevalue >30) {

					scalevalue -= 30;

					editor.createGrid(scalevalue);
				}
			}
			if (key == 'z') {

					if(scalevalue <= 90) {
						scalevalue += 30;
					}
				//system.out.println(scalevalue);
				editor.createGrid(scalevalue);
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
            collisionHandler.setTestJump(false);
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
        if (key == 'p') {
            savelevel.saveJsonLevel();
        }
        if (key == 'l') {
            savelevel.loadJsonLevel();
            key = 'b';
        }
	}

	//boolean für is editor mode
	@Override
	public void mouseClicked(){
		if(mouseButton == LEFT) {
            //system.out.println("Mouse Clicked left");
            if (editor.isEditorMode()) {
                editor.createObjects((float) mouseX - (mouseX % scalevalue), (float) mouseY - (mouseY % scalevalue));
            }else{
                editor.createObjects((float) mouseX , (float) mouseY );


            }
        }
		if(mouseButton == RIGHT) {
			//system.out.println("Mouse Clicked right");
			editor.deleteSpike((float) mouseX, (float) mouseY);
		}
	}
}


