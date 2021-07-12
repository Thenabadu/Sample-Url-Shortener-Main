package com.example.sampleurl_shortener.service;

import com.example.sampleurl_shortener.entity.SUSUrl;
import com.example.sampleurl_shortener.payload.request.SaveShortUrlRequest;

import java.util.Optional;

public interface ISUSUrlService
{
    public Optional<SUSUrl> getActiveURLByKey(String urlKey);
    public SUSUrl save(SaveShortUrlRequest urlRequest);
    public void updateHit(String urlKey);
}
