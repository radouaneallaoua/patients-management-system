package com.example.consultationservice.controller;

import com.example.consultationservice.model.Consultation;
import com.example.consultationservice.model.Doctor;
import com.example.consultationservice.model.Patient;
import com.example.consultationservice.service.ConsultationService;
import com.example.consultationservice.service.HealthProFeignClient;
import com.example.consultationservice.service.PatientFeignClient;
import com.example.consultationservice.service.ServiceIPFS;
import feign.FeignException;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.consultationservice.service.ServiceIPFS.*;

@RestController
@RequestMapping("/consultation")
@CrossOrigin("*")
public class ConsultationController {
    private final ConsultationService consultationService;
    private final HealthProFeignClient healthProFeignClient;
    private final PatientFeignClient patientFeignClient;
    private final ServiceIPFS serviceIPFS=new ServiceIPFS() ;

    public ConsultationController(ConsultationService consultationService,
                                  HealthProFeignClient healthProFeignClient, PatientFeignClient patientFeignClient)
    {
        this.consultationService = consultationService;
        this.healthProFeignClient = healthProFeignClient;
        this.patientFeignClient = patientFeignClient;
    }

    @GetMapping
    public List<Consultation> getById(){
        return consultationService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        Optional<Consultation> co =  consultationService.getById(id);
        if (co.isPresent())
            return ResponseEntity.ok(co.get());
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Consultation consultation) throws Exception {
        consultation = consultationService.create(consultation);
        String jsonFilePath = createJsonFile(consultation.getDiagnostics());
        System.out.println(consultation.getDiagnostics());
        String baseUrl = "http://localhost:8091/upload";
        String hash = uploadJsonFile(baseUrl, jsonFilePath);
        if (hash != null) {
            String downloadUrl = "http://localhost:8091/file/" + hash;
            downloadJsonFile(downloadUrl, "downloaded.json");

            String blockchainUrl = "http://192.168.13.119:8080/assets";
            String assetId = String.valueOf(consultation.getId());
            String owner = consultation.getPatientId();
            String permissions = "doctor1";
            createBlockchainAsset(blockchainUrl, assetId, hash, owner, permissions);
        }
        return ResponseEntity.ok(consultation);
    }



    private void createBlockchainAsset(String url, String id, String hash, String owner, String permissions) throws Exception {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost postRequest = new HttpPost(url);
            List<NameValuePair> formData = new ArrayList<>();
            formData.add(new BasicNameValuePair("id", id));
            formData.add(new BasicNameValuePair("hash", hash)); // Send hash or list of hashes
            formData.add(new BasicNameValuePair("owner", owner));
            formData.add(new BasicNameValuePair("permissions", permissions));

            postRequest.setEntity(new UrlEncodedFormEntity(formData));
            postRequest.setHeader("Content-Type", "application/x-www-form-urlencoded");

            try (CloseableHttpResponse response = httpClient.execute(postRequest)) {
                int statusCode = response.getCode();
                if (statusCode != 200) {
                    throw new RuntimeException("Blockchain API call failed with status: " + statusCode);
                }
                System.out.println("Blockchain asset created successfully");
            }
        }
    }
}
