package by.mlionik.cafe.controller;

import by.mlionik.cafe.exception.NoSuchRequestParameterException;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Session request content.
 */
public class SessionRequestContent {
    private Map<String, Object> requestAttributes = new HashMap<>();
    private Map<String, String[]> requestParameters = new HashMap<>();
    private Map<String, Object> sessionAttributes = new HashMap<>();

    /**
     * Instantiates a new session and request contents.
     *
     * @param request the request
     */
    public SessionRequestContent(HttpServletRequest request) {
        extractValues(request);
    }

    /**
     * Gets the session attribute.
     *
     * @param attributeName the attribute name
     * @return the session attribute
     * @throws NoSuchRequestParameterException indicates that there're no such request parameter
     */
    public Object getSessionAttribute(String attributeName) throws NoSuchRequestParameterException {
        if (sessionAttributes.get(attributeName) != null) {
            return sessionAttributes.get(attributeName);
        } else {
            throw new NoSuchRequestParameterException(attributeName);
        }
    }

    /**
     * Gets the request parameter.
     *
     * @param parameterName the parameter name
     * @return the parameter
     * @throws NoSuchRequestParameterException the no such request parameter exception
     */
    public String getParameter(String parameterName) throws NoSuchRequestParameterException {
        if (requestParameters.get(parameterName) != null) {
            return requestParameters.get(parameterName)[0];
        } else {
            throw new NoSuchRequestParameterException(parameterName);
        }
    }

    /**
     * Sets the request attribute.
     *
     * @param attributeName the attribute name
     * @param attributeValue the attribute value
     */
    public void setAttribute(String attributeName, Object attributeValue) {
        requestAttributes.put(attributeName, attributeValue);
    }

    /**
     * Sets the session attribute.
     *
     * @param attributeName the attribute name
     * @param attributeValue the attribute value
     */
    public void setSessionAttribute(String attributeName, Object attributeValue) {
        sessionAttributes.put(attributeName, attributeValue);
    }

    /**
     * Inserts values from request.
     *
     * @param request the request
     */
    public void insertValues(HttpServletRequest request) {
        for (Map.Entry<String, Object> requestAttribute : requestAttributes.entrySet()) {
            request.setAttribute(requestAttribute.getKey(), requestAttribute.getValue());
        }
        for (Map.Entry<String, Object> sessionAttribute : sessionAttributes.entrySet()) {
            request.getSession().setAttribute(sessionAttribute.getKey(), sessionAttribute.getValue());
        }
    }

    /**
     * Extracts values from request.
     *
     * @param request the request
     */
    private void extractValues(HttpServletRequest request) {
        requestParameters = request.getParameterMap();
        extractRequestAttributes(request);
        extractSessionAttributes(request);
    }

    /**
     * Extracts request attributes.
     *
     * @param request the request
     */
    private void extractRequestAttributes(HttpServletRequest request) {
        Enumeration attributeNames = request.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            String name = (String) attributeNames.nextElement();
            Object value = request.getAttribute(name);
            requestAttributes.put(name, value);
        }
    }

    /**
     * Extracts session attributes.
     *
     * @param request the request
     */
    private void extractSessionAttributes(HttpServletRequest request) {
        Enumeration attributeNames = request.getSession().getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            String name = (String) attributeNames.nextElement();
            Object value = request.getSession().getAttribute(name);
            sessionAttributes.put(name, value);
        }
    }
}
