package com.vds.app.other.jpa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.vds.app.other.model.OtherCar;

public interface OtherCarJpa extends JpaRepository<OtherCar, String> {

	Page<OtherCar> findByOtCarUsId(String usId, Pageable pageable);

	OtherCar findByOtCarIdAndOtCarUsId(String carId, String usId);

	List<OtherCar> findByOtCarUsId(String usId);

}
