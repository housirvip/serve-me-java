package edu.uta.cse.serveme.base;

/**
 * @author housirvip
 */
public class ResultResponse<T> extends BaseResponse<T> {

    public ResultResponse(T result) {

        super(Constant.SUCCESS_CODE, null, null, result);
    }
}
