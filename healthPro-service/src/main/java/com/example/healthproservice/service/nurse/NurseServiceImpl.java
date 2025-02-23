package com.example.healthproservice.service.nurse;

import com.example.healthproservice.DTOs.Nurse.NurseDTO;
import com.example.healthproservice.DTOs.Nurse.NurseDTOMapper;
import com.example.healthproservice.model.Nurse;
import com.example.healthproservice.model.auth.Account;
import com.example.healthproservice.repo.NurseRepo;
import com.example.healthproservice.service.AuthServiceClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NurseServiceImpl implements NurseService {
    private final NurseRepo nurseRepo;
    private final NurseDTOMapper nurseDTOMapper;
    private final AuthServiceClient authClient;

    public NurseServiceImpl(NurseRepo nurseRepo, NurseDTOMapper nurseDTOMapper, AuthServiceClient authClient) {
        this.nurseRepo = nurseRepo;
        this.nurseDTOMapper = nurseDTOMapper;
        this.authClient = authClient;
    }

    @Override
    public List<NurseDTO> getAll() {
        return nurseRepo.findAll().stream().map(nurseDTOMapper::nurseToDTO).toList();
    }

    @Override
    public NurseDTO create(NurseDTO nurseDTO) {
        Nurse nurse = nurseDTOMapper.DTOToNurse(nurseDTO);
        nurse.setId(null);
        nurse = nurseRepo.save(nurse);
        // trying to create account for nurse
        try{
            authClient.createAccount(new Account(nurseDTO.getEmail(),nurseDTO.getPassword(), "nurse",null));
        }catch (Exception e){ // deleting nurse if account creation failed
            System.out.println("Couldn't create Account for nurse.");
            nurseRepo.delete(nurse);
            return null;
        }

        return nurseDTOMapper.nurseToDTO(nurse);
    }

    @Override
    public Optional<NurseDTO> getNurseById(Long id) {
        return nurseRepo.findById(id).map(nurseDTOMapper::nurseToDTO);
    }

    @Override
    public boolean existById(Long id) {
        return nurseRepo.existsById(id);
    }

    @Override
    public NurseDTO updateNurse(NurseDTO nurseDTO) {
        if (!nurseRepo.existsById(nurseDTO.getId()))
            throw new RuntimeException(String.format("No Nurse with id %d was found.",nurseDTO.getId()));
        Nurse nurse = nurseDTOMapper.DTOToNurse(nurseDTO);
        nurseDTO =nurseDTOMapper.nurseToDTO(nurseRepo.save(nurse));

        return nurseDTO;
    }

    @Override
    public void deleteNurse(Long id) {
        nurseRepo.deleteById(id);
        // TODO-5: delete nurse account.
    }
}
