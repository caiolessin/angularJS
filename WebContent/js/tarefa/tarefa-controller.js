(function(){
	
	'use strict';
	angular.module("tarefas").controller("TarefaController", Controller);
	Controller.$inject = ["$scope","lowercaseFilter","TarefaFactory", "toaster"];
	function Controller($scope,lc, tarefaFactory, toaster){
		
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
		self.pesquisar = function(){
			tarefaFactory.search(self.pesquisa).then(function(result){
				self.tarefas = result.data || [];
			});
		}
		function incluirTarefa(tarefa){
			tarefaFactory.save(tarefa).then(function(result){
				self.tarefas.push(result.data);
				self.novaTarefa();
				toaster.pop({
					type: result.status,
					title: "Aviso",
					body: result.mensagem
				});
			});
		}
		function editarTarefa(tarefa){
			
			tarefaFactory.update(tarefa.id, tarefa).then(function(result){				
				var pos = -1;
				angular.forEach(this.tarefas,function(item,index){
					if(result.data.id == item.id){
						pos = index;
					}
				});
				if(pos > -1){
					self.tarefas.splice(pos,1,result.data);
				}
				self.novaTarefa();
				toaster.pop({
					type: result.status,
					title: "Aviso",
					body: result.mensagem
				});
			});
		}
		function init(){
			self.pesquisar();
		}
		init();
	}
	self.selecionarTarefa = function(tarefa){
		tarefaFactory.getById(tarefa.id).then(function(result){
			self.tarefa = result.data;
		});
	}
})();