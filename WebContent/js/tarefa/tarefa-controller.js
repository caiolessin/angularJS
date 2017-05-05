(function(){
	'use strict';
	angular.module("tarefas").controller("TarefaController", Controller);
	Controller.$inject = ["$scope","lowercaseFilter"];
	function Controller($scope,lc){
		
		var self = this;
		
		self.tarefa = {};
		self.tarefas = [];
		self.pesquisa = ""
		self.novaTarefa = function(){
			self.tarefa = {};
		};
		self.salvarTarefa = function(tarefa){
			tarefa.descricao = lc(tarefa.descricao);
			if(tarefa.id){
				editarTarefa(tarefa)
			}else{
				incluirTarefa(tarefa);
			}
			$scope.meuFormulario.$setPristine();
		};
		function incluirTarefa(tarefa){
			tarefa.id = new Date().getTime();
			self.tarefas.push(tarefa);
			self.novaTarefa();
		}
		function editarTarefa(tarefa){
			var pos = -1;
			angular.forEach(this.tarefas,function(item,index){
				if(tarefa.id == item.id){
					pos = index;
				}
			});
			if(pos > -1){
				self.tarefas.splice(pos,1,this.tarefa);
				self.novaTarefa();
			}
		}
		
	}
	self.selecionarTarefa = function(tarefa){
		self.tarefa = angular.copy(tarefa);
	}
})();