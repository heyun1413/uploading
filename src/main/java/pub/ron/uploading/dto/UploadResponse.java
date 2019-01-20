package pub.ron.uploading.dto;

/**
 * @author ron
 * 2019.01.20
 * 上传后的结果
 */
public class UploadResponse {

    /**
     * 原始文件名
     */
    private final String originFileName;

    /**
     * 访问文件名
     */
    private final String accessFileName;

    public UploadResponse(String originFileName, String accessFileName) {
        this.originFileName = originFileName;
        this.accessFileName = accessFileName;
    }

    public String getOriginFileName() {
        return originFileName;
    }

    public String getAccessFileName() {
        return accessFileName;
    }
}
