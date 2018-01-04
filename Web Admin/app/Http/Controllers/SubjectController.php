<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;
use App\Http\Requests;
use App\Http\Controllers\Controller;
use App\Subject;
use Illuminate\Support\Facades\Input;;


class SubjectController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        //
        $subs = Subject::all();
        return view('sub.index', compact('subs'));
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        //
        return view('sub.create');
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
        $sub = new Subject();
        $sub->id = $request->txtID;
        $sub->subCode = $request->txtCode;
        $sub->subName = $request->txtName;
        $sub->description = $request->txtDes;
        $sub->credit = $request->txtCredit;
        $sub->save();
        return redirect('subject/index');
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
        $subEdit = Subject::find($id);
        return view('sub.edit', compact('subEdit'));
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
        $sub = Subject::find($id);
        $sub->id = $request->txtID;
        $sub->subCode = $request->txtCode;
        $sub->subName = $request->txtName;
        $sub->description = $request->txtDes;
        $sub->credit = $request->txtCredit;
        $sub->save();
        return redirect('subject/index');
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
        $sub = Subject::find($id)->delete();
        return redirect('subject/index');
    }
}
