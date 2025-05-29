package com.cecd.help.document.domain.repository;

import com.cecd.help.document.domain.entity.Directory;
import com.cecd.help.document.domain.entity.Store;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository {
    void save(Store store);

    Store findById(Long documentId);
    void delete(Store store);
    List<Store> findByDirectory(Directory directory);

}
