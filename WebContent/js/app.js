$(document).ready(function(){	

	$("select").selecter();
	$('input[type="file"], input[type="checkbox"]').uniform();
	
	// markdown
	if($('#preview').length != 0) { 
		$('#preview').html(markdown.toHTML($('.textarea-content').val()));
	}
	$('.textarea-content').keyup(function() {
		$('#preview').html(markdown.toHTML($(this).val()));
	});
	
	// layout
    var sidebar = $('.sidebar');
    var content = $('.content');
    sidebar.css('height', $(document).height());
    content.css('height', $(document).height());

	var html = $("html:not(.flexbox)");
	html.find('.content').css('width', $(document).width() - sidebar.width());
	html.find('.sidebar').css('float', 'left');
	html.find('.content').css('float', 'left');

	$(window).resize(function() {
        sidebar.css('height', 0);
        content.css('height', 0);

        sidebar.css('height', $(document).height());
        content.css('height', $(document).height());

		html.find('.content').css('width', $(document).width() - $('.sidebar').width());
		html.find('.sidebar').css('float', 'left');
		html.find('.content').css('float', 'left');
	});
	
	// Sidebar
    $('.sidebar__dropdown > .active').parent().parent().addClass("selected");
	$('.sidebar__dropdown').hide();
	$('.selected').find('.sidebar__dropdown').show();
	
	$('.sidebar__item--dropdown > a').click(function(){
		var item = $(this).parent();
		
		var nope;
		
		if (item.hasClass("selected")){
			nope = true;
		} else {
			nope = false;
		}
		
		$('.selected > a').each(function(){
			var item = $(this).parent();
			var child = item.find('.sidebar__dropdown');
			item.removeClass("selected");
			child.stop().slideUp("slow");
		});
		
		if (!nope){
			item.addClass("selected");
			item.find('.sidebar__dropdown').slideDown();
		}
	});
});