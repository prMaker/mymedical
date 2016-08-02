<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <link rel="stylesheet" href="http://cdn.staticfile.org/webuploader/0.1.1/webuploader.css">
    <link rel="stylesheet" href="http://cdn.staticfile.org/bootstrap-datepicker/1.3.0/css/datepicker.min.css">
    <link rel="stylesheet" href="/static/medical/css/style.css">
</head>
<body>
<jsp:include page="../include/nav.jsp">
    <jsp:param name="menu" value="visit"/>
</jsp:include>


<div class="container-fluid">
    <div class="row-fluid">
        <div class="span12">

            <div class="box">
                <div class="box-header">
            <span class="title">
              <i class="fa fa-info"></i>
              患者基本信息
            </span>
                </div>
                <div class="box-body">
                    <table class="table">
                        <tbody>

                        <tr>
                            <td width="100"><strong>姓名</strong></td>
                            <td width="300">
                                <input type="text" id="name" style="margin:0px">
                            </td>
                            <td width="100"><strong>性别</strong></td>
                            <td width="300"><span id="sex"></span></td>
                            <td width="100"><strong>年龄</strong></td>
                            <td><span id="age"></span></td>
                        </tr>
                        <tr>
                            <td><strong>身份证号</strong></td>
                            <td><span id="idcard"></span></td>
                            <td><strong>联系电话</strong></td>
                            <td><span id="tel"></span></td>
                            <td><strong>医保类型</strong></td>
                            <td><span id="medicalensuretype"></span></td>
                        </tr>
                        <tr>
                            <td><strong>地址</strong></td>
                            <td colspan="5"><span id="address"></span></td>
                        </tr>
                        <tr>
                            <td colspan="6"><strong>过敏史</strong></td>
                        </tr>
                        <tr>
                            <td colspan="6">
                                <span id="allergichistory"></span>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="6"><strong>备注</strong></td>
                        </tr>
                        <tr>
                            <td colspan="6"><span id="remarks"></span></td>
                        </tr>
                        </tbody>

                    </table>
                </div>
            </div>
            <!-- box end -->

            <div class="box">
                <div class="box-header">
            <span class="title">
              <i class="fa fa-plus"></i> 病历信息
            </span>
                </div>
                <div class="box-body form">
                    <form id="caseForm">
                        <input type="hidden" name="patient.id">
                        <label>科室</label>
                        <select name="department.id">
                            <option value=""></option>
                            <c:forEach items="${departmentList}" var="dept">
                                <option value="${dept.id}">${dept.departmentname}</option>
                            </c:forEach>
                        </select>
                        <label>病种</label>
                        <select name="disease.id">
                            <option value=""></option>
                            <c:forEach items="${diseaseList}" var="disease">
                                <option value="${disease.id}">${disease.diseasename}</option>
                            </c:forEach>
                        </select>
                        <label>初步诊断</label>
                        <input type="text" name="easydiagnose" class="span12">
                        <label>主要症状</label>
                        <textarea class="editor1" name="mainsymptom"></textarea>
                        <label>相关病史</label>
                        <textarea class="editor2" name="relatedhistory"></textarea>
                        <label>阳性体征</label>
                        <textarea class="editor3" name="positivesign"></textarea>
                        <label>检查结果</label>
                        <textarea class="editor4" name="checkresult"></textarea>
                        <label>治疗方案</label>
                        <textarea class="editor5" name="treatmentplan"></textarea>
                        <label>管床医生</label>
                        <input type="text" name="beddoctor">
                        <label>下次复诊时间</label>
                        <input type="text" id="nextTime" name="nextdiagnosetime">
                        <label>影像资料</label>
                        <div id="picker">选择资料</div>
                        <ul id="fileList" class="thumbnails">
                            <li id="WU_FILE_0" class="span2 upload-state-done uploadfile">
                                <div class="msg">正在上传...</div>
                            </li>
                        </ul>
                        <div class="form-actions">
                            <button id="saveBtn" class="button button-flat-action button-pill">保存</button>
                        </div>
                    </form>
                </div>
            </div>


        </div>
    </div>
</div>


