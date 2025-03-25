import java.util.*;

public class TextUI {

    // Attributes
    Scanner sc = new Scanner(System.in);

    // ____________________________________________________________

    public ArrayList<String> promptChoice( ArrayList<String> options, int limit, String msg){

        displayList(options, "");

        ArrayList<String> choices = new ArrayList<>();  //Lave en beholder til at gemme brugerens valg

        while(choices.size() < limit){                  //tjekke om brugeren skal vælge flere drinks

            int choice = promptNumeric(msg);
            choices.add(options.get(choice-1));
        }
        return choices;
    }

    // ____________________________________________________________

    public void displayList(ArrayList<String>list, String msg) {
        for (int i = 0; i < list.size();i++) {
            System.out.println(i+1+". "+list.get(i));
        }
    }

    // ____________________________________________________________

    public int promptNumeric(String msg){
        System.out.println(msg);                      //Stille brugeren et spørgsmål
        String input = sc.nextLine();                 //Give brugere et sted at placere sit svar og vente på svaret
        int numInput = Integer.parseInt(input);       //Konvertere svaret til et tal

        return numInput;
    }

    // ____________________________________________________________

    public void promptWelcome(){
        System.out.println("Welcome to Matador!");
    }

    // ____________________________________________________________

    public String promptText(String msg){
        System.out.println(msg);               //Stille brugeren et spørgsmål
        String input = sc.nextLine();          //Give brugere et sted at placere sit svar og vente på svaret

        return input;
    }

    // ____________________________________________________________

    public boolean promptBinary(String msg){

        // Asks for y / n depending on a message as parameter.
        System.out.println(msg);
        String input = sc.nextLine().toLowerCase();
        String l = "Wrong input.. Please use y / n.";

        if(input.equals("y")){
            return true;
        } else if(input.equals("n")){
            return false;
        }
        // Recursive
        return promptBinary(l);
    }

} // Class end
