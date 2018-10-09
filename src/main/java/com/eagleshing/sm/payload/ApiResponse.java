package com.eagleshing.sm.payload;

import java.util.Optional;

public class ApiResponse {
	
    private Boolean success;
    
    private String message;
    
    private Optional<?> object;

    public ApiResponse(Boolean success, String message,Optional<?> object) {
        this.success = success;
        this.message = message;
        this.object = object;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

	public Optional<?> getObject() {
		return object;
	}

	public void setObject(Optional<?> object) {
		this.object = object;
	}
    
    
}
