<?php

session_start();
include_once 'conn.php';

function is_valid_type($file) {
    $valid_types = array("image/jpg", "image/jpeg", "image/bmp", "image/gif", "images/png");
    if (in_array($file['type'], $valid_types))
        return 1;
    return 0;
}

function is_valid_category($tmp) {
    $valid_category = array("History", "ArvindKejriwal", "Leaders", "PathBreakingNews", "CampaignInnovation",
        "LokSabha2014", "AapCelebs", "Jokes", "Policies", "Videos");
    if (in_array($tmp, $valid_category))
        return 1;
    return 0;
}

$image = $_FILES['image'];
$category = $_POST['category'];
$title = $_POST['title'];
$subtitle = $_POST['subtitle'];
$content = $_POST['content'];
$created_on = date("Y-m-d");

$imageLink = $_POST['imageLink'];
$videoLink = $_POST['videoLink'];


$extra1 = "extra1";
$extra2 = "extra2";

$image['name'] = mysql_real_escape_string($image['name']);

$title = mysql_real_escape_string($title);
$subtitle = mysql_real_escape_string($subtitle);
$content = mysql_real_escape_string($content);

$seconds = microtime(true);
$image_tmp_name = round(($seconds * 1000));



if (!is_valid_category($category)) {
    echo "Incorrect category";
} else {
    //History
    if ($category == "History") {
         $TARGET_PATH = "images/History/";
        $TARGET_PATH .= $image_tmp_name . ".jpeg";
        if ($image['name'] == "" || $content == "" || $title == "") {
            $_SESSION['error'] = "All fields are required";
            echo "All fields are required";
        }
        if (!is_valid_type($image)) {
            $_SESSION['error'] = "You must upload a jpeg, gif, or bmp";
        }
        if (move_uploaded_file($image['tmp_name'], $TARGET_PATH)) {
            $sql = "insert into History (title,subheading,image_path,content,created_on,extra1,extra2) values ('$title','$subtitle','" . $TARGET_PATH . "','$content', '$created_on','$extra1','$extra2')";
            $result = mysql_query($sql) or die("Could not insert data into DB: " . mysql_error());
            echo "Done..!";
        } else {
            echo "Error while uploading file";
        }
    }



//ArvindKejriwal
if ($category == "ArvindKejriwal") {
     $TARGET_PATH = "images/ArvindKejriwal/";
        $TARGET_PATH .= $image_tmp_name . ".jpeg";
        if ($image['name'] == "" || $content == "" || $title == "") {
            $_SESSION['error'] = "All fields are required";
            echo "All fields are required";
        }
        if (!is_valid_type($image)) {
            $_SESSION['error'] = "You must upload a jpeg, gif, or bmp";
        }
        if (move_uploaded_file($image['tmp_name'], $TARGET_PATH)) {
            $sql = "insert into ArvindKejriwal (title,subheading,image_path,content,created_on,extra1,extra2) values ('$title','$subtitle','" . $TARGET_PATH . "','$content', '$created_on','$extra1','$extra2')";
            $result = mysql_query($sql) or die("Could not insert data into DB: " . mysql_error());
            echo "Done..!";
        } else {
            echo "Error while uploading file";
        }
    }
//Leaders
if ($category == "Leaders") {
     $TARGET_PATH = "images/Leaders/";
        $TARGET_PATH .= $image_tmp_name . ".jpeg";
        if ($image['name'] == "" || $content == "" || $title == "") {
            $_SESSION['error'] = "All fields are required";
            echo "All fields are required";
        }
        if (!is_valid_type($image)) {
            $_SESSION['error'] = "You must upload a jpeg, gif, or bmp";
        }
        if (move_uploaded_file($image['tmp_name'], $TARGET_PATH)) {
            $sql = "insert into Leaders (title,subheading,image_path,content,created_on,extra1,extra2) values ('$title','$subtitle','" . $TARGET_PATH . "','$content', '$created_on','$extra1','$extra2')";
            $result = mysql_query($sql) or die("Could not insert data into DB: " . mysql_error());
            echo "Done..!";
        } else {
            echo "Error while uploading file";
        }
    }
//PathBreakingNews
if ($category == "PathBreakingNews") {
     $TARGET_PATH = "images/PathBreakingNews/";
        $TARGET_PATH .= $image_tmp_name . ".jpeg";
        if ($image['name'] == "" || $content == "" || $title == "") {
            $_SESSION['error'] = "All fields are required";
            echo "All fields are required";
        }
        if (!is_valid_type($image)) {
            $_SESSION['error'] = "You must upload a jpeg, gif, or bmp";
        }
        if (move_uploaded_file($image['tmp_name'], $TARGET_PATH)) {
            $sql = "insert into PathBreakingNews (title,subheading,image_path,content,created_on,extra1,extra2) values ('$title','$subtitle','" . $TARGET_PATH . "','$content', '$created_on','$extra1','$extra2')";
            $result = mysql_query($sql) or die("Could not insert data into DB: " . mysql_error());
            echo "Done..!";
        } else {
            echo "Error while uploading file";
        }
    }
//CampaignInnovation
if ($category == "CampaignInnovation") {
     $TARGET_PATH = "images/CampaignInnovation/";
        $TARGET_PATH .= $image_tmp_name . ".jpeg";
        if ($image['name'] == "" || $content == "" || $title == "") {
            $_SESSION['error'] = "All fields are required";
            echo "All fields are required";
        }
        if (!is_valid_type($image)) {
            $_SESSION['error'] = "You must upload a jpeg, gif, or bmp";
        }
        if (move_uploaded_file($image['tmp_name'], $TARGET_PATH)) {
            $sql = "insert into CampaignInnovation (title,subheading,image_path,content,created_on,extra1,extra2) values ('$title','$subtitle','" . $TARGET_PATH . "','$content', '$created_on','$extra1','$extra2')";
            $result = mysql_query($sql) or die("Could not insert data into DB: " . mysql_error());
            echo "Done..!";
        } else {
            echo "Error while uploading file";
        }
    }
//LokSabha2014
if ($category == "LokSabha2014") {
     $TARGET_PATH = "images/LokSabha2014/";
        $TARGET_PATH .= $image_tmp_name . ".jpeg";
        if ($image['name'] == "" || $content == "" || $title == "") {
            $_SESSION['error'] = "All fields are required";
            echo "All fields are required";
        }
        if (!is_valid_type($image)) {
            $_SESSION['error'] = "You must upload a jpeg, gif, or bmp";
        }
        if (move_uploaded_file($image['tmp_name'], $TARGET_PATH)) {
            $sql = "insert into LokSabha2014 (title,subheading,image_path,content,created_on,extra1,extra2) values ('$title','$subtitle','" . $TARGET_PATH . "','$content', '$created_on','$extra1','$extra2')";
            $result = mysql_query($sql) or die("Could not insert data into DB: " . mysql_error());
            echo "Done..!";
        } else {
            echo "Error while uploading file";
        }
    }
//AapCelebs
if ($category == "AapCelebs") {
     $TARGET_PATH = "images/AapCelebs/";
        $TARGET_PATH .= $image_tmp_name . ".jpeg";
        if ($image['name'] == "" || $content == "" || $title == "") {
            $_SESSION['error'] = "All fields are required";
            echo "All fields are required";
        }
        if (!is_valid_type($image)) {
            $_SESSION['error'] = "You must upload a jpeg, gif, or bmp";
        }
        if (move_uploaded_file($image['tmp_name'], $TARGET_PATH)) {
            $sql = "insert into AapCelebs (title,subheading,image_path,content,created_on,extra1,extra2) values ('$title','$subtitle','" . $TARGET_PATH . "','$content', '$created_on','$extra1','$extra2')";
            $result = mysql_query($sql) or die("Could not insert data into DB: " . mysql_error());
            echo "Done..!";
        } else {
            echo "Error while uploading file";
        }
    }
//Jokes
if ($category == "Jokes") {
     $TARGET_PATH = "images/Jokes/";
        $TARGET_PATH .= $image_tmp_name . ".jpeg";
        if ($image['name'] == "" || $title == "") {
            $_SESSION['error'] = "All fields are required";
            echo "All fields are required";
        }
        if (!is_valid_type($image)) {
            $_SESSION['error'] = "You must upload a jpeg, gif, or bmp";
        }
        if (move_uploaded_file($image['tmp_name'], $TARGET_PATH)) {
            $sql = "insert into Jokes (title,image_path,created_on,extra1,extra2) values ('$title','" . $TARGET_PATH . "', '$created_on','$extra1','$extra2')";
            $result = mysql_query($sql) or die("Could not insert data into DB: " . mysql_error());
            echo "Done..!";
        } else {
            echo "Error while uploading file";
        }
    }
//Policies
if ($category == "Policies") {
     $TARGET_PATH = "images/Policies/";
        $TARGET_PATH .= $image_tmp_name . ".jpeg";
        if ($image['name'] == "" || $content == "" || $title == "") {
            $_SESSION['error'] = "All fields are required";
            echo "All fields are required";
        }
        if (!is_valid_type($image)) {
            $_SESSION['error'] = "You must upload a jpeg, gif, or bmp";
        }
        if (move_uploaded_file($image['tmp_name'], $TARGET_PATH)) {
            $sql = "insert into Policies (title,subheading,image_path,content,created_on,extra1,extra2) values ('$title','$subtitle','" . $TARGET_PATH . "','$content', '$created_on','$extra1','$extra2')";
            $result = mysql_query($sql) or die("Could not insert data into DB: " . mysql_error());
            echo "Done..!";
        } else {
            echo "Error while uploading file";
        }
    }
//Videos
    if ($category == "Videos") {
        if ($title == "" || $imageLink == "" ||$videoLink == "") {
            $_SESSION['error'] = "All fields are required";
            echo "All fields are required";
        }
        else{
            $sql = "insert into Videos (title,image_url,video_url,created_on,extra1,extra2) values ('$title','$imageLink','$videoLink', '$created_on','$extra1','$extra2')";
            $result = mysql_query($sql) or die("Could not insert data into DB: " . mysql_error());
            echo "Done..!";
        }
    }
    
}
?>