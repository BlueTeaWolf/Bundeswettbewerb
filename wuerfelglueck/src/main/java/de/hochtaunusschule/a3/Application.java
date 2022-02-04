package de.hochtaunusschule.a3;

import de.hochtaunusschule.testsuite.TestSuite;
import de.hochtaunusschule.testsuite.test.SimpleTest;
import de.hochtaunusschule.testsuite.token.TokenStream;
import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author David (_Esel)
 */
public class Application extends SimpleTest<List<Dice>> {
    public static void main(String[] args) {
        TestSuite.launch(Application::new, args);
    }

    protected Application(File testFile) {
        super(testFile);
    }

    @Override
    protected void test(List<Dice> test) {
        test.stream().sorted(Comparator.reverseOrder()).forEach(dice -> {
            System.out.println(dice);
        });
    }

    @Override
    protected List<Dice> convert(TokenStream stream) {
        List<Dice> dices = new ArrayList<>();
        int size = stream.next().asInt();
        for (int i = 0; i < size; i++) {
            TokenStream dice = stream.splitSpace();
            int[] sides = new int[dice.next().asInt()];
            for (int j = 0; j < sides.length; j++) {
                sides[j] = dice.next().asInt();
            }
            dices.add(new Dice(sides));
        }
        return dices;
    }
}
