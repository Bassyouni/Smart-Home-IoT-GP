<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Device;

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
    return $device;
  }
}
