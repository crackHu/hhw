package com.vds.app.user.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import com.vds.app.user.model.UserRoleResources;

public interface UserRoleResourcesJpa extends JpaRepository<UserRoleResources, String>{

}
