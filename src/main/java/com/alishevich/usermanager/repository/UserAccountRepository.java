package com.alishevich.usermanager.repository;

import com.alishevich.usermanager.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserAccountRepository extends JpaRepository<UserAccount, Integer> {
}
