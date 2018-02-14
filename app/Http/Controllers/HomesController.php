<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Illuminate\Database\Eloquent\ModelNotFoundException;
use App\Home;
use App\Device;
use App\Log;
use App\User;

class HomesController extends Controller
{
    public function getAllHomes()
    {
      return response()->json(Home::with("devices.logs")->get());
    }

    public function getAllHomesByUserId($userId)
    {
      try
      {
          $user = User::findOrFail($userId);
          $homes = $user->homes()->with("devices")->get();

          return response()->json(["response" => $homes, "status" => "success"]);
      }
      catch (ModelNotFoundException $e)
      {
        return response()->json(["status" => "failure", "error" =>  "Can't find User with given id"]);
      }
    }

    public function createHome(Request $request)
    {
      $home = new Home();
      $home->name = $request->name;
      $home->save();
      return $home;
    }

    public function getHomeUsers($homeId)
    {
      try
      {
          $home = Home::findOrFail($homeId);
          $users = array();
          foreach($home->users as $userId)
          {
            $user = User::findOrFail($userId);
            array_push($users, $user);
          }

          return response()->json(["response" => $users, "status" => "success"]);

      }
      catch (ModelNotFoundException $e)
      {
        return response()->json(["status" => "failure", "error" =>  "Can't find Home with given id"]);
      }
    }


    public function addDevice($homeId, Request $request)
    {
      try
      {
          $home = Home::findOrFail($homeId);
          if($request->has("deviceName") && $request->has("pinNumber") && $request->has("deviceType") && $request->has("deviceDescription"))
          {
            $device = new Device();
            $device->name = $request->deviceName;
            $device->pin_number = $request->pinNumber;
            $device->type = $request->deviceType;
            $device->description = $request->deviceDescription;
            $home->devices()->save($device);
            return response()->json(["response" => $device, "status" => "success"]);
          }
          return response()->json(["status" => "failure", "error" => "Parameters are missing or Invalid Parameter names."]);
      }
      catch (ModelNotFoundException $e)
      {
        return response()->json(["status" => "failure", "error" =>  "Can't find Home with given id"]);
      }
    }


    public function updateHome($id, Request $request)
    {
      try
      {
          $home = Home::findOrFail($id);
          if($request->has("homeName") || $request->has("homeAddress"))
          {
            if($request->has("homeName"))
            {
              $home->name = $request->homeName;
            }
            if($request->has("homeAddress"))
            {
              $home->address = $request->homeAddress;
            }
            return response()->json(["response" => $home, "status" => "success"]);
          }
          return response()->json(["status" => "failure", "error" => "Parameter are missing or Invalid Parameter names."]);
      }
      catch (ModelNotFoundException $e)
      {
        return response()->json(["status" => "failure", "error" =>  "Can't find Home with given id"]);
      }
    }


    public function removeDevice($homeId, $deviceId)
    {
      try
      {
        $home = Home::findOrFail($homeId);
        $device = $home->devices()->where("id", "=", $deviceId)->first();
        if($device)
        {
          $device->delete();
          return response()->json(["status" => "success"]);
        }
        return response()->json(["status" => "failure", "error" =>  "Can't find Device with given id"]);
      }
      catch (ModelNotFoundException $e)
      {
        return response()->json(["status" => "failure", "error" =>  "Can't find Home with given id"]);
      }

    }


    public function updateDevice($homeId, $deviceId, Request $request)
    {
      try
      {
        $home = Home::findOrFail($homeId);
        $device = $home->devices()->where("id", "=", $deviceId)->first();
        if($device)
        {
          if($request->has("deviceName") || $request->has("deviceType") || $request->has("deviceDescription") || $request->has("pinNumber"))
          {
            if($request->has("deviceName"))
            {
              $device->name = $request->deviceName;
            }
            if($request->has("deviceType"))
            {
              $device->type = $request->deviceType;
            }
            if($request->has("deviceDescription"))
            {
              $device->description = $request->deviceDescription;
            }
            if($request->has("pinNumber"))
            {
              $device->pin_number = $request->pinNumber;
            }
            $device->save();
            return response()->json(["status" => "success"]);
          }
          return response()->json(["status" => "failure", "error" => "Parameters are missing or Invalid Parameter names."]);

        }
        return response()->json(["status" => "failure", "error" =>  "Can't find Device with given id"]);
      }
      catch (ModelNotFoundException $e)
      {
        return response()->json(["status" => "failure", "error" =>  "Can't find Home with given id"]);
      }

    }


    public function addUser($homeId, $userId)
    {
      try
      {
        $home = Home::findOrFail($homeId);
        $user = User::findOrFail($userId);
        $user->homes()->attach($home);
        $user->save();
        return response()->json(["status" => "success"]);

      }
      catch (ModelNotFoundException $e)
      {
        return response()->json(["status" => "failure", "error" =>  "Can't find Home/User with given id"]);
      }

    }


    public function addUserByEmail($homeId, $userEmail)
    {
      try
      {
        $home = Home::findOrFail($homeId);
        $user = User::where("email", "=", $userEmail)->get()->first();
        if(!$user)
        {
          throw 99;
        }
        $user->homes()->attach($home);
        $user->save();
        return response()->json(["status" => "success"]);

      }
      catch (ModelNotFoundException $e)
      {
        return response()->json(["status" => "failure", "error" =>  "Can't find Home/User with given id/email"]);
      }

    }


    public function removeUser($homeId, $userId)
    {
      try
      {
        $home = Home::findOrFail($homeId);
        $user = User::findOrFail($userId);
        $user->homes()->detach($home);
        $user->save();
        return response()->json(["status" => "success"]);

      }
      catch (ModelNotFoundException $e)
      {
        return response()->json(["status" => "failure", "error" =>  "Can't find Home/User with given id"]);
      }

    }

    public function deleteHome($homeId)
    {
      try
      {
        $home = Home::findOrFail($homeId);
        $home->delete();
        return response()->json(["status" => "success"]);
      }
      catch (ModelNotFoundException $e)
      {
        return response()->json(["status" => "failure", "error" =>  "Can't find Home with given id"]);
      }
    }



    public function addLogToDevice($homeId, $deviceId, $command)
    {
      try
      {
        $home = Home::findOrFail($homeId);
        $device = $home->devices()->where("id", "=", $deviceId)->first();
        if($device)
        {
          $log = new Log();
          $log->command = $command;
          $device->logs()->save($log);
          return response()->json(["status" => "success"]);
        }
        return response()->json(["status" => "failure", "error" =>  "Can't find Device with given id"]);

      }
      catch (ModelNotFoundException $e)
      {
        return response()->json(["status" => "failure", "error" =>  "Can't find Home with given id"]);
      }
    }



    public function getDeviceLogs($homeId, $deviceId)
    {
      try
      {
        $home = Home::findOrFail($homeId);
        $device = $home->devices()->where("id", "=", $deviceId)->first();
        if($device)
        {
          $logs = $device->logs()->get();

          return response()->json(["response" => $logs, "status" => "success"]);
        }
        return response()->json(["status" => "failure", "error" =>  "Can't find Device with given id"]);

      }
      catch (ModelNotFoundException $e)
      {
        return response()->json(["status" => "failure", "error" =>  "Can't find Home with given id"]);
      }
    }



    public function getDevicesByHome($homeId)
    {
      try
      {
        $home = Home::findOrFail($homeId);

        return response()->json(["response" => $home->devices()->get(), "status" => "success"]);

      }
      catch (ModelNotFoundException $e)
      {
        return response()->json(["status" => "failure", "error" =>  "Can't find Home with given id"]);
      }
    }

















}
