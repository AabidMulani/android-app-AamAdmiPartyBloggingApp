<?php

include_once 'conn.php';
$category = $_REQUEST["category"];
$start_limit = $_REQUEST["id"];
$limit = $_REQUEST["limit"];
//    History
if ($category == null || $start_limit == null || $limit == null) {
    echo "All Fields Required";
} else {
    if ($category == "History") {
        $query = "Select * from History LIMIT 0,".$limit." WHERE id >= ".$start_limit;
        $var = array();
        $i = 0;
        $result = mysql_query($query) or die("Error" . mysql_error());
        while ($row2 = mysql_fetch_array($result)) {
            $var[$i] = array("id" => $row2['id'], "title" => $row2['title'], "subheading" => $row2['subheading'], "image_path" => $row2['image_path'], "content" => $row2['content'], "created_on" => $row2['created_on']);
            $i = $i + 1;
        }

        if ($var == null) {
            echo "NONE";
        } else {
            echo json_encode($var);
        }
    }



//    ArvindKejriwal
    if ($category == "ArvindKejriwal") {
        $query = "Select * from ArvindKejriwal LIMIT 0,".$limit." WHERE id >= ".$start_limit;
        $var = array();
        $i = 0;
        $result = mysql_query($query) or die("Error" . mysql_error());
        while ($row2 = mysql_fetch_array($result)) {
            $var[$i] = array("id" => $row2['id'], "title" => $row2['title'], "subheading" => $row2['subheading'], "image_path" => $row2['image_path'], "content" => $row2['content'], "created_on" => $row2['created_on']);
            $i = $i + 1;
        }

        if ($var == null) {
            echo "NONE";
        } else {
            echo json_encode($var);
        }
    }
//    Leaders
    if ($category == "Leaders") {
        $query = "Select * from Leaders LIMIT 0,".$limit." WHERE id >= ".$start_limit;
        $var = array();
        $i = 0;
        $result = mysql_query($query) or die("Error" . mysql_error());
        while ($row2 = mysql_fetch_array($result)) {
            $var[$i] = array("id" => $row2['id'], "title" => $row2['title'], "subheading" => $row2['subheading'], "image_path" => $row2['image_path'], "content" => $row2['content'], "created_on" => $row2['created_on']);
            $i = $i + 1;
        }

        if ($var == null) {
            echo "NONE";
        } else {
            echo json_encode($var);
        }
    }
//    PathBreakingNews
    if ($category == "PathBreakingNews") {
        $query = "Select * from PathBreakingNews LIMIT 0,".$limit." WHERE id >= ".$start_limit;
        $var = array();
        $i = 0;
        $result = mysql_query($query) or die("Error" . mysql_error());
        while ($row2 = mysql_fetch_array($result)) {
            $var[$i] = array("id" => $row2['id'], "title" => $row2['title'], "subheading" => $row2['subheading'], "image_path" => $row2['image_path'], "content" => $row2['content'], "created_on" => $row2['created_on']);
            $i = $i + 1;
        }

        if ($var == null) {
            echo "NONE";
        } else {
            echo json_encode($var);
        }
    }
//    CampaignInnovation
    if ($category == "CampaignInnovation") {
        $query = "Select * from CampaignInnovation LIMIT 0,".$limit." WHERE id >= ".$start_limit;
        $var = array();
        $i = 0;
        $result = mysql_query($query) or die("Error" . mysql_error());
        while ($row2 = mysql_fetch_array($result)) {
            $var[$i] = array("id" => $row2['id'], "title" => $row2['title'], "subheading" => $row2['subheading'], "image_path" => $row2['image_path'], "content" => $row2['content'], "created_on" => $row2['created_on']);
            $i = $i + 1;
        }

        if ($var == null) {
            echo "NONE";
        } else {
            echo json_encode($var);
        }
    }
//    LokSabha2014
    if ($category == "LokSabha2014") {
        $query = "Select * from LokSabha2014 LIMIT 0,".$limit." WHERE id >= ".$start_limit;
        $var = array();
        $i = 0;
        $result = mysql_query($query) or die("Error" . mysql_error());
        while ($row2 = mysql_fetch_array($result)) {
            $var[$i] = array("id" => $row2['id'], "title" => $row2['title'], "subheading" => $row2['subheading'], "image_path" => $row2['image_path'], "content" => $row2['content'], "created_on" => $row2['created_on']);
            $i = $i + 1;
        }

        if ($var == null) {
            echo "NONE";
        } else {
            echo json_encode($var);
        }
    }
//    AapCelebs
    if ($category == "AapCelebs") {
        $query = "Select * from AapCelebs LIMIT 0,".$limit." WHERE id >= ".$start_limit;
        $var = array();
        $i = 0;
        $result = mysql_query($query) or die("Error" . mysql_error());
        while ($row2 = mysql_fetch_array($result)) {
            $var[$i] = array("id" => $row2['id'], "title" => $row2['title'], "subheading" => $row2['subheading'], "image_path" => $row2['image_path'], "content" => $row2['content'], "created_on" => $row2['created_on']);
            $i = $i + 1;
        }

        if ($var == null) {
            echo "NONE";
        } else {
            echo json_encode($var);
        }
    }
//    Jokes
    if ($category == "Jokes") {
        $query = "Select * from Jokes LIMIT 0,".$limit." WHERE id >= ".$start_limit;
        $var = array();
        $i = 0;
        $result = mysql_query($query) or die("Error" . mysql_error());
        while ($row2 = mysql_fetch_array($result)) {
            
            $var[$i] = array("id" => $row2['id'], "title" => $row2['title'], "image_url" => $row2['image_url'], "created_on" => $row2['created_on']);
            $i = $i + 1;
        }

        if ($var == null) {
            echo "NONE";
        } else {
            echo json_encode($var);
        }
    }
//    Policies
    if ($category == "Policies") {
        $query = "Select * from Policies LIMIT 0,".$limit." WHERE id >= ".$start_limit;
        $var = array();
        $i = 0;
        $result = mysql_query($query) or die("Error" . mysql_error());
        while ($row2 = mysql_fetch_array($result)) {
            $var[$i] = array("id" => $row2['id'], "title" => $row2['title'], "subheading" => $row2['subheading'], "image_path" => $row2['image_path'], "content" => $row2['content'], "created_on" => $row2['created_on']);
            $i = $i + 1;
        }

        if ($var == null) {
            echo "NONE";
        } else {
            echo json_encode($var);
        }
    }

//    Videos
    if ($category == "Videos") {
        $query = "Select * from Videos LIMIT 0,".$limit." WHERE id >= ".$start_limit;
        $var = array();
        $i = 0;
        $result = mysql_query($query) or die("Error" . mysql_error());
        while ($row2 = mysql_fetch_array($result)) {
            $var[$i] = array("id" => $row2['id'], "title" => $row2['title'], "image_url" => $row2['image_url'], "video_url" => $row2['video_url'], "created_on" => $row2['created_on']);
            $i = $i + 1;
        }

        if ($var == null) {
            echo "NONE";
        } else {
            echo json_encode($var);
        }
    }
}
?>