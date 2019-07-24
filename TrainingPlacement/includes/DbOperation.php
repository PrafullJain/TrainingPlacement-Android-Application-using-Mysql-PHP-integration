<?php
 
class DbOperation
{
    //Database connection link
    private $con;
 
    //Class constructor
    function __construct()
    {
        //Getting the DbConnect.php file
        require_once dirname(__FILE__) . '/DbConnect.php';
 
        //Creating a DbConnect object to connect to the database
        $db = new DbConnect();
 
        //Initializing our connection link of this class
        //by calling the method connect of DbConnect class
        $this->con = $db->connect();
    }
	
	/*
	* The create operation
	* When this method is called a new record is created in the database
	*/
	function createCompany($id,$name,$detail,$venue,$vacancy,$post,$vision,$mission,$trash,$date,$gpa,$twelveth,$tenth){
		$stmt = $this->con->prepare("INSERT INTO company ( company_id, company_name,company_detail,
		company_venue,company_vacancy,company_post,company_vision,company_mission,trash,date,gpa,twelveth,tenth) 
		VALUES (?, ?, ?,?, ?, ?, ?,?,?,?,?,?,?)");
		
		$stmt->bind_param("issssisssssss",$id,$name,$detail,$venue,$vacancy,$post,$vision,$mission,$trash,$date,
		$gpa,$twelveth,$tenth);
		if($stmt->execute())
			return true; 
		return false; 
	}

