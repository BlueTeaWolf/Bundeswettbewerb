package de.hochtaunusschule.schiebeparkplatz;

import de.hochtaunusschule.testsuite.TestSuite;
import de.hochtaunusschule.testsuite.test.SimpleTest;
import de.hochtaunusschule.testsuite.token.TokenStream;
import java.io.File;

public class Main extends SimpleTest<SchiebeParkpPlatz> {
    public static void main(String[] args) {
        TestSuite.launch(Main::new, args);
    }

    protected Main(File testFile) {
        super(testFile);
    }

    @Override
    protected void test(SchiebeParkpPlatz test) {
        for (ParkSlot slot : test.slots()) {
            System.out.println(slot.getIdentifier() + ": "
                + slot.bestPath().stringRepresentation());
        }
    }

    @Override
    protected SchiebeParkpPlatz convert(TokenStream stream) {
        TokenStream first = stream.splitSpace();
        SchiebeParkpPlatz schiebeParkpPlatz = new SchiebeParkpPlatz(first.next().asChar(),
            first.next().asChar());
        int blocking = stream.next().asInt();
        for (int i = 0; i < blocking; i++) {
            TokenStream line = stream.splitSpace();
            new BlockingCar(schiebeParkpPlatz, line.next().asChar()).atIndex(line.next().asInt());
        }
        return schiebeParkpPlatz;
    }
}

