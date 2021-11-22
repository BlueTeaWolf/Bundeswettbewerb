package de.hochtaunusschule.testsuite.test;

import de.hochtaunusschule.testsuite.token.TokenStream;
import java.io.File;
import java.io.IOException;

public abstract class SimpleTest<D> implements TestCase {
    private final File testFile;

    protected SimpleTest(File testFile) {
        this.testFile = testFile;
    }

    protected abstract void test(D test);
    protected abstract D convert(TokenStream stream);

    @Override
    public void execute() throws IOException {
        test(convert(TokenStream.fromFile(testFile.toPath())));
    }
}
