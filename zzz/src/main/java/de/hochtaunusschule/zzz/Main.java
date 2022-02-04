package de.hochtaunusschule.zzz;

import de.hochtaunusschule.testsuite.TestSuite;
import de.hochtaunusschule.testsuite.test.SimpleTest;
import de.hochtaunusschule.testsuite.token.TokenStream;
import org.paukov.combinatorics3.Generator;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main extends SimpleTest<Model> {
    public static void main(String[] args) {
        TestSuite.launch(Main::new);
    }

    protected Main(File testFile) {
        super(testFile);
    }

    @Override
    protected void test(Model test) {
        System.out.println(test.toString());
        Generator.combination(test.getKeys())
                .simple(test.getOriginalKeys())
                .stream().forEach(longs -> {

                    System.out.println();
                });
    }

    @Override
    protected Model convert(TokenStream stream) {
        TokenStream first = stream.splitSpace();
        int totalKeys = first.next().asInt();
        int originalKeys = first.next().asInt();
        int bits = first.next().asInt();
        long[] keys = new long[totalKeys];
        for (int i = 0; i < totalKeys; i++) {
            keys[i] = Long.parseLong(stream.next().asString(), 2);
        }
        return new Model(totalKeys, originalKeys, keys);
    }
}
