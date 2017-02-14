package com.vds.app.other.jpa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.vds.app.other.model.OtherParkSite;

public interface OtherParkSiteJpa extends JpaRepository<OtherParkSite, String> {

	Page<OtherParkSite> findByOtIsEnabled(boolean isEnable, Pageable pageable);
	
	List<OtherParkSite> findByOtIsEnabled(boolean isEnable);

}
