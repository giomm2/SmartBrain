package com.smartbrain.giovanny.smartbrain.vowels;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 * Created by ADMIN on 07/07/2015.
 */
public class SlidePuzzle {

    //direcciones de las 4 posibles direcciones de las piezas del rompecabezas
    public static final int DIRECTION_LEFT = 0;
    public static final int DIRECTION_UP = 1;
    public static final int DIRECTION_RIGHT = 2;
    public static final int DIRECTION_DOWN = 3;


    //ubicaciones de las piezas en el eje x y y
    public static final int[] DIRECTION_X = {-1, 0, +1, 0};
    public static final int[] DIRECTION_Y = {0, -1, 0, +1};

    private int[] tiles;
    private int handleLocation;

    private Random random = new Random();
    private int width;
    private int height;

    //el metodo init define la altura y la anchura de la pieza de puzzle acomodandolo despues a una matriz
    public void init(int width, int height) {
        this.width = width;
        this.height = height;
        tiles = new int[width * height];

        for(int i = 0; i < tiles.length; i++)
        {
            tiles[i] = i;
        }

        handleLocation = tiles.length - 1;
    }

    public void setTiles(int[] tiles) {
        this.tiles = tiles;

        for(int i = 0; i < tiles.length; i++)
        {
            if(tiles[i] == tiles.length - 1)
            {
                handleLocation = i;
                break;
            }
        }
    }

    public int[] getTiles() {
        return tiles;
    }
    //getters de las columnas filas altura y ancho de cada pieza del rompecabezas
    public int getColumnAt(int location) {
        return location % width;
    }

    public int getRowAt(int location) {
        return location / width;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    //distancia que hay entre cada pieza en el arreglo
    public int distance() {
        int dist = 0;

        for(int i = 0; i < tiles.length; i++)
        {
            dist += Math.abs(i - tiles[i]);
        }

        return dist;
    }
    //metodo para determinar los posibles movimientos de las piezas
    public int getPossibleMoves() {
        int x = getColumnAt(handleLocation);
        int y = getRowAt(handleLocation);

        boolean left = x > 0;
        boolean right = x < width - 1;
        boolean up = y > 0;
        boolean down = y < height - 1;

        return (left ? 1 << DIRECTION_LEFT : 0) |
                (right ? 1 << DIRECTION_RIGHT : 0) |
                (up ? 1 << DIRECTION_UP : 0) |
                (down ? 1 << DIRECTION_DOWN : 0);
    }

    //este metodo se encarga de que la pieza haga un movimiento aleatorio
    //La clase random genera un numero entero al azar que es retornado por este metodo
    private int pickRandomMove(int exclude) {
        List<Integer> moves = new ArrayList<Integer>(4);
        int possibleMoves = getPossibleMoves() & ~exclude;

        if((possibleMoves & (1 << DIRECTION_LEFT)) > 0)
        {
            moves.add(DIRECTION_LEFT);
        }

        if((possibleMoves & (1 << DIRECTION_UP)) > 0)
        {
            moves.add(DIRECTION_UP);
        }

        if((possibleMoves & (1 << DIRECTION_RIGHT)) > 0)
        {
            moves.add(DIRECTION_RIGHT);
        }

        if((possibleMoves & (1 << DIRECTION_DOWN)) > 0)
        {
            moves.add(DIRECTION_DOWN);
        }

        return moves.get(random.nextInt(moves.size()));
    }

    //metodo para invertir el movimiento hace lo mismo que pickRandomMove pero de manera inversa
    private int invertMove(int move) {
        if(move == 0)
        {
            return 0;
        }

        if(move == 1 << DIRECTION_LEFT)
        {
            return 1 << DIRECTION_RIGHT;
        }

        if(move == 1 << DIRECTION_UP)
        {
            return 1 << DIRECTION_DOWN;
        }

        if(move == 1 << DIRECTION_RIGHT)
        {
            return 1 << DIRECTION_LEFT;
        }

        if(move == 1 << DIRECTION_DOWN)
        {
            return 1 << DIRECTION_UP;
        }

        return 0;
    }
    //recibe 2 enteros para calcular el movimiento
    //retorna un boolean falso o verdadereo.
    public boolean moveTile(int direction, int count) {
        boolean match = false;

        for(int i = 0; i < count; i++)
        {
            int targetLocation = handleLocation + DIRECTION_X[direction] + DIRECTION_Y[direction] * width;
            tiles[handleLocation] = tiles[targetLocation];
            match |= tiles[handleLocation] == handleLocation;
            tiles[targetLocation] = tiles.length - 1; // handle tile
            handleLocation = targetLocation;
        }
        return match;
    }
    //metodo que se encarga de mazclar todas las piezas del rompecabezas
    public void shuffle() {
        if(width < 2 || height < 2)
        {
            return;
        }

        int limit = width * height * Math.max(width, height);
        int move = 0;

        while(distance() < limit)
        {
            move = pickRandomMove(invertMove(move));
            moveTile(move, 1);
        }
    }
    //metodo  que me devuelve la direccion y en el lugar que se mueve
    public int getDirection(int location) {
        int delta = location - handleLocation;

        if(delta % width == 0)
        {
            return delta < 0 ? DIRECTION_UP : DIRECTION_DOWN;
        }
        else if(handleLocation / width == (handleLocation + delta) / width)
        {
            return delta < 0 ? DIRECTION_LEFT : DIRECTION_RIGHT;
        }
        else
        {
            return -1;
        }
    }
    //devuelve el espacio del tablero del rompecabezas

    public int getHandleLocation() {
        return handleLocation;
    }

    public boolean isSolved() {
        for(int i = 0; i < tiles.length; i++)
        {
            if(tiles[i] != i)
            {
                return false;
            }
        }

        return true;
    }


}
