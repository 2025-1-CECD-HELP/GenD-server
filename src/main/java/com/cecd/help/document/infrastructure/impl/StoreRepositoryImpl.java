package com.cecd.help.document.infrastructure.impl;

import com.cecd.help.core.exception.CustomException;
import com.cecd.help.core.exception.ErrorCode;
import com.cecd.help.document.domain.entity.Directory;
import com.cecd.help.document.domain.entity.Store;
import com.cecd.help.document.domain.repository.StoreRepository;
import com.cecd.help.document.infrastructure.jpa.StoreJpaRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class StoreRepositoryImpl implements StoreRepository {
    private final StoreJpaRepository storeJpaRepository;

    public void save(Store store) {
        storeJpaRepository.save(store);
    }

    public Store findById(Long documentId) {
        return storeJpaRepository.findById(documentId)
                .orElseThrow(() -> new CustomException(ErrorCode.SERVER_ERROR));
    }

    public void delete(Store store) {
        storeJpaRepository.delete(store);
    }

    public List<Store> findByDirectory(Directory directory) {
        return storeJpaRepository.findByDirectory(directory);
    }

}
