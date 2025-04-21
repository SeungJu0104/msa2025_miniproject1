window.onload = function () {
    const contextPath = document.body.dataset.context; // body에 context 루트를 담아 넘겨준 것을 받는다.
    const navItems = document.querySelectorAll("#nav-items .nav-item");
    const size = document.querySelector("#size");
    const searchValue = document.querySelector("#searchValue");
    const searchForm = document.querySelector("#searchForm");

    const pathMap = {
        "K리그": "/board/getBoard?board=kleague",
        "EPL": "/board/getBoard?board=epl",
        "분데스리가": "/board/getBoard?board=bundesliga",
        "라리가": "/board/getBoard?board=laliga",
        "자유게시판": "/board/getBoard?board=freeboard"
    };

    navItems.forEach(function (item) {
        const link = item.querySelector(".nav-link");
        if (!link) return;

        link.addEventListener("click", function (e) {
            e.preventDefault();

            const textContent = link.textContent.trim();
            const targetPath = pathMap[textContent];

            if (targetPath) {
                window.location.href = contextPath + targetPath;
            }
        });
    });



    size.addEventListener("change", e => {
        location = contextPath + "/board/goBoard?pageNo=1&size=" + size.value + "&searchValue=" + searchValue.value;	
    });
    function pageMove(pageNo) {
        searchForm.pageNo.value = pageNo; 
        searchForm.submit();
    }

};
