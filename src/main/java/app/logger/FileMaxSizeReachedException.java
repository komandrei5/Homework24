package app.logger;
public class FileMaxSizeReachedException extends RuntimeException {
    public FileMaxSizeReachedException(String fileSizeLimitReached, Integer maxFileSize, long length, String absolutePath) {
        super(fileSizeLimitReached);
    }
}
