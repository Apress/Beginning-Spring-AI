package ch02.service;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JaccardSimilarityCalculator {
    public double calculateJaccardSimilarity(String text1, String text2, int ngramSize) {
        // Preprocess texts
        Set<String> set1 = new HashSet<>(preprocessText(text1, ngramSize));
        Set<String> set2 = new HashSet<>(preprocessText(text2, ngramSize));

        // Calculate intersection
        Set<String> intersection = new HashSet<>(set1);
        intersection.retainAll(set2);

        // Calculate union
        Set<String> union = new HashSet<>(set1);
        union.addAll(set2);

        // Calculate Jaccard similarity
        return (double) intersection.size() / union.size();
    }

    private Set<String> preprocessText(String text, int ngramSize) {
        String[] tokens=text.toLowerCase().split("\\W+");
        Set<String> ngrams = new HashSet<>();
        for (int i = 0; i <= tokens.length - ngramSize; i++) {
            StringBuilder ngram = new StringBuilder();
            for (int j = 0; j < ngramSize; j++) {
                ngram.append(tokens[i + j]).append(" ");
            }
            ngrams.add(ngram.toString().trim());
        }
        return ngrams;
    }
}
