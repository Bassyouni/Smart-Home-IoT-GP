<?php

namespace App;

use Jenssegers\Mongodb\Eloquent\Model as Eloquent;

class Device extends Eloquent
{
  protected $collection = "devices";

  protected $fillable = [
    "name", "pin_number", "type", "description"
  ];


  public function logs()
  {
    return $this->embedsMany("App\Log");
  }

  public function getLogs()
  {
    return $this->logs;
  }




}
