/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gauee.wishlist.utils;

import junit.framework.TestCase;

/**
 *
 * @author gauee
 */
public class HttpActionTest extends TestCase {
    
    public HttpActionTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    public void testValues() {
    }

    public void testValueOf() {
        assertEquals("GET", HttpAction.GET.toString());
        assertEquals("POST", HttpAction.POST.toString());
    }
    
    
}
