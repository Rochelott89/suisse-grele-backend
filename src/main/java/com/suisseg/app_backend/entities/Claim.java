package com.suisseg.app_backend.entities;

import com.suisseg.app_backend.enums.StatusEnum;
import com.suisseg.app_backend.enums.DamageEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Claim {

    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String idTechnique;

    private String title;

    private String description;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    private StatusEnum status;

    private DamageEnum damageType;

    //idClient
    @ManyToOne
    @JoinColumn(name = "id_Client")
    private Client idClient;

    //idContract
    @ManyToOne
    @JoinColumn(name = "id_Contract")
    private Contract idContract;

    public Claim(ClaimDTO claimDTO, Client client, Contract contract) {
        this.idTechnique = claimDTO.getId();
        this.title = claimDTO.getTitle();
        this.description = claimDTO.getDescription();
        this.createdAt = claimDTO.getCreatedAt();
        this.updatedAt = claimDTO.getUpdatedAt();
        this.status = StatusEnum.valueOf(claimDTO.getStatus());
        this.damageType = DamageEnum.valueOf(claimDTO.getDamageType());
        this.idClient = client;
        this.idContract = contract;
    }

    static void mapDTO(ClaimDTO claimDTO, Claim claim) {
        claim.setTitle(claimDTO.getTitle());
        claim.setDescription(claimDTO.getDescription());
        claim.setCreatedAt(claimDTO.getCreatedAt());
        claim.setUpdatedAt(claimDTO.getUpdatedAt());
        claim.setStatus(StatusEnum.valueOf(claimDTO.getStatus()));
        claim.setDamageType(DamageEnum.valueOf(claimDTO.getDamageType()));

    }

    public ClaimDTO toDTO() {
        ClaimDTO claimDTO = new ClaimDTO();
        claimDTO.setId(this.idTechnique);
        claimDTO.setTitle(title);
        claimDTO.setDescription(description);
        claimDTO.setCreatedAt(createdAt);
        claimDTO.setUpdatedAt(updatedAt);
        claimDTO.setStatus(this.status != null ? String.valueOf(status) : null);
        claimDTO.setDamageType(this.damageType != null ? String.valueOf(damageType) : null);
        claimDTO.setIdClient(this.idClient != null ? idClient.getIdTechnique() : null);
        claimDTO.setIdContract(this.idContract != null ? idContract.getIdTechnique(): null);
        return claimDTO;
    }

    public String toString() {
        return "Claim{" +
                "id=" + id +
                ", idTechnique='" + idTechnique + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", status=" + status +
                ", damageType=" + damageType +
                ", client=" + idClient +
                ", contract=" + idContract +
                '}';
    }

}
