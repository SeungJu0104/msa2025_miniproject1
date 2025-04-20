window.onload = function () {

    const basePath = "${PageContext.request.contextPath}"; // contextPath 지원
    const navItems = document.querySelectorAll("#nav-items .nav-item");
  
    const pathMap = {
      "게시판1": "/board/board1?board=board1",
      "게시판2": "/board/board2?board=board2",
      "게시판3": "/board/board3?board=board3",
      "게시판4": "/board/board4?board=board4",
      "게시판5": "/board/board5?board=board5"
    };
  
    navItems.forEach(function (item) {
      const link = item.querySelector(".nav-link");
  
      link.addEventListener("click", function (e) {
        e.preventDefault(); // a 태그 기본 동작 막기
  
        const textContent = link.textContent.trim();
        const targetPath = pathMap[textContent];
        
        window.location.href = basePath + targetPath;
      });
    });
     
}