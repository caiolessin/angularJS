(function(){
	'use strict';
	
	angular.module("tarefas").factory("TarefaFactory", Factory);
	
	Factory.$inject = ["$resource"];
	
	function Factory($resource){
		var factory = {};
		
		var resource = $resource("http://localhost:8080/tarefas-rest/api/tarefas/:id");
		
		factory.save = function(tarefa){
			return resource.save({}, tarefa).$promise;
		}
		
		return factory;
	}
})
