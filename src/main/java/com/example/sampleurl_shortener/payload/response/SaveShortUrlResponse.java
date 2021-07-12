package com.example.sampleurl_shortener.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SaveShortUrlResponse
{
    private String message;
    private String shortUrl;
    private String url;
    private String urlExpire;
}
