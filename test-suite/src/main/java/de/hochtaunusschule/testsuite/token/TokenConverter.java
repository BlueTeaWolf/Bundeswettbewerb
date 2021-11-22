package de.hochtaunusschule.testsuite.token;

public interface TokenConverter<D> {
    D convert(String input);
}
