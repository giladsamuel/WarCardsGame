import javafx.application.Application;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.control.ButtonType;
import java.util.Optional;

/**
 * This class represents the main gameplay of the War Cards Game.
 * The first player to run out of cards lose the game.
 * It extends the Application class from the JavaFX library to create a GUI application
 * that interacts user with dialog box.
 */
public class GamePlay extends Application{
    final static String MASSAGE_GAME_TITLE = "War Cards GamePlay";
    private final Model model = new Model(); // Holds the game data

    /**
     * This method is the main entry point for all JavaFX applications.
     */
    @Override
    public void start(Stage stage) throws Exception {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle(MASSAGE_GAME_TITLE);
        dialog.setHeaderText("Welcome to WAR\nPlease enter your name:");
        dialog.setContentText("Name:");

        Optional<String> result = dialog.showAndWait();

        // Process the user's response
        if (!result.isPresent() || result.get().isEmpty()) {
            noUserNameError();
        } else {
            model.userName = result.get();
            initGame();
        }
    }

    /**
     * This method initializes the game by setting up the decks and showing the welcome message.
     */
    private void initGame() {
        initDecks();
        ButtonType userButtonAct =  showConfirmMessage("Welcome to WAR " + model.userName,
                                        "Press 'OK' to start the game.");
        if (userButtonAct == ButtonType.OK) {
            startGame();
        } else {
            exitGame();
        }

    }

    /**
     * This method starts the game loop where cards are drawn, compared and results are shown.
     * The loop continues until one of the players wins or game is exited.
     */
    private void startGame() {
        while(true) {
            drawCards();
            compareCards();
            showBattle();

            while(model.warDeclared) {
                warMode();
            }
        }
    }

    /**
     * This method initializes the decks by creating a full card set in main deck,
     * shuffling it and splitting the main deck into two - one for the user and one for the PC.
     * The user and PC decks are then filled with the split decks.
     */
    private void initDecks() {
        CardDeck[] decks;

        model.mainDeck.initializeDeck();
        model.mainDeck.shuffle();
        // model.mainDeck.printDeck();
        decks = model.mainDeck.splitDeck();
        model.mainDeck.emptyDeck();
        model.userDeck.addCardsToBottom(decks[0].getCards());
        model.pcDeck.addCardsToBottom(decks[1].getCards());
    }

    /**
     * This method draws cards from the user's and PC's decks and adds them to the main deck.
     * It also checks if any of the players has run out of cards and shows the win message.
     */
    private void drawCards() {
        model.userCard = model.userDeck.removeTopCard();
        model.pcCard = model.pcDeck.removeTopCard();
        if (model.userCard == null || model.pcCard == null) {
            showWinMessage();
        }
        model.userCardInfo = model.userName +"'s card: " + model.userCard.getNumber() + " of " + model.userCard.getShape();
        model.pcCardInfo = "PC's card: " + model.pcCard.getNumber() + " of " + model.pcCard.getShape();

        model.mainDeck.addCardsToBottom(model.userCard);
        model.mainDeck.addCardsToBottom(model.pcCard);
    }

    /**
     * This method compares the cards drawn by the user and the PC and determines the winner of the battle.
     * The winner gets all the cards in the main deck.
     * If the cards are equal, a war is declared and the game enters the war mode.
     */
    private void compareCards() {
        int compareResult = model.userCard.compareTo(model.pcCard);

        if(compareResult > 0) {
            model.compareResultMessage = model.userName + " wins battle!";
            model.userDeck.addCardsToBottom(model.mainDeck.getCards());
            model.mainDeck.emptyDeck();
        } else if(compareResult < 0) {
            model.compareResultMessage = "PC wins battle!";
            model.pcDeck.addCardsToBottom(model.mainDeck.getCards());
            model.mainDeck.emptyDeck();
        } else {
            model.compareResultMessage = "It's a tie!\nWAR declared";
            model.warDeclared = true;
        }
    }

    /**
     * This method shows the result of the battle and asks the user whether they want to continue the game or exit.
     * If the user chooses to exit, the game is terminated.
     */
    private void showBattle() {
        String gameStatus = model.userCardInfo + "\n" + model.pcCardInfo + "\n" + model.compareResultMessage;
        String gameInstructions = "To draw next cards press 'OK', to exit 'Cancel'";
        ButtonType userButtonAction = showConfirmMessage(gameStatus, gameInstructions);

        if(userButtonAction == ButtonType.CANCEL) {
            exitGame();
        }
    }

    /**
     * This method handles the war mode where 3 additional cards are drawn and the last compared.
     */
    private void warMode() {
        model.warDeclared = false;

        for (int i = 0; i < 2; i++) {
            drawCards();
            showInfoMessage("Cards were drawn!", "Press 'OK' to draw next cards.");
        }
        drawCards();
        compareCards();
        showBattle();
    }

    /**
     * This method shows an error message when the user does not enter a name and exits the game.
     */
    private void noUserNameError() {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(MASSAGE_GAME_TITLE);
        alert.setHeaderText("User did not enter any name.");
        alert.showAndWait();
        System.exit(0);
    }

    /**
     * This method exits the game by showing a goodbye message and then terminating the application.
     */
    private void exitGame() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(MASSAGE_GAME_TITLE);
        alert.setHeaderText("Goodbye! Hope to see you soon.");
        alert.showAndWait();
        System.exit(0);
    }

    /**
     * This method shows a win message when one of the players has no cards left and then exits the game.
     */
    private void showWinMessage() {
        if (model.userDeck.getCards().isEmpty()) {
            showInfoMessage("PC wins the game!",  model.userName+ " has no cards left.");
        } else {
            showInfoMessage( model.userName + " wins the game!", "PC has no cards left.");
        }
        exitGame();
    }

    /**
     * This method shows an information message with the given header and content.
     * @param headerMessage the header of the message.
     * @param ContentMessage the content of the message.
     */
    private void showInfoMessage(String headerMessage, String ContentMessage) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(MASSAGE_GAME_TITLE);
        alert.setHeaderText(headerMessage);
        alert.setContentText(ContentMessage);
        alert.showAndWait();
    }

    /**
     * This method shows a confirmation message with the given header and content and returns the user's response.
     * @param headerMessage the header of the message.
     * @param ContentMessage the content of the message.
     * @return the user's response as a ButtonType.
     */
    private ButtonType showConfirmMessage(String headerMessage, String ContentMessage) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle(MASSAGE_GAME_TITLE);
        alert.setHeaderText(headerMessage);
        alert.setContentText(ContentMessage);
        alert.showAndWait();
        return alert.getResult();
    }

    /**
     * The entry point for Java application, launches the JavaFX application.
     * @param args the command-line arguments.
     */
    public static void main(String[] args) {
        launch(args);
        System.out.println();
    }
}

