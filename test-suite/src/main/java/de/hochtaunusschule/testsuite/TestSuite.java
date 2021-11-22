package de.hochtaunusschule.testsuite;

import de.hochtaunusschule.testsuite.environment.ContentRoot;
import de.hochtaunusschule.testsuite.test.TestCase;
import java.io.File;
import java.io.IOException;
import java.util.function.Function;

public class TestSuite {
    public static void launch(Function<File, TestCase> factory,
                              String... args) {
        ContentRoot contentRoot = args.length > 0
            ? new ContentRoot(new File(args[0]))
            : ContentRoot.searchRoot();
        new TestSuite(factory, contentRoot).runAll();
    }

    private final Function<File, TestCase> factory;
    private final ContentRoot testRoot;

    public TestSuite(
        Function<File, TestCase> factory,
        ContentRoot testRoot) {
        this.factory = factory;
        this.testRoot = testRoot;
    }

    public void runAll() {
        testRoot.selfOrChildren(file -> {
            System.out.println("Executing test " + file.getName());
            TestCase testCase = factory.apply(file);
            try {
                testCase.execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
