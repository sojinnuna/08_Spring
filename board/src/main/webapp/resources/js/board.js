// 삭제 버튼의 click 이벤트 핸들러
// 클래스가 delete인 요소를 찾아서 클릭 이벤트에 함수 연결
document.querySelector('.delete').onclick = function() {
    // confirm: 사용자한테 메세지를 보여주고 확인 여부를 boolean으로 받아온다
    // 취소 클릭시 밑에 코드가 실행되지 않고 return
    if(!confirm('정말 삭제할까요?')) return;
    // 아이디가 deleteForm인 요소를 찾아서 제출시킨다
    document.getElementById('deleteForm').submit();
}

// querySelector(선택자) => querySelector('#deleteForm')
// getElementById(아이디 이름) => getElementById('deleteForm')