package entity;

import java.awt.Rectangle;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {

	KeyHandler keyH;
	public final int screenX;
	public final int screenY;
	int standCounter = 0;

//	public int hasKey = 0;
//	boolean moving = false;
//	int pixelCounter = 0;

	public Player(GamePanel gp, KeyHandler keyH) {
		super(gp);
		this.keyH = keyH;

		screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
		screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

		soliArea = new Rectangle();
		soliArea.x = 1;
		soliArea.y = 1;
		soliAreaDefaultX = soliArea.x;
		soliAreaDefaultY = soliArea.y;
		soliArea.width = 46;
		soliArea.height = 46;

		setDefaultValues();
		getPlayerImage();
	}

	public void setDefaultValues() {
		worldX = gp.tileSize * 23;
		worldY = gp.tileSize * 21;

//		worldX = gp.tileSize * 0;
//		worldY = gp.tileSize * 0;
		// ZOOM IN, ZOOM IN (SETTING MOVE)
//		speed = gp.worldWidth / 600;

		speed = 4;
		direction = "down";
	}

	public void getPlayerImage() {
		up1 = setup("/player/boy_up_1");
		up2 = setup("/player/boy_up_2");
		down1 = setup("/player/boy_down_1");
		down2 = setup("/player/boy_down_2");
		left1 = setup("/player/boy_left_1");
		left2 = setup("/player/boy_left_2");
		right1 = setup("/player/boy_right_1");
		right2 = setup("/player/boy_right_2");
	}

	public void update() {
		if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true
				|| keyH.rightPressed == true) {
			if (keyH.upPressed == true) {
				direction = "up";
			} else if (keyH.downPressed == true) {
				direction = "down";
			} else if (keyH.leftPressed == true) {
				direction = "left";
			} else if (keyH.rightPressed == true) {
				direction = "right";
			}
			// CHECK TILE COLLISION
			collisionOn = false;
			gp.cChecker.checkTile(this);

			// CHECK OBJECT COLLISION
			int indexObject = gp.cChecker.checkObject(this, true);
			pickUpObject(indexObject);

			// CHECK NPC COLLISION
			int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
			interactNPC(npcIndex);

			// IF COLLISION IS FALSE, PLAYER CAN MOVE
			if (collisionOn == false) {
				switch (direction) {
				case "up":
					worldY -= speed;
					break;
				case "down":
					worldY += speed;
					break;
				case "left":
					worldX -= speed;
					break;
				case "right":
					worldX += speed;
					break;
				}
			}

			spriteCounter++;
			if (spriteCounter > 12) {
				if (spriteNum == 1) {
					spriteNum = 2;
				} else if (spriteNum == 2) {
					spriteNum = 1;
				}
				spriteCounter = 0;
			}
		} else {
			standCounter++;

			if (standCounter == 20) {
				spriteNum = 1;
				standCounter = 0;
			}
		}
	}
//	public void update() {
//		if (moving == false) {
//			if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true
//					|| keyH.rightPressed == true) {
//				if (keyH.upPressed == true) {
//					direction = "up";
//				} else if (keyH.downPressed == true) {
//					direction = "down";
//				} else if (keyH.leftPressed == true) {
//					direction = "left";
//				} else if (keyH.rightPressed == true) {
//					direction = "right";
//				}
//
//				moving = true;
//
//				// CHECK TILE COLLISION
//				collisionOn = false;
//				gp.cChecker.checkTile(this);
//
//				// CHECK OBJECT COLLITION
//				int indexObject = gp.cChecker.checkObject(this, true);
//				pickUpObject(indexObject);
//			} else {
//				standCounter++;
//
//				if (standCounter == 20) {
//					spriteNum = 1;
//					standCounter = 0;
//				}
//			}
//		}
//
//		if (moving == true) {
//			// IF COLLISION IS FALSE, PLAYER CAN MOVE
//			if (collisionOn == false) {
//				switch (direction) {
//				case "up":
//					worldY -= speed;
//					break;
//				case "down":
//					worldY += speed;
//					break;
//				case "left":
//					worldX -= speed;
//					break;
//				case "right":
//					worldX += speed;
//					break;
//				}
//			}
//
//			spriteCounter++;
//			if (spriteCounter > 12) {
//				if (spriteNum == 1) {
//					spriteNum = 2;
//				} else if (spriteNum == 2) {
//					spriteNum = 1;
//				}
//				spriteCounter = 0;
//			}
//
//			pixelCounter += speed;
//
//			if (pixelCounter == 48) {
//				moving = false;
//				pixelCounter = 0;
//			}
//		}
//	}

	public void pickUpObject(int i) {
		if (i != 999) {
		}
	}
//	public void pickUpObject(int i) {
//		if (i != 999) {
//			String objectName = gp.obj[i].name;
//
//			switch (objectName) {
//			case "Key":
//				gp.playSE(1);
//				hasKey++;
//				gp.obj[i] = null;
//				gp.ui.showMessgae("You got a key!");
//				break;
//			case "Door":
//				if (hasKey > 0) {
//					gp.playSE(3);
//					gp.obj[i] = null;
//					hasKey--;
//					gp.ui.showMessgae("You opened the door!");
//				} else {
//					gp.ui.showMessgae("You need a key!");
//				}
//				break;
//			case "Boots":
//				gp.playSE(2);
//				speed += 0;
//				gp.obj[i] = null;
//				gp.ui.showMessgae("Speed up!");
//				break;
//			case "Chest":
//				gp.ui.gameFinished = true;
//				gp.stopMusic();
//				gp.playSE(4);
//				break;
//			}
//		}
//	}
	
	public void interactNPC(int i) {
		if (i != 999) {
			System.out.println("you are hitting an NPC!");
		}
	}

	public void draw(Graphics2D g2) {
//		g2.setColor(Color.white);
//		g2.fillRect(x, y, gp.tileSize, gp.tileSize);

		BufferedImage image = null;

		switch (direction) {
		case "up":
			if (spriteNum == 1) {
				image = up1;
			}
			if (spriteNum == 2) {
				image = up2;
			}
			break;
		case "down":
			if (spriteNum == 1) {
				image = down1;
			}
			if (spriteNum == 2) {
				image = down2;
			}
			break;
		case "left":
			if (spriteNum == 1) {
				image = left1;
			}
			if (spriteNum == 2) {
				image = left2;
			}
			break;
		case "right":
			if (spriteNum == 1) {
				image = right1;
			}
			if (spriteNum == 2) {
				image = right2;
			}
			break;
		}

		int x = screenX;
		int y = screenY;

		if (screenX > worldX) {
			x = worldX;
		}
		if (screenY > worldY) {
			y = worldY;
		}
		int rightOffset = gp.screenWidth - screenX;
		if (rightOffset > gp.worldWidth - worldX) {
			x = gp.screenWidth - (gp.worldWidth - worldX);
		}
		int bottomOffset = gp.screenHeight - screenY;
		if (bottomOffset > gp.worldHeight - worldY) {
			y = gp.screenHeight - (gp.worldHeight - worldY);
		}

		g2.drawImage(image, x, y, null);

		// TRANGLE PLAYER
//		g2.setColor(Color.red);
//		g2.drawRect(screenX + soliArea.x, screenY + soliArea.y, soliArea.width, soliArea.height);
	}
}
