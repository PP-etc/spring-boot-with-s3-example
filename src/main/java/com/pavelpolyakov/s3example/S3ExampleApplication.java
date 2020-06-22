package com.pavelpolyakov.s3example;

import com.amazonaws.services.s3.AmazonS3;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.aws.context.config.annotation.EnableContextInstanceData;
import org.springframework.cloud.aws.core.io.s3.SimpleStorageProtocolResolver;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.WritableResource;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.OutputStream;

@SpringBootApplication
@Slf4j
@EnableContextInstanceData
public class S3ExampleApplication {
    @Autowired
    ResourceLoader resourceLoader;

    public static void main(String[] args) {
        SpringApplication.run(S3ExampleApplication.class, args);
    }

//    @Autowired
//    public void configureResourceLoader(AmazonS3 amazonS3, DefaultResourceLoader resourceLoader) {
//        SimpleStorageProtocolResolver simpleStorageProtocolResolver = new SimpleStorageProtocolResolver(amazonS3);
//        // As we are calling it by hand, we must initialize it properly.
//        simpleStorageProtocolResolver.afterPropertiesSet();
//        resourceLoader.addProtocolResolver(simpleStorageProtocolResolver);
//    }

    @PostConstruct
    @SneakyThrows
    public void init() {
        log.info("We are here");
        File file = ResourceUtils.getFile(
                "classpath:test.txt");
        String s3Url = "s3://pp-s3-test-bucket/test.txt";

        Resource resource = this.resourceLoader.getResource(s3Url);
        WritableResource writableResource = (WritableResource) resource;
        try (OutputStream outputStream = writableResource.getOutputStream()) {
            outputStream.write("test".getBytes());
        }
        log.info("Executed");
    }

}
