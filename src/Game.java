import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Game {
   static TextUI ui = new TextUI();
   static FileIO io = new FileIO();



    private String name;
    private int maxPlayers;
    private ArrayList<Player> players;
    private String currentPlayer;

    public Game(String name, int maxPlayers){
        this.name = name;
        this.maxPlayers = maxPlayers;
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
        displayPlayers();
    }



    public void registerPlayers(){
    int playerNum =  ui.promptNumeric("Tast antal deltagere");
        if(playerNum >= 2 && playerNum <= 6){
            while(playerNum > this.players.size()) {

                String playerName = ui.promptText("Tast spiller navn");
                this.createPlayer(playerName, 0);

            }
        }else{
            ui.displayMessage("Ugyldigt antal, prøv igen");
            registerPlayers();
        }
        Collections.shuffle(players);

    }
public void runGameLoop(){

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

}
