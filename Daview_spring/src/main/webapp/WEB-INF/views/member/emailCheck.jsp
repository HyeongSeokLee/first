<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이메일 인증코드 입력</title>
<script type="text/javascript">
var numberCode=${numberCode};
var SetTime = 180; //최초 설정 시간 180초
window.onload=function TimerStart(){ //브라우저가 로드되면 항상 TimerStart 함수를 실행
	tid=setInterval('msg_time()',1000)//1초마다 msg_time()함수가 실행
};
/* 메일인증 타이머 함수 */
function msg_time(){
	m=Math.floor(SetTime/60)+"분 "+(SetTime%60)+"초";
	var msg = "남은 인증 시간은 <font color='red'>"+m+"</font>";
	document.getElementById("TimerView").innerHTML=msg; //TimerView에 msg 표시
	SetTime--; //1초씩 감소
	if(SetTime<0){ //메일 인증 코드 시간을 초과하면
		alert("인증시간이 초과되었습다. \n 재발송하여 다시 인증해 주십시오");
		numberCode="@#$@BH#!sasdfsaf@#34ksjdfie%^&*(#$&^*(ji!2131233874"; 
		clearInterval(tid); //타이머 해제
	}
}
	
/* 인증코드확인 여부 함수 */
function chk(){
	var form = document.authenform;
	if(!form.numberCode.value){
		alert("인증코드를 입력해주세요");
		form.numberCode.focus();
		return false;
	}
	if(form.numberCode.value!=numberCode){
		alert("인증코드가 틀렸습니다.");
		form.numberCode.value="";
		form.numberCode.focus();
		return false;
	}
	alert("인증완료");
 	opener.parent.location='/member/insertMember?email=${email}&address=${address}&email_auth=Y'; 
	window.close();
	return true;
}
</script>
</head>
<body>
<div class="container">
<form name="authenform">
	<input type="text" name="numberCode">
	<input type="button" value="인증코드재발송" onclick="history.go(0)"><br>
	<span id="TimerView"></span><br>
	<input type="button" value="확인" onclick="javascript:chk()">
</form>
</div>
</body>
</html>