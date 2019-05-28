import AbstractAppRepo from './abstractAppRepo';
import ThemeRepo from './themeRepo';
import UserRepo from './userRepo';

angular.module('app')
  .service('AbstractAppRepo', AbstractAppRepo)
  .repo('ThemeRepo', ThemeRepo)
  .repo('UserRepo', UserRepo);

export {
  AbstractAppRepo,
  ThemeRepo,
  UserRepo
};