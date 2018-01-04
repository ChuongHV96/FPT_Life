@extends('admin_template') @section('title', 'Page Title')



<!-- Main content -->
@section('content')

<section class="content">
    <div class="row">
        <div class="col-xs-12">

            <div class="box box-primary">
                <div class="box-header">
                    <h3 class="box-title">List Students</h3>
                    <a href="/student/create" class="btn btn-primary pull-right" >New Student</a>
                </div>
                <!-- /.box-header -->
                <div class="box-body">
                    <table id="example1" class="table table-bordered table-striped">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Code</th>
                                <th>username</th>
                                <th>password</th>
                                <th>Name</th>
                                <th>Phone</th>
                                <th>Address</th>
                                <th>Gender</th>
                                <th>Birthday</th>
                                <th>StartDate</th>
                                <th>EndDate</th>
                                <th>Action</th>
                                <th>Info</th>
                            </tr>
                        </thead>
                        <tbody>
                            @foreach($stus as $stu)
                            <tr style="text-align: center;">
                                <td>{{ $stu->id}}</td>
                                <td>{{ $stu->stuCode}}</td>
                                <td>{{ $stu->username}}</td>
                                <td>{{ $stu->password}}</td>
                                <td>{{ $stu->stuName }}</td>
                                <td>{{ $stu->stuPhone}}</td>
                                <td>{{ $stu->stuAddress}}</td>
                                <td>{{ $stu->stuGender}}</td>
                                <td>{{ $stu->stuBirthday}}</td>
                                <td>{{ $stu->stuStartDate}}</td>
                                <td>{{ $stu->stuEndDate}}</td>
                                <td>
                                    <a href="/student/edit/{{$stu->id}}" class="btn btn-warning fa fa-pencil"></a>
                                    <a href="/student/delete/{{$stu->id}}" class="btn btn-danger fa fa-trash warning-alert" onclick="return confirm('Are you sure you want to delete this item?');"></a>
<!--                                    <a href="/student/delete/{{$stu->id}}" id="delete1" class="btn btn-danger fa fa-trash warning-alert"></a>-->
                                </td>
                                   <td>
                                    <a href="/schedule/{{$stu->id}}" >Show Schedule</a>
                                </td>
                            </tr>
                            @endforeach
                        </tbody>
                    </table>

                            {{csrf_field()}}
                            <div class="box-body">
                                <div class="form-group">
                                    <button type=""><a href="{{URL::to('getExport')}}">Export</a>
                                </div>
                            </div>
                
                </div>
                <!-- /.box-body -->
            </div>
            <!-- /.box -->
        </div>
        <!-- /.col -->
    </div>
    <!-- /.row -->
</section>
<!-- /.content -->

<!-- jQuery 3 -->
<script src="../../bower_components/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="../../bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- DataTables -->
<script src="../../bower_components/datatables.net/js/jquery.dataTables.min.js"></script>
<script src="../../bower_components/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
<!-- SlimScroll -->
<script src="../../bower_components/jquery-slimscroll/jquery.slimscroll.min.js"></script>
<!-- FastClick -->
<script src="../../bower_components/fastclick/lib/fastclick.js"></script>
<!-- AdminLTE App -->
<script src="../../bower_components/admin-lte/dist/js/adminlte.min.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="../../bower_components/admin-lte/dist/js/demo.js"></script>
<!-- SweetAlert -->
<script src="../../bower_components/bootstrap-sweetalert/dist/sweetalert.min.js"></script>

<script>
    $(function(){
        $('#example1').DataTable();
   
       
    });
//    $('#delete1').onclick(function(){
//        swal({
//            title: "Are you sure?",
//            text: "Your will not be able to recover this imaginary file!",
//            type: "warning",
//            showCancelButton: true,
//            confirmButtonClass: "btn-danger",
//            confirmButtonText: "Yes, delete it!",
//            closeOnConfirm: false
//        },
//             function(){
//            swal("Deleted!", "Your imaginary file has been deleted.", "success");
//        });
//    }) ;
    
    
</script>


@endsection




