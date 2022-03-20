$(document).ready(function() {
	ajaxGetComment(1);
	function ajaxGetComment(page) {
		let searchParams = new URLSearchParams(window.location.search);
		var grammarId = searchParams.get('idGram');
		/*alert(grammarId);*/

		$.ajax({
			type: "GET",
			url: "http://localhost:8080/api/comment/grammar/id=" + grammarId + "?page=" + page,
			success: function(result) {
				soCau = result.totalElements;
				console.log(result);
				var divCMT = "";
				$.each(result.object, function(i, objres) {
					divCMT += '<tr>'
						+ '<td>'				
						+ '<div class="col-md-2" style="padding: 10px;">'
						+ '<img class="rounded-circle img-fluid" src="https://lovablemessages.com/wp-content/uploads/2021/12/hinh-nen-3D-cho-dien-thoai-lung-linh-16.jpg" alt="" width="100" height="100">'
						+ '</div>'
						+ '<div class="col-md-10 my-date">'
						+ '<h4 style="color: red" id="name_member">' + objres.userName + '</h4>'
						+ '<p name="cmtgrammarcontent">' + objres.content + '</p>'
						+ '<i name="commentdategrammar">' + objres.commentDate + '</i>'
						+ '</div>'
						+ '<td>'
						+ '<tr>'
				});
				$('#lstcommentgrammar').html(divCMT);
			},
			error: function(e) {
				alert("Error: ", e);
				console.log("Error", e);
			}
		});
	};
	
	$('#btnComment').click(function(){
		let searchParams = new URLSearchParams(window.location.search);
		
		var formData = new FormData();
		var grammarId=searchParams.get('idGram');
		/*alert("id bài grammar");*/
		var content = $("#contentComment").val();
		
		/*alert(content);*/
		
		formData.append("content", content);
		
		$.ajax({
			data: formData,
			dataType: "json",
			type: 'POST',
			url: "http://localhost:8080/api/comment/grammar/add-comment/",
			enctype: 'multipart/form-data',
			contentType: 'application/json',
			cache: false,
			processData: false,
			success: function(data){
				ajaxGetComment(1);
				window.location.reload();
				/*var obj = JSON.parse(result);
				
				var name = "<h4 style='color: red' id='name_member'>" + obj.userName + "'</h4>'";
				var contentGrammar = "<p name='cmtgrammarcontent'>" + obj.content + "'</p>'";
				var date = "<i name='commentdategrammar'>" + obj.commentDate + "'</i>'";
				
				$('#lstcommentgrammar').append(name);
				$('#lstcommentgrammar').append(contentGrammar);
				$('#lstcommentgrammar').append(date);	*/			
				
			},
			
			error: function(e){
				alert("error");
				console.log("ERROR: ", e);
			}
		});
	});
	
	$(document).on('click', '.directpage', function(event) {
		var directId = $(this).attr('id');
		var fields = directId.split('.');
		var page = fields[1];
		ajaxGetForCauHoi(page);
	});







});