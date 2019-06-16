public class Field {

    private int x;
    private int y;
    private int sign;
    public static final String BLUE_B = "\u001B[44m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String WHITE = "\033[4;97m";  // WHITE
    public static final String WHITE_B = "\033[47m";

    public Field(int x, int y) {
        this.x = x;
        this.y = y;
        if (x == 0 || y == 0) {
            sign = -1;
        } else {
            sign = 0;
        }
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getSign() {
        return sign;
    }
    public void setSign(int sign) {
        this.sign = sign;
    }
    public void printField() {
        if (sign == -1) {
            if (x == 0 && y == 0) {
                System.out.print(WHITE + "   |" + ANSI_RESET);
            } else if (x == 0 && y <= 9) {
                System.out.print(WHITE + BLUE_B + " " + y + " |" + ANSI_RESET);
            } else if (y == 0 && x <= 9) {
                System.out.print(WHITE + BLUE_B + " " + x + " |" + ANSI_RESET);
            } else if (x == 0 && y > 9) {
                System.out.print(WHITE + BLUE_B + " " + y + "|" + ANSI_RESET);
            } else if (y == 0 && x > 9) {
                System.out.print(WHITE + BLUE_B + " " + x + "|" + ANSI_RESET);
            }
        }
        if (sign == 0) {
            System.out.print(WHITE + WHITE_B+ "   |" + ANSI_RESET);
        }
        if (sign == 1) {
            System.out.print(WHITE + WHITE_B+" X |" + ANSI_RESET);
        }
        if (sign == 2) {
            System.out.print(WHITE + WHITE_B+" O |" + ANSI_RESET);
        }
    }
}

