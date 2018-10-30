import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Driver extends Application {

	public static void main(String[] args) {

		launch(args);
	}

	private DiceGame game = new DiceGame();
	private Label score = new Label("Score: " + game.getScore());
	private Label roundScore = new Label("Round Score: " + game.getRoundScore());
	private Label rolls = new Label("Roll: " + game.getRolls());
	private Label hand = new Label();
	
	@Override
	public void start(Stage primaryStage) throws Exception {

		class DiceClick implements EventHandler<MouseEvent>{

			Dice dice;

			public DiceClick(ImageView iv, Dice dice) {
				this.dice = dice;
			}

			@Override
			public void handle(MouseEvent event) {

				if(game.getRolls() == 3) 
					hand.setText("You haven't even rolled yet!");
				else if(game.getRolls() == 0)
					hand.setText("You have no more rolls!");
				else {

					dice.setHeld(!dice.isHeld());

					if(dice.isHeld()) {
						dice.setImgHeld();
					}
					else {
						dice.setImg();
					}

				}

			}

		}

		for(int i = 0; i < game.getDices().length; i++) {
			game.getDice(i).getImg().setFitHeight(85);;
			game.getDice(i).getImg().setPreserveRatio(true);
			game.getDice(i).getImg().setId("dice");
			game.getDice(i).getImg().addEventHandler(MouseEvent.MOUSE_CLICKED, new DiceClick(game.getDice(i).getImg(), game.getDice(i)));

		}

		Button submitAction = new Button("Roll Dice");

		submitAction.setOnAction(e ->{

			if(game.getRolls() == 0) {
				game.resetGame();

				for(int i = 0; i < game.getDices().length; i++) {
					game.getDice(i).setImg();
				}
				
				displayLabels();
				
				submitAction.setText("Roll Dices");


			}
			else {
				game.rollDices();

				for(int i = 0; i < game.getDices().length; i++) {
					game.getDice(i).setImg();
				}

				rolls.setText("Rolls: " + game.getRolls());
				hand.setText(game.getHand());

			}
			
			if(game.getRolls() == 0) {
				game.scorer();
				
				displayLabels();
				
				submitAction.setText("Play Again?");
			}
		});

		HBox topBar = new HBox(10, score,rolls);
		HBox diceBox = new HBox(10);

		for(int i = 0; i < game.getDices().length; i++)
			diceBox.getChildren().add(game.getDice(i).getImg());

		VBox buttonBox = new VBox(10, topBar, diceBox, roundScore, hand, submitAction);
		BorderPane mainWindow = new BorderPane();

		mainWindow.setTop(topBar);
		mainWindow.setCenter(diceBox);
		mainWindow.setBottom(buttonBox);

		topBar.setPadding(new Insets(10));
		diceBox.setPadding(new Insets(10));
		buttonBox.setPadding(new Insets(10));

		topBar.setAlignment(Pos.CENTER);
		diceBox.setAlignment(Pos.CENTER);
		buttonBox.setAlignment(Pos.CENTER);

		Scene myScene = new Scene(mainWindow);

		myScene.getStylesheets().add("mystyles.css");

		primaryStage.setScene(myScene);
		primaryStage.setTitle("Dice Game");
		primaryStage.show();

	}
	
	private void displayLabels() {
		
		score.setText("Score: "  + game.getScore());
		roundScore.setText("Round Score: " + game.getRoundScore());
		rolls.setText("Rolls: " + game.getRolls());
		hand.setText(game.getHand());
		
	}
}
