package de.killedbycheese.recipeBookServer.util;

public class ErrorInfo {
    public final String url;
    public final String exceptionMessage;

    public ErrorInfo(String url, Exception ex) {
        this.url = url;
        this.exceptionMessage = ex.getLocalizedMessage();
    }
}