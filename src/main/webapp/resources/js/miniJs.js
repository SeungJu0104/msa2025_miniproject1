
let validPwChk = false;
let pwRegexRes = false;
let idRegexRes = false;
let phoneRegexRes = false;
let idDuplicateChk = false;
const idRegex = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,10}$/;
const pwRegex = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?])[A-Za-z\d!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]{8,12}$/;
const phoneRegex = /^010-\d{4}-\d{4}$/;
const postRegex = /^\d{4}$/;

document.addEventListener("DOMContentLoaded", () => {

    contextPath = document.body.dataset.context; // body에 context 루트를 담아 넘겨준 것을 받는다.
    const navItems = document.querySelectorAll("#nav-items .nav-item");
    const pageItems = document.querySelectorAll("#page-items .page-item");
    const searchForm = document.querySelector("#searchForm");
    const goRegister = document.querySelector("#goRegister");
    const memberForm = document.querySelector("#memberForm");
    const updateForm = document.querySelector("#updateForm");

    // 브라우저에서 board 값을 영어로 줄 경우
    // const pathMap = {
    //     "K리그": "/board/getBoard?board=kleague",
    //     "EPL": "/board/getBoard?board=epl",
    //     "분데스리가": "/board/getBoard?board=bundesliga",
    //     "라리가": "/board/getBoard?board=laliga",
    //     "자유게시판": "/board/getBoard?board=freeboard",
    //     "회원목록": "/member/memberList"
    // };

    const pathMap = {
        "K리그": "/board/getBoard?board=K리그",
        "EPL": "/board/getBoard?board=EPL",
        "분데스리가": "/board/getBoard?board=분데스리가",
        "라리가": "/board/getBoard?board=라리가",
        "자유게시판": "/board/getBoard?board=자유게시판",
        "회원목록": "/member/memberList"
    };

    navItems.forEach(function (item) {
        const link = item.querySelector(".nav-link");
        if (!link) return;
        link.addEventListener("click", function (e) {
            preventEvent(e);
            if (pathMap[link.textContent.trim()]) window.location.href = contextPath + pathMap[link.textContent.trim()];
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

    if(sessionStorage.getItem('boardAuth') == 'Y') document.getElementById("memberList")?.style && (document.getElementById("memberList").style.display = 'inline-block');
    else document.getElementById("memberList")?.style && (document.getElementById("memberList").style.display = 'none');
	
    if(searchForm){
        const path = ((searchForm.type.value == 'member') ? "/member/memberList" : "/board/getBoard");
        formAction(searchForm, path, 'Get');
    }

    if(document.querySelector("#size")){
        document.querySelector("#size").addEventListener("change", e => {
            change();
        });
    }

    if(document.querySelector("#goLogin")){
        document.querySelector("#goLogin").addEventListener("click", e => {
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
            location.href = contextPath + '/member/getMember?id=' + sessionStorage.getItem("id");
        //      form 대신 fetch로 전송
        //     fetch(contextPath + '/member/getMember', {
        //         method: 'POST',
        //         headers: {
        //             'Content-Type': 'application/json; charset=utf-8' 
        //         },
        //         body: JSON.stringify({'id': sessionStorage.getItem("id")})
        //     })
        //         .then(response => response.json())
        //         .then(data => {
        //             if(data.status === 'ok'){
        //                 location.href = contextPath + '/member/updateForm';
        //             }else {
        //                 alert(data.status);
        //                 location.href = contextPath + '/';
        //             }
        //         })
        //         .catch(error => {
        //             alert("다시 시도해주세요.");
        //             location.href = contextPath + '/';
        //         });
        });
    }

    if(document.querySelector("#goRegister")){
        document.querySelector("#goRegister").addEventListener("click", e => {
            location.href = contextPath + '/member/registerForm';
        });
    }

    if(document.querySelector("#loginForm")) {
        document.querySelector("#loginForm").addEventListener("submit", e => {
            preventEvent(e);
            // 아이디, 비밀번호 로그인 검증
            if(regex(idRegex, loginForm.login_id.value, "아이디는 8~10자 사이이며, 영문자와 숫자만 포함해야 합니다.")) return;
            if(regex(pwRegex, loginForm.login_password.value, "비밀번호는 8~12자 사이이며, 영문자, 숫자, 특수문자를 각각 1개 이상 포함해야 합니다.")) return;
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

    if(memberForm?.userid){
        memberForm.userid.addEventListener("input", e => {
            idDuplicateChk = false;
            console.log("id" + idDuplicateChk);
            if(regexNo(idRegex, e.target.value)) {
                document.querySelector(".idInfo").style.display = 'none';
                idRegexRes = true;
            }else {
                document.querySelector(".idInfo").style.display = 'block';
                idRegexRes = false;
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
                }
                else {
                    idDuplicateChk = true;
                    alert(data.status);
                }
            })
            .catch(error => {
                alert("다시 시도해주세요.");  // 오류 처리
            });
        });
    }
    
    if(memberForm?.birthDate){
        memberForm.birthDate.max = new Date().toISOString().split("T")[0];
    }

    if(memberForm){
        console.log("a");
        formAction(memberForm, '/member/register', 'post');
        validate(memberForm); // form 제출 전 검증
        memberForm.addEventListener("submit", e => {

            if(!idRegexRes) {
                window.alert("아이디는 8~10자 사이이며, 영문자와 숫자만 포함해야 합니다.");
                memberForm.userid.focus();
                preventEvent(e);
                return;
            }

            if(!idDuplicateChk){
                console.log("id" + idDuplicateChk);
                alert("아이디 중복확인을 해주세요.");
                preventEvent(e);
                return;
            }

            submitValidate(e, memberForm);

        //     // const data = {
        //     //     "id" : memberForm.userid.value,
        //     //     "password" : memberForm.password.value,
        //     //     "name" : document.querySelector("#name").value,
        //     //     "phoneNumber" : memberForm.phoneNumber.value,
        //     //     "postCode" : memberForm.postCode.value,
        //     //     "address" : memberForm.address.value,
        //     //     "detailAddress" : memberForm.detailAddress.value,
        //     //     "birthDate" : memberForm.birthDate.value
        //     // }

        //     // fetch(contextPath + '/member/register', {
        //     //     method: 'POST',
        //     //     headers: {
        //     //         'Content-Type': 'application/json; charset=utf-8' 
        //     //     },
        //     //     body: JSON.stringify(data)
        //     // })
        //     // .then(response => response.json())
        //     // .then(data => {
        //     //     if(data.status === 'ok') {
        //     //         console.log("ok");
        //     //         alert(data.msg);
        //     //     }
        //     //     else {
        //     //         console.log("not ok");
        //     //         alert(data.status);
        //     //     }
        //     // })
        //     // .catch(error => {
        //     //     alert("다시 시도해주세요.");  // 오류 처리
        //     // });

        });

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
        formAction(updateForm, '/member/updateMember', 'post');
        validate(updateForm); // form 제출 전 검증
        updateForm.addEventListener("submit", e => {

            submitValidate(e, updateForm);
        //      form 대신 fetch로 전송
        //     const data = {
        //         "id":updateForm.id.value,
        //         "password":updateForm.password.value,
        //         "name":document.querySelector("#name").value,
        //         "phoneNumber":updateForm.handphone.value,
        //         "postCode":updateForm.postCode.value,
        //         "address":updateForm.address.value,
        //         "detailAddress":updateForm.detailAddress.value
        //     }

        //     fetch(contextPath + '/member/updateMember', {
        //         method: 'POST',
        //         headers: {
        //             'Content-Type': 'application/json; charset=utf-8' 
        //         },
        //         body: JSON.stringify(data)
        //     })
        //     .then(response => response.json())
        //     .then(data => {
        //         if(data.status === 'ok'){
        //             location.href = contextPath + '/';
        //         }else{
        //             alert(data.status);
        //         }
        //     })
        //     .catch(error => {
        //         alert("다시 시도해주세요.");
        //     });         
        });
    }

    if(document.querySelector("#memberDel")){
        document.querySelector("#memberDel").addEventListener("click", e => {
            if(!confirm("탈퇴하시겠습니까?")) return;
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

    if(document.querySelector("#detailMember")){
        document.querySelector("#detailMember").addEventListener("click", e => {
            location.href = contextPath + '/member/detailForm?id=' + sessionStorage.getItem("id");
        });
    }

    if(document.querySelectorAll(".rows")){
        document.querySelectorAll(".rows").forEach(row => {
            row.addEventListener("click", () => {
                location.href = contextPath + '/board/getPost?postNo=' + row.dataset.postNo + '&board=' + document.querySelector("#board").value;
            });
        });
    }

    if(document.querySelector("#boardUCBtn")){
        const postId = document.querySelector("tr[data-post-id]").getAttribute("data-post-id");
        console.log(postId);
        console.log(sessionStorage.getItem("id"));
        document.querySelector("#boardUCBtn").style.display = (sessionStorage.getItem("id") === postId) ? 'block' : 'none';
    }

    if(document.querySelector(".boardReg")){
        const postId = document.querySelector("tr[data-post-id]").getAttribute("data-post-id");
        const boardName = document.querySelector("tr[data-board-name]").getAttribute("data-board-name");
        console.log(postId);
        document.querySelector(".boardReg").style.display = (sessionStorage.getItem("id") === postId) ? 'block' : 'none';
        document.querySelector(".boardReg").addEventListener("click", e => {
            location.href = contextPath + '/board/postRegisterForm?board=' + boardName + '&id=' + postId;
        });
    }

    if(document.querySelectorAll(".boardDetail")){
        document.querySelectorAll(".boardDetail").forEach(btn => {
            // btn.style.display = (sessionStorage.getItem("id")) ? 'inline-block' : 'none';
            btn.addEventListener("click", e => {
                preventEvent(e);
                const postId = document.querySelector("tr[data-post-id]").getAttribute("data-post-id");
                const postNo = document.querySelector("tr[data-post-no]").getAttribute("data-post-no");
                const boardName = document.querySelector("tr[data-board-name]").getAttribute("data-board-name");
                if(btn.innerText === '수정' && postNo > 0 && boardName !== undefined && boardName !== null && boardName.trim() !== '') {
                    location.href = contextPath + '/board/updateForm?postNo=' + postNo + '&board=' + boardName;
                }
                if(btn.innerText === '삭제' && postNo > 0 && boardName !== undefined && boardName !== null && boardName.trim() !== ''){
                    if(!confirm("삭제하시겠습니까?")) return;
                    const password = prompt("비밀번호(4자리)를 입력해주세요.");
                    if(password === null) return;
                    if(password.trim().length > 4) {
                        alert("비밀번호는 4자리입니다.");
                        return;
                    }
                    fetch(contextPath + '/board/deletePost', {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json; charset=utf-8' 
                        },
                        body: JSON.stringify({"postNo" : postNo, "password" : password})
                    })
                    .then(response => response.json())
                    .then(data => {
                        if(data.status === 'ok') {
                            alert(data.msg);
                            location.href = contextPath + "/board/getBoard?board=" + boardName + "&pageNo=1&size=10&searchValue=";
                        }
                        else {
                            alert(data.status);
                        }
                    })
                    .catch(error => {
                        alert("다시 시도해주세요.");
                    });

                }
                // if(btn.innerText === '작성' && postNo > 0 && boardName !== undefined && boardName !== null && boardName.trim() !== ''){
                //     //const board = document.querySelector("#board");
                //     console.log(boardName);
                //     location.href = contextPath + '/board/postRegisterForm?board=' + boardName + '&id=' + postId;
                // }
            });
        })
    }

    if(document.querySelector("#updatePost")){
        const form = document.querySelector("#updatePost");
        formAction(form, '/board/updatePost', 'post');
        form.addEventListener("click", e => {
            if(form.postTitle.value === null || form.postTitle.value === undefined 
                || form.postContent.value === null || form.postContent.value === undefined){
                    preventEvent(e);
                    alert("값을 입력해주세요.");
                    return;
            }
        });
    }

    if(document.querySelector("#postRegister")){
        const postRegister = document.querySelector("#postRegister");
        document.querySelector("#postRegBtn").addEventListener("click", e => {
            preventEvent(e);
            if(postRegister.boardTitle.value === null || postRegister.boardTitle.value === undefined || postRegister.boardTitle.value.trim().length === 0) {
                alert("제목을 입력해주세요.");
                return;
            }
            if(regex(postRegex, postRegister.postPassword.value, "숫자 4자리를 입력해주세요.")) return;
            const data = {
                "title" : postRegister.boardTitle.value,
                "boardName" : postRegister.boardSelect.value,
                "content" : postRegister.boardContent.value,
                "id" : sessionStorage.getItem("id"),
                "password" : postRegister.postPassword.value
            }
            fetch(contextPath + '/board/registerPost', {
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
                    location.href = contextPath + "/board/getBoard?board=" + postRegister.boardSelect.value + "&pageNo=1&size=10&searchValue=";
                }
                else {
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

function pageMove(pageNo) {
    if(!searchForm) return;
    searchForm.pageNo.value = pageNo; 
    searchForm.submit();
}

function formAction(formId, path, method){
    if(!formId) return;
    formId.action = contextPath + path;
    formId.method = method;
}

function submitValidate(e, formName){
    if(formName.password.value !== formName.passwordValid.value){
        alert("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
        formName.password.focus();
        preventEvent(e);
        return;
    }
    if(!pwRegexRes || !validPwChk) {
        alert("비밀번호는 8~12자 사이이며, 영문자, 숫자, 특수문자를 각각 1개 이상 포함해야 합니다.");
        formName.password.focus();
        preventEvent(e);
        return;
    }
    if(!phoneRegexRes){
        alert("010-0000-0000 형식으로 입력해주세요.");
        formName.handphone.focus();
        preventEvent(e);
        return;
    }
    idRegexRes = false;
    idDuplicateChk = false;
}

function validate(formName2){
    if(formName2?.password && formName2?.passwordValid) {
        document.querySelectorAll(".pwChk").forEach(p => {
            p.addEventListener("input", e => {
                document.querySelector(".pwInfo").style.display = regexNo(pwRegex, e.target.value) ? 'none' : 'block';
                document.querySelector(".pwInfo2").style.display = (formName2.password.value === formName2.passwordValid.value) ? 'none' : 'block';
            });
            p.addEventListener("blur", e => {
                if(regexNo(pwRegex, formName2.password.value) && regexNo(pwRegex, formName2.passwordValid.value)) pwRegexRes = true;
                if(pwRegexRes === true && formName2.password.value === formName2.passwordValid.value) validPwChk = true;
            });
            p.addEventListener("change", e => {
                validPwChk = false;
                pwRegexRes = false;
            });
        });
    }

    if(formName2?.handphone){
        formName2.handphone.addEventListener("input", e => {
            if(regexNo(phoneRegex, e.target.value)) {
                document.querySelector(".phoneInfo").style.display = 'none';
                phoneRegexRes = true;
            }else {
                document.querySelector(".phoneInfo").style.display = 'block';
                phoneRegexRes = false;
            }
            
        });
    }  
}

function preventEvent(e) {
    e.preventDefault();
    e.stopPropagation();
}

