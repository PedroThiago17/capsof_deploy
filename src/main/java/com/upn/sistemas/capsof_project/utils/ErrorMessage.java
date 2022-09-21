package com.upn.sistemas.capsof_project.utils;

import java.io.Serializable;

public class ErrorMessage implements Serializable {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    private String error;

    /**
     *
     * @param error
     */
    public ErrorMessage(String error) {
        super();
        this.setError(error);
    }

    /**
     *
     * @return
     */
    public String getError() {
        return error;
    }

    /**
     *
     * @param error
     */
    public void setError(String error) {
        this.error = error;
    }

}
