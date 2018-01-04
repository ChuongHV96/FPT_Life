@extends('admin_template') @section('title', 'Page Title')



<!-- Main content -->
@section('content')


<section class="content">
    <div class="row">
        <div class="col-xs-12">

            <div class="box box-primary">
                <div class="box-header">
                    <h3 class="box-title">List Subject</h3>
                    <a href="/subject/create" class="btn btn-primary pull-right">New Subject</a>
                </div>
                <!-- /.box-header -->
                <div class="box-body">
                    <table id="example1" class="table table-bordered table-striped">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Code</th>
                                <th>Name</th>
                                <th>Credit</th>
                                <th>Description</th>
                                <th colspan="2">Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            @foreach($subs as $sub)
                            <tr style="text-align: center;">
                                <td>{{ $sub->id}}</td>
                                <td>{{ $sub->subCode}}</td>
                                <td>{{ $sub->subName}}</td>
                                <td>{{ $sub->credit}}</td>
                                <td>{{ $sub->description}}</td>
                                <td>
                                    <a href="/subject/edit/{{$sub->id}}" class="btn btn-warning fa fa-pencil"></a>
                                </td>
                                <td>
                                    <a href="/subject/delete/{{$sub->id}}" class="btn btn-danger fa fa-trash" onclick="return confirm('Are you sure you want to delete this item?');"></a>
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
    $(function(){
        $('#example1').DataTable()
    })

</script>


@endsection




