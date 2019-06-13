public class Result {

    public int score;
    public int x;
    public int y;

    public Result(){}
    public Result(Field field, int score){
        this.score=score;
        this.x=field.getX();
        this.y=field.getY();
    }
    public Result(int x, int y, int score){
        this.score=score;
        this.x=x;
        this.y=y;
    }
    public Result(int score){
        this.score=score;
    }
    public Result(int x, int y){
        this.x=x;
        this.y=y;
    }
}
