package com.example.sampleurl_shortener;

import com.example.sampleurl_shortener.util.RandomStringKeyGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SampleUrlShortenerApplication
{
    @Bean
    public RandomStringKeyGenerator getKeyGenerator(){
        return new RandomStringKeyGenerator();
    }

    public static void main(String[] args)
    {
        SpringApplication.run(SampleUrlShortenerApplication.class, args);
    }

}
