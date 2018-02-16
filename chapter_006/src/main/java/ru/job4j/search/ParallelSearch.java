package ru.job4j.search;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author Dmitry Belokursky
 * @since 03.02.18.
 */
@ThreadSafe
public class ParallelSearch extends SimpleFileVisitor<Path> {

    private final Path root;

    private final String searchPhrase;

    @GuardedBy("this")
    private List<String> extensions;

    @GuardedBy("this")
    private Queue<Path> files;

    @GuardedBy("this")
    private Queue<Path> result;

    private volatile boolean isOver;

    public ParallelSearch(Path root, String text, List<String> extensions) {
        this.root = root;
        this.searchPhrase = text;
        this.extensions = extensions;
        this.files = new ConcurrentLinkedQueue<>();
        this.result = new ConcurrentLinkedQueue<>();
        this.isOver = false;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        for (String extension : extensions) {
            if (attrs.isRegularFile() && file.toString().endsWith(extension)) {
                files.add(file);
            }
        }
        return FileVisitResult.CONTINUE;
    }

    public void init() throws InterruptedException {
        Thread search = new Thread() {
            @Override
            public void run() {
                ParallelSearch ps = new ParallelSearch(root, searchPhrase, extensions);
                try {
                    Files.walkFileTree(root, ps);
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    files = ps.files;
                    isOver = true;
                }
            }
        };
        Thread read = new Thread() {
            @Override
            public void run() {
                while (!isOver || files.size() != 0) {
                    try {
                        Path currentFile = files.poll();
                        if (currentFile != null) {
                            String content = new String(Files.readAllBytes(currentFile));
                            if (content.contains(searchPhrase)) {
                                result.add(currentFile);
                            }
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        search.start();
        read.start();
        search.join();
        read.join();
    }
}
