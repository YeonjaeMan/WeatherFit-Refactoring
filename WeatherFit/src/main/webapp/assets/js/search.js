
let inputField = document.getElementById('search');

inputField.addEventListener('keyup', function() {
    console.log('입력값:', this.value);
    // 여기에 입력값이 변경될 때마다 실행하고 싶은 코드를 추가
    
    let senddata = { keyWord : this.value }; 
    
    $.ajax({
        url: "Searchs",
        type: "get",
        data: senddata, // 수정된 부분
        dataType: "json",
        success: function(search) {
            console.log(search);
            
            $("#ajaxcontainer").empty(); // 기존의 검색 결과를 지우고 새로운 결과를 추가
            search.forEach(function(item) { // 모든 검색 결과를 순회하여 추가
                $("#ajaxcontainer").append(`
                    ${item.postContent}<br>
                    ${item.userNick}<br>
                    ${item.hashTag}
                `);
                
            });
        },
        error: function(err) {
            console.log("연결 실패");
        }
    });
});
