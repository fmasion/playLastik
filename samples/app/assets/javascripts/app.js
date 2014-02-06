'use strict';

/** app level module which depends on services and controllers */
angular.module('frenchMedia', ['frenchMedia.controllers', 'akoenig.deckgrid']);

/** services module initialization, allows adding services to module in multiple files */
angular.module('frenchMedia.services', []);