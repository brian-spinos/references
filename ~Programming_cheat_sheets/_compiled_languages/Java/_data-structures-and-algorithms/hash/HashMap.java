package com.example;

/**
 * TODO: add remove("key") method
 *     - increase/decrease hashMap
 */
class Entry {
    private String key;
    private String value;
    private Entry next;

    public Entry(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Entry getNext() {
        return next;
    }

    public void setNext(Entry next) {
        this.next = next;
    }
}

class HashMap {
    private Entry[] list;

    public HashMap(){
        list = new Entry[16]; // default size
    }

    public void put(String key, String value) {
        Entry newEntry = new Entry(key, value);
        int hashCode = getHash(key);
        int index = hashCode % list.length;

        if (list[index] == null) {
            // System.out.println("No collision");
            list[index] = newEntry;
            return;
        }

        //System.out.println("Found collision for key: " + key + " -> using chaining");
        Entry currEntry = list[index];
        Entry prevEntry = currEntry;

        while (currEntry != null) {
            if (currEntry.getKey().equals(key)) {
                //System.out.println("Found existing key: " + key + " -> updating");
                currEntry.setValue(value);
                return; // we are done!
            }
            prevEntry = currEntry;
            currEntry = prevEntry.getNext();
        }

        prevEntry.setNext(newEntry);
    }

    public String get(String key) {
        int hashCode = getHash(key);
        int index = hashCode % list.length;
        Entry currEntry = list[index];

        while (currEntry != null) {
            if (currEntry.getKey().equals(key)) {
                return currEntry.getValue();
            }
            currEntry = currEntry.getNext();
        }

        return null; // Not found
    }

    private int getHash(String key) {
        int hashValue = 0;

        for(char c : key.toCharArray()){
            hashValue += c;
            hashValue *= 31;
        }

        // hashValue could be negative
        if (hashValue < 0) return hashValue * -1;
        return hashValue;
    }
}

public class HashMapExample {
    public static void main(String[] args) {
        HashMap hash = new HashMap();

        hash.put("k1", "v1");
        hash.put("k2", "v2");
        hash.put("k3", "v3");
        System.out.println(hash.get("k1"));
        System.out.println(hash.get("k2"));
        System.out.println(hash.get("k3"));

        hash.put("k1", "v1-updated");
        hash.put("k2", "v2-updated");
        hash.put("k3", "v3-updated");
        System.out.println(hash.get("k1"));
        System.out.println(hash.get("k2"));
        System.out.println(hash.get("k3"));

        hash.put("asdf", "aaa");
        hash.put("gfs", "bbb");
        System.out.println(hash.get("asdf"));
        System.out.println(hash.get("gfs"));

        hash.put("asdf", "aaa-updated");
        hash.put("gfs", "bbb-updated");
        System.out.println(hash.get("asdf"));
        System.out.println(hash.get("gfs"));
    }
}

/* OUTPUT

v1
v2
v3
v1-updated
v2-updated
v3-updated
aaa
bbb
aaa-updated
bbb-updated

*/
