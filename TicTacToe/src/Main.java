import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        final int PLAYER_X=1;
        final int PLAYER_O=2;
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nWhen typing coordinates of field, firstly type horizontal coordinate, then vertical");
        System.out.println("Who starts the game? Player - 1/Computer - 2");
        int start = scanner.nextInt();
        Board b = new Board(3,3,3);
        if (start == 2){
            b.aiMakeMove(b);
            b.printBoard();
        }else {
            b.printBoard();
        }
        int[] cords;
        while(true){
            cords=b.chooseField(PLAYER_X);
            b.placeSign(PLAYER_X,cords[0],cords[1]);
            b.printBoard();
            if(b.isGameOver(PLAYER_X)){
                System.out.println("X won!");
                break;
            }
            if(!b.hasEmptyFields()){
                System.out.println("Tie!");
                break;
            }
            b.aiMakeMove(b);
            b.printBoard();
            if(b.isGameOver(PLAYER_O)){
                System.out.println("O won!");
                break;
            }
            if(!b.hasEmptyFields()){
                System.out.println("Tie!");
                break;
            }
        }
    }
}
