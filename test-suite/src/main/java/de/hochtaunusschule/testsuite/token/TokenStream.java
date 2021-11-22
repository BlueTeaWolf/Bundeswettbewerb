package de.hochtaunusschule.testsuite.token;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class TokenStream {
    public static TokenStream fromFile(Path file) throws IOException {
        return fromStream(Files.lines(file).map(Token::new));
    }

    public static TokenStream fromStream(Stream<Token> stream) {
        return new TokenStream(stream.iterator(), new ArrayList<>());
    }

    private final Iterator<Token> iterator;
    private final List<Token> tokens;
    private Token current;
    private int read;

    public TokenStream(Iterator<Token> iterator,
                       List<Token> tokens) {
        this.iterator = iterator;
        this.tokens = tokens;
    }

    public Iterable<Token> left() {
        return () -> iterator;
    }

    private static final Pattern SPACE = Pattern.compile(" ");

    public TokenStream splitSpace() {
        return splitNext(SPACE);
    }

    public TokenStream splitNext(Pattern delimiter) {
        return fromStream(delimiter.splitAsStream(next().asString()).
            map(Token::new));
    }

    public Token next() {
        if (!iterator.hasNext()) {
            return null;
        }
        current = iterator.next();
        read++;
        tokens.add(current);
        return current;
    }

    public Token at(int position) {
        if (read >= position) {
            return tokens.get(position);
        }
        while (read < position) {
            next();
        }
        return current;
    }
}
