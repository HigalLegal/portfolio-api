package dev.higormorais.client.model;

public class Root {

    public Data data;

    public boolean success;

    public int status;

    // ---------------------------------------------------------------------

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String urlImage() {
        return data.getUrl();
    }

}
