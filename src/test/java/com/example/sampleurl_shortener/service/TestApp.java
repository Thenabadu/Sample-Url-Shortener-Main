package com.example.sampleurl_shortener.service;

import org.apache.commons.text.CharacterPredicates;
import org.apache.commons.text.RandomStringGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Testing")
public class TestApp
{
    @Test
    public void shouldAnswerWithTrue()
    {
        RandomStringGenerator generator = new RandomStringGenerator
                .Builder()
                .withinRange('0', 'z')
                .filteredBy(CharacterPredicates.LETTERS, CharacterPredicates.DIGITS)
                .build();

        String generatedString1 = generator.generate(7);

        assertTrue( generatedString1.length()==7 );
    }
}
