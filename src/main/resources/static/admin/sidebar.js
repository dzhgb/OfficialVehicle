function expandMenu(menu){
    $("#menu li").removeClass("avtive").removeClass("menu-open");
    $("#menu li").each(function(){
        if($(this).attr("menu")==menu){
            $(this).addClass("active");
            $(this).parents("li:first").addClass("active").addClass("menu-open");
        }
    });
}