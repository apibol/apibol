import angular from 'angular';
import 'angular-ui-router';
import configurations from './configurations';
import modules from './modules';

const app = angular.module('bolaoUi', ['ui.router']);
configurations(app);
modules(app);

angular
    .element(document)
    .ready(()=> angular.bootstrap(document, ['bolaoUi']));

export default 'bolaoUi';