	/*
	* The read operation
	* When this method is called it is returning all the existing record of the database
	*/
	function getCompanies(){
		$stmt = $this->con->prepare("SELECT company_id, company_name,company_detail,company_venue,company_vacancy,
		company_post,company_vision,company_mission,trash,date,gpa,twelveth,tenth FROM company");
		$stmt->execute();
		$stmt->bind_result($id, $name,$detail, $venue,$vacancy,$post,$vision,$mission,
		$trash,$date,$gpa,$twelveth,$tenth);
		
		$companies = array(); 
		
		while($stmt->fetch()){
			$company  = array();
			$company['company_id'] = $id; 
			$company['company_name'] = $name; 			
			$company['company_detail'] = $detail; 
			$company['company_venue'] = $venue; 
			$company['company_vacancy'] = $vacancy; 
			$company['company_post'] = $post; 
			$company['company_vision'] = $vision; 
			$company['company_mission'] = $mission; 
			$company['trash'] = $trash; 
			$company['date'] = $date; 
			$company['gpa'] = $gpa; 
			$company['twelveth'] = $twelveth; 
			$company['tenth'] = $tenth; 
			
			
			array_push($companies, $company); 
		}
		
		return $companies; 
	}
	
	function getCompany($company_name){
		$stmt = $this->con->prepare("SELECT company_id, company_name,company_detail,
		company_venue,company_vacancy,company_post,company_vision,company_mission,trash,date,gpa,twelveth,tenth FROM company where company_name=?");
		$stmt->bind_param("s",$company_name);
		$stmt->execute();
		$stmt->bind_result($id, $name,$detail, $venue,$vacancy,$post,$vision,$mission,$trash,$date,$gpa,$twelveth,$tenth);
		
		$companies = array(); 
		
		while($stmt->fetch()){
			$company  = array();
			$company['company_id'] = $id; 
			$company['company_name'] = $name; 			
			$company['company_detail'] = $detail; 
			$company['company_venue'] = $venue; 
			$company['company_vacancy'] = $vacancy; 
			$company['company_post'] = $post; 
			$company['company_vision'] = $vision; 
			$company['company_mission'] = $mission; 
			$company['trash'] = $trash; 
			$company['date'] = $date; 
			$company['gpa'] = $gpa; 
			$company['twelveth'] = $twelveth; 
			$company['tenth'] = $tenth; 
			
			array_push($companies, $company); 
		}
		
		return $companies; 
	}
	
	/*
	* The update operation
	* When this method is called the record with the given id is updated with the new given values
	
	function updateCompany($id, $name, $realname, $rating, $teamaffiliation){
		$stmt = $this->con->prepare("UPDATE heroes SET name = ?, realname = ?, rating = ?, teamaffiliation = ? WHERE id = ?");
		$stmt->bind_param("ssisi", $name, $realname, $rating, $teamaffiliation, $id);
		if($stmt->execute())
			return true; 
		return false; 
	}
	
	*/
	/*
	* The delete operation
	* When this method is called record is deleted for the given id 
	*/
	function deleteCompany($id){
		$stmt = $this->con->prepare("DELETE FROM company WHERE company_id = ? ");
		$stmt->bind_param("i", $id);
		if($stmt->execute())
			return true; 
		
		return false; 
	}
	/**************************************Student*********************************************/
	function createStudent($name,$email,$registration_no,$mob_no,$alt_mob_no,$password,$gpa,$twelveth,$tenth){
		$stmt = $this->con->prepare("INSERT INTO student(`name`,`email`,`registration_no`,`mob_no`,`alt_mob_no`,`password`
		,`gpa`,`twelveth`,`tenth`)VALUES (?, ?, ?,?, ?, ?,?,?,?)");
		$stmt->bind_param("sssssssss",$name,$email,$registration_no,$mob_no,$alt_mob_no,$password,$gpa,$twelveth,$tenth);
		if($stmt->execute())
			return true; 
		return false; 
	}

	/*
	* The read operation
	* When this method is called it is returning all the existing record of the database
	*/
	function getStudents(){
		$stmt = $this->con->prepare("SELECT `name`,`email`,`registration_no`,`mob_no`,`alt_mob_no`,`password`,`gpa`,
		`twelveth`,`tenth` FROM student");
		$stmt->execute();
		$stmt->bind_result($name,$email, $registration_no,$mob_no,$alt_mob_no,$password,$gpa,$twelveth,$tenth);
		
		$students = array(); 
		
		while($stmt->fetch()){
			$student  = array();
			$student['name'] = $name; 			
			$student['email'] = $email; 
			$student['registration_no'] = $registration_no; 
			$student['mob_no'] = $mob_no; 
			$student['alt_mob_no'] = $alt_mob_no; 
			$student['password'] = $password; 			
			$student['gpa'] = $gpa; 			
			$student['twelveth'] = $twelveth; 			
			$student['tenth'] = $tenth; 			
				
			array_push($students, $student); 
		}
		
		return $students; 
	}
	
	function getStudent($email){
		$stmt = $this->con->prepare("SELECT * FROM student where `email`=?");
		$stmt->bind_param("s",$email);
		$stmt->execute();
		$stmt->bind_result($name,$email, $registration_no,$mob_no,$alt_mob_no,$password,$gpa,$twelveth,$tenth);
		
		$students = array(); 
		
		while($stmt->fetch()){
			$student  = array();
			$student['name'] = $name; 			
			$student['email'] = $email; 
			$student['registration_no'] = $registration_no; 
			$student['mob_no'] = $mob_no; 
			$student['alt_mob_no'] = $alt_mob_no; 
			$student['password'] = $password; 			
			$student['gpa'] = $gpa; 			
			$student['twelveth'] = $twelveth; 			
			$student['tenth'] = $tenth; 			
				
		array_push($students, $student); 
		}
		
		return $students; 
	}
	
	/*
	* The update operation
	* When this method is called the record with the given id is updated with the new given values
	
	function updateStudent($id, $name, $realname, $rating, $teamaffiliation){
		$stmt = $this->con->prepare("UPDATE heroes SET name = ?, realname = ?, rating = ?, teamaffiliation = ? WHERE id = ?");
		$stmt->bind_param("ssisi", $name, $realname, $rating, $teamaffiliation, $id);
		if($stmt->execute())
			return true; 
		return false; 
	}
	
	*/
	/*
	* The delete operation
	* When this method is called record is deleted for the given id 
	*/
	function deleteStudent($name){
		$stmt = $this->con->prepare("DELETE FROM student WHERE `name` = ? ");
		$stmt->bind_param("s", $name);
		if($stmt->execute())
			return true; 
		return false; 
	}
}
?>