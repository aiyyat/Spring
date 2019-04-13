'use strict';

App.controller('MemberController', ['$scope', 'MemberService', function($scope, MemberService) {
          var self = this;
          self.member={id:null,name:'',role:'',email:''};
          self.members=[];
              
          self.fetchAllMembers = function(){
              MemberService.fetchAllMembers()
                  .then(
      					       function(d) {
      						        self.members = d;
      					       },
            					function(errResponse){
            						console.error('Error while fetching Currencies');
            					}
      			       );
          };
           
          self.createMember = function(member){
              MemberService.createMember(member)
		              .then(
                      self.fetchAllMembers, 
				              function(errResponse){
					               console.error('Error while creating Member.');
				              }	
                  );
          };

         self.updateMember = function(member, id){
              MemberService.updateMember(member, id)
		              .then(
				              self.fetchAllMembers, 
				              function(errResponse){
					               console.error('Error while updating Member.');
				              }	
                  );
          };

         self.deleteMember = function(id){
              MemberService.deleteMember(id)
		              .then(
				              self.fetchAllMembers, 
				              function(errResponse){
					               console.error('Error while deleting Member.');
				              }	
                  );
          };

          self.fetchAllMembers();

          self.submit = function() {
              if(self.member.id==null){
                  console.log('Saving New Member', self.member);    
                  self.createMember(self.member);
              }else{
                  self.updateMember(self.member, self.member.id);
                  console.log('Member updated with id ', self.member.id);
              }
              self.reset();
          };
              
          self.edit = function(id){
              console.log('id to be edited', id);
              for(var i = 0; i < self.members.length; i++){
                  if(self.members[i].id == id) {
                     self.member = angular.copy(self.members[i]);
                     break;
                  }
              }
          };
              
          self.remove = function(id){
              console.log('id to be deleted', id);
              if(self.member.id === id) {//clean form if the member to be deleted is shown there.
                 self.reset();
              }
              self.deleteMember(id);
          };

          
          self.reset = function(){
              self.member={id:null,name:'',role:'',email:''};
              $scope.myForm.$setPristine(); //reset Form
          };

      }]);
