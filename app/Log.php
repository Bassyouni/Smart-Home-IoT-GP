<?php

namespace App;

use Jenssegers\Mongodb\Eloquent\Model as Eloquent;

class Log extends Eloquent
{
  protected $collection = "logs";

  protected $fillable = [
    "command"
  ];
}
