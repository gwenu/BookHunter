$(document).ready(function(){

$.fn.setInfToPreviewContainer = function(title, author, bookDescription, url, imageRef){
       var strAuthor = author.split("by ");
       var strTitle = title.split("[");
       
       $('input[name="preview_title"]').val($.trim(strTitle[0]));
       $('input[name="preview_author"]').val(strAuthor[1]);
       $("textarea#preview_description").text($.trim(bookDescription));
       $("textarea#preview_url").text(url);
       
       $('#book_img').attr('src', imageRef);
       $('#preview_img_src').val($.trim(imageRef));
  } 

  $("#btn-submit-url").click(function(){
     var url = $('#book-url').val();
     
    if (url.indexOf("http://www.amazon.com") >= 0){
     
	     $.ajax({
	        type: 'GET',
	        url: '/proxy?resourceLink=' + url,
	        dataType: 'HTML',
	        beforeSend: function() {
	           $('#loading').show();
	        },
	        complete: function(){
	           $('#loading').hide();
	        },
	        success: function(data) {
	       		var title = $(data).find("#title").text();
	       		var author = $(data).find("#fbt_x_title span.bxgy-binding-byline span.bxgy-byline-text").text();
	       		var imageRef = $(data).find('#imgBlkFront').attr("src");
	       		var bookDescription = $(data).find(".productDescriptionWrapper").text();
	
	            $.fn.setInfToPreviewContainer(title, author, bookDescription, url, imageRef);
	            
	            $(".add-book-container").hide();
	            $(".book-preview-container").show();
	        },
	        error: function(){
	            alert("Error was occured when try to parse amazon url. Please try again!");
	        }
	      });
      }
      else{
     	alert("Please enter correct url!");
      }
   });
  
  
});

 


