import java.util.ArrayList;
import java.util.Collections;

public class Game {
    static TextUI ui = new TextUI();
    static FileIO io = new FileIO();
    private String name;
    private int maxPlayers;
    private ArrayList<Player> players;
    private Player currentPlayer;

    public Game(String name){
        this.name = name;
        players = new ArrayList<>();

    }

    public void startSession(){
        ArrayList<String> data = io.readData("data/playerData.csv");
        ui.displayMessage("Velkommen til "+ this.name);

        if(!data.isEmpty() && ui.promptBinary("would your like to continue previous game: Y/N")){
            for(String s : data){
              String[] values =  s.split(",");//  "tess, 0"
                int score = Integer.parseInt(values[1].trim());
               createPlayer(values[0],score);
            }
        }else{
            registerPlayers();
        }
        Collections.shuffle(players); // Randomise order of players
        displayPlayers();
    }


    public void registerPlayers(){
        this.maxPlayers = ui.promptNumeric("Hvor mange spillere?: ");

        while (maxPlayers < 2 || maxPlayers > 6){
            ui.displayMessage("Antallet af spillere skal være mellem 2-6, prøv igen");
            maxPlayers = ui.promptNumeric("Hvor mange spillere?: ");
        }

        while(this.players.size() < maxPlayers) {
            String playerName = ui.promptText("Tast spiller navn");
            this.createPlayer(playerName, 0);
        }
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
        currentPlayer = players.getFirst();
        ui.displayMessage("Det er " + currentPlayer.getName() + "'s tur");
    }
}
