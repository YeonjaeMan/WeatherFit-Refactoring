$(document).on("click", "#like-heart", function() {
   console.log($(this).val());
   
   $.ajax({
      url: "PostLike",
      data: {"postIdx" : PostIdx},
      dataType: "json",
      type: "get",
      
      success: function(post) {
         console.log(post);
         console.log("연결성공");
         
      },
      error: function() {
         console.log("요청 실패");
      }
   });
});