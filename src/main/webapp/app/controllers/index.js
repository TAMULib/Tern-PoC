import AdminController from './adminController';
import NavigationController from './navigationController';
import ProcessorController from './processorController';
import UserRepoController from './userRepoController';

angular.module('app')
  .controller('AdminController', AdminController)
  .controller('NavigationController', NavigationController)
  .controller('ProcessorController', ProcessorController)
  .controller('UserRepoController', UserRepoController);

export {
  AdminController,
  NavigationController,
  UserRepoController
};