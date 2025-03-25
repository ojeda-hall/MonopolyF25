public class Main {

    public static void main(String[] args) {

        Game g = new Game("Matador");
        g.startSession();
        g.runGameLoop();

        g.endSession();
    }
}