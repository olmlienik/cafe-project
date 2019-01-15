package by.mlionik.cafe.util;

/**
 * The type Xss prevention.
 */
public class XssPrevention {
    private static final String XSS_PATTERN = "<(|\\/|[^\\/>][^>]+|\\/[^>][^>]+)>";

    /**
     * Resets scripts from the string.
     *
     * @param string the string
     * @return string without scripts
     */
    public static String resetScripts(String string){
        return string.trim().replaceAll(XSS_PATTERN, "");
    }
}
