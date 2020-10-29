package HardNightGame.DesanCardGamestatistic.testTools;

import com.sun.source.tree.WhileLoopTree;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class UniqueRandom extends Random {
    private Set<Integer> usedInteger = new HashSet<>();

    public Integer NextUniqueInt() {
        Integer newInt;
        do {
            newInt = super.nextInt();
        } while (usedInteger.contains(newInt));
        return newInt;
    }
}
