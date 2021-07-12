package com.example.sampleurl_shortener.controller;

import com.example.sampleurl_shortener.entity.SUSUrl;
import com.example.sampleurl_shortener.service.ISUSUrlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.net.URI;
import java.util.Optional;

@RestController
public class URLMappingController
{
    private static final Logger logger = LoggerFactory.getLogger(URLMappingController.class);

    @Value("${app-allow-cross-origin-domain}")
    private String badRequestRedirectDomain;

    @Autowired
    private ISUSUrlService urlService;

    @GetMapping("/{key}")
    public ResponseEntity<?> redirectTo(@PathVariable String key){
        Optional<SUSUrl> opUrl = urlService.getActiveURLByKey(key);
        if(opUrl.isPresent()){
            SUSUrl url =opUrl.get();
            urlService.updateHit(key);
            return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(url.getUrl())).build();
        }else{
            return ResponseEntity
                    .status(HttpStatus.FOUND).location(URI.create(badRequestRedirectDomain+"error")).build();
        }
    }
}
