<?php
use \Psr\Http\Message\ServerRequestInterface as Request;
use \Psr\Http\Message\ResponseInterface as Response;

require '../vendor/autoload.php';
require_once '../includes/DbOperation.php';

//crating a new app with the config to show errors
$app=new \Slim\App([
    'settings'=>[
        'displayErrorDetails'=>true
    ]
]);
//signup a new user
$app->post('/register',function (Request $request,Response $response){
    if(isTheseParametersAvailable(array('name','department'))){
        $requestData=$request->getParsedBody();
        $name=$requestData['name'];
        $department=$requestData['department'];
        $db=new DbOperation();
        $responseData=array();

        $result=$db->signupStudents($name,$department);
        if($result==USER_CREATED){
            $responseData['error']=false;
            $responseData['message']='signup successful';
            $responseData['user']=$db->getStudentsByName($name);

        }elseif($result==USER_CREATION_FAILED){
            $responseData['error']=true;
            $responseData['message']='error occurred';
        }elseif($result==USER_EXIST){
            $responseData['error']=true;
            $responseData['message']='this name already exits';
        }
        $response->getBody()->write(json_encode($responseData));
    }
});
//getting all user
$app->get('/students',function (Request $request,Response $response){
    $requestDAta=$request->getParsedBody();
    $name=$requestDAta['name'];
    $db=new DbOperation();

    $students=$db->getStudentsByName($name );
    $response->getBody()->write(json_encode(array("students"=>$students)));

});
//process to check parameters
function isTheseParametersAvailable($required_fields)
{
    $error=false;
    $error_fields="";
    $request_params=$_REQUEST;
    foreach ($required_fields as $field){
        if(!isset($request_params[$field])||strlen(trim($request_params[$field]))<=0){
            $error=true;
            $error_fields.=$field.', ';
        }
    }
    if($error){
        $response=array();
        $response['error']=true;
        $response["message"]='Required fields'.substr($error_fields, 0, -2) . ' is missing or empty';
        echo json_encode($response);
        return false;
    }
    return true;
}
$app->run();