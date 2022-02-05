package com.ntg.adm.dao;

import org.springframework.stereotype.Repository;

import com.ntg.adm.base.BaseRepository;
import com.ntg.adm.model.AdmContentStorageProperties;

@Repository
public interface AdmContentStoragePropertiesRepository extends BaseRepository<AdmContentStorageProperties, Long> {
	AdmContentStorageProperties getFileByContentId(Long contentId);
}
