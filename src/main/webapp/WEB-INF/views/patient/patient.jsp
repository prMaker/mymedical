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
              <i class="fa fa-info"></i>
              <a href="patient-list.html"> 患者列表</a>  /  患者基本信息
            </span>
                    <ul class="unstyled inline pull-right">
                        <li><a href="/patient/edit/${patient.id}"><i class="fa fa-edit"></i> 修改</a></li>
                        <li><a href="/patient/del/${patient.id}"><i class="fa fa-times"></i> 删除</a></li>
                    </ul>
                </div>
                <div class="box-body">
                    <table class="table">
                        <tbody>
                        <tr>
                            <td width="100"><strong>姓名</strong></td>
                            <td width="300">${patient.patientname}</td>
                            <td width="100"><strong>性别</strong></td>
                            <td width="300">${patient.sex}</td>
                            <td width="100"><strong>年龄</strong></td>
                            <td width="">${patient.age}</td>
                        </tr>
                        <tr>
                            <td><strong>身份证号</strong></td>
                            <td>${patient.idcard}</td>
                            <td><strong>联系电话</strong></td>
                            <td>${patient.tel}</td>
                            <td><strong>医保类型</strong></td>
                            <td>${patient.medicalType.medicalensuretype}</td>
                        </tr>
                        <tr>
                            <td><strong>地址</strong></td>
                            <td colspan="5">${patient.address}</td>
                        </tr>
                        <tr>
                            <td colspan="6"><strong>过敏史</strong></td>
                        </tr>
                        <tr>
                            <td colspan="6">
                                ${patient.allergichistory}
                            </td>
                        </tr>
                        <tr>
                            <td colspan="6"><strong>备注</strong></td>
                        </tr>
                        <tr>
                            <td colspan="6">${patient.remarks}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>




<script src="http://cdn.staticfile.org/jquery/1.11.1/jquery.min.js"></script>
<script src="http://cdn.staticfile.org/twitter-bootstrap/3.0.0/js/bootstrap.min.js"></script>
<script src="/static/medical/js/simditor/scripts/js/simditor-all.min.js"></script>
<script src="http://cdn.staticfile.org/select2/3.4.8/select2.min.js"></script>
<script src="/static/plugins/validate/jquery.validate.min.js"></script>

<script>
    $(function(){



    });
</script>

</body>
</html>