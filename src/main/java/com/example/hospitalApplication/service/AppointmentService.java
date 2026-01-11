//package com.example.hospital.service;
//
//import com.example.hospital.dto.AppointmentDto;
//import com.example.hospital.dto.type.Role;
//import com.example.hospital.entity.AppointmentEntity;
//import com.example.hospital.entity.DoctorEntity;
//import com.example.hospital.entity.PatientEntity;
//import com.example.hospital.entity.UserEntity;
//import com.example.hospital.repository.AppointmentRepository;
//import com.example.hospital.repository.DoctorRepository;
//import com.example.hospital.repository.PatientRepository;
//import jakarta.transaction.Transactional;
//import lombok.RequiredArgsConstructor;
//import org.modelmapper.ModelMapper;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class AppointmentService {
//    private final AppointmentRepository appointmentRepository;
//    private final DoctorRepository doctorRepository;
//    private final PatientRepository patientRepository;
//    private final ModelMapper mapper;
//
//    public AppointmentDto create(AppointmentDto request) {
////        DoctorEntity doctor = doctorRepository.findById(request.getDoctor()).orElseThrow();
////        PatientEntity patient = patientRepository.findById(request.getPatient()).orElseThrow();
////
////        AppointmentEntity appointment = AppointmentEntity.builder().time(LocalDateTime.now()).doctor(doctor).patient(patient).build();
////
////        return mapper.map(appointmentRepository.save(appointment), AppointmentDto.class);
//    }
//
//    public List<AppointmentDto> read(UserEntity user) {
////         UserEntity user = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
////        if (user.getRoles().contains(Role.DOCTOR)) {
////            DoctorEntity doctor = doctorRepository.findById(user.getId()).orElseThrow();
////
////            return appointmentRepository.findByDoctor(doctor).stream().map(x -> mapper.map(x, AppointmentDto.class)).toList();
////        } else if (user.getRoles().contains(Role.ADMIN)) {
////            PatientEntity patient = patientRepository.findById(user.getId()).orElseThrow();
////
////            return appointmentRepository.findByPatient(patient).stream().map(x -> mapper.map(x, AppointmentDto.class)).toList();
////        }
////
////        return appointmentRepository.findAll().stream().map(x -> mapper.map(x, AppointmentDto.class)).toList();
//    }
//
//    public AppointmentDto read(UserEntity user, Integer id) {
////         UserEntity user = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//        return null;
//    }
//
//    @Transactional
//    public AppointmentDto update(AppointmentDto request) {
////        AppointmentEntity appointment = appointmentRepository.findById(request.getId()).orElseThrow();
////        DoctorEntity doctor = doctorRepository.findById(request.getDoctor()).orElseThrow();
////
////        appointment.setDoctor(doctor);
////
////        return mapper.map(appointment, AppointmentDto.class);
//    }
//
//    public AppointmentDto delete(Integer id) {
////        AppointmentEntity appointment = appointmentRepository.findById(id).orElseThrow();
////
////        appointmentRepository.deleteById(id);
////
////        return mapper.map(appointment, AppointmentDto.class);
//    }
//}
