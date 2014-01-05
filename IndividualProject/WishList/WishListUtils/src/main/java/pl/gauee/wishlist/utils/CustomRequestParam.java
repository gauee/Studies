/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gauee.wishlist.utils;

/**
 *
 * @author gauee
 */
public class CustomRequestParam {

    private String paramName;
    private String paramValue;

    public CustomRequestParam(String paramName, String paramValue) {
        this.paramName = paramName;
        this.paramValue = paramValue;
    }

    public CustomRequestParam(String listId, long listId0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }
}
