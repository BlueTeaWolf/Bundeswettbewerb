package de.hochtaunusschule.marktwaage;

import de.hochtaunusschule.testsuite.TestSuite;
import de.hochtaunusschule.testsuite.test.SimpleTest;
import de.hochtaunusschule.testsuite.token.TokenStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Application extends SimpleTest<WaageIndex> {
    public static void main(String[] args) {
        TestSuite.launch(Application::new, args);
    }

    protected Application(File testFile) {
        super(testFile);
    }

    @Override
    protected void test(WaageIndex test) {
        test.generate();
        //int add = new Scanner(System.in).nextInt();
        // Waage waage = test.optimal(add);
        //System.out.println("Beste Kombination: "
        //    + waage.stringRepresentation(add));
    }

    @Override
    protected WaageIndex convert(TokenStream stream) {
        int size = stream.next().asInt();
        List<Integer> wights = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            TokenStream line = stream.splitSpace();
            int wight = line.next().asInt();
            int occurrences = line.next().asInt();
            for (int j = 0; j < occurrences; j++) {
                wights.add(wight);
            }
        }
        return new WaageIndex(wights);
    }
}
