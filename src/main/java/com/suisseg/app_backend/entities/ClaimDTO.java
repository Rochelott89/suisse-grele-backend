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
public class ClaimDTO {

    private String id;
    private String title;
    private String description;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private String status;
    private String damageType;
    private String idClient;
    private String idContract;

    public String toString() {
        return "ClaimDTO{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", status='" + status + '\'' +
                ", damageType='" + damageType + '\'' +
                ", idClient='" + idClient + '\'' +
                ", idContract='" + idContract + '\'' +
                '}';
    }
}
