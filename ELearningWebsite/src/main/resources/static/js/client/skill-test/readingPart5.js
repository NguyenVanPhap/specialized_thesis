$(document).ready(function() {

	var soCauDung = [];
	var soCau = 0;
	ajaxGetForCauHoi(1);
	startReadingClock();
	function ajaxGetForCauHoi(page) {
		var readingExerciseId = $("#readingExerciseId").val();
		$.ajax({
			type: "GET",
			url: "http://localhost:8080/api/client/reading-exercise/id=" + readingExerciseId + "?page=" + page,
			success: function(result) {
				soCau = result.totalElements;
				console.log(result);
				var divCauHoi = "";
				$.each(result.object.content, function(i, cauHoi) {
					divCauHoi += '<div class="postmetadata" style="margin-left: 0px">'
						+ '<ul><li style="font-weight: bold"><i class="icon-user"></i>Câu ' + cauHoi.number + ': ' + cauHoi.question + '</li></ul></div>'
						+ '<div class="form-group">'
						+ '  <div class="span8" style="float:none; display: inline-block;">'
						+ '   <div class="span4" style="margin-left: 0px">'
						+ '     <label style="float: left;" class="radio-inline radioLabel">'
						+ '       <input type="radio" onclick="markColorReading(' + cauHoi.number + ')" name="' + cauHoi.number + '" id="dapAn_1" value="A">A. ' + cauHoi.answer_1 + '</label></div>'
						+ '     <div class="span4" style="margin-left: 0px">'
						+ '         <label  style="float: left;" class="radio-inline radioLabel">'
						+ '        <input type="radio" onclick="markColorReading(' + cauHoi.number + ')" name="' + cauHoi.number + '" id="answer_2" value="B">B. ' + cauHoi.answer_2 + '</label></div>'
						+ '    <div class="span4" style="margin-left:0px">'
						+ '      <label  style="float: left;" class="radio-inline radioLabel">'
						+ '       <input type="radio" onclick="markColorReading(' + cauHoi.number + ')" name="' + cauHoi.number + '" id="answer_3" value="C">C.' + cauHoi.answer_3 + '</label></div>'
						+ '     <div class="span4" style="margin-left: 0px"><label  style="float: left;" class="radio-inline radioLabel">'
						+ '        <input type="radio" onclick="markColorReading(' + cauHoi.number + ')" name="' + cauHoi.number + '" id="answer_4" value="D">D. ' + cauHoi.answer_4 + '</label></div>'
						+ '        <input type="radio" name="' + cauHoi.number + '" id="correct_answer" value="' + cauHoi.correct_answer + '" class="hidden">'
						+ '   </div>'
					'</div>'

						+ '<hr align="center">';


				});
				$('#cauHoi').html(divCauHoi);
				if (result.object.totalPages > 0) {

					$('.pagination').empty();
					for (var numberPage = 1; numberPage <= result.object.totalPages; numberPage++) {
						var li;
						if (numberPage == page)
							li = '<a class="directpage active" id="direct.' + numberPage + '"> ' + numberPage + '</a>';
						else
							li = '<a class="directpage" id="direct.' + numberPage + '"> ' + numberPage + '</a>';
						$('.pagination').append(li);
					};


				};
				/*if (result.totalPages > 1) {
					for (var numberPage = 1; numberPage <= result.totalPages; numberPage++) {
						var li = '<li class="page-item "><a class="pageNumber-cauHoi">'   numberPage + '</a></li>';
						$('.ul-pagination').append(li);
					};

					// active page pagination
					$(".pageNumber-cauHoi").each(function(index) {
						if ($(this).text() == page) {
							$(this).parent().removeClass().addClass("page-item active");
						}
					});
				};*/
			},
			error: function(e) {
				alert("Error: ", e);
				console.log("Error", e);
			}
		});
	};
	$(document).on('click', '.directpage', function(event) {
		var directId = $(this).attr('id');
		var fields = directId.split('.');
		var page = fields[1];
		ajaxGetForCauHoi(page);
	});

	// event khi click vào phân trang bài đọc
	$(document).on('click', '.pageNumber-cauHoi', function(event) {
		event.preventDefault();
		var page = $(this).text();
		$('#cauHoi').empty();
		$('.ul-pagination li').remove();
		ajaxGetForCauHoi(page);
	});

	// event khi click vào phân trang giải thích bài đọc
	$(document).on('click', '.pageNumber-giaiThich', function(event) {
		event.preventDefault();
		var page = $(this).text();
		$('#cauHoi').empty();
		$('.ul-pagination li').remove();
		ajaxGetForGiaiThich(page);
	});

	//    // event khi click vào button Nop bài
	//	$(document).on('click', '#btnNopBai', function (event){
	//		alert("Số câu trả lời đúng của bạn là: " + nameCheck.length);
	//	});

	var checkExam = function(nameRadio, dapAnChon) {
		var dapAnDung = $('input:radio[name=' + nameRadio + '][id="dapAnDung"]').val();
		if (dapAnDung === dapAnChon) {
			if (soCauDung.indexOf(nameRadio) == -1) {
				soCauDung.push(nameRadio);
			}
		} else {
			soCauDung = $.grep(soCauDung, function(value) {
				return value != nameRadio;
			});
		}
	}
	///////////////////////
	var timecheckReading;
	function startTimerReading(duration, display) {

		var timer = duration, minutes, seconds;

		timecheckReading = setInterval(function() {

			minutes = parseInt(timer / 60, 10)
			seconds = parseInt(timer % 60, 10);

			minutes = minutes < 10 ? "0" + minutes : minutes;
			seconds = seconds < 10 ? "0" + seconds : seconds;

			document.getElementById("timeReading").textContent = minutes + ":" + seconds;
			if (--timer < 0) {
				clearInterval(timecheckReading);
				alert("Đã hết thời gian làm bài test");
				clickSubmitReading();
			}
		}, 1000);



	}

	function startReadingClock() {
		//change time here
		//var fortyFiveMinutes = 0.2 * 30;
		var fortyFiveMinutes = 60 * 45;
		// display = document.querySelectorAll('#timeReading');
		// var check = document.getElementById("timeReading").value();
		//console.log("check:"+check);
		startTimerReading(fortyFiveMinutes, '45:00');
	};


	/////////////////////
	$(document).on('click', '.radioLabel', function(event) {
		var nameRadio = $(this).find('input:radio').attr("name");
		var dapAnChon = $(this).find('input:radio').val();
		console.log(nameRadio);
		checkExam(nameRadio, dapAnChon);
	});

	// click event button Thêm mới bài đọc
	$('#btnNopBai').on("click", function(event) {
		event.preventDefault();
		var confirmation = confirm("Bạn chắc chắn nộp bài ?");
		if (confirmation) {
			$("#ketQuaText").html("Số câu đúng của bạn là: " + soCauDung.length + '/' + soCau);
			$('#nopBaiModal').modal();
		}
	});

	$('#btnLamLai').on("click", function(event) {
		location.reload();
	});

	$('#btnXemGiaiThich').on("click", function(event) {
		event.preventDefault();
		$('#btnNopBai').addClass("hidden");
		$('#btnBaiThiKhac').removeClass("hidden");
		$('#script-detail').removeClass("hidden");
		$('#cauHoi').empty();
		$('.ul-pagination li').remove();
		ajaxGetForGiaiThich(1);
		$('#nopBaiModal').modal('hide');
	});

	function ajaxGetForGiaiThich(page) {
		var baiDocId = $("#readingExerciseId").val();
		$.ajax({
			type: "GET",
			url: "http://localhost:8080/api/client/reading-exercise/id=" + baiDocId + "?page=" + page,
			success: function(result) {
				//				soCau = result.totalElements;
				$.each(result.object.content, function(i, cauHoi) {
					var stt = cauHoi.number;
					var divGiaiThich = '<div class="postmetadata" style="text-align: center"><ul><li><i class="icon-user"></i>Câu ' + stt;
					if (soCauDung.indexOf(stt) > -1) {
						console.log(soCauDung.indexOf(stt) > -1)
						divGiaiThich += " - <span>ĐÚNG</span><br>";
					} else {
						divGiaiThich += " - <span>SAI</span><br>";
					}

					divGiaiThich += '</li></ul></div>'
						+ '<div class="form-group" style="text-align: center">'
						+ '   <textarea style="width: 500px; height: 100px; border: 0px; cursor: default; background-color: white;" disabled> '
						+ cauHoi.ansExplain + '</textarea>'
						+ '</div>'
						+ '<hr align="center">';

					$('#cauHoi').append(divGiaiThich);
				});

				if (result.totalPages > 1) {
					for (var numberPage = 1; numberPage <= result.totalPages; numberPage++) {
						var li = '<li class="page-item "><a class="pageNumber-giaiThich">' + numberPage + '</a></li>';
						$('.ul-pagination').append(li);
					};

					// active page pagination
					$(".pageNumber-giaiThich").each(function(index) {
						if ($(this).text() == page) {
							$(this).parent().removeClass().addClass("page-item active");
						}
					});
				};
			},
			error: function(e) {
				alert("Error: ", e);
				console.log("Error", e);
			}
		});
	};
});