function deselect() {
    $(".add_list_popup").slideFadeToggle(function() { 
        $("#btn_add_to_list").removeClass("selected");
    });    
}

$(function() {
    $("#btn_add_to_list").live('click', function() {
        if($(this).hasClass("selected")) {
            deselect();               
        } else {
            $(this).addClass("selected");
            $(".add_list_popup").slideFadeToggle(function() { 
                $("#select_user-list").focus();
            });
        }
        return false;
    });
    
    $("#add_to_list").live('click', function() {
        $("#add_user_list").submit();
    
        deselect();
        return false;
    });
    
    $("#cancel_add").live('click', function() {
        deselect();
        return false;
    });
});

$.fn.slideFadeToggle = function(easing, callback) {
    return this.animate({ opacity: 'toggle', height: 'toggle' }, "fast", easing, callback);
};


