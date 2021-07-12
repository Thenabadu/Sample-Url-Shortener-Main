package com.example.sampleurl_shortener.service.impl;

import com.example.sampleurl_shortener.dao.repository.ISUSUrlRepository;
import com.example.sampleurl_shortener.entity.SUSUrl;
import com.example.sampleurl_shortener.payload.request.SaveShortUrlRequest;
import com.example.sampleurl_shortener.service.ISUSUrlService;
import com.example.sampleurl_shortener.util.RandomStringKeyGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional
public class SUSUrlServiceImpl implements ISUSUrlService
{
    private final ISUSUrlRepository urlRepository;
    private final RandomStringKeyGenerator keyGenerator;

    @Autowired
    public SUSUrlServiceImpl(ISUSUrlRepository urlRepository,
                             RandomStringKeyGenerator keyGenerator)
    {
        this.urlRepository = urlRepository;
        this.keyGenerator = keyGenerator;
    }

    @Override
    public Optional<SUSUrl> getActiveURLByKey(String urlKey)
    {
        return urlRepository.findByUrlKeyAndRawActiveStatusAndUrlDeleteStatus(urlKey,1,0);
    }

    @Override
    public SUSUrl save(SaveShortUrlRequest urlRequest)
    {
        Optional<SUSUrl> opUrl=urlRepository.findByUrl(urlRequest.getUrl());
        SUSUrl url = new SUSUrl();
        LocalDateTime now = LocalDateTime.now();
        if(opUrl.isPresent()){
            url = opUrl.get();
            url.setUrlUpdateStatus(url.getUrlUpdateStatus()+1);
            url.setUrlDeleteStatus(0);
            url.setUrlExpireStatus(urlRequest.getExpireStatus());
            if(urlRequest.getExpireStatus()!=0){
                url.setUrlExpireDateTime(Timestamp.valueOf(now.plusDays(urlRequest.getActivePeriod())));
            }
            url.setRawLastUpdateDateTime(Timestamp.valueOf(now));
            url.setRawActiveStatus(1);

        }
        else
        {

            String key = keyGenerator.get8CharKey();
            while(urlRepository.existsByUrlKey(key)){
                key = keyGenerator.get8CharKey();
            }
            url.setUrlKey(key);
            url.setUrl(urlRequest.getUrl());
            url.setUrlUpdateStatus(0);
            url.setUrlDeleteStatus(0);
            url.setUrlExpireStatus(urlRequest.getExpireStatus());
            if(urlRequest.getExpireStatus()!=0){
                url.setUrlExpireDateTime(Timestamp.valueOf(now.plusDays(urlRequest.getActivePeriod())));
            }
            url.setRawCreateDateTime(Timestamp.valueOf(now));
            url.setRawLastUpdateDateTime(Timestamp.valueOf(now));
            url.setRawActiveStatus(1);
            url.setUrlHit(0);
        }
        return urlRepository.save(url);
    }

    @Override
    public void updateHit(String urlKey)
    {
        Optional<SUSUrl> opUrl=urlRepository.findByUrlKey(urlKey);
        if(opUrl.isPresent()){
            SUSUrl url = opUrl.get();
            url.setUrlHit(url.getUrlHit()+1);
            urlRepository.save(url);
        }
    }
}
