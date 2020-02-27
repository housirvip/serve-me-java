package edu.uta.cse.serveme.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author housirvip
 */
@Getter
@AllArgsConstructor
public enum VendorCategory {

    /**
     * VendorCategory
     */
    Appliances,
    Electrical,
    Plumbing,
    HomeCleaning,
    Tutoring,
    PackagingAndMoving,
    ComputerRepair,
    HomeRepairAndPainting,
    PestControl
}
