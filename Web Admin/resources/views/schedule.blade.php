@extends('admin_template') @section('title', 'Page Title')
@section('content')
<section class="content">
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <!-- Bootstrap 3.3.6 -->
<link rel="stylesheet" href="../bower_components/bootstrap/dist/css/bootstrap.min.css">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="../bower_components/font-awesome/css/font-awesome.min.css">
  <!-- Ionicons -->
  <link rel="stylesheet" href="../bower_components/Ionicons/css/ionicons.min.css">
  <!-- fullCalendar -->
  <link rel="stylesheet" href="../bower_components/fullcalendar/dist/fullcalendar.min.css">
  <link rel="stylesheet" href="../bower_components/fullcalendar/dist/fullcalendar.print.min.css" media="print">
  <!-- Theme style -->
  <link rel="stylesheet" href="../dist/css/AdminLTE.min.css">
  <!-- AdminLTE Skins. Choose a skin from the css/skins
       folder instead of downloading all of them to reduce the load. -->
  <link rel="stylesheet" href="../dist/css/skins/_all-skins.min.css">




    <!-- Main content -->
    <section class="content" style="padding:0px;">
      <div class="row">
        <!-- /.col -->
        <div class="col-md-12">
          <div class="box box-primary">
            <div class="box-body no-padding"> 
              <!-- THE CALENDAR -->
              <div id="calendar"></div>
            </div>
            <!-- /.box-body -->
          </div>
          <!-- /. box -->
        </div>
        <!-- /.col -->
      </div>
      <!-- /.row -->
    </section>
    <a href="/student/createSchedule/{{$stuEdit->id}}" class="btn btn-primary pull-right" >New Schedule</a>
    <!-- /.content -->
  <!-- /.content-wrapper -->

  <!-- Control Sidebar -->

  <!-- /.control-sidebar -->
  <!-- Add the sidebar's background. This div must be placed
       immediately after the control sidebar -->
  <div class="control-sidebar-bg"></div>
<!-- ./wrapper -->

<!-- jQuery 2.2.3 -->
<script src="../bower_components/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="../bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- jQuery UI 1.11.4 -->
<script src="../bower_components/jquery-ui/jquery-ui.min.js"></script>
<!-- Slimscroll -->
<script src="../bower_components/jquery-slimscroll/jquery.slimscroll.min.js"></script>
<!-- FastClick -->
<script src="../bower_components/fastclick/lib/fastclick.js"></script>
<!-- AdminLTE App -->
<script src="../dist/js/adminlte.min.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="../dist/js/demo.js"></script>
<!-- fullCalendar -->
<script src="../bower_components/moment/moment.js"></script>
<script src="../bower_components/fullcalendar/dist/fullcalendar.min.js"></script>
<script src="../bower_components/jquery-table2excel/dist/jquery.table2excel.min.js"></script>



<!-- Page specific script -->

