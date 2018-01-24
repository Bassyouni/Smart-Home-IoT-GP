<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Device;
use Illuminate\Database\Eloquent\ModelNotFoundException;

class DevicesController extends Controller
{
  public function getAllDevices()
  {
    return response()->json(Device::all());
  }

  public function createDevice(Request $request)
  {
    $device = new Device();
    $device->name = $request->name;
    $device->pin_number = $request->pin_number;
    $device->type = $request->type;
    $device->description = $request->description;

    $device->save();
    return response()->json(["response" => $device, "status" => "success"]);
  }

  public function deleteDevice($deviceId)
  {
    try
    {
      $device = Device::findOrFail($deviceId);
      $device->delete();
      return response()->json(["status" => "success"]);
    }
    catch (ModelNotFoundException $e)
    {
      return response()->json(["status" => "failure", "error" => "Can't find Device with given id"]);
    }

  }




}
