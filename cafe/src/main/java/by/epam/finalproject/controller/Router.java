package by.epam.finalproject.controller;

/**
 * The type Router.
 */
public class Router {
    /**
     * The enum Type.
     */
    public enum Type{
        /**
         * Forward type.
         */
        FORWARD,
        /**
         * Redirect type.
         */
        REDIRECT
    }

    private Type currentType = Type.FORWARD;
    private String currentPage;

    /**
     * Instantiates a new Router.
     */
    public Router(){}

    /**
     * Instantiates a new Router.
     *
     * @param currentPage the current page
     */
    public Router(String currentPage){
        this.currentPage = currentPage;
    }

    /**
     * Instantiates a new Router.
     *
     * @param currentType the current type
     * @param currentPage the current page
     */
    public Router(Type currentType, String currentPage){
        this.currentType = currentType;
        this.currentPage = currentPage;
    }

    /**
     * Get current type type.
     *
     * @return the type
     */
    public Type getCurrentType(){
        return currentType;
    }

    /**
     * Set redirect type.
     */
    public void setRedirectType(){
        this.currentType = Type.REDIRECT;
    }

    /**
     * Get current page string.
     *
     * @return the string
     */
    public  String getCurrentPage(){
        return currentPage;
    }

    /**
     * Set current page.
     *
     * @param currentPage the current page
     */
    public void setCurrentPage(String currentPage){
        this.currentPage = currentPage;
    }

}
