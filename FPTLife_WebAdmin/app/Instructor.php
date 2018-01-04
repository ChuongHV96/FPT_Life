<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Instructor extends Model
{
	protected $table = 'instructor';
    public $timestamps = false;
        protected $fillable = [
        'insCode', 'insName', 'insPhone', 'insAddress', 'insGender', 'insBirthDay', 'insStartDate', 'username', 'password',
        ]; 
}
