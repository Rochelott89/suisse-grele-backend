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
    //anotaciones de clases : @Entity, @NoArgsConstructor, @AllArgsConstructor, @Getter, @Setter
    //atributos : todos String salvo id (Integer), Timestamp y Enums
    //anotaciones de atributos :     @Id y @GeneratedValue(strategy = GenerationType.AUTO) para id, @ManyToOne y @JoinColumn para conectar con otros Modelos
    //constructor : aparte de los que aportan las anotaciones, un constructor public "entity"(entityDTO, Client, Contract)
    //métodos : public static void mapDTO(entityDTO, entity), public entityDTO toDTO, public String toString


    @Id
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

    public static void mapDTO(ClaimDTO claimDTO, Claim claim) {
        claim.setTitle(claimDTO.getTitle());
        claim.setDescription(claimDTO.getDescription());
        claim.setUpdatedAt(claimDTO.getUpdatedAt());
        claim.setStatus(StatusEnum.valueOf(claimDTO.getStatus()));
        claim.setDamageType(DamageEnum.valueOf(claimDTO.getDamageType()));

        //maybe allow to map and update a client and contract IF admin, maybe in a separate method

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
