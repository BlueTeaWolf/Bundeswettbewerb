package de.hochtaunusschule.testsuite.token;

import java.util.regex.Pattern;

public class Token {
    private final String asString;

    public Token(String asString) {
        this.asString = asString;
    }

    private static final Pattern SPACE = Pattern.compile(" ");

    public TokenStream splitSpace() {
        return splitNext(SPACE);
    }

    public TokenStream splitNext(Pattern delimiter) {
        return TokenStream.fromStream(delimiter.splitAsStream(asString).
            map(Token::new));
    }

    public <D> D convertWith(TokenConverter<D> converter) {
        return converter.convert(asString());
    }

    public int asInt() {
        return Integer.parseInt(asString);
    }

    public long asLong() {
        return Long.parseLong(asString);
    }

    public String asString() {
        return asString;
    }

    public double asDouble() {
        return Double.parseDouble(asString);
    }

    @Override
    public String toString() {
        return "Token{" +
            "" + asString +
            '}';
    }

    public char asChar() {
        return asString.charAt(0);
    }
}
