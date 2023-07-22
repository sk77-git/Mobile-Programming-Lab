<?php

$host = 'localhost';
$dbname = 'mobile_programming';
$username = 'root';
$password = '';
try {
    // Connect to the database
    $pdo = new PDO("mysql:host=$host;dbname=$dbname", $username, $password);
    // Set PDO error mode to exception
    $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    // Prepare and execute the SQL query to fetch posts
    $stmt = $pdo->query('SELECT * FROM posts');
    $posts = $stmt->fetchAll(PDO::FETCH_ASSOC);
    // Close the database connection
    $pdo = null;
    // Return the posts array as JSON response
    header('Content-Type: application/json');
    echo json_encode($posts);
} catch (PDOException $e) {
    die('Error: ' . $e->getMessage());
}
