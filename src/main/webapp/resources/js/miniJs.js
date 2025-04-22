// console.log(contextPath);
// let contextPath = null;
document.addEventListener("DOMContentLoaded", () => {

    contextPath = document.body.dataset.context; // body에 context 루트를 담아 넘겨준 것을 받는다.
    const navItems = document.querySelectorAll("#nav-items .nav-item");
    const pageItems = document.querySelectorAll("#page-items .page-item");
    const size = document.querySelector("#size");
    const searchValue = document.querySelector("#searchValue");
    const searchForm = document.querySelector("#searchForm");
    const goLogin = document.querySelector("#goLogin");
    const loginForm = document.querySelector("#loginForm");
    const goRegister = document.querySelector("#goRegister");
    const memberForm = document.querySelector("#memberForm");
    const memberList = document.getElementById("memberList");
    const idRegex = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,10}$/;
    const pwRegex = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?])[A-Za-z\d!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]{8,12}$/;
    
    console.log(contextPath);

    const pathMap = {
        "K리그": "/board/getBoard?board=kleague",
        "EPL": "/board/getBoard?board=epl",
        "분데스리가": "/board/getBoard?board=bundesliga",
        "라리가": "/board/getBoard?board=laliga",
        "자유게시판": "/board/getBoard?board=freeboard",
        "회원목록": "/member/memberList"
    };

    navItems.forEach(function (item) {
        const link = item.querySelector(".nav-link");
        if (!link) return;
        
        link.addEventListener("click", function (e) {
            preventEvent(e);
            if (pathMap[link.textContent.trim()]) {
                window.location.href = contextPath + pathMap[link.textContent.trim()];
            }
        });
    });

    pageItems.forEach((item) => {
        const pageLink = item.querySelector(".page-link");
        if(!pageLink) return;

        pageLink.addEventListener("click", (e) => {
            preventEvent(e);
            pageMove(pageLink.dataset.page);
        });
    });

    // ?. => 옵셔널 체이닝 => 앞의 값이 null 이거나 undefined인 경우 에러내지 않고, undefined를 반환시켜준다.
    if (sessionStorage.getItem('id') != null) {
        document.querySelectorAll(".nologin").forEach(btn => btn?.style && (btn.style.display = 'none'));
        document.querySelectorAll(".login").forEach(btn => btn?.style && (btn.style.display = 'inline-block'));
    } else {
        document.querySelectorAll(".nologin").forEach(btn => btn?.style && (btn.style.display = 'inline-block'));
        document.querySelectorAll(".login").forEach(btn => btn?.style && (btn.style.display = 'none'));
    }

    if(sessionStorage.getItem('boardAuth') == 'Y') {
        memberList?.style && (memberList.style.display = 'inline-block');
    }else{
        memberList?.style && (memberList.style.display = 'none');
    }

    if(searchForm){
        const path = ((searchForm.type.value == 'member') ? "/member/memberList" : "/board/getBoard");
        searchForm.action = contextPath + path;
    }

    if(size){
        size.addEventListener("change", e => {
            change();
        });
    }

    if(goLogin){
        goLogin.addEventListener("click", e => {
            location.href= contextPath + "/member/loginForm";
        });
    }

    if(document.querySelector("#goLogOut")){
        document.querySelector("#goLogOut").addEventListener("click", e => {
            sessionStorage.removeItem('id');
            sessionStorage.removeItem('boardAuth');
            //sessionStorage.clear();
            location.href = contextPath + '/';
        });
    }

    if(document.querySelector("#updateMember")){
        document.querySelector("#updateMember").addEventListener("click", e => {
            location.href = contextPath + '/member/updateForm';
        });
    }

    if(loginForm) {
        loginForm.addEventListener("submit", e => {
            preventEvent(e);

            // 아이디, 비밀번호 로그인 정규식
            // if(regex(idRegex, loginForm.login_id.value, "아이디는 8자이며, 영문자와 숫자만 포함해야 합니다.")) return;
            // if(regex(pwRegex, loginForm.login_password.value, "비밀번호는 8~12자 사이이며, 영문자, 숫자, 특수문자를 각각 1개 이상 포함해야 합니다.")) return;
        
            fetch(contextPath + '/member/login', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json; charset=utf-8' 
                },
                body: JSON.stringify({'id': document.querySelector("#login_id").value, 'password':document.querySelector("#login_password").value})
            })
                .then(response => response.json())
                .then(data => {
                    if(data.status === 'ok') {
                        console.log(data.boardAuth);
                        sessionStorage.setItem('id', data.id);
                        sessionStorage.setItem('boardAuth', data.boardAuth);
                        location.href = contextPath + '/';
                    }
                    else {
                        alert(data.status);
                    }
                })
                .catch(error => {
                    alert("다시 시도해주세요.");  // 오류 처리
                });
        });
    }

    if(memberForm && memberForm.userid){
        memberForm.userid.addEventListener("blur", e => {
            if(regex(idRegex, memberForm.userid.value, "아이디는 8자이며, 영문자와 숫자만 포함해야 합니다.")) {
                memberForm.userid.value = '';
                memberForm.userid.focus();
                return;
            }
        });
    }
