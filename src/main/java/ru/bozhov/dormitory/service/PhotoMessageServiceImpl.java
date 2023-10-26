package ru.bozhov.dormitory.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

@Service
@Slf4j
public class PhotoMessageServiceImpl implements PhotoMessageService{
    @Override
    public File savePhoto(org.telegram.telegrambots.meta.api.objects.File file, PhotoSize photoSize, String botToken) {

        String resourcePath = getClass().getClassLoader().getResource("upload").getPath();

        File uploadDir = new File(resourcePath);
        log.info("Resource Path: " + resourcePath);
        if (!uploadDir.exists()) {
            boolean created = uploadDir.mkdirs();
            if (!created) {
                log.error("Failed to create directory");
            }
        }

        String originalFileName = file.getFilePath();
        String localFilePath = resourcePath + "/" + originalFileName;
        File localFile = new File(localFilePath);

        URL url = null;
        try {
            url = new URL(file.getFileUrl(botToken));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        try (InputStream in = url.openStream(); OutputStream out = new FileOutputStream(localFile)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        log.info(photoSize.getFileId());
        return localFile;
    }
}
