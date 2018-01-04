<?php

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| contains the "web" middleware group. Now create something great!
|
*/

Route::get('/', function () {
    return view('auth.login');
});
//Instructor
Route::get('instructor/index', 'InstructorController@index');
Route::get('instructor/create', 'InstructorController@create');
Route::post('instructor/add', 'InstructorController@store');
Route::get('instructor/edit/{id}', 'InstructorController@edit');
Route::post('instructor/edit/{id}', 'InstructorController@update');
Route::get('instructor/delete/{id}', 'InstructorController@destroy');
Route::get('/scheduleIns/{id}', 'InstructorController@scheduleIndex');

//Student
Route::get('student/index', 'StudentController@index');
Route::get('student/create', 'StudentController@create');
Route::get('student/createSchedule/{id}', 'StudentController@createSchedule');
Route::post('student/add', 'StudentController@store');
Route::post('/student/addSchedule/{id}', 'StudentController@storeSchedule');
Route::get('student/edit/{id}', 'StudentController@edit');
Route::get('/schedule/{id}', 'StudentController@scheduleIndex');
Route::post('student/edit/{id}', 'StudentController@update');
Route::post('studentSchedule/edit/{id}/{id2}', 'StudentController@updateTask');
Route::get('student/delete/{id}', 'StudentController@destroy');
Route::get('/scheduleDelete/{id}/{id2}', 'StudentController@destroySchedule');
Route::get('/scheduleEdit/{id}/{id2}', 'StudentController@editTask');

//Subject
Route::get('subject/index', 'SubjectController@index');
Route::get('subject/create', 'SubjectController@create');
Route::post('subject/add', 'SubjectController@store');
Route::get('subject/edit/{id}', 'SubjectController@edit');
Route::post('subject/edit/{id}', 'SubjectController@update');
Route::get('subject/delete/{id}', 'SubjectController@destroy');
Route::get('schedule', 'TasksController@index');
Auth::routes();

Route::get('/home', 'HomeController@index')->name('home');

Route::get('/master', function () {
    // Only authenticated users may enter...
    return view('admin_template');
})->middleware('auth');
Route::resource('tasks', 'TasksController');
Route::get('/getExport','StudentController@getExport');
Route::get('/getScheduleExport/{id}','StudentController@getScheduleExport');