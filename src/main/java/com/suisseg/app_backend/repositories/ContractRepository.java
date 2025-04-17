package com.suisseg.app_backend.repositories;

import com.suisseg.app_backend.entities.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Integer> {

    Contract findByIdTechnique(String idTechnique);


}
