package com.aiyyatti.spring.wordtransformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.*;

@SpringBootApplication
public class WordTransformerApplication {
    public static void main(String[] args) {
        SpringApplication.run(WordTransformerApplication.class, args);
    }

    @Autowired
    TransfromerService service;

    @Bean
    public List<String> wordList() {
        LinkedList<String> list = new LinkedList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("wordList.txt")))) {
            String word = null;
            while (null != (word = reader.readLine())) list.add(word);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}

@RestController
class WordTransformerController {
    @Autowired
    TransfromerService service;

    @RequestMapping(value = "/transform/from/{from}/to/{to}", produces = "application/json", method = RequestMethod.GET)
    public List<String> pathTo(@PathVariable String from, @PathVariable String to) {
        return service.pathTo(from.toUpperCase(), to.toUpperCase());
    }
}

@Component
class TransfromerService {
    @Autowired
    List<String> wordList;

    public List<String> pathTo(String from, String to) {
        WordsUtil util = new WordsUtil(wordList);
        TransformContainer left = new TransformContainer(util, from);
        TransformContainer right = new TransformContainer(util, to);
        while (true) {
            List<String> connection = pathTo(left, right);
            if (connection != null) return connection;
            connection = pathTo(right, left);
            if (connection != null) return connection;
        }
    }

    private List<String> pathTo(TransformContainer containerLeft, TransformContainer containerRight) {
        WordPath leftPath = containerLeft.visitOne();
        if (leftPath == null) {
            return Collections.EMPTY_LIST;
        }
        return pathTo(containerRight, leftPath);
    }

    public List<String> pathTo(TransformContainer container, WordPath path) {
        WordPath wordPath = container.contains(path.word);
        if (wordPath != null) {
            List<String> pathString = new LinkedList<>();
            path = path.from;
            while (wordPath != null) {
                pathString.add(wordPath.word);
                wordPath = wordPath.from;
            }
            while (path != null) {
                pathString.add(0, path.word);
                path = path.from;
            }
            return pathString;
        } else return null;
    }
}

class WordsUtil {
    Map<String, LinkedList<String>> wildConnections;

    public WordsUtil(List<String> words) {
        wildConnections = wildCardConnections(words);
    }

    private Map<String, LinkedList<String>> wildCardConnections(@Autowired List<String> words) {
        Map<String, LinkedList<String>> result = new HashMap<>();
        words.forEach(word -> {
            toWildCard(word).forEach(wildKey -> {
                LinkedList<String> values = result.getOrDefault(wildKey, new LinkedList<String>());
                values.add(word);
                result.put(wildKey, values);
            });
        });
        return result;
    }

    public List<String> toWildCard(String word) {
        int N = word.length();
        String[] result = new String[N];
        for (int i = 0; i < N; i++) result[i] = word.substring(0, i) + "*" + word.substring(i + 1);
        return Arrays.asList(result);
    }

    public LinkedList<String> connectingWords(String word) {
        LinkedList<String> words = new LinkedList<>();
        toWildCard(word).stream().forEach(wildcard -> {
            words.addAll(wildConnections.get(wildcard));
        });
        return words;
    }
}

class TransformContainer {
    private HashMap<String, WordPath> visited = new HashMap<>();
    private Queue<WordPath> toVisit = new LinkedList<>();
    WordsUtil util;

    public TransformContainer(WordsUtil util, String starting) {
        this.util = util;
        toVisit.add(new WordPath(starting, null));
    }

    public WordPath visitOne() {
        if (toVisit.isEmpty()) return null;
        WordPath path = toVisit.poll();
        String pathWord = path.word;
        visited.put(pathWord, path);
        util.connectingWords(pathWord).stream().forEach(word -> {
            if (!visited.containsKey(word)) toVisit.add(new WordPath(word, path));
        });
        return path;
    }

    public WordPath contains(String word) {
        return visited.get(word);
    }
}

class WordPath {
    String word;
    WordPath from;

    public WordPath(String word, WordPath from) {
        this.word = word;
        this.from = from;
    }

    @Override
    public String toString() {
        return word + " " + (from != null ? from : " ");
    }
}