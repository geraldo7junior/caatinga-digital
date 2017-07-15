<?php
require 'Slim/Slim.php';

$app = new Slim();

$app->get('/Reports', 'getReports');
$app->get('/Reports/:id', 'getReport');
$app->post('/New_Report', 'addReport');
$app->put('/Reports/:id', 'updatetReport');
$app->delete('/Reports/:id', 'deleteReport');

$app->run();

// Get Database Connection
function DB_Connection() {	
	$dbhost = "127.0.0.1";
	$dbuser = "root";
	$dbpass = "";
	$dbname = "caatinga_reports";
	$dbh = new PDO("mysql:host=$dbhost;dbname=$dbname", $dbuser, $dbpass);	
	$dbh->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
	return $dbh;
}
//Get Report Details
function getReports() {
	$sql = "select id,Place,Datet,Description,Contact,Status FROM reports";
	try {
		$db = DB_Connection();
		$stmt = $db->query($sql);  
		$list = $stmt->fetchAll(PDO::FETCH_OBJ);
		$db = null;
		echo json_encode($list);
	} catch(PDOException $e) {
		echo '{"error":{"text":'. $e->getMessage() .'}}'; 
	}
}

// Add new Report to the Database
function addReport() {
	$request = Slim::getInstance()->request();
	$cus = json_decode($request->getBody());

	$sql = "INSERT INTO reports (Place, Datet, Description, Contact, Status) VALUES (:place, :datet, :description, :contact, :status)";
	try {
		$db = DB_Connection();
		$stmt = $db->prepare($sql);  
		$stmt->bindParam("place", $cus->Place);
		$stmt->bindParam("datet", $cus->Datet);
		$stmt->bindParam("description", $cus->Description);
		$stmt->bindParam("contact", $cus->Contact);
		$stmt->bindParam("status", $cus->Status);
		$stmt->execute();
		$cus->id = $db->lastInsertId();
		$db = null;
		echo json_encode($cus); 
	} catch(PDOException $e) {
		echo '{"error":{"text":'. $e->getMessage() .'}}'; 
	}
}
// GET One Report Details
function getReport($id) {
	$sql = "select id,Place,Datet,Description,Contact,Status FROM reports WHERE id=".$id." ORDER BY id";
	try {
		$db = DB_Connection();
		$stmt = $db->query($sql);  
		$list = $stmt->fetchAll(PDO::FETCH_OBJ);
		$db = null;
		echo json_encode($list);
	} catch(PDOException $e) {
		echo '{"error":{"text":'. $e->getMessage() .'}}'; 
	}
}
//Updatet Report Details
function updatetReport($id) {
	$request = Slim::getInstance()->request();
	$cus = json_decode($request->getBody());

	$sql = "UPDATE reports SET Place=:place,Datet=:datet,Description=:description, Contact=:contact, Status=:status WHERE id=:id";
	try {
		$db = DB_Connection();
		$stmt = $db->prepare($sql);  
		$stmt->bindParam("place", $cus->Place);
		$stmt->bindParam("datet", $cus->Datet);
		$stmt->bindParam("description", $cus->Description);
		$stmt->bindParam("contact", $cus->Contact);
		$stmt->bindParam("status", $cus->Status);
		$stmt->bindParam("id", $id);
		$stmt->execute();
		$db = null;
		echo json_encode($cus); 
	} catch(PDOException $e) {
		echo '{"error":{"text":'. $e->getMessage() .'}}'; 
	}
}
//DELETE Report From the Database
function deleteReport($id) {
	$sql = "DELETE FROM reports WHERE id=".$id;
	try {
		$db = DB_Connection();
		$stmt = $db->query($sql);  
		$list = $stmt->fetchAll(PDO::FETCH_OBJ);
		$db = null;
		echo json_encode($list);
	} catch(PDOException $e) {
		echo '{"error":{"text":'. $e->getMessage() .'}}'; 
	}
}

?>