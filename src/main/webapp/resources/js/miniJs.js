// console.log(contextPath);
// let contextPath = null;
document.addEventListener("DOMContentLoaded", () => {

    contextPath = document.body.dataset.context; // body에 context 루트를 담아 넘겨준 것을 받는다.
    const navItems = document.querySelectorAll("#nav-items .nav-item");
    const pageItems = document.querySelectorAll("#page-items .page-item");
    const size = document.querySelector("#size");
    const searchForm = document.querySelector("#searchForm");
    const goLogin = document.querySelector("#goLogin");
    const loginForm = document.querySelector("#loginForm");
    const goRegister = document.querySelector("#goRegister");
    const memberForm = document.querySelector("#memberForm");
    const memberList = document.getElementById("memberList");
    const updateForm = document.querySelector("#updateForm");
    const idRegex = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,10}$/;
    const pwRegex = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?])[A-Za-z\d!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]{8,12}$/;
    const phoneRegex = /^010-\d{4}-\d{4}$/;
    let validPwChk = false;
    let pwRegexRes = false;
    let idRegexRes = false;
    let phoneRegexRes = false;
    let idDuplicateChk = false;

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
            fetch(contextPath + '/member/getMember', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json; charset=utf-8' 
                },
                body: JSON.stringify({'id': sessionStorage.getItem("id")})
            })
                .then(response => response.json())
                .then(data => {
                    if(data.status === 'ok'){
                        location.href = contextPath + '/member/updateForm';
                    }else {
                        alert(data.status);
                        location.href = contextPath + '/';
                    }
                })
                .catch(error => {
                    alert("다시 시도해주세요.");
                    location.href = contextPath + '/';
                });
        });
    }

    if(document.querySelector("#goRegister")){
        document.querySelector("#goRegister").addEventListener("click", e => {
            location.href = contextPath + '/member/registerForm';
        });
    }

    if(loginForm) {
        loginForm.addEventListener("submit", e => {
            preventEvent(e);

            // 아이디, 비밀번호 로그인 정규식
            // if(regex(idRegex, loginForm.login_id.value, "아이디는 8~10자 사이이며, 영문자와 숫자만 포함해야 합니다.")) return;
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

    if(memberForm){
        console.log("a");
        memberForm.addEventListener("submit", e => {
            preventEvent(e);
            console.log("b");
            if(!idRegexRes) {
                window.alert("아이디는 8~10자 사이이며, 영문자와 숫자만 포함해야 합니다.");
                memberForm.userid.focus();
                return;
            }
            console.log("c");
            if(!idDuplicateChk){
                console.log("id" + idDuplicateChk);
                alert("아이디 중복확인을 해주세요.");
                return;
            }
            console.log("d");
            if(!pwRegexRes || !validPwChk) {
                alert("비밀번호는 8~12자 사이이며, 영문자, 숫자, 특수문자를 각각 1개 이상 포함해야 합니다.");
                memberForm.password.focus();
                return;
            }
            console.log("e");
            if(!phoneRegexRes){
                alert("010-0000-0000 형식으로 입력해주세요.");
                memberForm.handphone.focus();
                return;
            }
            console.log("f");
            const data = {
                "id" : memberForm.userid.value,
                "password" : memberForm.password.value,
                "name" : document.querySelector("#name").value,
                "phoneNumber" : memberForm.phoneNumber.value,
                "postCode" : memberForm.postCode.value,
                "address" : memberForm.address.value,
                "detailAddress" : memberForm.detailAddress.value,
                "birthDate" : memberForm.birthDate.value
            }

            console.log(data);

            fetch(contextPath + '/member/register', {
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

        });
    }

    if(memberForm?.userid){
        memberForm.userid.addEventListener("input", e => {
            idDuplicateChk = false;
            console.log("id" + idDuplicateChk);
            if(regexNo(idRegex, e.target.value)) {
                document.querySelector("#idInfo").style.display = 'none';
                idRegexRes = true;
                console.log("idRegexRes : " + idRegexRes);
            }else {
                document.querySelector("#idInfo").style.display = 'block';
                idRegexRes = false;
                console.log("idRegexRes : " + idRegexRes);
            }
        });
    }

    if(memberForm?.idDupChk){
        memberForm.idDupChk.addEventListener("click", e => {
            preventEvent(e);
            fetch(contextPath + '/member/idDupChk', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json; charset=utf-8' 
                },
                body: JSON.stringify({'id' : memberForm.userid.value})
            })
                .then(response => response.json())
                .then(data => {
                    if(data.status === 'ok') {
                        alert(data.msg);
                        idDuplicateChk = true;
                        console.log("id" + idDuplicateChk);
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

    if(memberForm?.password && memberForm?.passwordValid) {
        document.querySelectorAll(".pwChk").forEach(p => {
            p.addEventListener("input", e => {
                document.querySelector("#pwInfo").style.display = regexNo(pwRegex, e.target.value) ? 'none' : 'block';
            });

            p.addEventListener("blur", e => {
                if(regexNo(pwRegex, memberForm.password.value) && regexNo(pwRegex, memberForm.passwordValid.value)) pwRegexRes = true;
                if(pwRegexRes === true && memberForm.password.value === memberForm.passwordValid.value) validPwChk = true;
                console.log("pwRegexRes : " + pwRegexRes);
                console.log("validPwChk : " + validPwChk);
            });

            p.addEventListener("change", e => {
                validPwChk = false;
                pwRegexRes = false;
                console.log("pwRegexRes : " + pwRegexRes);
                console.log("validPwChk : " + validPwChk);
            });
        });
    }

    if(memberForm?.handphone){
        memberForm.handphone.addEventListener("input", e => {
            if(regexNo(phoneRegex, e.target.value)) {
                document.querySelector("#phoneInfo").style.display = 'none';
                phoneRegexRes = true;
                console.log("phoneRegexRes : " + phoneRegexRes);
            }else {
                document.querySelector("#phoneInfo").style.display = 'block';
                phoneRegexRes = false;
                console.log("phoneRegexRes : " + phoneRegexRes);
            }
            
        });
    }

    if(memberForm?.birthDate){
        memberForm.birthDate.max = new Date().toISOString().split("T")[0];
    }

    if(goRegister){
        goRegister.addEventListener("click", e => {
            preventEvent(e);

            location.href = contextPath + '/member/registerForm';
        });
    }

    document.querySelectorAll('.lockYn').forEach(function(checkbox) {
        checkbox.addEventListener('click', () => {
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
                if (data.status !== 'ok') {
                    alert(data.status);
                    checkbox.checked = !checkbox.checked;
                }
            })
            .catch(error => {
                checkbox.checked = !checkbox.checked;
            });
        });
    });

    if(updateForm){

        updateForm.addEventListener("submit", e => {
            preventEvent(e);

            const data = {
                "id":updateForm.id.value,
                "password":updateForm.password.value,
                "name":document.querySelector("#name").value,
                "phoneNumber":updateForm.handphone.value,
                "postCode":updateForm.postCode.value,
                "address":updateForm.address.value,
                "detailAddress":updateForm.detailAddress.value
            }

            fetch(contextPath + '/member/updateMember', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json; charset=utf-8' 
                },
                body: JSON.stringify(data)
            })
                .then(response => response.json())
                .then(data => {
                    if(data.status === 'ok'){
                        location.href = contextPath + '/';
                    }else{
                        alert(data.status);
                    }
                })
                .catch(error => {
                    alert("다시 시도해주세요.");
                });
            
        });
    }

    if(document.querySelector("#memberDel")){
        document.querySelector("#memberDel").addEventListener("click", e => {

            if(!confirm("삭제하시겠습니까?")) return;

            fetch(contextPath + '/member/deleteMember', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json; charset=utf-8' 
                },
                body: JSON.stringify({"id":sessionStorage.getItem("id")})
            })
                .then(response => response.json())
                .then(data => {
                    if(data.status === 'ok'){
                        location.href = contextPath + '/';
                        sessionStorage.removeItem("id");
                        sessionStorage.removeItem("boardAuth");
                    }else{
                        alert(data.status);
                    }
                })
                .catch(error => {
                    alert("다시 시도해주세요.");
                });


        });
    }





});

function regex (regex, val, msg){
    if (!regex.test(val)) {
        alert(msg);
        return true;
    }
    return false;
}

function regexNo(regex, val){
    if(!regex.test(val)) return false;
    return true;
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
                alert(data.msg);
            }
            else {
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

function formAction(formId, path){
    console.log("b");
    if(!formId) return;
    console.log("c");
    formId.action = contextPath + path;
}

