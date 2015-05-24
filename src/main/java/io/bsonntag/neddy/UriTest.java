
package io.bsonntag.neddy;

import java.net.URI;
import java.util.Objects;

/**
 * UrlTest
 *
 * @author Benjamim Sonntag <benjamimsonntag@gmail.com>
 * @version 23/mai/2015
 */
public class UriTest {
    
    private static final String urlString = 
            "foo://username:password@example.com:8042/over/there/index.dtb?type=animal&name=narwhal#nose";
    
    private static int count = 0;

    public static void main(String[] args) throws Exception {
        test(new URI(urlString));
        pl(count + " tests passed.");
    }
    
    private static void test(URI uri) throws Exception {
        doTests(
            assertEqual("foo", uri.getScheme()),
            assertEqual("username:password@example.com:8042", uri.getAuthority()),
            assertEqual("username:password", uri.getUserInfo()),
            assertEqual("example.com", uri.getHost()),
            assertEqual(8042, uri.getPort()),
            assertEqual("/over/there/index.dtb", uri.getPath()),
            assertEqual("type=animal&name=narwhal", uri.getQuery()),
            assertEqual("nose", uri.getFragment())
        );
    }
    
    private static void doTests(Tester... tests) {
        for(Tester t : tests) {
            t.test();
        }
    }
    
    private static Tester assertEqual(String expected, String actual) {
        return () -> {
            if(Objects.equals(expected, actual) == false) {
                pl("Failed: Expected '" + expected + "', got '" + actual + "'");
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
