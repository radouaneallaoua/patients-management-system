package com.example.consultationservice.service;
import com.example.consultationservice.model.Diagnostic;
import com.example.consultationservice.model.Treatment;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.entity.mime.MultipartEntityBuilder;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

public class ServiceIPFS {

    public static void main(String[] args) {
        Treatment treatment=Treatment.builder().state("completed2002").planDetails("test").build();
        Diagnostic diagnostic=Diagnostic.builder().symptoms("mouatamid").notes("hankach").condition("allaaaaaaaaaaaaaaaaaaaoua").condition("good").treatment(treatment).build();
        String jsonFilePath = createJsonFile(diagnostic);

        String baseUrl = "http://localhost:8091/upload";
        String hash = uploadJsonFile(baseUrl, jsonFilePath);
        if (hash != null) {
            String downloadUrl = "http://localhost:8091/file/" + hash;
            downloadJsonFile(downloadUrl, "downloaded.json");
        }
    }

    public static String createJsonFile(Diagnostic diagnostic) {
        ObjectMapper mapper = new ObjectMapper();
        String filePath = "data.json";
        try {
            Data data = new Data("BOURRICH kiswlni", 30, true, new Address("123 Main St", "Anytown", "12345"));

            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath), diagnostic);
            System.out.println("JSON file created at: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filePath;
    }

    public static String uploadJsonFile(String url, String filePath) {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost post = new HttpPost(url);

            HttpEntity entity = MultipartEntityBuilder.create()
                    .addBinaryBody("file", new File(filePath))
                    .build();
            post.setEntity(entity);

            CloseableHttpResponse response = client.execute(post);
            if (response.getCode() == 200) {
                String hash = new String(response.getEntity().getContent().readAllBytes());
                System.out.println("Uploaded successfully. Hash: " + hash);
                return hash;
            } else {
                System.out.println("Failed to upload file. Response Code: " + response.getCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void downloadJsonFile(String url, String outputFileName) {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpGet get = new HttpGet(url);

            CloseableHttpResponse response = client.execute(get);
            if (response.getCode() == 200) {
                byte[] fileBytes = response.getEntity().getContent().readAllBytes();
                try (FileOutputStream fos = new FileOutputStream(outputFileName)) {
                    fos.write(fileBytes);
                    System.out.println("File downloaded successfully as: " + outputFileName);
                }
            } else {
                System.out.println("Failed to download file. Response Code: " + response.getCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static class Data {
        public String name;
        public int age;
        public boolean isDeveloper;
        public Address address;

        public Data(String name, int age, boolean isDeveloper, Address address) {
            this.name = name;
            this.age = age;
            this.isDeveloper = isDeveloper;
            this.address = address;
        }
    }

    static class Address {
        public String street;
        public String city;
        public String zipcode;

        public Address(String street, String city, String zipcode) {
            this.street = street;
            this.city = city;
            this.zipcode = zipcode;
        }
    }
}
