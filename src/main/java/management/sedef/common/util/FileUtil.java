package management.sedef.common.util;

import lombok.experimental.UtilityClass;
import management.sedef.common.exception.FileReadException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@UtilityClass
public class FileUtil {

    public static boolean isExists(String path) {
        return Files.exists(Path.of(path));
    }

    public static String findContent(String path) {
        try {
            byte[] keyBytes = Files.readAllBytes(Paths.get(path));
            return new String(keyBytes);
        } catch (IOException exception) {
            throw new FileReadException(exception);
        }
    }

}
