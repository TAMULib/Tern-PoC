package edu.tamu.app.controller;

import static edu.tamu.weaver.response.ApiStatus.SUCCESS;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.tamu.app.service.VoyagerExtractorService;
import edu.tamu.weaver.response.ApiResponse;

@RestController
@RequestMapping("/extraction")
public class ExtractionController {
    @PersistenceContext(unitName = "extractor-database")
    EntityManager extrationDatasourceManager;

    @Autowired
    VoyagerExtractorService voyagerExtractorService;

    @Transactional
    @RequestMapping("/schema")
    @PreAuthorize("hasRole('ANONYMOUS')")
    public ApiResponse describeSchema() {
        return new ApiResponse(SUCCESS, voyagerExtractorService.describeSchema(extrationDatasourceManager));
    }

}


