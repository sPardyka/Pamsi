import java.util.ArrayList;
import java.util.Scanner;

public class Board {
    private Field[][] board;
    private int boardWidth;
    private int boardHeight;
    private int winCondition;
    public static final int PLAYER_O=2;
    public static final int PLAYER_X=1;
    Scanner scanner = new Scanner(System.in);

    public Board(int boardWidth, int boardHeight, int winCondition){
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        this.winCondition = winCondition;
        board=createBoard(boardWidth+1, boardHeight+1);
    }
    public Field[][] createBoard (int boardWidth, int boardHeight){
        Field[][] board = new Field[boardWidth][boardHeight];
        for(int i =0;i<boardWidth;i++){
            for(int j=0;j<boardHeight;j++){
                Field field = new Field(i,j);
                board[i][j]=field;
            }
        }
        return board;
    }
    public void printBoard(){
        for(int i=0;i<=boardWidth;i++){
            for(int j=0;j<=boardHeight;j++){
                board[i][j].printField();
            }
            System.out.println("");
        }
    }

    private int whoWon(){
        if(isGameOver(PLAYER_X)){
            return 1;
        }
        if(isGameOver(PLAYER_O)){
            return 2;
        }
        if(!hasEmptyFields()){
            return 0;
        }
        return -1;
    }
    private Result findBestMove(Board b, int movingSign){
        int gameResult = b.whoWon();
        if(gameResult==PLAYER_X){
            return new Result(10);
        }
        else if(gameResult==PLAYER_O){
            return new Result(-10);
        }
        else if(gameResult==0){
            return new Result(0);
        }
//        else if(depth==0){
//            return new Result(0);
//        }
        ArrayList<Result> moves = new ArrayList<>();
        for(int i=1;i<=boardWidth;i++){
            for(int j=1;j<=boardHeight;j++){
                if(board[i][j].getSign()==0){
                    Result result= new Result(i,j);
                    if(movingSign==PLAYER_O){
                        b.placeSign(PLAYER_O, i, j);
                        result.score=findBestMove(b,PLAYER_X).score;
                    }
                    else{
                        b.placeSign(PLAYER_X,i,j);
                        result.score=findBestMove(b,PLAYER_O).score;
                    }
                    moves.add(result);
                    b.placeSign(0,i,j);
                }
            }
        }
        int bestMove=0;
        if(movingSign==PLAYER_X){
            int bestScore=Integer.MIN_VALUE;
            for(int i=0;i<moves.size();i++){
                if(moves.get(i).score>bestScore){
                    bestMove=i;
                    bestScore=moves.get(i).score;
                }
            }
        }
        else{
            int bestScore=Integer.MAX_VALUE;
            for(int i=0;i<moves.size();i++){
                if(moves.get(i).score<bestScore){
                    bestMove=i;
                    bestScore=moves.get(i).score;
                }
            }
        }
        return moves.get(bestMove);
    }
    public void aiMakeMove(Board b){
        Result result = b.findBestMove(b,PLAYER_O);
        int[] cords = new int[2];
        cords[0]=result.x;
        cords[1]=result.y;
        System.out.println("O move:\n"+cords[0]+","+cords[1]);
        b.placeSign(PLAYER_O,cords[0],cords[1]);
    }
    public boolean isGameOver(int movingSign){
        if(checkHorizontally(movingSign) || checkVertically(movingSign) || checkDiagonallyLR(movingSign) || checkDiagonallyRL(movingSign)){
            return true;
        }
        return false;
    }
    public boolean hasEmptyFields(){
        for(int i=1;i<=boardWidth;i++){
            for(int j=1;j<=boardHeight;j++){
                if(board[i][j].getSign()==0){
                    return true;
                }
            }
        }
        return false;
    }
    private boolean checkHorizontally(int movingSign){
        int counter;
        for(int i=1;i<=boardWidth;i++){
            counter=0;
            for(int j=1;j<=boardHeight;j++){
                if(board[i][j].getSign()==movingSign){
                    counter++;
                    if(counter==winCondition){
                        return true;
                    }
                }
                else{
                    counter=0;
                }
            }
        }
        return false;
    }
    private boolean checkVertically(int movingSign){
        int counter;
        for(int i=1;i<=boardHeight;i++){
            counter=0;
            for(int j=1;j<=boardWidth;j++){
                if(board[j][i].getSign()==movingSign){
                    counter++;
                    if(counter==winCondition){
                        return true;
                    }
                }
                else{
                    counter=0;
                }
            }
        }
        return false;
    }
    private boolean checkDiagonallyRL(int movingSign){
        int counter=0;
        for(int i=boardWidth;i>=1;i--){
            int b=1;
            counter=0;
            for(int j=i;j<=boardWidth;j++){
                if(board[j][b].getSign()==movingSign){
                    counter++;
                    if(counter==winCondition){
                        return true;
                    }
                }
                else{
                    counter=0;
                }
                if(b==boardHeight){
                    break;
                }
                b++;
            }
        }
        for(int i=2;i<=boardHeight;i++){
            int b=1;
            counter=0;
            for(int j=i;j<=boardHeight;j++){
                if(board[b][j].getSign()==movingSign){
                    counter++;
                    if(counter==winCondition){
                        return true;
                    }
                }
                else{
                    counter=0;
                }
                if(b==boardWidth){
                    break;
                }
                b++;
            }
        }
        return false;
    }
    public boolean checkDiagonallyLR(int movingSign){
        int counter=0;
        for(int i=boardWidth;i>=1;i--){
            int b=boardHeight;
            counter=0;
            for(int j=i;j<=boardWidth;j++){
                if(board[j][b].getSign()==movingSign){
                    counter++;
                    if(counter==winCondition){
                        return true;
                    }
                }
                else{
                    counter=0;
                }
                if(b==2){
                    break;
                }
                b--;
            }
        }
        for(int i=boardWidth;i>=1;i--){
            int b=1;
            counter=0;
            for(int j=i;j>=1;j--){
                if(board[b][j].getSign()==movingSign){
                    counter++;
                    if(counter==winCondition){
                        return true;
                    }
                }
                else{
                    counter=0;
                }
                if(b==boardWidth){
                    break;
                }
                b++;
            }
        }
        return false;
    }
    public int[] chooseField(int movingSide){
        int[] cords = new int[2];
        while(true) {
            if(movingSide==PLAYER_O){
                System.out.println("O turn");
                System.out.println("Choose field to place your token");
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                if(board[x][y].getSign()!= 0){
                    System.out.println("Wrong coordinates, try again...");
                }
                else{
                    cords[0]=x;
                    cords[1]=y;
                    return cords;
                }
            }
            else{
                System.out.println("X turn");
                System.out.println("Choose field to place your token");
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                if(board[x][y].getSign()!= 0){
                    System.out.println("Wrong coordinates, try again...");
                }
                else{
                    cords[0]=x;
                    cords[1]=y;
                    return cords;
                }
            }
        }
    }
    public void placeSign(int movingSide, int x, int y){
        if(movingSide==PLAYER_O){
            board[x][y].setSign(2);
        }
        else if(movingSide==PLAYER_X){
            board[x][y].setSign(1);

        }
        else{
            board[x][y].setSign(0);
        }
    }

}
