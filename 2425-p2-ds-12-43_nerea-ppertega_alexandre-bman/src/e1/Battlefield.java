package e1;

import e1.village.Village;

import java.util.ArrayList;
import java.util.List;

public class Battlefield {

    public List<String> battle(Village<?> attacker, Village<?> defender) {
        if (attacker == null || defender == null || attacker == defender) {
            throw new IllegalArgumentException();
        }

        List<String> result = new ArrayList<>();
        int atk = attacker.getAtk(), def = defender.getDef();

        result.add("### Starts the battle! --> " + attacker.getName() +
                   " Attacks " + defender.getName() + "! ###\n");
        //Attacker
        result.add(attacker.getName() + " have the following soldiery:\n");
        result.addAll(attacker.getArmyStringList());
        result.add("Total " + attacker.getName() + " attack power: " +
                   atk + "\n");
        result.add("\n");
        // Defender
        result.add(defender.getName() + " have the following soldiery:\n");
        result.addAll(defender.getArmyStringList());
        result.add("Total " + defender.getName() + " defense power: " +
                   def + "\n");
        result.add("\n");
        // Winner
        result.add(whenWins(attacker, defender, atk, def));

        return result;
    }

    private String whenWins(Village<?> attacker, Village<?> defender,
                            int atk, int def) {
        // When wins the battle?
        String winnerMessage;
        if (atk > def) {
            winnerMessage = attacker.getName() + " with an age of " +
                    attacker.getYears() + " years WINS!\n";
        } else if (def > atk) {
            winnerMessage = defender.getName() + " with an age of " +
                    defender.getYears() + " years WINS!\n";
        } else {
            winnerMessage = "It's a DRAW!\n";
        }
        return winnerMessage;
    }
}
