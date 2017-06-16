<?php

 class DbOperation
 {
     private $con;
     function __construct()
     {
         require_once dirname(__FILE__).'/DbConnect.php';
         $db=new DbConnect();
         $this->con=$db->connect();
     }
     //method to create a new user
     function signupStudents($name,$department)
     {
         if(!$this->isStudentExists($name)){
             $stmt=$this->con->prepare("INSERT INTO students (name, department) VALUES (?,?)");
             $stmt->bind_param("ss",$name,$department);
             if($stmt->execute())
                 return USER_CREATED;
             return USER_CREATION_FAILED;
         }
         return USER_EXIST;
     }
     function isStudentExists($name)
     {
      $stmt=$this->con->prepare("SELECT id FROM students WHERE name = ?");
      $stmt->bind_param("s",$name);
      $stmt->execute();
      $stmt->store_result();
      return $stmt->num_rows>0;
     }
     //process to get students by name
     function getStudentsByName($name){
         $stmt=$this->con->prepare("SELECT id, name, department  FROM students WHERE department=?");
         $stmt->bind_param("s",$name);
         $stmt->execute();
         $stmt->bind_result($id,$name,$department);
         $stmt->fetch();
         $students=array();


         $students['id']=$id;
         $students['name']=$name;
         $students['department']=$department;


         return $students;
     }
 }