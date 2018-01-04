@extends('admin_template') @section('title', 'Page Title')



<!-- Main content -->
@section('content')


<!-- SELECT2 EXAMPLE -->
<div class="box box-default">
    <div class="box-header with-border">
        <h3 class="box-title">Update Student</h3>


    </div>
    <div class="box-body">
        <div class="row">
            <form action="/instructor/edit/{{$inEdit->id}}" method="post">
                {{csrf_field()}}
                <div class="col-md-6">
                    <div class="form-group">
                        <label>ID</label>
                        <input class="form-control" type="text" name="txtID" value="{{$inEdit->id}}">
                    </div>
                    <!-- /.form-group -->
                    <div class="form-group">
                        <label>Name</label>
                        <input class="form-control" type="text" name="txtName" value="{{$inEdit->insName}}">
                    </div>
                    <div class="form-group">
                        <label>Address</label>
                        <input class="form-control" type="text" name="txtAddress" value="{{$inEdit->insAddress}}">
                    </div>
                    <div class="form-group">
                        <label>Birthday</label>
                        <input class="form-control" type="text" name="txtBirthday" id="datepicker" value="{{$inEdit->insBirthDay}}">
                    </div>
                    <div class="form-group">
                        <label>StartDate</label>
                        <input class="form-control" type="text" name="txtStartDate" id="datepicker1" value="{{$inEdit->insStartDate}}">
                    </div>
                    
                    <!-- /.form-group -->
                </div>
                <!-- /.col -->
                <div class="col-md-6">
                    <div class="form-group">
                        <label>Code</label>
                        <input class="form-control" type="text" name="txtCode" value="{{$inEdit->insCode}}">
                    </div>
                    <!-- /.form-group -->
                    <div class="form-group">
                        <label>Phone</label>
                        <input class="form-control" type="text" name="txtPhone" value="{{$inEdit->insPhone}}">
                    </div>
                    <div class="form-group" style="margin-bottom: 19px;">
                        <label>Gender</label>
                        <input class="form-control hidden" type="text" name="txtGender" value="{{$inEdit->insGender}}">
                        <div class="radio radio-primary gender">
                            <input type="radio" name="gender" id="radio1" value="Male" {{($inEdit->insGender=='Male')?'checked':''}}>
                            <label for="radio1">Male</label>

                            <input type="radio" name="gender" id="radio2" value="Female" style="margin-left: 20px;"
                            {{($inEdit->insGender=='Female')?'checked':''}}>
                            <label for="radio2" style="margin-left: 40px;">Female</label>
                        </div>

                    </div>
                    <div class="form-group">
                        <label>Username</label>
                        <input class="form-control" type="text" name="txtUsername" value="{{$inEdit->username}}">
                    </div>
                    <div class="form-group">
                        <label>Password</label>
                        <input class="form-control" type="text" name="txtPassword" value="{{$inEdit->password}}">
                    </div>
                    <!-- /.form-group -->
                </div>

                <div style="text-align: center;">
                    <input class="btn btn-success" type="submit" value="Update">
                    <button type="button" class="btn btn-default" style="margin-left: 20px;" >Cancel</button>
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


</script>

@endsection
