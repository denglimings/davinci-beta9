$(function () {
  var url = location.search;
  var params = {};
  if (url.indexOf("?") != -1) {
    var str = url.substr(1);
    var strs = str.split("&");
    for (var i = 0; i < strs.length; i++) {
      params[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1]);
    }
  }

  if (params.username) {
    $.ajax({
      type:"POST",
      url:"/api/v3/login",
      contentType:"application/json", // 必须有
      dataType:"json", // 表示返回值类型，不必须
      data:JSON.stringify({
        "username":params.username,
        "password":params.password
      }),
      success:function(jsonResult) {
        var localStorage = window.localStorage;
        if (jsonResult.header && jsonResult.header.code == 200) {
          localStorage.setItem("TOKEN", jsonResult.header.token);
          localStorage.setItem("TOKEN_EXPIRE", ("" + ((new Date).getTime() + 36e5)));
          localStorage.setItem("loginUser", JSON.stringify(jsonResult.payload));
          openUrl(params);
        }
      }
    });
  } else {
    openUrl(params);
  }

});
function openUrl(params){
  if (params.redirect_uri) {
    window.location.href = params.redirect_uri;
  }
}
