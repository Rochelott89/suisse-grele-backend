package com.suisseg.app_backend.controllers;

import com.suisseg.app_backend.entities.ClaimDTO;
import com.suisseg.app_backend.entities.Claim;
import com.suisseg.app_backend.services.ClaimService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/claims")
public class ClaimController {

    @Autowired
    private ClaimService claimService;

    @GetMapping("/get-all-claims")
    public ResponseEntity<List<ClaimDTO>> getAllClaims() {
        log.info("Getting all the claims");
        List <ClaimDTO> claims = claimService.getAllClaims();
        return ResponseEntity.ok(claims);
    }

    @GetMapping("/get-claim-by-id/{id}")
    public ResponseEntity<ClaimDTO> getClaimById(String id) {
        log.info("Getting claim with id: {}", id);
        ClaimDTO response = claimService.getClaimById(id);
        return ResponseEntity.ok(response);
    }
    //a verifier si marche
    @GetMapping("/get-claims-by-client/{idClient}")
    public ResponseEntity<List<ClaimDTO>> getClaimsByClient(@PathVariable String idClient) {
        log.info("Getting claims with idClient: {}", idClient);
        List <ClaimDTO> response = claimService.getClaimsByClient(idClient);
        return ResponseEntity.ok(response);
    }

    //maybe a getByContract

    @PostMapping("/save-claim")
    public ResponseEntity<ClaimDTO> saveClaim(@PathVariable ClaimDTO claimDTO) {
        log.info("Creating claim: {}", claimDTO);
        ClaimDTO response = claimService.saveClaim(claimDTO);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/update-claim")
    public ResponseEntity<ClaimDTO> updateClaim(@PathVariable ClaimDTO claimDTO) {
        log.info("Updating claim: {}", claimDTO);
        ClaimDTO response = claimService.updateClaim(claimDTO);
        return ResponseEntity.ok(response);
    }



}
