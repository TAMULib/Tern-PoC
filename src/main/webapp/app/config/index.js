import { apiMapping } from './apiMapping';
import { appConfig } from './appConfig';
import routes from './routes';
import runTime from './runTime';

angular.module('app')
  .constant('apiMapping', apiMapping)
  .constant('appConfig', appConfig)
  .config(routes)
  .run(runTime);

export {
  apiMapping,
  appConfig,
  routes,
  runTime
};