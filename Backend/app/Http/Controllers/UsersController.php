<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\User;
use App\Home;

class UsersController extends Controller
{
    public function getAllUsers()
    {
        return response()->json(User::with("homes")->get());
    }

    public function function signUp(Request $request)
    {
      if($request->has("name") && $request->has("email") && $request->has("birthDate") && $request->has("password"))
      {
          $user = new User();
          $user->name = $request->name;
          $user->email = $request->email;
          $user->birthDate = $request->birthDate;
          $user->save();
          return $user;
      }
      else
      {
        return "Error: Parameters are missing."
      }

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
        $user = User::findOrFail($request->userId);

        $newHome = new Home();
        $newHome->name = $request->name;
        $newHome->save();

        $user->homes()->attach($newHome->id);

        return $user;

    }


}
