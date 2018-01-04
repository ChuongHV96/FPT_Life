@extends('admin_template') @section('title', 'Page Title')

<!-- Main content -->
@section('content')

<!-- SELECT2 EXAMPLE -->
<div class="box box-default">
    <div class="box-header with-border">
        <h3 class="box-title">Update Subject</h3>

    </div>
    <div class="box-body">
        <div class="row">
            <form action="/subject/edit/{{$subEdit->id}}" method="post">
                {{csrf_field()}}
                <div class="col-md-6">
                    <div class="form-group">
                        <label>ID</label>
                        <input class="form-control" type="text" name="txtID" value="{{$subEdit->id}}">
                    </div>
                    <!-- /.form-group -->
                    <div class="form-group">
                        <label>Code</label>
                        <input class="form-control" type="text" name="txtCode" value="{{$subEdit->subCode}}">
                    </div>
                    
                    <div class="form-group" >
                        <label>Credit</label>
                        <input class="form-control" type="text" name="txtCredit" value="{{$subEdit->credit}}">
                    </div>
                   
                    

                    <!-- /.form-group -->
                </div>
                <!-- /.col -->
                <div class="col-md-6">
                    <div class="form-group">
                        <label>Name</label>
                        <input class="form-control" type="text" name="txtName" value="{{$subEdit->subName}}">
                    </div>
                    <div class="form-group">
                        <label>Description</label>
                        <textarea class="form-control" id="exampleTextarea" name="txtDes" value="{{$subEdit->description}}" style="height:108px"></textarea>
                        
                    </div>
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


<script>
   

</script>

@endsection

