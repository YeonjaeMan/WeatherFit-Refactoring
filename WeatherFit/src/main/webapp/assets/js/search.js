// 'search'라는 id를 가진 입력 필드를 찾아 inputField 변수에 할당
let inputField = document.getElementById('search');

inputField.addEventListener('keyup', function() {
    console.log('입력값:', this.value);
    // 여기에 입력값이 변경될 때마다 실행하고 싶은 코드를 추가
    
    let senddata = { keyWord : this.value }; 
    
    $.ajax({
        url: "Searchs.ajax",
        type: "get",
        data: senddata,
        dataType: "json",
        success: function(search) {
            console.log(search);
            
            // 기존의 검색 결과를 지우고 새로운 결과를 추가
            $("#ajaxcontainer").empty(); 
            // 모든 검색 결과를 순회하여 추가
            search.forEach(function(item) { 
                $("#ajaxcontainer").html(`
                    ${item.userNick}<br>
                    ${item.hashTag}
                `);
                
            });
        },
        error: function(err) {
            console.log("검색 연결 실패");
        }
    });
});
