package com.example.healthproservice.service.doctor;

import com.example.healthproservice.DTOs.Doc.DocDTOMapper;
import com.example.healthproservice.DTOs.Doc.DoctorDTO;
import com.example.healthproservice.model.Doctor;
import com.example.healthproservice.model.DoctorSpeciality;
import com.example.healthproservice.model.auth.Account;
import com.example.healthproservice.repo.DoctorRepo;
import com.example.healthproservice.repo.DoctorSpecialityRepo;
import com.example.healthproservice.service.AuthServiceClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepo doctorRepo;
    private final DocDTOMapper docDTOMapper;
    private final DoctorSpecialityRepo specialityRepo;
    private final AuthServiceClient authClient;

    public DoctorServiceImpl(DoctorRepo doctorRepo, DocDTOMapper docDTOMapper,
                             DoctorSpecialityRepo specialityRepo, AuthServiceClient authClient)
    {
        this.doctorRepo = doctorRepo;
        this.docDTOMapper = docDTOMapper;
        this.specialityRepo = specialityRepo;
        this.authClient = authClient;
    }
    @Override
    public DoctorDTO add(DoctorDTO doc){
        DoctorSpeciality speciality = specialityRepo.findById(doc.getSpecialityId())
                .orElseThrow(()->new RuntimeException(
                        String.format("No Speciality with id '%d' was found.",doc.getSpecialityId()))
                );
        Doctor doctor = docDTOMapper.DocDTOToDoc(doc);
        doctor.setSpeciality(speciality);

        // creating account for doctor
        try{
            authClient.createAccount(new Account(doc.getEmail(),doc.getPassword(),"doctor",null));
        }catch (Exception e ){
            System.out.println("Couldn't create account for doctor!");
        }
        // trying to save the new doctor entity
        Doctor newDoc = doctorRepo.save(doctor);
        // TODO: if not successful, then delete doctor account
        return docDTOMapper.DocToDocTDO(newDoc);
    }
    @Override
    public DoctorDTO updateDoctor(DoctorDTO doctorDTO){
        if( !doctorRepo.existsById(doctorDTO.getId()))
            throw new RuntimeException(String.format("No Doctor with id %d was found.",doctorDTO.getSpecialityId()) );
        Doctor doctor =docDTOMapper.DocDTOToDoc(doctorDTO);
        DoctorSpeciality speciality = specialityRepo.findById(doctorDTO.getSpecialityId())
                .orElseThrow(()->new RuntimeException(
                        String.format("No Speciality with id '%d' was found.",doctorDTO.getSpecialityId()))
                );
        doctor.setSpeciality(speciality);
        return docDTOMapper.DocToDocTDO(doctorRepo.save(doctor));
    }

    @Override
    public List<DoctorDTO> getAll(){
        return doctorRepo.findAll().stream().
                map(docDTOMapper::DocToDocTDO).toList();
    }
    @Override
    public Optional<DoctorDTO> getDoctor(Long id){
        return doctorRepo.findById(id).map(docDTOMapper::DocToDocTDO);
    }
    @Override
    public void deleteDoctor(Long id) {
        doctorRepo.deleteById(id);
        // TODO-2: Delete related doctor account.
    }
}
