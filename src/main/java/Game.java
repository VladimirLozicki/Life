import java.io.IOException;
import java.util.Random;
import java.util.jar.JarOutputStream;

import sun.net.www.content.image.gif;

/**
 * @author vladimirlozickiy
 * @version 1.0
 */
public class Game {
    private int i;
    private int j;
    private int area[][];
    private static final int SIZE = 10;
    private int a[] = new int[8];
    private int life = 0;
    private int dead = 0;
 
    Game(int i, int j) {
        this.i = i;
        this.j = j;
        area = new int[i][j];
        initArea(area);
        setInitLocation();
    }

    public Game run() {
        for (int u = 1; u < i - 1; u++){
            for(int o = 1; o < j - 1; o++){
                result(u, o);
               // printArea(getArea());
                try {
                    Thread.sleep(100); 
                } catch (InterruptedException e) {
                }
                //System.out.print("\033[H\033[2J");
            }
        }
        return this;
    }

    /**
     * init area
     */
    private void initArea(int area[][]) {
        for (int k = 0; k < i; k++) {
            for (int p = 0; p < j; p++) {
                area[k][p] = 0;
            }
        }
    }

    /**
     * print area
     */
    public void printArea(int area[][]) {
        for (int k = 0; k < i; k++) {
            for (int p = 0; p < j; p++) {
                System.out.print(area[k][p]);
            }
            System.out.print("\n");
        }
    }

    /**
     * print area
     */
    public void bv(int area[][]) {
        for (int k = 0; k < i; k++) {
            for (int p = 0; p < j; p++) {
                result(k, p);
            }
            System.out.print("\n");
        }
    }


    /**
     * set begin location life
     */
    public void setInitLocation() {
        int count = 0;
        while (count < SIZE) {
            int x = (int) (Math.random() * i);
            int y = (int) (Math.random() * j);

            if (getArea()[x][y] == 0) {
                getArea()[x][y] = 1;
            } else {
                count--;
            }
            count++;
        }
    }

    /**
     * check condition cell
     *
     * @param a
     * @param b
     */
    private void result(int a, int b) {
        getNeighboirs(a, b);
        checkNeighbors();
        int value = getArea()[a][b];
        if (value == 1 && (getLife() == 2 || getLife()==3)) {
            cellBorn(a, b);
         }else if(value == 1 && (getLife() < 2 || getLife() > 3)){
            cellDead(a, b);
        }else if (value == 0 && getLife() == 3) {
                cellBorn(a, b);
        }
    }

    private void checkNeighbors() {
        for (int p : getA()
        ) {
            if (p == 0) {
                dead++;
            } else {
                life++;
            }
        }
    }

    private void getNeighboirs(int i, int j) {
        a[0] = getArea()[i - 1][j - 1];
        a[1] = getArea()[i - 1][j];
        a[2] = getArea()[i - 1][j + 1];
        a[3] = getArea()[i][j + 1];
        a[4] = getArea()[i + 1][j + 1];
        a[5] = getArea()[i + 1][j];
        a[6] = getArea()[i + 1][j - 1];
        a[7] = getArea()[i][j - 1];
    }

    public void cellDead(int i, int j) {
        getArea()[i][j] = 0;
    }

    public void cellBorn(int i, int j) {
        getArea()[i][j] = 1;
    }

    public int[] getA() {
        return a;
    }

    public int getLife() {
        return life;
    }


    public int getDead() {
        return dead;
    }

    public int[][] getArea() {
        return area;
    }

    public static void main(String[] args) throws IOException {
        Game game = new Game(5, 5);
        game.printArea(game.getArea());
        game.run();
        System.out.println();
        game.printArea(game.getArea());
    }

}
