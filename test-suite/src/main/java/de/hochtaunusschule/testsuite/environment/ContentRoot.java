package de.hochtaunusschule.testsuite.environment;

import java.io.File;
import java.util.Objects;
import java.util.function.Consumer;

public class ContentRoot {
    public static ContentRoot searchRoot() {
        File file = new File(".");
        File candidate;
        while (!(candidate = new File(file, "testresources")).exists()) {
            System.out.println(candidate.getAbsolutePath());
            file = file.getParentFile();
            if (file == null) {
                throw new IllegalArgumentException("Failed to detect root");
            }
        }
        return new ContentRoot(candidate);
    }

    private final File root;

    public ContentRoot(File root) {
        this.root = root;
    }

    public void selfOrChildren(Consumer<File> consumer) {
        if (!root.isDirectory()) {
            consumer.accept(root);
            return;
        }
        File[] children = root.listFiles();
        for (File file : Objects.requireNonNull(children, "children")) {
            consumer.accept(file);
        }
    }
}
