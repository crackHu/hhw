package com.vds.app.other.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.vds.app.other.model.OtherParkImage;

public interface OtherParkImageJpa extends JpaRepository<OtherParkImage, String>{
 
	public List<OtherParkImage> findByOtSiteId(String otSiteId);
}
