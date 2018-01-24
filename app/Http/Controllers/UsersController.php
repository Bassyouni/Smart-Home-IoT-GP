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
            return $user;
          }
          return "Error: Passwords don't match";
      }
      return "Error: Parameters are missing or Invalid paramter names.";
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
            return $user;
          }
        }
        return "Error: Invalid email/password";
      }
      return "Error: Invalid email/password";
    }


    public function createUser(Request $request)
    {
      $user = new User();

      $user->name = $request->name;
      $user->email = $request->email;
      $user->save();
      return $user;
    }

    public function addHome(Request $request)
    {
        try
        {
          $user = User::findOrFail($request->userId);
          if($request->has("homeName") && $request->has("homeAddress"))
          {
            $newHome = new Home();
            $newHome->name = $request->homeName;
            $newHome->address = $request->homeAddress;
            $newHome->save();
            $newHome->topic = Hash::make($newHome->id);
            $user->homes()->attach($newHome->id);
            return $newHome;
          }
          return "Error: Parameters are missing or Invalid paramter names.";
        }
        catch (ModelNotFoundException $e)
        {
          return "Error: Can't find User with given id";
        }



    }


}
