/**
* 
*/ 

$(document).ready(function() {
	var fullText = new Array();
	$(".additem").click(function() {
		$(this).toggleClass("additem");
		$(this).toggleClass("highlighted");
		if($(this).hasClass("highlighted")) {
			fullText.push(parseInt($(this).attr("id")));
			fullText.sort(function(a,b) { return a-b});
			$("#test").val(fullText);
		}
		else{
			var thisID = $(this).attr("id");
			console.log("thisId: " + thisID);
			var index = fullText.indexOf(parseInt(thisID), 0);
			console.log("found in array at: " + index);
			if(index > -1) {
				fullText.splice(index, 1);	
			}
			fullText.sort(function(a,b) { return a-b});
			$("#test").val(fullText);
		}
	});
	
	var removeList = new Array();
	$(".myitem").click(function() {
		$(this).toggleClass("myitem");
		$(this).toggleClass("highlighted");
		if($(this).hasClass("highlighted")) {
			removeList.push(parseInt($(this).attr("id")));
			removeList.sort(function(a,b) { return a-b});
			$("#removelist").val(removeList);
		}
		else{
			var thisID = $(this).attr("id");
			var index = removeList.indexOf(parseInt(thisID));
			if(index > -1) {
				removeList.splice(index, 1);	
			}
			removeList.sort(function(a,b) { return a-b});
			$("#removelist").val(removeList);
		}
		
		if(removeList.length > 0) {
			$("#removefromwatchlist").attr('class', 'active');
		}
		else {
			$("#removefromwatchlist").attr('class', 'hidden');
		}
	});
	
	var pageParts = $(".paginate")
	var numPages = pageParts.length;
	var perPage = 2;
	
	pageParts.slice(perPage).hide();
	
	$("#page-nav").pagination({
		items: numPages,
		itemsOnPage: perPage,
		cssStyle: "compact-theme",
		
		onPageClick: function(pageNum) {
			var start = perPage * (pageNum - 1);
			var end = start + perPage;
			
			pageParts.hide()
						.slice(start,end).show();
		}
	})
	
});



