<?php
class DbConnect
{
    //setup database link to this variable
    private $con;
    //class constructor made for future use
    function __construct()
    {

    }
    //process to connect database
    function connect()
    {
        //adding constants.php file
        include_once dirname(__FILE__).'/Constants.php';
        //mysql database connection
        $this->con=new mysqli(DB_HOST,DB_USERNAME,DB_PASSWORD,DB_NAME);
        //error check
        if(mysqli_connect_errno()){
            echo "Failed to connect to mysql:".mysqli_connect_error();
            return null;
        }
        //returning to the connection link
        return $this->con;
    }
}