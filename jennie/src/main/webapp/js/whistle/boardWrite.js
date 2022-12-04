$(document).ready(function(){
	/* 페이지 버튼 클릭이벤트 처리 */
	$('.pbtn').click(function(){
		// 페이지번호 읽고
		var pno = $(this).attr('id');
		// 페이지 번호 셋팅하고
		$('#nowPage').val(pno);
		// 폼 태그 전송
		$('#frm').submit();
	});
	
	$('.menubtn').not('#wbtn').click(function(){
		var bid = $(this).attr('id');
		
		var addr = '/whistle/';
		switch(bid){
		case 'hbtn':
			// 기본 주소를 사용
			break;
		case 'lbtn':
			addr = '/whistle/member/login.blp';
			break;
		case 'jbtn':
			addr = '/whistle/member/join.blp';
			break;
		case 'obtn':
			addr = '/whistle/member/logout.blp';
			break;
		}
		
		$(location).attr('href', addr);
	});
	
	$('#wbtn').click(function(){
		$(location).attr('href', '/whistle/board/boardWrite.blp');
	});
	
	$('#wpbtn').click(function(){
		$('#frm').submit();
	});
	
	function setAttr(el){
		for(i = 1; i <= len ; i++ ){
			$(el).eq(i).attr('id', 'file'+ i);
			$(el).eq(i).attr('name', 'file'+ i);
		}
	}
	
	$('.filebox').on('change', '.upfile', function(){
		var ln = $('.upfile').not('[value]').length;
		alert(ln);
		var sdata = $(this).val();
		var len = $('.upfile').length;
		if(!sdata && len == 1){
			return;
		} else if(!sdata && len > 1){
			$(this).remove();
			return;
		}
		$('.filebox').append('<input type="file" class="w3-input w3-border w3-margin-bottom upfile">');
		
		var el = $('[type="file"]');
		alert('el len : ' + $(el).length);
		for(i = 1; i <= len ; i++ ){
			$(el).eq(i).attr('id', 'file'+ i);
			$(el).eq(i).attr('name', 'file'+ i);
		}
	});
});