<?php

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| contains the "web" middleware group. Now create something great!
|
*/

Route::get('/', function () {
    return view('welcome');
});


Route::get("users/get", "UsersController@getAllUsers");
 Route::post("users/create", "UsersController@createUser");
 Route::post("users/add-home", "UsersController@addHome");




 Route::get("homes/get/{userId}", "HomesController@getAllHomesById");

 Route::get("homes/get", "HomesController@getAllHomes");
 Route::post("homes/create", "HomesController@createHome");

  Route::post("homes/add-device", "HomesController@addDevice");
