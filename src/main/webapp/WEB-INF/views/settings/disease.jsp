<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>医院|科室设置</title>
    <link rel="stylesheet" href="http://cdn.staticfile.org/twitter-bootstrap/2.3.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="http://cdn.staticfile.org/font-awesome/4.1.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="/static/medical/css/style.css">
    <link rel="stylesheet" href="/static/bootstrap/css/bootstrap.min.css">
</head>
<body>

<jsp:include page="../include/nav.jsp">
    <jsp:param name="menu" value="settings"/>
</jsp:include>


<div class="container">
    <div class="row-fluid">
        <div class="span12">
            <div class="box">
                <div class="box-header">
                    <span class="title"><i class="fa fa-sitemap"></i> 病种列表</span>
                    <ul class="unstyled inline pull-right">
                        <li><a href="javascript:;" id="newBtn" class="btn btn-success btn-xs pull-right"><i
                                class="fa fa-plus"></i> 新建</a></li>
                    </ul>
                </div>
                <div class="box-body">
                    <table class="table">
                        <thead>
                        <tr>
                            <th width="200">疾病名称</th>
                            <th width="200">所属科室</th>
                            <th>#</th>
                        </tr>
                        </thead>

                        <tbody>
                        <c:forEach items="${diseaseList}" var="disease">
                            <tr>
                                <th>${disease.diseasename}</th>
                                <th>${disease.department.departmentname}</th>
                                <th>
                                    <a href="javascript:;" rel="${disease.id}"
                                       class="edit btn btn-primary btn-xs">修改</a>
                                    <a href="javascript:;" rel="${disease.id}" class="del btn btn-danger btn-xs">删除</a>
                                </th>
                            </tr>
                        </c:forEach>
                        </tbody>

                    </table>
                </div>
            </div>
        </div>
    </div>

</div>

<div class="modal fade" style="position:relative;margin:auto" id="newModel" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">新建疾病类型</h4>
            </div>
            <div class="modal-body">
                <form id="newForm">
                    <input type="hidden" id="event_id">
                    <div class="form-group">
                        <label>疾病名称</label>
                        <input type="text" style="border: 2px blueviolet solid; padding: 15px" name="diseasename"
                               class="form-control">
                    </div>
                    <div class="form-group">
                        <label>所属科室</label>
                        <select name="department.id" style="border: 2px blueviolet solid; padding: 15px"
                                class="form-control" id="">
                            <option value=""></option>
                            <c:forEach items="${departmentList}" var="department">
                                <option value="${department.id}">${department.departmentname}</option>
                            </c:forEach>
                        </select>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button class="btn btn-danger" id="newSaveBtn" type="button"><i class="fa fa-bank"></i> 保存</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" style="position:relative;margin:auto" id="editModel" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">修改疾病</h4>
            </div>
            <div class="modal-body">
                <form id="editForm">

                    <input type="hidden" id="edit_id" name="id">

                    <div class="form-group">
                        <label>疾病名称</label>
                        <input type="text" id="edit_diseasename" style="border: 2px blueviolet solid; padding: 15px"
                               name="diseasename" class="form-control">
                    </div>

                    <div class="form-group">
                        <label>所属科室</label>
                        <select name="department.id" id="edit_department.id"
                                style="border: 2px blueviolet solid; padding: 15px"
                                class="form-control">

                            <option value=""></option>
                            <c:forEach items="${departmentList}" var="department">
                                <option value="${department.id}">${department.departmentname}</option>
                            </c:forEach>

                        </select>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button class="btn btn-danger" id="editSaveBtn" type="button"><i class="fa fa-bank"></i> 保存</button>
            </div>
        </div>
    </div>
</div>

<script src="http://cdn.staticfile.org/jquery/1.11.1/jquery.min.js"></script>
<script src="http://cdn.staticfile.org/twitter-bootstrap/3.0.0/js/bootstrap.min.js"></script>
<script src="/static/bootstrap/js/bootstrap.min.js"></script>
<script src="/static/plugins/validate/jquery.validate.min.js"></script>

<script>

    $(function () {
        // 点击摸态框 1.save 2.update
        $("#newBtn").click(function () {
            $("#newForm")[0].reset();
            $("#newModel").modal({
                show: true,
                backdrop: 'static'
            })
        });
        $(document).delegate(".edit", "click", function () {
            var id = $(this).attr("rel");
            $.get("/settings/disease/edit/" + id).done(function (result) {
                if (result.state == "success") {
                    $("#editForm")[0].reset();
                    var diease = result.data;
                    $("#edit_diseasename").val(diease.diseasename);

                    var $editdepartment = $("#edit_department.id");
                    $editdepartment.html("");

//                  TODO 未做select中选值传入
                    <%--<c:forEach items="${departmentList}" var="department">--%>

                        <%--var deptid = "${department.id}";--%>
                        <%--var deptname = "${department.departmentname}";--%>
                        <%--var option = null;--%>

                        <%--if (deptid == diease.department.id) {--%>
                            <%--option = "<option value='" + deptid + "' selected='selected' >" + deptname + "</option>";--%>
                        <%--} else {--%>
                            <%--option = "<option value='" + deptid + "'>" + deptname + "</option>";--%>
                        <%--}--%>
                        <%--$editdepartment.append(option);--%>

                    <%--</c:forEach>--%>

                    $("#edit_id").val(diease.id);

                    $("#editModel").modal({
                        show: true,
                        backdrop: 'static'
                    })
                }
            }).fail(function () {
                alert("请求服务器错误");
            });
        })
        $(document).delegate(".del", "click", function () {
            if (confirm("删除病种将要删除该病种所有病人病种！，确定要删除该病种么？")) {
                var id = $(this).attr("rel");
                $.get("/settings/disease/del/" + id);
//          TODO     为什么必须得手动刷新页面
                window.history.go([0]);
            }
        })

        // 保存  1.save 2.update
        $("#newSaveBtn").click(function () {
            $("#newForm").submit();
        });
        $("#editSaveBtn").click(function () {
            $("#editForm").submit();
        });


        // 验证  1.save 2.update
        $("#newForm").validate({
            errorElement: "span",
            errorClass: "text-danger",
            rules: {
                diseasename: "required",
                "department.id": "required"
            },
            messages: {
                diseasename: "请输入疾病名称",
                "department.id": "请选择该疾病所对应的科室"
            },
            submitHandler: function (form) {
                $.post("/settings/disease/new", $(form).serialize()).done(function (result) {
                    $("#newModel").hide();
                    if (result == "success") {
                        window.history.go([0]);
                    }
                }).fail(function () {
                    alert("请求服务器错误");
                });
            }
        });
        $("#editForm").validate({
            errorElement: "span",
            errorClass: "text-danger",
            rules: {
                diseasename: "required",
                "department.id": "required"
            },
            messages: {
                diseasename: "请输入疾病名称",
                "department.id": "请选择该疾病所对应的科室"
            },
            submitHandler: function (form) {
                $.post("/settings/department/edit", $(form).serialize()).done(function (result) {
                    if (result == "success") {
                        window.history.go([0]);
                        $("#newModel").hide();
                    }
                }).fail(function () {
                    alert("请求服务器错误");
                });
            }
        });

    })
    ;

</script>


</body>
</html>