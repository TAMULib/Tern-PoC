import AbstractAppModel from './abstractAppModel';
import Theme from './theme';

angular.module('app')
  .factory('AbstractAppModel', AbstractAppModel)
  .model('Theme', Theme);

export {
  AbstractAppModel
};
