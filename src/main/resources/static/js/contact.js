

$("#contact-form").submit(function( event ) {
    event.preventDefault();
    grecaptcha.execute();
});


function onComplete(token){
    var fields = $('#contact-form').serialize();
    var url = "/contactForm";
    var req = '{"test":"test"}'
    var token = $('#_csrf').attr('content');
        var header = $('#_csrf_header').attr('content');
        $.ajax({
                type: "POST",
                url: url,
                data: fields,
                beforeSend: function(){
                    $(".loader").show();
                },
                success: function (data){
                    // data = JSON object that contact.php returns

                    // we receive the type of the message: success or danger and apply it to the .message div
                    var messageAlert = 'alert-' + data.status;
                    var messageText = data.message;

                    // let's compose Bootstrap alert box HTML
                    var alertBox = '<div class="alert ' + messageAlert + ' alert-dismissable"><button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>' + messageText + '</div>';
                    // If we have messageAlert and messageText
                    if (messageAlert && messageText) {
                        // inject the alert to .messages div in our form
                        $('#contact-form').find('.messages').html(alertBox);
                        // empty the form
                        $('#contact-form')[0].reset();
                    }
                },
                error: function (request, error) {
                    console.log("Help");
                    alert(" Can't do because: " + error);
                },
                complete:function(data){
                    $(".loader").hide();
                },
            });
            return false;
}