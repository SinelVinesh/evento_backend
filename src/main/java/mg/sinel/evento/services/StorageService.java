package mg.sinel.evento.services;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class StorageService {
    public static String UPLOAD_DIR = "src/main/resources/static/uploads";

    public String store(MultipartFile file) throws IOException {
        String extension = "." + FilenameUtils.getExtension(file.getOriginalFilename());
        String name = String.valueOf(System.currentTimeMillis()) + extension;
        Path fileNameAndPath = Path.of(UPLOAD_DIR, name);
        Files.write(fileNameAndPath, file.getBytes());
        return name;
    }


}
