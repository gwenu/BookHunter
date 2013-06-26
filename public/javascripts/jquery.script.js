$(document).ready(function() {
   $("#registration_form").validate({
   
        // Specify the validation rules
        rules: {
            first_name: "required",
            last_name: "required",
            username: {
                required: true,
                minlength: 5
            },
            password: {
                required: true,
                minlength: 4
            },
            confirm_pwd: {
                required: true,
                equalTo : "#password"
            },
            
            user_image: {
            accept: "jpg|jpeg"
            }
        },
        
        // Specify the validation error messages
        messages: {
            first_name: "Please enter your first name",
            last_name: "Please enter your last name",
            username: {
              required: "Please provide a username",
              minlength: jQuery.format("Please, at least {0} characters are necessary")
            },
            password: {
                required: "Please provide a password",
                minlength: jQuery.format("Please, at least {0} characters are necessary")
            },
            confirm_pwd: {
                required: "Please confirm the password",
                equalTo : "Your password does not match"
            }
        },
            
        submitHandler: function(form) {
            form.submit();
        }
   });
   
   
   function readURL(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            
            reader.onload = function (e) {
                $('#img_preview').attr('src', e.target.result);
            }
            
            reader.readAsDataURL(input.files[0]);
        }
    }
    
    $("#user_image").change(function(){
        readURL(this);
    });
});


