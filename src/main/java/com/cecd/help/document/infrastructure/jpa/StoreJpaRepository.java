package com.cecd.help.document.infrastructure.jpa;

import com.cecd.help.document.domain.entity.Directory;
import com.cecd.help.document.domain.entity.Store;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreJpaRepository extends JpaRepository<Store, Long> {
    List<Store> findByDirectory(Directory directory);
}
