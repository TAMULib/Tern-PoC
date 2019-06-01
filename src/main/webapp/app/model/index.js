import AbstractAppModel from './abstractAppModel';
import Processor from './processor';

angular.module('app')
  .factory('AbstractAppModel', AbstractAppModel)
  .model('Processor', Processor);

export {
  AbstractAppModel,
  Processor
};
