package com.suisseg.app_backend.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ContractDTO {
    private String id;
    private Timestamp startDate;
    private Timestamp endDate;
    private String cropType;
    private double coverageAmount;
    private String idClient;
}
