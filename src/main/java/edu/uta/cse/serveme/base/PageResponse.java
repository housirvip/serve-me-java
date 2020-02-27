package edu.uta.cse.serveme.base;

/**
 * @author housirvip
 */
public class PageResponse<T> extends BaseResponse<T> {

    public PageResponse(T result, long total) {

        super(Constant.SUCCESS_CODE, null, total, result);
    }
}
