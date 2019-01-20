package pub.ron.uploading.web;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import pub.ron.uploading.config.UploadConfig;
import pub.ron.uploading.dto.UploadResponse;
import pub.ron.uploading.service.UploadService;

import java.io.IOException;

/**
 * @author ron
 * 2019.01.20
 * 上传
 */
@Controller
public class UploadController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UploadController.class);
    private final UploadService uploadService;
    private final UploadConfig uploadConfig;


    @Autowired
    public UploadController(UploadService uploadService,
                            UploadConfig uploadConfig) {
        this.uploadService = uploadService;
        this.uploadConfig = uploadConfig;
    }

    @ApiOperation("文件上传")
    @PostMapping(value = "/upload", consumes = "multipart/*", headers = "content-type=multipart/form-data")
    public ResponseEntity<UploadResponse> upload(@ApiParam(value = "文件", required = true) MultipartFile multipartFile) {
        try {
            UploadResponse response = uploadService.upload(multipartFile, uploadConfig.getPath());
            return ResponseEntity.ok(response);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

}
