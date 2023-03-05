package de.trio.imageshare.web.indexData.IndexController;

import java.util.Random;

public class RandomString {
    private final String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public String RandomString(int length) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(LETTERS.length());
            char randomChar = LETTERS.charAt(index);
            sb.append(randomChar);
        }
        return sb.toString();
    }
}
