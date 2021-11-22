package de.hochtaunusschule.ueberladen;

import de.hochtaunusschule.testsuite.TestSuite;
import de.hochtaunusschule.testsuite.test.SimpleTest;
import de.hochtaunusschule.testsuite.token.TokenStream;
import java.io.File;

public class Main extends SimpleTest<HotelRegistry> {
    public static void main(String[] args) {
        TestSuite.launch(Main::new, args);
    }

    protected Main(File testFile) {
        super(testFile);
    }

    @Override
    protected void test(HotelRegistry hotelRegistry) {
        PathFind pathFind = new PathFind(hotelRegistry);
        System.out.println("   " + pathFind.bestPath().format());
    }

    @Override
    protected HotelRegistry convert(TokenStream tokenStream) {
        HotelRegistry registry = new HotelRegistry();
        int size = tokenStream.next().asInt();
        int end = tokenStream.next().asInt();
        registry.register(new Hotel(10_000, end));
        for (int i = 0; i < size; i++) {
            TokenStream lineStream = tokenStream.next().splitSpace();
            registry.register(new Hotel(
                lineStream.at(1).asDouble(),
                lineStream.at(0).asInt()));
        }
        return registry;
    }
}
