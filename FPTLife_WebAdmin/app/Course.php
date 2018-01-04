<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Course extends Model
{
    //
    protected $table = 'course';
    public function subject()
    {
        return $this->belongsTo('App\Subject', 'sub_id');
    }
}
