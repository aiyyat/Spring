'use strict';
App.factory('MemberService', ['$http', '$q', function($http, $q){
	return {
			fetchAllMembers: function() {
					return $http.get('member/')
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while fetching members');
										return $q.reject(errResponse);
									}
							);
			},
		    
		    createMember: function(member){
					return $http.post('member/', member)
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while creating member');
										return $q.reject(errResponse);
									}
							);
		    },
		    updateMember: function(member, id){
					return $http.put('member/'+id, member)
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while updating member');
										return $q.reject(errResponse);
									}
							);
			},
		    
			deleteMember: function(id){
					return $http.delete('member/'+id)
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while deleting member');
										return $q.reject(errResponse);
									}
							);
			}
		
	};
}]);
