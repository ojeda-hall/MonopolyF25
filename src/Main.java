/*
* Vi startede dette program op for at lære om composition: Vi lavede et simpelt klassediagram med to klasser der viser at
* Game har en eller flere Players.
* Spillere bliver oprettet ved kald til registerPlayers, som starter en dialog med brugeren
* todo: integrer TextUI
* todo: Byg FileIO
* */

public class Main {

    public static void main(String[] args) {

        Game g = new Game("Matador",6);
        g.startSession();
        g.runGameLoop();

        // run the game loop


        g.endSession();
    }
}