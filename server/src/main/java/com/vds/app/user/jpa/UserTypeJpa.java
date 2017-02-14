package com.vds.app.user.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import com.vds.app.user.model.UserType;

public interface UserTypeJpa extends JpaRepository<UserType, String>{

}
