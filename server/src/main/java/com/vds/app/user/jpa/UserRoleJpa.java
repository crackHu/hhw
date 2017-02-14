package com.vds.app.user.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import com.vds.app.user.model.UserRole;

public interface UserRoleJpa extends JpaRepository<UserRole, String>{

}
