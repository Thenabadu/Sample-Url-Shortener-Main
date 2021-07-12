package com.example.sampleurl_shortener.service.impl;

import com.example.sampleurl_shortener.dao.repository.ISUSUrlRepository;
import com.example.sampleurl_shortener.entity.SUSUrl;
import com.example.sampleurl_shortener.payload.request.SaveShortUrlRequest;
import com.example.sampleurl_shortener.util.RandomStringKeyGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.OngoingStubbing;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class SUSUrlServiceImplTest {

    @InjectMocks
    private SUSUrlServiceImpl susUrlService;

    @Mock
    private RandomStringKeyGenerator keyGenerator;

    @Mock
    private ISUSUrlRepository urlRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getActiveURLByKey(){
        String urlKey = "ABCDEFG";
        SUSUrl url = new SUSUrl();
        url.setUrlKey(urlKey);
        url.setRawActiveStatus(1);
        url.setUrlDeleteStatus(0);
        Mockito.when(urlRepository.findByUrlKeyAndRawActiveStatusAndUrlDeleteStatus(urlKey,1,0))
                .thenReturn(java.util.Optional.of(url));

        Optional<SUSUrl> foundUrl = susUrlService.getActiveURLByKey(urlKey);
        assertTrue(foundUrl.isPresent());
    }

    @Test
    void save() {
        SaveShortUrlRequest saveShortUrlRequest = new SaveShortUrlRequest();
        saveShortUrlRequest.setUrl("https://www.google.com/");
        saveShortUrlRequest.setActivePeriod(1);
        saveShortUrlRequest.setExpireStatus(0);

        SUSUrl url1 = new SUSUrl();
        url1.setUrlUpdateStatus(1);

        SUSUrl url2 = new SUSUrl();
        url2.setUrlUpdateStatus(1);
        url2.setUrlDeleteStatus(0);
        url2.setUrlExpireStatus(0);
        url2.setUrl(saveShortUrlRequest.getUrl());
        url2.setUrlKey("ABCDEFG");

        Mockito.when(urlRepository.findByUrl(saveShortUrlRequest.getUrl()))
                .thenReturn(java.util.Optional.of(url1));

        Mockito.when(urlRepository.save(url2))
                .thenReturn(url2);

        when(urlRepository.save(any(SUSUrl.class))).thenReturn(url2);

        SUSUrl foundUrl = susUrlService.save(saveShortUrlRequest);
        assertEquals(url2,foundUrl);
    }

    @Test
    void updateHit() {

    }
}