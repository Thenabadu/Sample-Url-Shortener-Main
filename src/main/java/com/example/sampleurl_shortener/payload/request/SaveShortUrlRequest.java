package com.example.sampleurl_shortener.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SaveShortUrlRequest
{
    @NotBlank
    @Size(min = 3)
    private String url;

    @NotBlank
    @Size(min = 0, max = 1)
    private Integer activePeriod;

    @NotBlank
    @Size(min = 0, max = 1)
    private Integer expireStatus;
}
