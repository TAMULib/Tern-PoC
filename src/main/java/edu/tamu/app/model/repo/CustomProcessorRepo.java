package edu.tamu.app.model.repo;

import org.springframework.stereotype.Repository;

import edu.tamu.app.model.CustomProcessor;
import edu.tamu.app.model.repo.custom.CustomProcessorRepoCustom;
import edu.tamu.weaver.data.model.repo.WeaverRepo;

/**
 * Application User repository.
 * 
 */
@Repository
public interface CustomProcessorRepo extends WeaverRepo<CustomProcessor>, CustomProcessorRepoCustom {

}
