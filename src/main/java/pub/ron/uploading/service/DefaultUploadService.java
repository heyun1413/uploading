package pub.ron.uploading.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import pub.ron.uploading.dto.UploadResponse;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@Service
public class DefaultUploadService implements UploadService {

    private static final String DOT = ".";

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultUploadService.class);

    @Override
    public UploadResponse upload(MultipartFile multipartFile, String path) throws IOException {
        Objects.requireNonNull(multipartFile);
        Objects.requireNonNull(path);
        File directory = new File(path);
        if (!directory.exists()) {
            LOGGER.warn("upload path {} not exist", directory.getPath());
            boolean success = directory.createNewFile();
            if (success) {
                LOGGER.warn("upload path {} created", directory.getPath());
            }
        }
        String newFileName = newFileName(multipartFile.getOriginalFilename());
        File file = newFile(directory, newFileName);
        FileCopyUtils.copy(multipartFile.getBytes(), file);
        return new UploadResponse(multipartFile.getOriginalFilename(), newFileName);
    }

    private File newFile(File directory, String newFileName) {
        String filePath = directory.getPath() + File.separator + newFileName;
        return new File(filePath);
    }

    private String newFileName(String originalFileName) {
        String suffix = getSuffix(Objects.requireNonNull(originalFileName));
        if (suffix == null) {
            LOGGER.warn("the file does not contain a suffix", originalFileName);
            return randomName();
        }
        return randomName() + suffix;
    }


    private static String getSuffix(String fileName) {
        if (fileName.contains(DOT)) {
            return fileName.substring(fileName.lastIndexOf(DOT));
        }
        return null;
    }

    private static String randomName() {
        return UUID.randomUUID().toString();
    }
}
