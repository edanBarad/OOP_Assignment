package Ass2_Test_Runs;

import Game_Setup.Game;

public class Ass2Game {
    public static void main(String[] args) {
        Game game = Game.getInstance();
        game.initialize();
        game.run();
    }
}