<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Illuminate\Database\Eloquent\ModelNotFoundException;
use App\Home;
use App\Device;
use App\User;

class HomesController extends Controller
{
    public function getAllHomes()
    {
      return response()->json(Home::all());
    }

    public function getAllHomesByUserId($userId)
    {
      try
      {
          $user = User::findOrFail($userId);
          $homes = $user->homes()->get();
          return response()->json($homes);
      }
      catch (ModelNotFoundException $e)
      {
        return "Error: Can't find User with given id";
      }



    }

    public function createHome(Request $request)
    {
      $home = new Home();
      $home->name = $request->name;
      $home->save();
      return $home;
    }

    public function addDevice(Request $request)
    {

      $home = Home::findOrFail($request->homeId);

      $device = new Device();
      $device->name = $request->name;
      $device->pin_number = $request->pin_number;
      $device->type = $request->type;
      $device->description = $request->description;

      $home->devices()->save($device);

      return $home;
    }
}
