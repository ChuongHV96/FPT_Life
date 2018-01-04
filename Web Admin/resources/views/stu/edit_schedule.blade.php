@extends('admin_template') @section('title', 'Page Title')

<!-- Main content -->
@section('content')
<div class="box box-default">
    <div class="box-header with-border">
        <h3 class="box-title">Update Task</h3>
    </div>
    <div class="box-body">
        <div class="row">
            <form action="/studentSchedule/edit/{{$editTask->id}}/{{$id2}}" method="post">
                {{csrf_field()}}
                <div class="col-md-6">
                    <div class="form-group">
                        <label>ID</label>
                        <input class="form-control" type="text" name="txtID" value="{{$editTask->id}}">
                    </div>
                    <!-- /.form-group -->
                    <div class="form-group">
                        <label>Name</label>
                        <input class="form-control" type="text" name="txtName" value="{{$editTask->course_id}}">
                    </div>
                    <div class="form-group">
                        <label>Slot</label>
                        <select name="txtAddress" id="" class="form-control">
                        <!-- <input class="form-control" type="text" name="txtAddress" value="{{$editTask->slot_id}}"> -->
                            <option {{$editTask->slot_id=='1'?'selected':''}} value='1'>1</option>
                        	<option {{$editTask->slot_id=='2'?'selected':''}} value='2'>2</option>
                        	<option {{$editTask->slot_id=='3'?'selected':''}} value='3'>3</option>
                        	<option {{$editTask->slot_id=='4'?'selected':''}} value='4'>4</option>
                        	<option {{$editTask->slot_id=='5'?'selected':''}} value='5'>5</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>Room</label>
                       <!--  <input class="form-control" type="text" name="txtBirthday" 
                        value="{{$editTask->room->classroomCode}}"> -->
                        <select name="txtBirthday" id="" class="form-control">
                        	<option {{$editTask->room->classroomCode=='101'?'selected':''}} value='101'>101</option>
                        	<option {{$editTask->room->classroomCode=='201'?'selected':''}} value='201'>201</option>
                        	<option {{$editTask->room->classroomCode=='301'?'selected':''}} value='301'>301</option>
                        	<option {{$editTask->room->classroomCode=='401'?'selected':''}} value='401'>401</option>
                        	<option {{$editTask->room->classroomCode=='501'?'selected':''}} value='501'>501</option>
                        </select>
                    </div>
                    <div class="form-group col-md-6" style="padding-left: 0 !important;">
                        <label>Date</label>
                        <input class="form-control" type="text" name="txtStartDate" id="datepicker1" 
                        value="{{$editTask->date}}">
                    </div>

                    <!-- /.form-group -->
                </div>
                <!-- /.col -->
                <div style="text-align: center;">
                    <input class="btn btn-success" type="submit" value="Update">
                    <button type="button" class="btn btn-default" style="margin-left: 20px;">Cancel</button>
                </div>
                <div style="text-align: center;">
                    <!-- <button type="button" class="btn btn-default" style="margin-left: 20px;" formaction="/scheduleDelete/{{$editTask->id}}/{{$id2}}">Delete</button> -->
                    <a href="/scheduleDelete/{{$editTask->id}}/{{$id2}}" class="btn btn-warning fa fa-pencil"></a>
                </div>
                <!-- /.col -->
            </form>

        </div>
        <!-- /.row -->
    </div>
    <!-- /.box-body -->
</div>
<!-- /.box -->


<script src="../../bower_components/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="../../bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- bootstrap time picker -->
<script src="../../bower_components/bootstrap-datepicker/dist/js/bootstrap-datepicker.min.js"></script>

<script>
    //Date picker
    $('#datepicker').datepicker({
        format: 'yyyy-mm-dd',
        autoclose: true
    })
    $('#datepicker1').datepicker({
        format: 'yyyy-mm-dd',
        autoclose: true
    })
    $('#datepicker2').datepicker({
        format: 'yyyy-mm-dd',
        autoclose: true
    })

</script>
@endsection