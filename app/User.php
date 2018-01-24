<?php

namespace App;

use Illuminate\Notifications\Notifiable;

use Jenssegers\Mongodb\Eloquent\Model as Eloquent;

class User extends Eloquent
{
    use Notifiable;

    protected $collection = "users";

    protected $fillable = [
        'name', 'email', 'password',
    ];


    protected $hidden = [
        'password'
    ];


    public function homes()
    {
        return $this->belongsToMany('App\Home', null, 'users', 'homes');
    }


}
