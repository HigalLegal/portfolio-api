package dev.higormorais.handlers.entities;

public class ErrorJSON {

    private String title;

    private Integer statusCode;

    private String message;


    // --------------------------------------------------------

    public ErrorJSON() {}

    public ErrorJSON(String title, Integer statusCode, String message) {
        this.title = title;
        this.statusCode = statusCode;
        this.message = message;
    }

    // --------------------------------------------------------

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
