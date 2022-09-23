package com.example;

import java.util.*;

/**
 * Output:
 * _________________________________________________________
 * |  | | | |  |  | | | | | |  |  | | | |  |  | | | | | |  |
 * |  | | | |  |  | | | | | |  |  | | | |  |  | | | | | |  |
 * |  |_| |_|  |  |_| |_| |_|  |  |_| |_|  |  |_| |_| |_|  |
 * |   |   |   |   |   |   |   |   |   |   |   |   |   |   |
 * | X |   | X |   | X |   |   |   |   |   |   |   |   |   |
 * |___|___|___|___|___|___|___|___|___|___|___|___|___|___|
 */
public class MusicApp {
    public static void main(String[] args) {
        System.out.println(new Chord(ChordNote.C, ChordQuality.MAJOR));
    }
}

class ChordNote {
    public static String C = "C";
    public static String C_SHARP = "C#";
    public static String D = "D";
    public static String D_SHARP = "D#";
    public static String D_FLAT = "Db";
    public static String E = "E";
    public static String E_FLAT = "Eb";
    public static String F = "F";
    public static String F_SHARP = "F#";
    public static String F_FLAT = "Fb";
    public static String G = "G";
    public static String G_SHARP = "G#";
    public static String G_FLAT = "Gb";
    public static String A = "A";
    public static String A_SHARP = "A#";
    public static String A_FLAT = "Ab";
    public static String B = "B";
    public static String B_SHARP = "B#";
    public static String B_FLAT = "Bb";
}

class ChordQuality {
    public static String MAJOR = "M";
    public static String MINOR = "m";
    public static String SEVENTH = "7";
    public static String SEVENTH_SHARP_5_SHARP_9 = "7#5#9";
    public static String MAJOR_SEVENTH = "maj7";
    public static String MINOR_SEVENTH = "min7";
}

class ChordHelper {
    public static String getkeyboardAscii() {
        return "_________________________________________________________\n" +
                "|  | | | |  |  | | | | | |  |  | | | |  |  | | | | | |  |\n" +
                "|  | | | |  |  | | | | | |  |  | | | |  |  | | | | | |  |\n" +
                "|  |_| |_|  |  |_| |_| |_|  |  |_| |_|  |  |_| |_| |_|  |\n" +
                "|   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n" +
                "|   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n" +
                "|___|___|___|___|___|___|___|___|___|___|___|___|___|___|";
    }

    public static int mapping(int offset) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 292); // C
        map.put(1, 120);
        map.put(2, 296); // D
        map.put(3, 124);
        map.put(4, 300); // E
        map.put(5, 304); // F
        map.put(6, 132);
        map.put(7, 308);  // G
        map.put(8, 136);
        map.put(9, 312);  // A
        map.put(10, 140);
        map.put(11, 316);  // B
        map.put(12, 320);  // C
        map.put(13, 148);
        map.put(14, 324);  // D
        map.put(15, 152);
        map.put(16, 328);  // E
        map.put(17, 332); // F
        map.put(18, 160);
        map.put(19, 336); // G
        map.put(20, 164);
        map.put(21, 340); // A
        map.put(22, 168);
        map.put(23, 344);  // B
        return map.get(offset);
    }

    public static int getNoteMapping(String note) {
        Map<String, Integer> map = new HashMap<>();
        map.put("C", 0); // C
        map.put("C#", 1); //
        map.put("Db", 1); //
        map.put("D", 2); // D
        map.put("D#", 3); //
        map.put("Eb", 3); //
        map.put("E", 4); // E
        map.put("F", 5); // F
        map.put("F#", 6); //
        map.put("Gb", 6); //
        map.put("G", 7); // G
        map.put("G#", 8); //
        map.put("Ab", 8); //
        map.put("A", 9); // A
        map.put("A#", 10); //
        map.put("Bb", 10); //
        map.put("B", 11); // B
        return map.get(note);
    }

    public static List<Integer> getQualityMapping(String quality) {
        Map<String, List<Integer>> map = new HashMap<>();
        map.put("M", Arrays.asList(0, 4, 7));
        map.put("maj7", Arrays.asList(0, 4, 7, 11));
        map.put("7", Arrays.asList(0, 4, 7, 10));
        map.put("7#5#9", Arrays.asList(0, 4, 8, 10, 15));

        map.put("m", Arrays.asList(0, 3, 7));
        map.put("min7", Arrays.asList(0, 3, 7, 10));
        return map.get(quality);
    }
}

class Chord {
    String note;
    String quality;

    public Chord(String note, String quality) {
        this.note = note;
        this.quality = quality;
        System.out.println(drawChord());
    }

    private String drawChord() {
        StringBuilder keyboardAscii = new StringBuilder(ChordHelper.getkeyboardAscii());
        int offset = ChordHelper.getNoteMapping(note);
        List<Integer> notes = ChordHelper.getQualityMapping(quality);
        List<Integer> intervals = new ArrayList<>();
        for (int n : notes)
            intervals.add(n + offset);
        for (int i : intervals)
            keyboardAscii.setCharAt(ChordHelper.mapping(i), 'X');
        return keyboardAscii.toString();
    }
}
