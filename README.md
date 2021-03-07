# pushnotification
Push notification ornek
kayitol.php servisi
<?php

include("baglan5.php");
$kullaniciadi=$_POST["kullaniciadi"];
$sifre=$_POST["sifre"];
$kod=rand(10000,100000);
$mail=$_POST["mail"];

$ekle=mysqli_query($baglan,"insert into kayitlar (kullaniciadi,sifre,kod,mail,durum) values('$kullaniciadi','$sifre','$kod','$mail','0')");
if($ekle){
    
    function sendMessage($gelenkod) {
    $content      = array(
        "en" => 'Merhaba uyeligiinizin aktif edilmesi icin su kodu giriniz  '.$gelenkod
    );
    $hashes_array = array();
    array_push($hashes_array, array(
        "id" => "like-button",
        "text" => "Like",
        "icon" => "http://i.imgur.com/N8SN8ZS.png",
        "url" => "https://yoursite.com"
    ));
    array_push($hashes_array, array(
        "id" => "like-button-2",
        "text" => "Like2",
        "icon" => "http://i.imgur.com/N8SN8ZS.png",
        "url" => "https://yoursite.com"
    ));
    $fields = array(
        'app_id' => "ed921bd1-5554-44c3-8b7c-e02a4dea5bf2",
        'included_segments' => array(
            'Subscribed Users'
        ),
        'data' => array(
            "foo" => "bar"
        ),
        'contents' => $content,
        'web_buttons' => $hashes_array
    );
    
    $fields = json_encode($fields);
    print("\nJSON sent:\n");
    print($fields);
    
    $ch = curl_init();
    curl_setopt($ch, CURLOPT_URL, "https://onesignal.com/api/v1/notifications");
    curl_setopt($ch, CURLOPT_HTTPHEADER, array(
        'Content-Type: application/json; charset=utf-8',
        'Authorization: Basic ZGVlMzE4M2UtZjY3YS00MTZiLTliMzktMWJhN2JmYjZmNzEx'
    ));
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, TRUE);
    curl_setopt($ch, CURLOPT_HEADER, FALSE);
    curl_setopt($ch, CURLOPT_POST, TRUE);
    curl_setopt($ch, CURLOPT_POSTFIELDS, $fields);
    curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, FALSE);
    
    $response = curl_exec($ch);
    curl_close($ch);
    
    return $response;
}

$response = sendMessage($kod);
$return["allresponses"] = $response;
$return = json_encode($return);

$data = json_decode($response, true);
print_r($data);
$id = $data['id'];
print_r($id);

print("\n\nJSON received:\n");
print($return);
print("\n");
header("location:kodgonder.php");
    
}



?>


kayitolaktif.php servis

<?php

include("baglan5.php");

$kullaniciadi=$_POST["kullaniciadi"];

$kod=$_POST["kod"];

$kontrol=mysqli_query($baglan,"select * from kayitlar where kullaniciadi='$kullaniciadi' and kod='$kod' and durum='0' ");
$sayi=mysqli_num_rows($kontrol);
if(($sayi)>0){
    $guncelle=mysqli_query($baglan,"update kayitlar set durum='1' where kullaniciadi='$kullaniciadi' and kod='$kod'");
    
    if($guncelle){
        
        function sendMessage() {
    $content      = array(
        "en" => 'Uyeliniz aktif edilmistir '
    );
    $hashes_array = array();
    array_push($hashes_array, array(
        "id" => "like-button",
        "text" => "Like",
        "icon" => "http://i.imgur.com/N8SN8ZS.png",
        "url" => "https://yoursite.com"
    ));
    array_push($hashes_array, array(
        "id" => "like-button-2",
        "text" => "Like2",
        "icon" => "http://i.imgur.com/N8SN8ZS.png",
        "url" => "https://yoursite.com"
    ));
    $fields = array(
        'app_id' => "ed921bd1-5554-44c3-8b7c-e02a4dea5bf2",
        'included_segments' => array(
            'Subscribed Users'
        ),
        'data' => array(
            "foo" => "bar"
        ),
        'contents' => $content,
        'web_buttons' => $hashes_array
    );
    
    $fields = json_encode($fields);
    print("\nJSON sent:\n");
    print($fields);
    
    $ch = curl_init();
    curl_setopt($ch, CURLOPT_URL, "https://onesignal.com/api/v1/notifications");
    curl_setopt($ch, CURLOPT_HTTPHEADER, array(
        'Content-Type: application/json; charset=utf-8',
        'Authorization: Basic ZGVlMzE4M2UtZjY3YS00MTZiLTliMzktMWJhN2JmYjZmNzEx'
    ));
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, TRUE);
    curl_setopt($ch, CURLOPT_HEADER, FALSE);
    curl_setopt($ch, CURLOPT_POST, TRUE);
    curl_setopt($ch, CURLOPT_POSTFIELDS, $fields);
    curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, FALSE);
    
    $response = curl_exec($ch);
    curl_close($ch);
    
    return $response;
}


    $response = sendMessage();
$return["allresponses"] = $response;
$return = json_encode($return);

$data = json_decode($response, true);
print_r($data);
$id = $data['id'];
print_r($id);

print("\n\nJSON received:\n");
print($return);
print("\n");
header("location:kodgonder.php");
        
    }
    
   
}




?>
