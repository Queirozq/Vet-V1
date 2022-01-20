package com.queiroz.HappyPet.service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.joda.time.Instant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

@Service
@Slf4j
public class S3Service {


    @Autowired
    private AmazonS3 s3Client;

    @Value("${AWS_BUCKET_NAME}")
    private String bucketName;

    public URL uploadFile(MultipartFile file){
        try{
            String originalName = file.getOriginalFilename();
            String extension = FilenameUtils.getExtension(originalName);
            String fileName = Instant.now().toDate().getTime() + "." + extension;

            InputStream is = file.getInputStream();
            String contentType = file.getContentType();
            return uploadFile(is, fileName, contentType);
        }
        catch(IOException e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    private URL uploadFile(InputStream is, String fileName, String contentType) {
        ObjectMetadata meta = new ObjectMetadata();
        meta.setContentType(contentType);
        log.info("Upload Start");
        s3Client.putObject(bucketName, fileName, is, meta);
        log.info("Upload Finish");
        return s3Client.getUrl(bucketName, fileName);
    }
}
