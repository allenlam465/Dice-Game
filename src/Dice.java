import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Dice {
	
	private int value;
	private boolean held;
	private ImageView img;
	
	public Dice() {
		value = 1;
		setHeld(false);
		img = new ImageView(new Image("file:./res/Dice" + value + ".png"));

	}
	
	public void setDice(int value, boolean held) {
		this.value = value;
		this.setHeld(held);
	}
	
	protected void setValue(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}

	public boolean isHeld() {
		return held;
	}

	protected void setHeld(boolean held) {
		this.held = held;
	}

	public ImageView getImg() {
		return img;
	}

	protected void setImg() {
		this.img.setStyle("");
		this.img.setImage(new Image("file:./res/Dice" + value + ".png"));
	}
	
	protected void setImgHeld() {
		this.img.setStyle("-fx-effect: dropshadow(three-pass-box, rgb(212, 102, 131) , 45, 0, 0, 0);");
		this.img.setImage(new Image("file:./res/Dice" + value + "Held.png"));
	}
	
	protected void setImg(Image img) {
		this.img.setStyle("");
		this.img.setImage(img);;
	}
}
