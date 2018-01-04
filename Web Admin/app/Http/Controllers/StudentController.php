<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;
use App\Http\Requests;
use App\Http\Controllers\Controller;
use App\Student;
use App\Register;
use App\Task;
use App\Classroom;
use Illuminate\Support\Facades\Input;
use Illuminate\Support\Facades\Log;
use Excel;

class StudentController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        //
        $stus = Student::all();
        return view('stu.index', compact('stus'));

    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        //
        return view('stu.create');
    }
    public function createSchedule($id)
    {
        //
        return view('stu.add_schedule', compact('id'));
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        //
        $stus = new Student();
        $stus->id = $request->txtID;
        $stus->stuCode = $request->txtCode;
        $stus->stuName = $request->txtName;
        $stus->stuPhone = $request->txtPhone;
        $stus->stuAddress = $request->txtAddress;
        $stus->stuGender = $request->gender;
        $stus->stuBirthDay = $request->txtBirthday;
        $stus->stuStartDate = $request->txtStartDate;
        $stus->stuEndDate = $request->txtEndDate;
        $stus->username = $request->txtUsername;
        $stus->password = $request->txtPassword;
        $stus->save();
        return redirect('student/index/' .$id);
    }
    public function storeSchedule(Request $request, $id)
    {
        $taskEdit = new Task();
        $taskEdit->id = $request->txtID;
        $taskEdit->course_id = $request->txtName;
        $roomEdit = Classroom::where('classroomCode', $request->txtBirthday)->first();
        Log::info($roomEdit->id);
        $taskEdit->slot_id = $request->txtAddress;
        $taskEdit->classroom_id = $roomEdit->id;
        $taskEdit->date = $request->txtStartDate; 
        $taskEdit->save();
        return redirect('/schedule/'. $id);
    }
    /**
     * Display the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function show($id)
    {
        //
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function edit($id)
    {
        //
        $stuEdit = Student::find($id);
        return view('stu.edit', compact('stuEdit'));
    }
    public function scheduleIndex($id)
    {
        //
        $schedules1 = Register::where('stu_id', $id)->select('course_id')->get();
        // Log::info($schedules1);
        $schedules = Task::whereIn('course_id', $schedules1)->get();
        // Log::info($schedules);
        $stuEdit = Student::find($id);
        return view('schedule', compact('schedules','stuEdit'));
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request, $id)
    {
        //
        $stus = Student::find($id);
        $stus->id = $request->txtID;
        $stus->stuCode = $request->txtCode;
        $stus->stuName = $request->txtName;
        $stus->stuPhone = $request->txtPhone;
        $stus->stuAddress = $request->txtAddress;
        $stus->stuGender = $request->gender;
        $stus->stuBirthDay = $request->txtBirthday;
        $stus->stuStartDate = $request->txtStartDate;
        $stus->stuEndDate = $request->txtEndDate;
        $stus->username = $request->txtUsername;
        $stus->password = $request->txtPassword;
        $stus->save();
        return redirect('student/index');
    }
    public function editTask($id, $id2)
    {
        $editTask = Task::find($id);
        return view ('stu.edit_schedule', compact('editTask','id2'));

    }
    public function updateTask(Request $request, $id, $id2)
    {
        $taskEdit =Task::find($id);
        $taskEdit->id = $request->txtID;
        $taskEdit->course_id = $request->txtName;
        $roomEdit = Classroom::where('classroomCode', $request->txtBirthday)->first();
        Log::info($roomEdit->id);
        $taskEdit->slot_id = $request->txtAddress;
        $taskEdit->classroom_id = $roomEdit->id;
        $taskEdit->date = $request->txtStartDate; 
        $taskEdit->save();
        return redirect('/schedule/'. $id2);
    }


    /**
     * Remove the specified resource from storage.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function destroy($id)
    {
        //
        $ins = Student::find($id)->delete();
        return redirect('student/index');
    }
    public function destroySchedule($id, $id2)
    {
        Log::info('zxczxcz');
        $ins = Task::find($id)->delete();
        return redirect('/schedule/'. $id2);
    }
        public function __construct()
    {
        $this->middleware('auth');
    }
    public function getExport(){
        $studentExport = Student::all();
        Excel::create('Export data', function($excel) use($studentExport){
            $excel->sheet('Sheet 1', function($sheet) use($studentExport){
                $sheet->fromArray($studentExport);
            });
        }) ->download('xls');
    }
    public function getScheduleExport($id){
        // $scheduleExport = Student::all();
        $schedules1 = Register::where('stu_id', $id)->select('course_id')->get();
        // Log::info($schedules1);
        $scheduleExport = Task::whereIn('course_id', $schedules1)->get();
        Excel::create('Export data', function($excel) use($scheduleExport){
            $excel->sheet('Sheet 1', function($sheet) use($scheduleExport){
                $sheet->fromArray($scheduleExport);
            });
        }) ->download('xls');
    }

}
