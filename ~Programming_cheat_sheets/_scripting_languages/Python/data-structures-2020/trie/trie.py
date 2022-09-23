# Trie

#------------------------------ Node
class Node:
    def __init__(self):
        self.isWord = False
        self.letters = {}

#------------------------------ Trie
class Trie:
    def __init__(self):
        self.root = Node()

    def add(self, word):
        cn = self.root
        for letter in word:
            if letter in cn.letters:
                cn = cn.letters[letter]
            else:
                cn.letters[letter] = Node()
                cn = cn.letters[letter]
        cn.isWord = True

    def has(self, word):
        cn = self.root
        for letter in word:
            if letter in cn.letters:
                cn = cn.letters[letter]
            else:
                print("INFO::1001 WORD NOT PRESENT: {w}".format(w=word))
                return False

        if cn.isWord == False:
            print("INFO::1001 WORD NOT PRESENT: {w}".format(w=word))
        else:
            print("INFO::1002 WORD IS PRESENT: {w}".format(w=word))
        return cn.isWord

#------------------------------ Main
trie = Trie()

trie.add("brian")
trie.add("ana")
trie.add("brianspinos")
trie.add("anaspinos")

trie.has("brian")
trie.has("ana")
trie.has("brianspinos")
trie.has("anaspinos")

trie.has("xyz")
trie.has("bri")
trie.has("brians")

""" OUTPUT
INFO::1002 WORD IS PRESENT: brian
INFO::1002 WORD IS PRESENT: ana
INFO::1002 WORD IS PRESENT: brianspinos
INFO::1002 WORD IS PRESENT: anaspinos
INFO::1001 WORD NOT PRESENT: xyz
INFO::1001 WORD NOT PRESENT: bri
INFO::1001 WORD NOT PRESENT: brians
"""
