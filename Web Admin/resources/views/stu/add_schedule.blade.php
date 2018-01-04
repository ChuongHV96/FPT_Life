@extends('admin_template') @section('title', 'Page Title')

<!-- Main content -->
@section('content')
<div class="box box-default">
    <div class="box-header with-border">
        <h3 class="box-title">Update Task</h3>
    </div>
    <div class="box-body">
        <div class="row">
            <form  action="/student/addSchedule/{{$id}}" method="post">
                {{csrf_field()}}
                <div class="col-md-6">
                    <div class="form-group">
                        <label>ID</label>
                        <input class="form-control" type="text" name="txtID">
                    </div>
                    <!-- /.form-group -->
                    <div class="form-group">
                        <label>Name</label>
                        <input class="form-control" type="text" name="txtName">
                    </div>
                    <div class="form-group">
                        <label>Slot</label>
                        <select name="txtAddress" id="" class="form-control">
                            <option value='1'>1</option>
                        	<option value='2'>2</option>
                        	<option value='3'>3</option>
                        	<option value='4'>4</option>
                        	<option value='5'>5</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>Room</label>
                        <select name="txtBirthday" id="" class="form-control">
                        	<option value='101'>101</option>
                        	<option value='201'>201</option>
                        	<option value='301'>301</option>
                        	<option value='401'>401</option>
                        	<option value='501'>501</option>
                        </select>
                    </div>
                    <div class="form-group col-md-6" style="padding-left: 0 !important;">
                        <label>Date</label>
                        <input class="form-control" type="text" name="txtStartDate" id="datepicker1">
                    </div>

                    <!-- /.form-group -->
                </div>
                <!-- /.col -->
                <div style="text-align: center;">
                    <input class="btn btn-success" type="submit" value="ADD">
                    <button type="button" class="btn btn-default" style="margin-left: 20px;">Cancel</button>
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