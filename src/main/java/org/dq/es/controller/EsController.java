package org.dq.es.controller;

import org.dq.es.controller.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class EsController {
    @Autowired
    private SearchService searchService;

    @PostMapping("searchIndex")
    public Object searchIndex(@RequestParam String text) {
        return searchService.getData(text);
    }
}
