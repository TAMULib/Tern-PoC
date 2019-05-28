import AdminController from './adminController';
import DirectiveGalleryController from './directiveGalleryController';
import NavigationController from './navigationController';
import ThemeController from './themeController';
import UserRepoController from './userRepoController';

angular.module('app')
  .controller('AdminController', AdminController)
  .controller('DirectiveGalleryController', DirectiveGalleryController)
  .controller('NavigationController', NavigationController)
  .controller('ThemeController', ThemeController)
  .controller('UserRepoController', UserRepoController);

export {
  AdminController,
  DirectiveGalleryController,
  NavigationController,
  ThemeController,
  UserRepoController
};