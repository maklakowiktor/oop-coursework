package coursework.task3;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

public final class TextFile {
    private TextFile() {
    }

    public static String read(Path path) throws FileReadException {
        try {
            return Files.readString(path);
        } catch (NoSuchFileException e) {
            throw new FileReadException("file not found: " + path, e);
        } catch (AccessDeniedException e) {
            throw new FileReadException("access denied: " + path, e);
        } catch (IOException e) {
            throw new FileReadException("cannot read file: " + path, e);
        }
    }
}
