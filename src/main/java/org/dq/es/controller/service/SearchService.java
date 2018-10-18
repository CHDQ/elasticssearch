package org.dq.es.controller.service;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SearchService {
    @Autowired
    private TransportClient transportClient;

    public Object getData(String text) {
        var queryBuilder = QueryBuilders.matchPhraseQuery("content", text);
        var searchResponse = transportClient.prepareSearch("index").setTypes("fulltext").setQuery(queryBuilder).get();
        Map<String, Object> resultMap = new HashMap<>();
        var searchHits = searchResponse.getHits();
        var hits = searchHits.getHits();
        for (var searchHitFields : hits) {
            resultMap.put(String.valueOf(searchHitFields.getScore()), searchHitFields.getSourceAsMap());
        }
        return resultMap;
    }
}
