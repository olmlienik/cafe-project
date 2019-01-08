package by.mlionik.cafe.controller;


public class Router
{
    public enum RouteType{FORWARD, REDIRECT}

    private String pagePath;
    private RouteType routeType = RouteType.FORWARD;

    public void setPagePath(String pagePath)
    {
        this.pagePath=pagePath;
    }

    public String getPagePath()
    {
        return pagePath;
    }

    public void setRouteType(RouteType routeType)
    {
        this.routeType=routeType;
    }

    public RouteType getRouteType()
    {
        return routeType;
    }
}

