package com.example;

import java.util.Map;
import java.util.HashMap;

class TrieNode {
    private Map<Character, TrieNode> letters;
    private boolean isWord;

    public TrieNode() {
        letters = new HashMap<>();
    }

    public Map<Character, TrieNode> getLetters() {
        return letters;
    }

    public void setLetters(Map<Character, TrieNode> letters) {
        this.letters = letters;
    }

    public boolean isWord() {
        return isWord;
    }

    public void setWord(boolean word) {
        isWord = word;
    }
}

class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void add(String word) {
        TrieNode curr = root;
        for (char c : word.toCharArray()) {
            TrieNode next = curr.getLetters().get(c);
            if (next == null) {
                TrieNode tn = new TrieNode();
                curr.getLetters().put(c, tn);
                curr = tn;
            } else {
                curr = next;
            }
        }
        curr.setWord(true);
    }

    public boolean has(String word) {
        TrieNode curr = root;

        for (char c : word.toCharArray()) {
            TrieNode next = curr.getLetters().get(c);
            if (next == null) {
                return false;
            } else {
                curr = next;
            }
        }

        return curr.isWord();
    }
}

public class TrieExample {
    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.add("brian");
        trie.add("ana");
        System.out.println("trie.has('brian') = " + trie.has("brian"));
        System.out.println("trie.has('ana') = " + trie.has("ana"));
        System.out.println("trie.has('bri') = " + trie.has("bri"));
        System.out.println("trie.has('an') = " + trie.has("an"));
        System.out.println("trie.has('briana') = " + trie.has("briana"));
        System.out.println("trie.has('anan') = " + trie.has("anan"));
    }
}

/*

trie.has('brian') = true
trie.has('ana') = true

trie.has('bri') = false
trie.has('an') = false
trie.has('briana') = false
trie.has('anan') = false

*/
