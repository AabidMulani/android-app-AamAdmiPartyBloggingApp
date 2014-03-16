<?php
$host="mysql.hostinger.in";
$user="u197858136_aabid";
$pass="aabid123456";
$db="u197858136_aabid";
$con=mysql_connect($host,$user,$pass) or die("unable to connect");
$db=mysql_select_db($db) or die("unable to select db");
?>