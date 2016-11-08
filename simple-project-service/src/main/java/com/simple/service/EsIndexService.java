package com.simple.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.simple.constant.Constant;
import com.simple.elasticsearch.v020.ESIndexUtil;
import com.simple.elasticsearch.v020.ESMapping;
import com.simple.elasticsearch.v200.EsUtil;
import com.simple.model.DataInfo;

@Service
public class EsIndexService {

	public void createIndex(String indexName) throws Exception {
		EsUtil.createIndex(indexName);
	}
	
	public void deleteIndex(String indexName) throws IOException {
		EsUtil.deleteIndex(indexName);
	}
	
	public void batchInsertOrUpdateDoc(String indexName,String type,List infos) throws Exception {
		EsUtil.batchInsertOrUpdateDoc(indexName, type, infos);
	}
	
	public void batchDeleteDoc(String indexName,String type,List<String> indexIdlist) throws Exception {
		EsUtil.batchDeleteDoc(indexName, type, indexIdlist);
	}
	
	public void deleteDoc(String indexName,String type,String indexId) throws IOException {
		EsUtil.deleteDoc(indexName, type, indexId);
	}
	
	public T queryById(String indexName,String type,String indexId,Class<T> classType) throws IOException {
		return EsUtil.searchById(indexName, type, indexId, classType);
	}
	
	public List<DataInfo> queryDataInfo(Integer id,String text,Integer type,int pageIndex,int pageSize) throws IOException {
		BoolQueryBuilder bu = QueryBuilders.boolQuery();
		if ( null != id && id > 0) {
			QueryBuilder idbuilder = QueryBuilders.matchQuery("id", String.valueOf(id));
			bu.must(idbuilder);
		}
		if ( null != type && type > 0) {
			QueryBuilder typebuilder = QueryBuilders.matchQuery("type", String.valueOf(type));
			bu.must(typebuilder);
		}
		if (!StringUtils.isEmpty(text)) {
			QueryBuilder namebuilder = QueryBuilders.matchQuery("name", text);
			QueryBuilder bookNamebuilder = QueryBuilders.matchQuery("bookName", text);
			QueryBuilder descbuilder = QueryBuilders.matchQuery("desc", text);
			QueryBuilder authorsbuilder = QueryBuilders.matchQuery("authors", text);
			QueryBuilder tagsbuilder = QueryBuilders.matchQuery("tags", text);
			bu.should(namebuilder).should(bookNamebuilder).should(descbuilder).should(authorsbuilder).should(tagsbuilder);
		}
		if ( pageIndex <= 0 ) {
			pageIndex = 1;
		}
		return EsUtil.searchList(Constant.INDEX_DATA_NAME, Constant.INDEX_DATA_TYPE_BOOK,(pageIndex-1)*pageSize,pageSize, bu, DataInfo.class);
	}
	
}
