@extends('admin_template') @section('title', 'Page Title')



<!-- Main content -->
@section('content')


<section class="content">
    <div class="row">
        <div class="col-xs-12">

            <div class="box box-primary">
                <div class="box-header">
                    <h3 class="box-title">List Instructors</h3>
                    <a href="/instructor/create" class="btn btn-primary pull-right">New Instructor</a>
                </div>
                <!-- /.box-header -->
                <div class="box-body">
                    <table id="example1" class="table table-bordered table-striped">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Code</th>
                                <th>Name</th>
                                <th>Phone</th>
                                <th>Address</th>
                                <th>Gender</th>
                                <th>Birthday</th>
                                <th>StartDay</th>
                                <th>username</th>
                                <th>password</th>
                                <th>Action</th>
                                <th>Schedule</th>
                            </tr>
                        </thead>
                        <tbody>
                            @foreach($ins as $in)
                            <tr>
                                <td>{{ $in->id}}</td>
                                <td>{{ $in->insCode}}</td>
                                <td>{{ $in->insName}}</td>
                                <td>{{ $in->insPhone}}</td>
                                <td>{{ $in->insAddress }}</td>
                                <td>{{ $in->insGender}}</td>
                                <td>{{ $in->insBirthDay}}</td>
                                <td>{{ $in->insStartDate}}</td>
                                <td>{{ $in->username}}</td>
                                <td>{{ $in->password}}</td>
                                <td>
                                    <a href="/instructor/edit/{{$in->id}}" class="btn btn-warning fa fa-pencil"></a>
                                    <a href="/instructor/delete/{{$in->id}}" class="btn btn-danger fa fa-trash" onclick="return confirm('Are you sure you want to delete this item?');"></a>
                                </td>
                                <td>
                                    <a href="/scheduleIns/{{$in->id}}" >Show Schedule</a>
                                </td>
                            </tr>
                            @endforeach
                        </tbody>
                    </table>
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
<script>
    $(document).ready(function() {
        $('#example1').DataTable();
    });

</script>


@endsection
