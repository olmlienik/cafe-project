package by.mlionik.cafe.controller;

/**
 * The type Router.
 */
public class Router {
    /**
     * The enum that contains route type.
     */
    public enum RouteType {FORWARD, REDIRECT}

    private String pagePath;
    private RouteType routeType = RouteType.FORWARD;

    /**
     * Sets the page path.
     *
     * @param pagePath the new page path
     */
    public void setPagePath(String pagePath) {
        this.pagePath = pagePath;
    }

    /**
     * Gets the page path.
     *
     * @return the page path
     */
    public String getPagePath() {
        return pagePath;
    }

    /**
     * Sets the route type.
     *
     * @param routeType the new route type
     */
    public void setRouteType(RouteType routeType) {
        this.routeType = routeType;
    }

    /**
     * Gets the route type.
     *
     * @return the route type
     */
    public RouteType getRouteType() {
        return routeType;
    }
}

