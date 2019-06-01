import AbstractAppRepo from './abstractAppRepo';
import ProcessorRepo from './processorRepo';
import UserRepo from './userRepo';

angular.module('app')
  .service('AbstractAppRepo', AbstractAppRepo)
  .repo('ProcessorRepo', ProcessorRepo)
  .repo('UserRepo', UserRepo);

export {
  AbstractAppRepo,
  ProcessorRepo,
  UserRepo
};