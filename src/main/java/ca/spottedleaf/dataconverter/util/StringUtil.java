package ca.spottedleaf.dataconverter.util;

public class StringUtil {

    // https://github.com/apache/commons-lang/blob/master/src/main/java/org/apache/commons/lang3/StringUtils.java
    public static int ordinalIndexOf(final String str, final String searchStr, final int ordinal, final boolean lastIndex) {
        if (str == null || searchStr == null || ordinal <= 0) {
            return -1;
        }
        if (searchStr.length() == 0) {
            return lastIndex ? str.length() : 0;
        }
        int found = 0;
        // set the initial index beyond the end of the string
        // this is to allow for the initial index decrement/increment
        int index = lastIndex ? str.length() : -1;
        do {
            if (lastIndex) {
                index = str.indexOf(searchStr, index - 1); // step backwards through string
            } else {
                index = str.indexOf(searchStr, index + 1); // step forwards through string
            }
            if (index < 0) {
                return index;
            }
            found++;
        } while (found < ordinal);
        return index;
    }

}