<!-- <script>
$("#button1").click(function(){

  $("#calendar").table2excel({
    // exclude CSS class
    name: "Worksheet Name",
    filename: "SomeFile" //do not include extension

  });
});
</script> -->
<script>

  $(function () {

    /* initialize the external events
     -----------------------------------------------------------------*/
    function ini_events(ele) {
      ele.each(function () {

        // create an Event Object (http://arshaw.com/fullcalendar/docs/event_data/Event_Object/)
        // it doesn't need to have a start or end
        var eventObject = {
          title: $.trim($(this).text()) // use the element's text as the event title
        };

        // store the Event Object in the DOM element so we can get to it later
        $(this).data('eventObject', eventObject);

        // make the event draggable using jQuery UI
        $(this).draggable({
          zIndex: 1070,
          revert: true, // will cause the event to go back to its
          revertDuration: 0  //  original position after the drag
        });

      });
    }

    ini_events($('#external-events div.external-event'));

    /* initialize the calendar
     -----------------------------------------------------------------*/
    //Date for the calendar events (dummy data)
    var date = new Date();
    var d = date.getDate(),
        m = date.getMonth(),
        y = date.getFullYear();
    $('#calendar').fullCalendar({
       
      header: {
        left: 'prev,next today',
        center: 'title',
        right: 'month,agendaWeek,agendaDay'
      },
      defaultView: 'agendaWeek',
      firstDay : 1,
      minTime     : '07:00:00',
      maxTime     : '17:30:00',
      allDaySlot  : false,
      eventStartEditable: false,
      // slotDuration    : '00:15:00',
      buttonText: {
        today: 'today',
        month: 'month',
        week: 'week',
        day: 'day'
      },
       
      //Random default events
      events : [
                @foreach($schedules as $schedule)
                {   
                  @switch($schedule->slot_id)
                    @case(1)
                    title : '{{$schedule->course1->subject->subCode}} room:{{$schedule->room->classroomCode}}',
                    start : '{{$schedule->date}}T07:00:00',
                    end   : '{{$schedule->date}}T08:30:00',
                    backgroundColor: "#f56954", //red
                    url : '/scheduleEdit/{{$schedule->id}}/{{$stuEdit->id}}',
                    @break
                    @case(2)
                    title : '{{$schedule->course1->subject->subCode}} room:{{$schedule->room->classroomCode}}',
                    start : '{{$schedule->date}}T08:45:00',
                    end   : '{{$schedule->date}}T10:15:00',
                    backgroundColor: "#f39c12", //yellow
                    url : '/scheduleEdit/{{$schedule->id}}/{{$stuEdit->id}}',
                    @break
                    @case(3)
                    title : '{{$schedule->course1->subject->subCode}} room:{{$schedule->room->classroomCode}}',
                    start : '{{$schedule->date}}T10:30:00',
                    end   : '{{$schedule->date}}T12:00:00',
                    backgroundColor: "#0073b7", //Blue
                    url : '/scheduleEdit/{{$schedule->id}}/{{$stuEdit->id}}',
                    @break
                    @case(4)
                    title : '{{$schedule->course1->subject->subCode}} room:{{$schedule->room->classroomCode}}',
                    start : '{{$schedule->date}}T12:30:00',
                    end   : '{{$schedule->date}}T14:00:00',
                    backgroundColor: "#00c0ef", //Info (aqua)
                    url : '/scheduleEdit/{{$schedule->id}}/{{$stuEdit->id}}',
                    @break
                    @case(5)
                    title : '{{$schedule->course1->subject->subCode}} room:{{$schedule->room->classroomCode}}',
                    start : '{{$schedule->date}}T14:15:00',
                    end   : '{{$schedule->date}}T15:45:00',
                    backgroundColor: "#00a65a", //Success (green)
                    url : '/scheduleEdit/{{$schedule->id}}/{{$stuEdit->id}}',
                    @break
                    @case(6)
                    title : '{{$schedule->course1->subject->subCode}} room:{{$schedule->room->classroomCode}}',
                    start : '{{$schedule->date}}T16:00:00',
                    end   : '{{$schedule->date}}T17:30:00',
                    backgroundColor: "#3c8dbc", //Primary (light-blue)
                    url : '/scheduleEdit/{{$schedule->id}}/{{$stuEdit->id}}',
                    @break
                  @endswitch
                },
                @endforeach
            ],
      editable: true,
      droppable: true, // this allows things to be dropped onto the calendar !!!
      drop: function (date, allDay) { // this function is called when something is dropped

        // retrieve the dropped element's stored Event Object
        var originalEventObject = $(this).data('eventObject');

        // we need to copy it, so that multiple events don't have a reference to the same object
        var copiedEventObject = $.extend({}, originalEventObject);

        // assign it the date that was reported
        copiedEventObject.start = date;
        copiedEventObject.allDay = allDay;
        copiedEventObject.backgroundColor = $(this).css("background-color");
        copiedEventObject.borderColor = $(this).css("border-color");

        // render the event on the calendar
        // the last `true` argument determines if the event "sticks" (http://arshaw.com/fullcalendar/docs/event_rendering/renderEvent/)
        $('#calendar').fullCalendar('renderEvent', copiedEventObject, true);

        // is the "remove after drop" checkbox checked?
        if ($('#drop-remove').is(':checked')) {
          // if so, remove the element from the "Draggable Events" list
          $(this).remove();
        }

      }
    });

    /* ADDING EVENTS */
    var currColor = "#3c8dbc"; //Red by default
    //Color chooser button
    var colorChooser = $("#color-chooser-btn");
    $("#color-chooser > li > a").click(function (e) {
      e.preventDefault();
      //Save color
      currColor = $(this).css("color");
      //Add color effect to button
      $('#add-new-event').css({"background-color": currColor, "border-color": currColor});
    });
    $("#add-new-event").click(function (e) {
      e.preventDefault();
      //Get value and make sure it is not null
      var val = $("#new-event").val();
      if (val.length == 0) {
        return;
      }

      //Create events
      var event = $("<div />");
      event.css({"background-color": currColor, "border-color": currColor, "color": "#fff"}).addClass("external-event");
      event.html(val);
      $('#external-events').prepend(event);

      //Add draggable funtionality
      ini_events(event);

      //Remove event from text input
      $("#new-event").val("");
    });
  });

</script>
@endsection
