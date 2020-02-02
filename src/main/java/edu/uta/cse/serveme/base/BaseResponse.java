package edu.uta.cse.serveme.base;

import lombok.*;

/**
 * @author housirvip
 */
@Data
@AllArgsConstructor
public class BaseResponse<T> {

    private Integer code;

    private String message;

    private Long total;

    private T result;
}
