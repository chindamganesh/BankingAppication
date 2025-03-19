package com.Bannking.BankingApplication.Repositoty;

import com.Bannking.BankingApplication.Model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepo extends JpaRepository<Account, Long> {
}
