<?php

namespace App\Http\Controllers;

use Illuminate\Support\Facades\Hash;
use Illuminate\Http\Request;
use Illuminate\Database\Eloquent\ModelNotFoundException;
use App\User;
use App\Home;

class UsersController extends Controller
{
    public function getAllUsers()
    {
        return response()->json(User::with("homes")->get());
    }

    public function signUp(Request $request)
    {
      if($request->has("name") && $request->has("email") && $request->has("birthDate") && $request->has("password") && $request->has("confirmPassword"))
      {
          if($request->password == $request->confirmPassword)
          {
            $user = new User();
            $user->name = $request->name;
            $user->email = $request->email;
            $user->birthDate = $request->birthDate;
            $user->password = Hash::make($request->password);
            $user->save();
            return response()->json(["response" => $user, "status" => "success"]);
          }
          return response()->json(["status" => "failure", "error" => "Passwords don't match"]);
      }
      return response()->json(["status" => "failure", "error" => "Parameters are missing or Invalid Parameter names."]);
    }


    public function login(Request $request)
    {
      if($request->has("email") && $request->has("password"))
      {
        $user = User::where("email", "=", $request->email)->get()->first();
        if($user)
        {
          if(Hash::check($request->password, $user->password))
          {
            //$user = $user->homes;
            return response()->json(["response" => $user, "status" => "success"]);
          }
        }
        return response()->json(["status" => "failure", "error" => "Invalid email/password"]);
      }
      return response()->json(["status" => "failure", "error" => "Invalid email/password"]);
    }


    public function createUser(Request $request)
    {
      $user = new User();

      $user->name = $request->name;
      $user->email = $request->email;
      $user->save();
      return response()->json(["response" => $user, "status" => "success"]);
    }

    public function addNewHome($userId, Request $request)
    {
        try
        {
          $user = User::findOrFail($userId);
          if($request->has("homeName") && $request->has("homeAddress"))
          {
            $newHome = new Home();
            $newHome->name = $request->homeName;
            $newHome->address = $request->homeAddress;
            $newHome->save();
            $newHome->topic = Hash::make($newHome->id);
            $newHome->save();
            $user->homes()->attach($newHome->id);
            return response()->json(["response" => $newHome, "status" => "success"]);
          }
          return response()->json(["status" => "failure", "error" => "Parameters are missing or Invalid Parameter names."]);
        }
        catch (ModelNotFoundException $e)
        {
          return response()->json(["status" => "failure", "error" => "Can't find User with given id"]);
        }
    }

    public function addHome($userId, $homeId)
    {
      try
      {
          $user = User::findOrFail($userId);
          $home = Home::findOrFail($homeId);
          $user->homes()->attach($homeId);

          return response()->json(["response" => $home, "status" => "success"]);
        
        return response()->json(["status" => "failure", "error" => "Parameters are missing or Invalid Parameter names."]);
      }
      catch (ModelNotFoundException $e)
      {
        return response()->json(["status" => "failure", "error" => "Can't find User with given id"]);
      }

    }


    public function getUserById($userId)
    {
      try
      {
        $user = User::findOrFail($userId);
        return response()->json(["response" => $user, "status" => "success"]);
      }
      catch (ModelNotFoundException $e)
      {
        return response()->json(["status" => "failure", "error" => "Can't find User with given id"]);
      }

    }

    public function updateUser($userId, Request $request)
    {
      try
      {

        if($request->has("name") || $request->has("birthDate") || $request->has("password") || $request->has("confirmPassword"))
        {
          $user = User::findOrFail($userId);
          if($request->has("name"))
          {
            $user->name = $request->name;
          }

          if($request->has("birthDate"))
          {
            $user->birthDate = $request->birthDate;
          }

          if($request->has("password") && $request->has("confirmPassword"))
          {
            if($request->password == $request->confirmPassword)
            {
              $user->password = Hash::make($request->password);
            }
            else
            {
                return response()->json(["status" => "failure", "error" => "Password and Confirm password don't match"]);
            }
          }
          else
          {
            return response()->json(["status" => "failure", "error" => "Password and Confirm password don't match"]);
          }
          $user->save();
          return response()->json(["response" => $user, "status" => "success"]);

        }

        return response()->json(["status" => "failure", "error" => "Parameters are missing or Invalid Parameter names."]);
      }
      catch (ModelNotFoundException $e)
      {
        return response()->json(["status" => "failure", "error" => "Can't find User with given id"]);
      }
    }







}
