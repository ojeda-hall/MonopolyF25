public class Player {

    // Attributes
    private String name;
    private int score;

    // ____________________________________________________________
    
    public Player(String name, int score){
        this.name = name;
        this.score = score;
    }

    // ____________________________________________________________

    @Override
    public String toString(){
        return name + ", "+ score;
    }

} // class end
