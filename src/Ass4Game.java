import Game_Setup.AnimationRunner;
import Game_Setup.GameFlow;
import Interfaces.LevelInformation;
import Levels.Level_1;
import Levels.Level_2;
import Levels.Level_3;
import Levels.Level_4;
import biuoop.GUI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Ass4Game {
    public static void main(String[] args){
        List<LevelInformation> levels = new ArrayList<>();
        if (args.length == 0){
            levels.add(new Level_1());
            levels.add(new Level_2());
            levels.add(new Level_3());
            levels.add(new Level_4());
        }
        else{
            for (String s: args){
                switch (Integer.parseInt(s)){
                    case 1:
                        levels.add(new Level_1());
                        break;
                    case 2:
                        levels.add(new Level_2());
                        break;
                    case 3:
                        levels.add(new Level_3());
                        break;
                    case 4:
                        levels.add(new Level_4());
                        break;
                    default:
                        continue;
                }
            }
        }
        GUI gui = new GUI("Arkanoid", 800, 600);
        GameFlow gameFlow = new GameFlow(new AnimationRunner(gui), gui.getKeyboardSensor());
        gameFlow.runLevels(levels);
        gui.close();
    }
}