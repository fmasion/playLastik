'use strict';

/** Controllers */
angular.module('frenchMedia.controllers', ['akoenig.deckgrid']).
    controller('frenchMediaCtrl',function ($scope, $location, $timeout) {
    	
        $scope.medias = [];
        
        var source = new EventSource("/mediaFeed");
        source.addEventListener('message', function(e) {
        	console.log(e.data);
            $scope.medias.push(JSON.parse(e.data));
            $scope.$digest();
        });

    });
