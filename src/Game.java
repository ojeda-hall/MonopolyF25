import java.util.*;


public class Game {
   static TextUI ui = new TextUI();
   static FileIO io = new FileIO();


    private String name;
    private int maxPlayers;
    private static String currentPlayer;
    private ArrayList<Player> players;

    public Game(String name, int maxPlayers){
        this.name = name;
        this.maxPlayers = maxPlayers;
        players = new ArrayList<>();

    }

    public static String getCurrentPlayer() {
        return currentPlayer;
    }

    public void startSession(){
        ArrayList<String> data = io.readData("data/playerData.csv");
        ui.displayMessage("Velkommen til "+ this.name);

        if(!data.isEmpty() && ui.promptBinary("Would you like to continue previous game: Y/N")){
            for(String s : data){
              String[] values =  s.split(",");//  "tess, 0"
                int score = Integer.parseInt(values[1].trim());
               createPlayer(values[0],score);
            }

        }else{

            registerPlayers();
        }
        displayPlayers();
    }



    public void registerPlayers() {

        int playerNumber = ui.promptNumeric("Hvor mange antal spillere er med?: ");
        if (playerNumber > 6 || playerNumber < 2) {
            ui.displayMessage("Indtast venligst et tal mellem 2 og 6");
            registerPlayers();
        } else if (playerNumber < 6 || playerNumber > 2) {
            while (this.players.size() < playerNumber) {
                String playerName = ui.promptText("Tast spiller navn");
                this.createPlayer(playerName, 0);
            }
        } else {
                ui.displayMessage("Der er opstået en fejl.");
                registerPlayers();
            }
        Collections.shuffle(players,new Random());
    }



    private void createPlayer(String name, int score){
        Player p = new Player(name, score);
        players.add(p);
    }
    public void displayPlayers(){
        for(Player p:players){
            System.out.println(p);
        }
    }

    public void endSession() {
        ArrayList<String> playerData = new ArrayList<>();

     //serialiserer player objekterner
        for(Player p: players){

          String s = p.toString();
          playerData.add(s);

      }
        //Test om promptChoice virker
        //ui.displayList(ui.promptChoice(playerData, 3, "vælg en spiller"), "Din spiller liste");
        io.saveData(playerData, "data/playerData.csv", "Name, Score");
    }

    public void runGameLoop(){
        currentPlayer = players.getFirst().getName();
        ui.displayMessage("Den nuværende spiller er: " + currentPlayer);
    }
}
