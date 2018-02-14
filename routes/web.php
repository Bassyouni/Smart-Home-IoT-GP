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


Route::prefix('api')->group(function () {

//................... Users Functions ...................//
Route::get("users/get", "UsersController@getAllUsers");
Route::get("users/get/{userId}", "UsersController@getUserById");
Route::get("users/add-home/{userId}/{homeId}", "UsersController@addHome");
Route::post("users/create", "UsersController@createUser");
Route::post("users/add-new-home/{userId}", "UsersController@addHome");
Route::post("users/auth/signup", "UsersController@signUp");
Route::post("users/auth/login", "UsersController@login");
Route::post("users/update/{userId}", "UsersController@updateUser");



//................... Home Functions .....................//
Route::get("homes/get", "HomesController@getAllHomes");
Route::get("homes/get/{userId}", "HomesController@getAllHomesByUserId");
Route::get("homes/remove-device/{homeId}/{deviceId}", "HomesController@removeDevice");
Route::get("homes/add-user/{homeId}/{userId}", "HomesController@addUser");
Route::get("homes/add-user-by-email/{homeId}/{userId}", "HomesController@addUserByEmail");
Route::get("homes/remove-user/{homeId}/{userId}", "HomesController@removeUser");
Route::get("homes/delete/{homeId}", "HomesController@deleteHome");
Route::get("homes/add-log-to-device/{homeId}/{deviceId}/{command}", "HomesController@addLogToDevice");
Route::get("homes/get-device-logs/{homeId}/{deviceId}", "HomesController@getDeviceLogs");
Route::get("homes/get-devices/{homeId}", "HomesController@getDevicesByHome");
Route::get("homes/get-users/{homeId}", "HomesController@getHomeUsers");

Route::post("homes/create", "HomesController@createHome");
Route::post("homes/add-device/{homeId}", "HomesController@addDevice");
Route::post("homes/update/{homeId}", "HomesController@updateHome");
Route::post("homes/update-device/{homeId}/{deviceId}", "HomesController@updateDevice");


//................. Devices Functions .....................//
Route::get("devices/delete/{deviceId}", "DevicesController@deleteDevice");
Route::get("devices/get/{deviceId}", "DevicesController@getDeviceById");








});
