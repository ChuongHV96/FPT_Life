<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Task extends Model
{
    //
    // protected $fillable = ['name', 'description', 'task_date'];
    protected $table = 'schedule';
    public function course1()
    {
        return $this->belongsTo('App\Course', 'course_id');
    }
    public function room()
    {
        return $this->belongsTo('App\Classroom', 'classroom_id');
    }
    public $timestamps = false;
    
}
