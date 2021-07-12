package com.example.sampleurl_shortener.util;

import org.apache.commons.text.CharacterPredicates;
import org.apache.commons.text.RandomStringGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RandomStringKeyGenerator
{
    private static final Logger logger = LoggerFactory.getLogger(RandomStringKeyGenerator.class);

    public String get8CharKey(){
        RandomStringGenerator generator = new RandomStringGenerator
                .Builder()
                .withinRange('0', 'z')
                .filteredBy(CharacterPredicates.LETTERS, CharacterPredicates.DIGITS)
                .build();
        return generator.generate(7);
    }
}
