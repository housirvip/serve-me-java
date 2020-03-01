package edu.uta.cse.serveme.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author housirvip
 */
@Getter
@AllArgsConstructor
public enum OrderStatus {

    /**
     * OrderStatus
     */
    Waiting,
    Pending,
    Biding,
    Processing,
    Completed,
    Closed,
    Finished
}
