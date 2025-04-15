package com.suisseg.app_backend.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Contract {

    @GeneratedValue(strategy = GenerationType.AUTO)

    private Integer id;
    private String idTechnique;

    private Timestamp startDate;
    private Timestamp endDate;
    private String cropType;
    private double coverageAmount;

    @ManyToOne
    @JoinColumn(name = "id_Client")
    private Client idClient;

    public Contract(ContractDTO contractDTO, Client client) {
        this.idTechnique = contractDTO.getId();
        this.startDate = contractDTO.getStartDate();
        this.endDate = contractDTO.getEndDate();
        this.cropType = contractDTO.getCropType();
        this.coverageAmount = contractDTO.getCoverageAmount();
        this.idClient = client;
    }

    static void mapDTO(ContractDTO contractDTO, Contract contract) {
        contract.setStartDate(contractDTO.getStartDate());
        contract.setEndDate(contractDTO.getEndDate());
        contract.setCropType(contractDTO.getCropType());
        contract.setCoverageAmount(contractDTO.getCoverageAmount());
    }

    public ContractDTO toDTO() {
        ContractDTO contractDTO = new ContractDTO();
        contractDTO.setId(this.idTechnique);
        contractDTO.setStartDate(this.startDate != null ? startDate : null);
        contractDTO.setEndDate(this.endDate != null ? endDate : null);
        contractDTO.setCropType(cropType);
        contractDTO.setCoverageAmount(coverageAmount);
        contractDTO.setIdClient(this.idClient != null ? idClient.getIdTechnique() : null);
        return contractDTO;
    }

    public String toString() {
        return "Contract{" +
                "id=" + id +
                ", idTechnique='" + idTechnique + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", cropType='" + cropType + '\'' +
                ", coverageAmount=" + coverageAmount +
                ", idClient='" + idClient + '\'' +
                '}';
    }
}
