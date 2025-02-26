package com.example.account.modules.account.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaAccountRepository extends JpaRepository<AccountEntity, Long>, PagingAndSortingRepository<AccountEntity, Long>, JpaSpecificationExecutor<AccountEntity> {
    Optional<AccountEntity> findByNumber(String number);
}
