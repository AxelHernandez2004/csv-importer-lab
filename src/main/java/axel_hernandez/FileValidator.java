package axel_hernandez;

import java.nio.file.Path;
import java.nio.file.Paths;

public class FileValidator {
    public static Path getSafePath(String fileName) throws Exception {
        String baseDir = "data"; 
        Path basePath = Paths.get(baseDir).toAbsolutePath().normalize();
        Path filePath = basePath.resolve(fileName).normalize();

        if (!filePath.startsWith(basePath)) {
            throw new SecurityException("¡Intento de Path Traversal detectado! No puedes salir de: " + baseDir);
        }

        return filePath;
    }
}
