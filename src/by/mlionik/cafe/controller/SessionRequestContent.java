package by.mlionik.cafe.controller;

import by.mlionik.cafe.exception.NoSuchRequestParameterException;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class SessionRequestContent {
    private Map<String, Object> requestAttributes = new HashMap<>();
    private Map<String, String[]> requestParameters = new HashMap<>();
    private Map<String, Object> sessionAttributes = new HashMap<>();

    public SessionRequestContent(HttpServletRequest request) {
        extractValues(request);
    }

    public void removeSessionAttribute(String attribute) {
        sessionAttributes.remove(attribute);
    }

    public Object getSessionAttribute(String attributeName) throws NoSuchRequestParameterException{
        if (sessionAttributes.get(attributeName) != null) {
            return sessionAttributes.get(attributeName);
        } else {
            throw new NoSuchRequestParameterException(attributeName);
        }
    }

    public String getParameter(String parameterName) throws NoSuchRequestParameterException {
        if (requestParameters.get(parameterName) != null) {
            return requestParameters.get(parameterName)[0];
        } else {
            throw new NoSuchRequestParameterException(parameterName);
        }
    }

    public String[] getParameters(String parameterName) throws NoSuchRequestParameterException {
        if (requestParameters.get(parameterName) != null) {
            return requestParameters.get(parameterName);
        } else {
            throw new NoSuchRequestParameterException(parameterName);
        }
    }

    public void setAttribute(String attributeName, Object attributeValue) {
        requestAttributes.put(attributeName, attributeValue);
    }

    public void setSessionAttribute(String attributeName, Object attributeValue) {
        sessionAttributes.put(attributeName, attributeValue);
    }


    public void insertValues(HttpServletRequest request) {
        for (Map.Entry<String, Object> requestAttribute : requestAttributes.entrySet()) {
            request.setAttribute(requestAttribute.getKey(), requestAttribute.getValue());
        }
        for (Map.Entry<String, Object> sessionAttribute : sessionAttributes.entrySet()) {
            request.getSession().setAttribute(sessionAttribute.getKey(), sessionAttribute.getValue());
        }
    }

    private void extractValues(HttpServletRequest request) {
        requestParameters = request.getParameterMap();
        extractRequestAttributes(request);
        extractSessionAttributes(request);
    }

    private void extractRequestAttributes(HttpServletRequest request) {
        Enumeration attributeNames = request.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            String name = (String) attributeNames.nextElement();
            Object value = request.getAttribute(name);
            requestAttributes.put(name, value);
        }
    }

    private void extractSessionAttributes(HttpServletRequest request) {
        Enumeration attributeNames = request.getSession().getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            String name = (String) attributeNames.nextElement();
            Object value = request.getSession().getAttribute(name);
            sessionAttributes.put(name, value);
        }
    }
}
