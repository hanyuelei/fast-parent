package com.framework.admin.repository;

import org.springframework.stereotype.Repository;

import com.framework.admin.entity.FwDicEntity;
import com.framework.common.base.repository.BaseRepository;

@Repository
public interface  FwDicRepository extends BaseRepository< FwDicEntity, Long> {

}
