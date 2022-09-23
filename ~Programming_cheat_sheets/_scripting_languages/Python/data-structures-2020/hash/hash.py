# HashMap

#------------------------------ Entry
class Entry:
    def __init__(self, key, value):
        self.key=key
        self.value=value
        self.next=None

    def __str__(self):
        return "Entry(key={key}, value={value})".format(key=self.key, value=self.value)

#------------------------------ HashMap
class HashMap:
    def __init__(self, capacity):
        self.list = [None] * capacity
        self.capacity=capacity

    def put(self, key, value):
        hash  = self.__getHash(key)
        idx = hash % self.capacity
        newEntry = Entry(key, value)

        if self.list[idx] == None:
            self.list[idx] = newEntry
            return

        currentEntry = self.list[idx]
        prevEntry = None
        while currentEntry != None:
            # update case:
            if currentEntry.key == key:
                currentEntry.value = value
                return
            prevEntry = currentEntry
            print(prevEntry)
            currentEntry = currentEntry.next
            print(currentEntry)
        prevEntry.next = newEntry

    def get(self, key):
        hash  = self.__getHash(key)
        idx = hash % self.capacity

        currentEntry = self.list[idx]

        while currentEntry != None:
            if currentEntry.key == key:
                print("GOT: " + str(currentEntry))
                return currentEntry.value
            currentEntry = currentEntry.next
        return None

    def show(self):
        idx=0
        print("HashMap(list=[")
        for entry in self.list:
            print("  [" + str(idx) + "]  "),
            idx += 1
            currentEntry = entry
            print(str(currentEntry) + " -> ")
            if currentEntry != None and currentEntry.next != None:
                while currentEntry.next != None:
                    print("        " + str(currentEntry) + " -> ")
                    currentEntry = currentEntry.next
        print("])")

    def __getHash(self, key):
        hash = 0
        for letter in key:
            hash *= 31
            hash += ord(letter)
        return hash

#------------------------------ Main
hash = HashMap(20)

hash.put("name", "brian")
hash.put("name", "spinos")
hash.put("a", "ABC")
hash.put("address", "123 foo bar")
hash.put("phone", "555-555-5555")

hash.get("name")
hash.get("a")
hash.get("address")
hash.get("phone")

hash.show()

'''OUTPUT

HashMap(list=[
  [0]   None ->
  [1]   None ->
  [2]   None ->
  [3]   None ->
  [4]   None ->
  [5]   None ->
  [6]   None ->
  [7]   Entry(key=name, value=spinos) ->
  [8]   None ->
  [9]   None ->
  [10]   None ->
  [11]   None ->
  [12]   Entry(key=address, value=123 foo bar) ->
  [13]   None ->
  [14]   None ->
  [15]   None ->
  [16]   None ->
  [17]   Entry(key=a, value=ABC) ->
  [18]   Entry(key=phone, value=555-555-5555) ->
  [19]   None ->
])

'''
