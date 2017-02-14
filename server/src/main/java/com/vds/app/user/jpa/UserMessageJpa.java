package com.vds.app.user.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import com.vds.app.user.model.UserMessage;

public interface UserMessageJpa extends JpaRepository<UserMessage, String>{

}
