package com.myeconomy.worldeconomyindex.exceptions;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NoDataAvailable extends RuntimeException {

    private String message;

    public NoDataAvailable(String message) {
        super(message);
        this.message = message;
    }
}
