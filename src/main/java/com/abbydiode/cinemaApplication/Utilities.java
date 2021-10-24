package com.abbydiode.cinemaApplication;

public class Utilities {
    /**
     * @param string String to try to parse into an integer
     * @return The corresponding integer, or otherwise null
     */
    public static Integer tryParseInteger(String string) {
        try {
            return Integer.parseInt(string);
        } catch(Exception exception) {
            return null;
        }
    }

    /**
     * @param string String to try parse into a double
     * @return The corresponding double, or otherwise null
     */
    public static Double tryParseDouble(String string) {
        try {
            return Double.parseDouble(string);
        } catch(Exception exception) {
            return null;
        }
    }
}
