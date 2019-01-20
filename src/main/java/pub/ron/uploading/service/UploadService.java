package pub.ron.uploading.service;

import org.springframework.web.multipart.MultipartFile;
import pub.ron.uploading.dto.UploadResponse;

import java.io.IOException;

/**
 * @author ron
 * 2019.01.20
 * 上传服务
 */
public interface UploadService {

    /**
     * 上传
     * @param multipartFile 文件
     * @param path 根路劲
     * @return 上传后的结果
     * @throws IOException io error
     */
    UploadResponse upload(MultipartFile multipartFile, String path) throws IOException;
}
