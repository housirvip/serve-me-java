package edu.uta.cse.serveme.base;

import edu.uta.cse.serveme.constant.Constant;

/**
 * @author housirvip
 */
public class ResultResponse<T> extends BaseResponse<T> {

    public ResultResponse(T result) {

        super(Constant.SUCCESS_CODE, null, null, result);
    }
}
