import Game_Setup.Game;

public class Ass3Game {
    public static void main(String[] args) {
        Game game = Game.getInstance();
        game.initialize();
        game.run();
    }
}