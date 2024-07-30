import java.util.Random;
import java.util.Scanner;

public class MainApp {
    static char[][] map;
    static final char DOT_X = 'X';
    static final char DOT_O = 'O';
    static final char DOT_EMPTY = '.';
    static final int SIZE = 3;

    static Scanner sc = new Scanner(System.in);
    static Random random = new Random();

    public static void main(String[] args) {
        init();
        printMap();

        while (true) {
            humanTurn();
            printMap();
            if (checkWin(DOT_X)) {
                System.out.println("Вы победили");
                break;
            }
            if (isFull()) {
                System.out.println("Ничья");
                break;
            }
            aiTurn();
            printMap();
            if (checkWin(DOT_O)) {
                System.out.println("Компьютер победил");
                break;
            }
            if (isFull()) {
                System.out.println("Ничья");
                break;
            }
        }

    }

    private static void aiTurn() {
        int x, y;
        // пробует победить
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (isCellValid(i, j)) {
                    map[i][j] = DOT_O;
                    if (checkWin(DOT_O)) {
                        return;
                    }
                    map[i][j] = DOT_EMPTY;
                }
            }
        }
        //проверяет победу другого игрока
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (isCellValid(i, j)) {
                    map[i][j] = DOT_X;
                    if (checkWin(DOT_X)) {
                        map[i][j] = DOT_O;
                        return;
                    }
                    map[i][j] = DOT_EMPTY;
                }
            }
        }

        do {
            y = random.nextInt(SIZE);
            x = random.nextInt(SIZE);

        }
        while (!isCellValid(y, x));
        map[y][x] = DOT_O;
    }

    private static boolean checkGorizontalLine(char c) {
        for (int i = 0; i < SIZE; i++) {
            if (map[i][0] == c && map[i][2] == c && map[i][1] == c) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkVerticalLine(char c) {
        for (int i = 0; i < SIZE; i++) {
            if (map[0][i] == c && map[2][i] == c && map[1][i] == c) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkWin(char c) {
        return checkDiagonalLine(c) || checkVerticalLine(c) || checkGorizontalLine(c);
    }

    private static boolean checkDiagonalLine(char c) {
        for (int i = 0; i < SIZE; i++) {
            if (map[0][0] == c && map[1][1] == c && map[2][2] == c) {
                return true;
            }
            if (map[0][2] == c && map[1][1] == c && map[2][0] == c) {
                return true;
            }
        }
        return false;
    }

    private static boolean isFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void humanTurn() {
        int x, y;
        do {
            System.out.println("Введите координаты ");
            y = sc.nextInt() - 1;
            x = sc.nextInt() - 1;

        }
        while (!isCellValid(y, x));
        map[y][x] = DOT_X;
    }

    private static boolean isCellValid(int y, int x) {
        if (y < 0 || x < 0 || y >= SIZE || x >= SIZE) {
            return false;
        }
        return map[y][x] == DOT_EMPTY;
    }

    private static void printMap() {
        System.out.print("   ");
        for (int i = 1; i <= SIZE; i++) {
            System.out.print(i + "  ");

        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.printf("%2d ", i + 1);
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j] + "  ");
            }
            System.out.println();
        }
    }

    private static void init() {
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }


}
