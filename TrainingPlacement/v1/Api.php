<?php 

	//getting the dboperation class
	require_once '../includes/DbOperation.php';

	//function validating all the paramters are available
	//we will pass the required parameters to this function 
	function isTheseParametersAvailable($params){
		//assuming all parameters are available 
		$available = true; 
		$missingparams = ""; 
		
		foreach($params as $param){
			if(!isset($_POST[$param]) || strlen($_POST[$param])<=0){
				$available = false; 
				$missingparams = $missingparams . ", " . $param; 
			}
		}
		
		//if parameters are missing 
		if(!$available){
			$response = array(); 
			$response['error'] = true; 
			$response['message'] = 'Parameters ' . substr($missingparams, 1, strlen($missingparams)) . ' missing';
			
			//displaying error
			echo json_encode($response);
			
			//stopping further execution
			die();
		}
	}
	
	//an array to display response
	$response = array();
	
	//if it is an api call 
	//that means a get parameter named api call is set in the URL 
	//and with this parameter we are concluding that it is an api call
	if(isset($_GET['apicall'])){
		
		switch($_GET['apicall']){
			
			case 'createCompany':
				
				//creating a new dboperation object
				$db = new DbOperation();
				
				//creating a new record in the database
				$result = $db->createCompany(
					$_POST['id'],
					$_POST['name'],
					$_POST['detail'],
					$_POST['venue'],
					$_POST['vacancy'],
					$_POST['post'],
					$_POST['vision'],
					$_POST['mission'],
					$_POST['trash'],
					$_POST['date'],
					$_POST['gpa'],
					$_POST['twelveth'],
					$_POST['tenth']
					);
				

				//if the record is created adding success to response
				if($result){
					//record is created means there is no error
					$response['error'] = false; 

					//in message we have a success message
					$response['message'] = 'Company added successfully';

					//and we are getting all the heroes from the database in the response
					$response['companies'] = $db->getCompanies();
				}else{

					//if record is not added that means there is an error 
					$response['error'] = true; 

					//and we have the error message
					$response['message'] = 'Some error occurred please try again';
				}
				
			break; 
			
			//the READ operation
			//if the call is getheroes
			case 'getCompanies':
				$db = new DbOperation();
				$response['error'] = false; 
				$response['message'] = 'Welcome to Training & Placement of Poornima University';
				$response['companies'] = $db->getCompanies();
			break; 
			
			
			//the UPDATE operation
			case 'updateCompany':
				isTheseParametersAvailable(array('id','name','realname','rating','teamaffiliation'));
				$db = new DbOperation();
				$result = $db->updateCompany(
					$_POST['id'],
					$_POST['name'],
					$_POST['realname'],
					$_POST['rating'],
					$_POST['teamaffiliation']
				);
				
				if($result){
					$response['error'] = false; 
					$response['message'] = 'Company updated successfully';
					$response['heroes'] = $db->getCompanies();
				}else{
					$response['error'] = true; 
					$response['message'] = 'Some error occurred please try again';
				}
			break; 
			
			//the delete operation
			case 'deleteCompany':

				//for the delete operation we are getting a GET parameter from the url having the id of the record to be deleted
				if(isset($_GET['id'])){
					$db = new DbOperation();
					if($db->deleteCompany($_GET['id'])){
						$response['error'] = false; 
						$response['message'] = 'Company deleted successfully';
						$response['companies'] = $db->getCompanies();
					}else{
						$response['error'] = true; 
						$response['message'] = 'Some error occurred please try again';
					}
				}else{
					$response['error'] = true; 
					$response['message'] = 'Nothing to delete, provide an id please';
				}
			break; 
			
			//the delete operation
			case 'getCompany':

				//for the delete operation we are getting a GET parameter from the url having the id of the record to be deleted
				if(isset($_GET['company_name'])){
					$db = new DbOperation();
					if($db->getCompany($_GET['company_name'])){
						$response['error'] = false; 
						$response['message'] = 'Please Wait..';
						$response['companies'] = $db->getCompany($_GET['company_name']);
					}else{
						$response['error'] = true; 
						$response['message'] = 'Some error occurred please try again';
					}
				}else{
					$response['error'] = true; 
					$response['message'] = 'Nothing to fetched, provide an company_name please';
				}
			break; 
			
			/*****************************Student************************************************/
			case 'createStudent':
				
				isTheseParametersAvailable(array('name','email','registration_no','mob_no','alt_mob_no','password','gpa','twelveth','tenth'));

				//creating a new dboperation object
				$db = new DbOperation();
				
				//creating a new record in the database
				$result = $db->createStudent(
					$_POST['name'],
					$_POST['email'],
					$_POST['registration_no'],
					$_POST['mob_no'],
					$_POST['alt_mob_no'],
					$_POST['password'],
					$_POST['gpa'],
					$_POST['twelveth'],
					$_POST['tenth']
					);
				

				//if the record is created adding success to response
				if($result){
					//record is created means there is no error
					$response['error'] = false; 

					//in message we have a success message
					$response['message'] = 'You\'re registered successfully';

					//and we are getting all the heroes from the database in the response
					$response['students'] = $db->getStudents();
				}else{

					//if record is not added that means there is an error 
					$response['error'] = true; 

					//and we have the error message
					$response['message'] = 'Some error occurred please try again';
				}
				
			break; 
			
			//the READ operation
			//if the call is getheroes
			case 'getStudents':
				$db = new DbOperation();
				$response['error'] = false; 
				//$response['message'] = 'Welcome to Training & Placement of Poornima University';
				$response['students'] = $db->getStudents();
			break; 
			
			
			//the UPDATE operation
			case 'updateStudent':
				isTheseParametersAvailable(array('name','email','registration_no','mob_no','alt_mob_no','password','gpa','twelveth','tenth'));
				$db = new DbOperation();
				$result = $db->updateStudent(
				$_POST['name'],
					$_POST['email'],
					$_POST['registration_no'],
					$_POST['mob_no'],
					$_POST['alt_mob_no'],
					$_POST['password'],
					$_POST['gpa'],
					$_POST['twelveth'],
					$_POST['tenth']
					);
				
				if($result){
					$response['error'] = false; 
					$response['message'] = 'Student details updated successfully';
					$response['students'] = $db->getCompanies();
				}else{
					$response['error'] = true; 
					$response['message'] = 'Some error occurred please try again';
				}
			break; 
			
			//the delete operation
			case 'deleteStudent':

				//for the delete operation we are getting a GET parameter from the url having the id of the record to be deleted
				if(isset($_GET['name'])){
					$db = new DbOperation();
					if($db->deleteStudent($_GET['name'])){
						$response['error'] = false; 
						$response['message'] = 'Student deleted successfully';
						$response['students'] = $db->getStudents();
					}else{
						$response['error'] = true; 
						$response['message'] = 'Some error occurred please try again';
					}
				}else{
					$response['error'] = true; 
					$response['message'] = 'Nothing to delete, provide an id please';
				}
			break; 
			
			//the delete operation
			case 'getStudent':

				//for the delete operation we are getting a GET parameter from the url having the id of the record to be deleted
				if(isset($_GET['email']))
				{
					
					$db = new DbOperation();
					if($db->getStudent($_GET['email']))
					{
						$response['error'] = false; 
						$response['message'] = 'Please Wait..';
						$response['students'] = $db->getStudent($_GET['email']);
					}else{
						$response['error'] = true; 
						$response['message'] = 'Some error occurred please try again';
					}
				}else{
					$response['error'] = true; 
					$response['message'] = 'Nothing to fetched, provide an name & email passowrd correct..';
				}
			break; 
			
	}
		
	}else{
		//if it is not api call 
		//pushing appropriate values to response array 
		$response['error'] = true; 
		$response['message'] = 'Invalid Request Call,Please Contact to Admin';
	}
	//displaying the response in json structure 
	echo json_encode($response);
?>