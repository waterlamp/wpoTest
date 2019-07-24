//检查手机号格式
function checkTel(tel) {
    var repTel=/^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$/;
    return repTel.test(tel)
}

//检查盒号格式
function checkBoxNum(value) {
    if(value===""||value===null){
        return false;
    }
    var v="0123456789,-";
    var startValue=value.substring(0,1);
    var endValue=value.substring(value.length-1);

    if(startValue==="0"||startValue===","||startValue==="-"||endValue===","||endValue==="-"){
        return false;
    }
    for(var i=0;i<value.length;i++){
        var n=value.substring(i,i+1);
        if(v.indexOf(n)<0){
            return false;
        }
    }
    return true;
}

function checkMis(mis) {
    if(mis==="") return false;
    $.get(path+"/project_checkMis.action?projectMis="+mis,function (result) {
        alert(result);
        return result === "success";
    })
}

function checkUser(userName) {
    if(userName==="") return false;
    $.get(path+"/user_checkUserName.action?userName="+userName,function (result) {
        return result === "success";
    })
}