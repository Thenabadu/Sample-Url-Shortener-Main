package com.example.sampleurl_shortener.dao.repository;

import com.example.sampleurl_shortener.entity.SUSUrl;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ISUSUrlRepository extends CrudRepository <SUSUrl, Integer>
{
    Optional<SUSUrl> findByUrlKeyAndRawActiveStatusAndUrlDeleteStatus(String urlKey, Integer rawActiveStatus, Integer urlDeleteStatus);
    Optional<SUSUrl> findByUrlAndAndRawActiveStatusAndUrlDeleteStatus(String url, Integer rawActiveStatus, Integer urlDeleteStatus);
    Optional<SUSUrl> findByUrl(String url);
    Optional<SUSUrl> findByUrlKey(String urlKey);
    Boolean existsByUrlKey(String urlKey);
    Boolean existsByUrlKeyAndRawActiveStatusAndUrlDeleteStatus(String urlKey, Integer rawActiveStatus, Integer urlDeleteStatus);
}
