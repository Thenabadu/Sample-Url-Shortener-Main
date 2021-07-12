package com.example.sampleurl_shortener.api.v01.resource;

import com.example.sampleurl_shortener.entity.SUSUrl;
import com.example.sampleurl_shortener.payload.request.SaveShortUrlRequest;
import com.example.sampleurl_shortener.payload.response.SaveShortUrlResponse;
import com.example.sampleurl_shortener.service.ISUSUrlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v01")
@CrossOrigin(origins = ("${app-allow-cross-origin-domain}"))
public class UrlResource
{
    private static final Logger logger = LoggerFactory.getLogger(UrlResource.class);

    @Autowired
    private ISUSUrlService urlService;

    @Value("${app-short-url-domain}")
    private String shortUrlDomain;


    @PostMapping("/url")
    public ResponseEntity<?> saveUrl(@Valid @RequestBody SaveShortUrlRequest urlRequest){
        SUSUrl url = urlService.save(urlRequest);
        String msg = url.getUrlUpdateStatus()>0 ? "URL has been updated successfully" : "URL has been added successfully";
        String expire = url.getUrlExpireStatus()>0 ? url.getUrlExpireDateTime().toLocalDateTime().toString() : "This URL will not expire";
        return ResponseEntity.ok(
                new SaveShortUrlResponse(
                        msg,
                        shortUrlDomain+url.getUrlKey(),
                        url.getUrl(),
                        expire
                )
        );
    }
}
