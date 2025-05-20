package ch02;

import ch02.service.JaccardSimilarityCalculator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class JaccardTests {
    @Autowired
    JaccardSimilarityCalculator calculator;

    public static Stream<Arguments> texts() {
        return Stream.of(
                Arguments.of(
                        "This is some cool text. More is better but this will do.",
                        "This is some cool text. More is better but this will do.",
                        1.0),
                Arguments.of(
                        "Now is the time for all good men to come to the aid of their country.",
                        "The quick brown fox jumped over the lazy dog's tail.",
                        0.0),
                Arguments.of(
                        "This is some cool text. More is better but this will do.",
                        "This is some cool text. More is better but this might do.",
                        0.7)
                );
    }

    @ParameterizedTest
    @MethodSource("texts")
    public void testTexts(String text1, String text2, double expected) {
        var similarity = calculator.calculateJaccardSimilarity(text1, text2,2);
        assertEquals(expected, similarity, 0.1);
    }
}
