
package io.bsonntag.neddy;

import java.net.URL;
import java.util.Objects;

/**
 * UrlTest
 *
 * @author Benjamim Sonntag <benjamimsonntag@gmail.com>
 * @version 23/mai/2015
 */
public class UrlTest {
    
    private static final String urlString = 
            "http://username:password@example.com:8042/over/there/index.dtb?type=animal&name=narwhal#nose";
    
    private static int count = 0;

    public static void main(String[] args) throws Exception {
        test(new URL(urlString));
        pl(count + " tests passed.");
    }
    
    private static void test(URL url) throws Exception {
        doTests(
            assertEqual("http", url.getProtocol()),
            assertEqual("username:password@example.com:8042", url.getAuthority()),
            assertEqual("username:password", url.getUserInfo()),
            assertEqual("example.com", url.getHost()),
            assertEqual(8042, url.getPort()),
            assertEqual("/over/there/index.dtb?type=animal&name=narwhal", url.getFile()),
            assertEqual("/over/there/index.dtb", url.getPath()),
            assertEqual("type=animal&name=narwhal", url.getQuery()),
            assertEqual("nose", url.getRef())
        );
    }
    
    private static void doTests(Tester... testers) {
        for(Tester t : testers) {
            t.test();
        }
    }
    
    private static Tester assertEqual(String expected, String actual) throws Exception {
        return () -> {
            if(Objects.equals(expected, actual) == false) {
                pl("Expected '" + expected + "', got '" + actual + "'");
            }
            count++;
        };
    }
    
    private static Tester assertEqual(int expected, int actual) {
        return () -> {
            if(expected != actual) {
                pl("Expected '" + expected + "', got '" + actual + "'");
            }
            count++;
        };
    }
    
    private static void pl(String line) {
        System.out.println(line);
    }
    
    @FunctionalInterface
    private static interface Tester {
        public void test();
    }

}
