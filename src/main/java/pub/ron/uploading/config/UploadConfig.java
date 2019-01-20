package pub.ron.uploading.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.StringUtils;

import javax.servlet.ServletContext;

/**
 * 上传配置
 * @author ron
 * 2019.01.20
 */
@Configuration
@PropertySource("classpath:/upload.properties")
public class UploadConfig {

    /**
     * 上传根路径
     */
    private final String path;

    public UploadConfig(@Value("${upload.path}") String path,
                        ServletContext servletContext) {
        this.path = StringUtils.isEmpty(path) ? servletContext.getRealPath("") : path;
    }


    public String getPath() {
        return path;
    }
}
