package com.vds.app.user.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import com.vds.app.user.model.UserRoles;

public interface UserRolesJpa extends JpaRepository<UserRoles, String>{

}
