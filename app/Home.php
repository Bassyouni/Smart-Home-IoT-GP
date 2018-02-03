<?php

namespace App;

use Jenssegers\Mongodb\Eloquent\Model as Eloquent;

class Home extends Eloquent
{
    protected $collection = "homes";

    protected $fillable = [
      "name", "address", "topic"
    ];



    public function devices()
    {
        return $this->embedsMany('App\Device');
    }

    









}
