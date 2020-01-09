package com.framework.admin.repository;

import org.springframework.stereotype.Repository;

import com.framework.admin.entity.FwImageEntity;
import com.framework.common.base.repository.BaseRepository;

@Repository
public interface  FwImageRepository extends BaseRepository< FwImageEntity, Long> {

}