<script src="http://cdn.staticfile.org/jquery/1.11.1/jquery.min.js"></script>
<script src="http://cdn.staticfile.org/twitter-bootstrap/2.3.2/js/bootstrap.min.js"></script>
<script src="/static/medical/js/simditor/scripts/js/simditor-all.min.js"></script>
<script src="http://cdn.staticfile.org/select2/3.4.8/select2.min.js"></script>
<script src="http://cdn.staticfile.org/jquery.devbridge-autocomplete/1.2.7/jquery.devbridge-autocomplete.min.js"></script>
<script src="http://cdn.staticfile.org/webuploader/0.1.1/webuploader.min.js"></script>
<script src="http://cdn.staticfile.org/bootstrap-datepicker/1.3.0/js/bootstrap-datepicker.min.js"></script>
<script src="http://cdn.staticfile.org/bootstrap-datepicker/1.3.0/js/locales/bootstrap-datepicker.zh-CN.min.js"></script>
<script src="/static/plugins/validate/jquery.validate.min.js"></script>

<script>

    $(function () {

        $("#name").blur(function () {
                var name = $("#name").val().toString();
//                  TODO  自动转为汉字双拼怎么搞
                $.post("patient/name/data",{"patientname":name}).done(function (result) {
                    if(result.state == "success"){
                        $("#sex").text(result.data.sex);
                        $("#age").text(result.data.age);
                        $("#tel").text(result.data.tel);
                        $("#idcard").text(result.data.idcard);
                        $("#address").text(result.data.address);
                        $("#allergichistory").text(result.data.allergichistory);
                        $("#remarks").text(result.data.remarks);
                        $("#medicalensuretype").text(result.data.medicalType.medicalensuretype);
                        $("#id").val(result.data.id);
                        return;
                    }
//                  TODO 查看老师如何处理Ajax获取信息不存在是处理情况
                    console.log("未找到该名字对应的信息："+names);
                }).fail(function () {
                    alert("请求服务器异常");
                });
        });
        $("#saveBtn").click(function () {
            $("#caseForm").submit();
        });
        $("#caseForm").validate({
            errorElement:"span",
            errorClass:"text-danger",
            rules:{
                "easydiagnose":"required",
                "mainsymptom":"required",
                "relatedhistory":"required",
                "positivesign":"required",
                "checkresult":"required",
                "treatmentplan":"required",
                "beddoctor":"required",
                "nextdiagnosetime":"required", // TODO  下次复查时间 查看上次datapicker
                "department.id":"required",
                "disease.id":"required"
            },
            messages:{
                "easydiagnose":"请输入初步诊断",
                "mainsymptom":"required",
                "relatedhistory":"required",
                "positivesign":"required",
                "checkresult":"required",
                "treatmentplan":"required",
                "beddoctor":"required",
                "nextdiagnosetime":"required", // TODO  下次复查时间 查看上次datapicker
                "department.id":"required",
                "disease.id":"required"
            },
            submitHandler: function (form) {
                $.post("/visit/case/new",$(form).serialize()).done(function (result) {
                    if(result == "success"){
                        window.location.href = "/visit/list";
                    }
                }).fail(function () {
                    alert("请求服务器错误！");
                });
            }
        });





        var editor = new Simditor({
            toolbar:['title','bold','italic','underline','strikethrough','ol','ul','blockquote','table','link','hr','indent','outdent'],
            textarea: $('.editor1')
        });
        var editor2 = new Simditor({
            toolbar:['title','bold','italic','underline','strikethrough','ol','ul','blockquote','table','link','hr','indent','outdent'],
            textarea: $('.editor2')
        });
        var editor3 = new Simditor({
            toolbar:['title','bold','italic','underline','strikethrough','ol','ul','blockquote','table','link','hr','indent','outdent'],
            textarea: $('.editor3')
        });
        var editor4 = new Simditor({
            toolbar:['title','bold','italic','underline','strikethrough','ol','ul','blockquote','table','link','hr','indent','outdent'],
            textarea: $('.editor4')
        });
        var editor5 = new Simditor({
            toolbar:['title','bold','italic','underline','strikethrough','ol','ul','blockquote','table','link','hr','indent','outdent'],
            textarea: $('.editor5')
        });

        //插件地址 http://www.devbridge.com/sourcery/components/jquery-autocomplete/
        $("#name").autocomplete({
            lookup:['java','javascript','alex','jsp']
        });
        var uploader = WebUploader.create({
            swf: 'http://cdn.staticfile.org/webuploader/0.1.1/Uploader.swf',
            server: 'http://webuploader.duapp.com/server/fileupload.php',
            pick: '#picker',
            // 只允许选择图片文件。
            accept: {
                title: 'Images',
                extensions: 'gif,jpg,jpeg,bmp,png',
                mimeTypes: 'image/*'
            }
        });

        $("#nextTime").datepicker({
            format: "yyyy-mm-dd",
            language: "zh-CN",
            autoclose: true
        });



    });

</script>


</body>
</html>