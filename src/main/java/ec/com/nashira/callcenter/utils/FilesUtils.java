package ec.com.nashira.callcenter.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import ec.com.nashira.callcenter.logger.Logger;

public class FilesUtils {
  @Autowired
  private static Logger log;

  private FilesUtils() {

  }

  /**
   * Uploads a file with a unique name
   *
   * @param file - file to be uploaded
   * @param path - path where file will be uploaded
   * @return complete name of file
   * @throws IOException
   */
  public static String uploadFile(MultipartFile file, String path) throws IOException {
    String fileName = UUID.randomUUID().toString().concat(ConstantsUtils.UNDERSCORE_SEPARATOR)
        .concat(file.getOriginalFilename().replace(" ", ""));
    Path filePath = Paths.get(path).resolve(fileName).toAbsolutePath();
    Files.copy(file.getInputStream(), filePath);
    return fileName;
  }

  /**
   * deletes a file
   *
   * @param fileName - file to be deleted
   * @param path - where is the file
   */
  public static void deleteFile(String fileName, String path) {
    if (fileName != null && fileName.length() > 0 && path != null && path.length() > 0) {
      Path pathFile = Paths.get(path).resolve(fileName).toAbsolutePath();
      File file = pathFile.toFile();
      if (!file.exists() || !file.canRead() || !file.delete()) {
        log.writeLog(ConstantsUtils.DELETE_FILE_ERROR_MESSAGE.concat(ConstantsUtils.DASH_SEPARATOR)
            .concat(fileName));
      }
    }
  }

}