//    if(regex(pwRegex, memberForm.password.value, "비밀번호는 8~12자 사이이며, 영문자, 숫자, 특수문자를 각각 1개 이상 포함해야 합니다.")) return;
    if(memberForm && memberForm.idDupChk){
        memberForm.idDupChk.addEventListener("click", e => {
            preventEvent(e);
            postJson(contextPath + '/member/idDupChk', {'id' : memberForm.userid.value}); 
        });
    }

    if(goRegister){
        goRegister.addEventListener("click", e => {
            preventEvent(e);

            location.href = contextPath + '/member/registerForm';
        });
    }

    document.querySelectorAll('.lockYn').forEach(function(checkbox) {
        checkbox.addEventListener('click', function() {
            //const isChecked = checkbox.checked;
            console.log(contextPath + '/member/lockYn');
            fetch(contextPath + '/member/lockYn', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json; charset=utf-8'
                },
                body: JSON.stringify({
                    memberNo: checkbox.closest('tr').querySelector('td:nth-child(1)').textContent,
                    lockYn: checkbox.checked ? 'Y' : 'N'
                })
            })
            .then(response => response.json())
            .then(data => {
                if (data.status === 'ok') {

                } else {
                    console.log("a");
                    alert(data.status);
                    checkbox.checked = !checkbox.checked;
                }
            })
            .catch(error => {
                checkbox.checked = !checkbox.checked;
            });
        });
    });

    if(document.querySelector("#updateForm")){
        document.querySelector("#updateForm").addEventListener("submit", e => {
            preventEvent(e);

            fetch('', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json; charset=utf-8' 
                },
                body: JSON.stringify({"id" : sessionStorage.getItem("id")})
            })
                .then(response => response.json())
                .then(data => {
                    if(data.status === 'ok') {
                        alert(data.msg);
                        locatioin.href = contextPath + '/'
                    }
                    else {
                        console.log("not ok");
                        alert(data.status);
                    }
                })
                .catch(error => {
                    alert("다시 시도해주세요.");  // 오류 처리
                });
            
        });
    }







});

function regex (regex, val, msg){
    if (!regex.test(val)) {
        console.log("b");
        alert(msg);
        return true;
    }
    return false;
}

function postJson(url, data){
    fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json; charset=utf-8' 
        },
        body: JSON.stringify(data)
    })
        .then(response => response.json())
        .then(data => {
            if(data.status === 'ok') {
                console.log("ok");
                alert(data.msg);
            }
            else {
                console.log("not ok");
                alert(data.status);
            }
        })
        .catch(error => {
            alert("다시 시도해주세요.");  // 오류 처리
        });
}

function change () {
    if(!searchForm) return;
    searchForm.pageNo.value = 1;
    searchForm.submit();
}

function preventEvent(e) {
    e.preventDefault();
    e.stopPropagation();
}

function pageMove(pageNo) {
    if(!searchForm) return;
    searchForm.pageNo.value = pageNo; 
    searchForm.submit();
}

