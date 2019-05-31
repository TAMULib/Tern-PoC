package edu.tamu.app.model.repo.impl;

import edu.tamu.app.model.CustomProcessor;
import edu.tamu.app.model.repo.CustomProcessorRepo;
import edu.tamu.app.model.repo.custom.CustomProcessorRepoCustom;
import edu.tamu.weaver.data.model.repo.impl.AbstractWeaverRepoImpl;

public class CustomProcessorRepoImpl extends AbstractWeaverRepoImpl<CustomProcessor, CustomProcessorRepo>
    implements CustomProcessorRepoCustom {

  @Override
  protected String getChannel() {
    return "/channel/custom-processor";
  }

}