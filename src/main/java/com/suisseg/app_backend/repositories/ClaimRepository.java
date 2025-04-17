package com.suisseg.app_backend.repositories;

import com.suisseg.app_backend.entities.Claim;
import com.suisseg.app_backend.entities.Client;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface ClaimRepository extends JpaRepository<Claim, Integer> {

    //anotaciones : @Repository
    //extends JpaRepository<"Entity", Integer>
    //métodos : "Entity" findbyIdTechnique(String idTechnique) y métodos de listas si necesitamos encontrar esa entidad por el id de una otra entidad List<"Entity"> findByClientIdTechnique(@Entity" idClient)
    //si hay un delete, también se tiene que realizar
    //los otros metodos son automaticamente dados por JpaRepository (ex : findAll())
    /*
        @Modifying
        @Query("DELETE FROM Note n WHERE n.idTechnique = :idTechnique")
        void deleteByIdTechnique(String idTechnique);
     */

    Claim findByIdTechnique(String idTechnique);

    List<Claim> findByIdClient(Client idClient);


}
