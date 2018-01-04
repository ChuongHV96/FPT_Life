<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;
use App\Http\Requests;
use App\Http\Controllers\Controller;
use App\Instructor;
use App\Student;
use App\Register;
use App\Course;
use App\Task;
use Illuminate\Support\Facades\Input;
use Illuminate\Support\Facades\Log;
class InstructorController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        //
        $ins = Instructor::all();
        // $ins = Instructor::paginate(3);
        return view('ins.index', compact('ins'));
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        //
        return view('ins.create');
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        $ins = new Instructor();
        $ins->id = $request->txtID;
        $ins->insCode = $request->txtCode;
        $ins->insName = $request->txtName;
        $ins->insPhone = $request->txtPhone;
        $ins->insAddress = $request->txtAddress;
        $ins->insGender = $request->gender;
        $ins->insBirthDay = $request->txtBirthday;
        $ins->insStartDate = $request->txtStartDate;
        $ins->username = $request->txtUsername;
        $ins->password = $request->txtPassword;
        $ins->save();
        return redirect('instructor/index');
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
        // $inEdit = Instructor::where('insCode', $id)->first();
        $inEdit = Instructor::find($id);
        return view('ins.edit', compact('inEdit'));
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
        $ins = Instructor::find($id);
        $ins->id = $request->txtID;
        $ins->insCode = $request->txtCode;
        $ins->insName = $request->txtName;
        $ins->insPhone = $request->txtPhone;
        $ins->insAddress = $request->txtAddress;
        $ins->insGender = $request->gender;
        $ins->insBirthDay = $request->txtBirthday;
        $ins->insStartDate = $request->txtStartDate;
        $ins->username = $request->txtUsername;
        $ins->password = $request->txtPassword;
        $ins->save();
        return redirect('instructor/index');
    }
    public function scheduleIndex($id)
    {
        //
        $schedules1 = Course::where('ins_id', $id)->select('id')->get();
        Log::info($schedules1);
        $schedules = Task::whereIn('course_id', $schedules1)->get();
        // Log::info($schedules);
        $stuEdit = Student::find($id);
        return view('schedule', compact('schedules','stuEdit'));

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
        $ins = Instructor::find($id)->delete();
        return redirect('instructor/index');
    }
    public function __construct()
    {
        $this->middleware('auth');
    }
}
