package com.vds.app.user.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import com.vds.app.user.model.UserResources;

public interface UserResourcesJpa extends JpaRepository<UserResources, String>{

}
