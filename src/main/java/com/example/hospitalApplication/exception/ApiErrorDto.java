package com.example.hospitalApplication.exception;

import lombok.*;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ApiErrorDto {
//    @Getter(AccessLevel.NONE)
    private HttpStatus status;
    private String message;

//    public Integer getStatus() {
//        return status.value();
//    }
}
