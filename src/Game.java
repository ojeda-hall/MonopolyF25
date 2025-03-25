import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Game {
   static TextUI ui = new TextUI();
   static FileIO io = new FileIO();



    private String name;
    private int maxPlayers ;
    private ArrayList<Player> players;
    private Player currentPlayer;

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

        int totalPlayers = 0;

        try {
            totalPlayers = ui.promptNumeric("Number of players:");       //Konvertere svaret til et tal
        } catch (NumberFormatException e) {
            ui.displayMessage("Wrong input.. Please try again.");
            registerPlayers();
        }


    if(totalPlayers > this.maxPlayers || totalPlayers < 0){
        System.out.println("Your input number was higher than the allowed " + this.maxPlayers + " players");
        registerPlayers();
    }

     while(this.players.size() < totalPlayers) {

        String playerName = ui.promptText("Tast spiller navn");
        this.createPlayer(playerName, 0);
     }

     Collections.shuffle(this.players);
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

    public String getPlayerName(){
        return currentPlayer.getName();
    }

    public void runGameLoop(){
        currentPlayer=players.get(0);
        ui.displayMessage("It's this players turn to pick: "+currentPlayer.getName());

    }

}
