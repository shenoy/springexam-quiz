 var interval;


    $("#start-btn").click(
    function() {
    var url ="/start";
      alert( "Handler for .start() called." );
      $.getJSON(url);
      $("#start-btn").attr('disabled','disabled');
    }
    );

    $("#stop-btn").click(
    function() {
    var url ="/stop";
      alert( "Handler for .stop() called." );
      $.getJSON(url);
      $("#stop-btn").attr('disabled','disabled');
    });


    $(document).ready(changeText);

    function changeText(){
    innerfunc();
    interval = setInterval(innerfunc,1000);
    function innerfunc(){
    var trurl ="/minutes";
      $.get(trurl, function(data, status){
      if(data)$("#demo").text("Time Remaining : " + data);
      });
    }
    }

    $("#stop-btn").click(clear);

    function clear(){
    clearInterval(interval);
    }


