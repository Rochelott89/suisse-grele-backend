package com.suisseg.app_backend.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.suisseg.app_backend.entities.ClaimDTO;
import com.suisseg.app_backend.entities.Client;
import com.suisseg.app_backend.entities.Contract;
import com.suisseg.app_backend.enums.StatusEnum;
import com.suisseg.app_backend.repositories.ClaimRepository;
import com.suisseg.app_backend.repositories.ClientRepository;
import com.suisseg.app_backend.repositories.ContractRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.suisseg.app_backend.entities.Claim;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class ClaimService {

    //anotaciones : @Service, @Slf4j
    //cosas necesarias : ObjectMapper, los repository a utilisar con @Autowired
    //métodos : corresponden a los endpoints, todos public y en general hacemos return del DTO

    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private ClaimRepository claimRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ContractRepository contractRepository;

    public List<ClaimDTO> getAllClaims() {

        List<Claim> claims = claimRepository.findAll();

        return claims.stream().map(Claim::toDTO).toList();

    }

    public ClaimDTO getClaimById(String id) {

        ClaimDTO claimDTO;

        try {

            Claim claim = claimRepository.findByIdTechnique(id);

            if(claim == null) {
                throw new RuntimeException("Claim not found");
            }

            claimDTO = claim.toDTO();


        } catch (Exception e) {
            log.error("Error getting claim with id: {}", id);
            return null;
        }

        return claimDTO;

    }

    public List<ClaimDTO> getClaimsByClient(String idClient) {

        List<ClaimDTO> claimDTOList;

        try {

            Client client = clientRepository.findByIdTechnique(idClient);

            if(client == null) {
                throw new RuntimeException("Client not found");
            }

            //we pass the client object to the repository because in the model will be mapped by its id
            List<Claim> claims = claimRepository.findByIdClient(client);

            claimDTOList = claims.stream().map(Claim::toDTO).toList();


        }catch(Exception e) {
            log.error("Error getting claims with idClient: {}", idClient);
            return null;
        }

        return claimDTOList;

    }

    public ClaimDTO saveClaim(ClaimDTO claimDTO) {

        Claim claim;
        ClaimDTO claimDTOResponse;

        try {

            Client client = clientRepository.findByIdTechnique(claimDTO.getIdClient());

            if(client == null) {
                throw new RuntimeException("Client not found");
            }

            Contract contract = contractRepository.findByIdTechnique(claimDTO.getIdContract());

            if(contract == null) {
                throw new RuntimeException("Contract not found");
            }

            Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
            claimDTO.setCreatedAt(currentTimestamp);
            claimDTO.setUpdatedAt(currentTimestamp);

            //random id for idTechnique
            String idTechnique = UUID.randomUUID().toString();
            claimDTO.setId(idTechnique);



            claim = new Claim(claimDTO, client, contract);

            claim.setStatus(StatusEnum.OPEN);

            claimRepository.save(claim);

            claimDTOResponse = claim.toDTO();

        }catch(Exception e) {
            log.error("Error saving claim: {}", claimDTO);
            return null;
        }


        return claimDTOResponse;

    }

    public ClaimDTO updateClaim(ClaimDTO claimDTO) {

        Claim claim;
        ClaimDTO claimDTOResponse;

        try {

            claim = claimRepository.findByIdTechnique(claimDTO.getId());

            if(claim == null) {
                throw new RuntimeException("Claim not found");
            }

            if(!claimDTO.getIdClient().equals(claim.getIdClient().getIdTechnique())) {
                throw new RuntimeException("Client not found");
            }
            if(!claimDTO.getIdContract().equals(claim.getIdContract().getIdTechnique())) {
                throw new RuntimeException("Contract not found");
            }

            Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
            claimDTO.setUpdatedAt(currentTimestamp);

            Claim.mapDTO(claimDTO, claim);

            claimRepository.save(claim);

            claimDTOResponse = claim.toDTO();


        }catch(Exception e) {
            log.error("Error updating claim: {}", claimDTO);
            return null;
        }


        return claimDTOResponse;

    }



}
