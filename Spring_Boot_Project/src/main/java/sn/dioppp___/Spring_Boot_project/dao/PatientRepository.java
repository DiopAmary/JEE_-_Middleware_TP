package sn.dioppp___.Spring_Boot_project.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;



public interface PatientRepository extends JpaRepository<Patient, Long>{
	public Page<Patient> findByNomContains(String motCle, Pageable pageable);
}
