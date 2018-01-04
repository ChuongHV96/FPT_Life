@extends('admin_template') @section('title', 'Page Title')



<!-- Main content -->
@section('content')


<!-- SELECT2 EXAMPLE -->
<div class="box box-default">
    <div class="box-header with-border">
        <h3 class="box-title">New Instructor</h3>


    </div>
    <div class="box-body">
        <div class="row">
            <form action="/instructor/add" method="post">
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
                        <label>Address</label>
                        <input class="form-control" type="text" name="txtAddress">
                    </div>
                    <div class="form-group">
                        <label>Birthday</label>
                        <input class="form-control" type="text" name="txtBirthday" id="datepicker">
                    </div>
                    <div class="form-group">
                        <label>StartDate</label>
                        <input class="form-control" type="text" name="txtStartDate" id="datepicker1">
                    </div>
                    
                    <!-- /.form-group -->
                </div>
                <!-- /.col -->
                <div class="col-md-6">
                    <div class="form-group">
                        <label>Code</label>
                        <input class="form-control" type="text" name="txtCode">
                    </div>
                    <!-- /.form-group -->
                    <div class="form-group">
                        <label>Phone</label>
                        <input class="form-control" type="text" name="txtPhone">
                    </div>
                    <div class="form-group" style="margin-bottom: 19px;">
                        <label>Gender</label>
                        <input class="form-control hidden" type="text" name="txtGender">
                        <div class="radio radio-primary gender">
                            <input type="radio" name="gender" id="radio1" value="Male">
                            <label for="radio1">Male</label>

                            <input type="radio" name="gender" id="radio2" value="Female" style="margin-left: 20px;">
                            <label for="radio2" style="margin-left: 40px;">Female</label>
                        </div>

                    </div>
                    <div class="form-group">
                        <label>Username</label>
                        <input class="form-control" type="text" name="txtUsername">
                    </div>
                    <div class="form-group">
                        <label>Password</label>
                        <input class="form-control" type="text" name="txtPassword">
                    </div>
                    <!-- /.form-group -->
                </div>

                <div style="text-align: center;">
                    <input class="btn btn-success" type="submit" value="Save">
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
