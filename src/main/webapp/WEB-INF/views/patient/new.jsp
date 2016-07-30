<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" href="http://cdn.staticfile.org/twitter-bootstrap/2.3.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="http://cdn.staticfile.org/font-awesome/4.1.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="/static/medical/js/simditor/styles/simditor.css">
    <link rel="stylesheet" href="http://cdn.staticfile.org/select2/3.4.8/select2.css">
    <link rel="stylesheet" href="/static/medical/css/style.css">
    <link rel="stylesheet" href="/static/bootstrap/css/bootstrap.min.css">
</head>
<body>


<jsp:include page="../include/nav.jsp">
    <jsp:param name="menu" value="patient"/>
</jsp:include>

<div class="container-fluid">
    <div class="row-fluid">
        <div class="span12">
            <div class="box">
                <div class="box-header">
                    <span class="title">
                      <i class="fa fa-plus"></i>
                      <a href="patient-list.html"> 患者列表</a>  /  新增患者
                    </span>
                </div>

                <div class="box-body form">

                    <form id="saveForm">
                        <div class="form-group">
                            <label>姓名</label>
                            <div style="width: 30%">
                                <input style="border: 2px #565171 solid; padding: 15px" type="text" name="patientname" class="form-control">
                            </div>
                        </div>
                        <div class="form-group">
                            <label>性别</label>
                            <div class="" style="width: 30%;border: 2px #565171 solid; padding: 15px">
                                <select id="" name="sex" class="form-control">
                                    <option value="男">男</option>
                                    <option value="女">女</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label>身份证号</label>
                            <div style="width: 30%">
                                <input style="border: 2px #565171 solid; padding: 15px" type="text" id="idcard" name="idcard" class="form-control">
                            </div>
                        </div>
                        <div class="form-group">
                            <label>年龄</label>
                            <div style="width: 30%">
                                <input style="border: 2px #565171 solid; padding: 15px" type="text" id="age" name="age" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>电话</label>
                            <input style="border: 2px #565171 solid; padding: 15px" type="text" name="tel" class="form-control input-group-sm">
                        </div>
                        </div>
                        <div class="form-group">
                            <label>医保类型</label>
                            <div class="" style="width:30%;border: 2px #565171 solid; padding: 15px">
                                <select id="yb" name="medicalType.id" style="display: block">
                                    <c:forEach items="${medicalTypeList}" var="medicaltype">
                                        <option value="${medicaltype.id}">${medicaltype.medicalensuretype}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label>住址</label>
                            <div style="width: 30%">
                                <input style="border: 2px #565171 solid; padding: 15px" type="text" name="address" class="form-control">
                            </div>
                        </div>
                        <div class="form-group">
                            <label>过敏史</label>
                            <div class="" style="border: 2px #565171 solid; padding: 15px">
                                <textarea name="allergichistory" id="allergichistory"  class="editor1 form-control" style="height:50px"></textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label>备注</label>
                            <div class="" style="border: 2px #565171 solid; padding: 15px">
                                <textarea name="remarks" id="remarks" class="editor2 form-control"></textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="form-actions">
                                <button id="saveBtn" class="button button-flat-action button-pill">保存</button>
                            </div>
                        </div>
                    </form>
                </div>
        </div>
        </div>

    </div>
</div>



<script src="http://cdn.staticfile.org/jquery/1.11.1/jquery.min.js"></script>
<script src="http://cdn.staticfile.org/twitter-bootstrap/3.0.0/js/bootstrap.min.js"></script>
<script src="/static/medical/js/simditor/scripts/js/simditor-all.min.js"></script>
<script src="http://cdn.staticfile.org/select2/3.4.8/select2.min.js"></script>
<script src="/static/plugins/validate/jquery.validate.min.js"></script>

<script>
    $(function(){

//      表单验证
        $("#saveForm").validate({
            errorElement:"span",
            errorClass:"text-danger",
            rules:{
                medicaltypeid:"required",
                patientname:"required",
                sex:"required",
                tel:{
                    required:true,
                    rangelength:[11,11]
                },
                idcard:{
                    required:true,
                    rangelength:[18,18]
                },
                address:"required",
                allergichistory:"required"
            },
            messages:{
                medicaltypeid:"请选择医保类型",
                patientname:"请输入病人姓名",
                sex:"请选择性别",
                tel:{
                    required:"请输入电话",
                    rangelength:"请输入11位电话号码"
                },
                idcard:{
                    required:"请输入身份证号",
                    rangelength:"请输入正确的身份证号"
                },
                address:"请输入地址",
                allergichistory:"请输入过敏史"
            },
            submitHandler:function (form) {
                $.post("/patient/new",$(form).serialize()).done(function (result) {
                    if(result.state == "success"){
                        window.location.href="/patient";
                    }
                }).fail(function () {
                    alert("请求服务器异常");
                });
            }
        });
        $("#saveBtn").click(function () {
            $("#saveForm").submit();
        });

//        动态获取年龄
        $("#idcard").blur(function () {
            $(this).change(function () {
                var idcard = $(this).val();
                if(idcard.length == 18){
                    $.get("/patient/idcard/"+idcard).done(function (result) {
                        if(result.state == "success"){
                            var age = result.data;
                            $("#age").val(age);
                        }
                    }).fail(function () {
                        alert("请求服务器获取年龄出错");
                    });
                }
            });
        });


        var simpditor = new Simditor({
            textarea:$("#allergichistory"),
            placeholder:'请输入过敏史'
        });
        var simpditor2 = new Simditor({
            textarea:$("#remarks"),
            placeholder:'请输入备注'
        });
        $("#yb").select2({
            placeholder: "请选择医保类型",
            width:'220px'
        });





    });
</script>

</body>
</html>