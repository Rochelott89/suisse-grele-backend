package com.suisseg.app_backend.repositories;

import com.suisseg.app_backend.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer>{

    Client findByIdTechnique(String idTechnique);
}
