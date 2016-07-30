<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" href="http://cdn.staticfile.org/twitter-bootstrap/2.3.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="http://cdn.staticfile.org/font-awesome/4.1.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="/static/medical/css/style.css">
    <link rel="stylesheet" href="/static/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/static/plugins/datatables/css/dataTables.bootstrap.min.css">
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
                    <span class="title">搜索</span>
                </div>
                <div class="box-body search-box">
                    <form class="form-search" id="searchForm">
                        <input type="text" style="border: 2px blueviolet dashed; padding: 15px" placeholder="姓名" id="search_patientname" name="search_patientname">
                        <input type="text" style="border: 2px blueviolet dashed; padding: 15px" placeholder="身份证号" id="search_idcard" name="search_idcard">
                        <input type="text" style="border: 2px blueviolet dashed; padding: 15px" placeholder="电话" id="search_tel" name="search_tel">
                        <button id="searchBtn" type="button" class="button button-flat-primary button-pill"><i class="fa fa-search"></i> 搜索</button>
                    </form>
                </div>
            </div>




            <div class="box">
                <div class="box-header">
                    <span class="title"><i class="fa fa-building"></i> 患者列表</span>
                    <ul class="unstyled inline pull-right">
                        <li><a href="/patient/new"><i class="fa fa-plus"></i> 新建</a></li>
                    </ul>
                </div>
                <div class="box box-body">
                    <div class="container">
                        <table class="table" id="dataTable" style="margin: 50px">
                            <thead>
                                <tr>
                                    <th width="50">选中</th>
                                    <th width="150">姓名</th>
                                    <th width="50">性别</th>
                                    <th width="200">电话</th>
                                    <th width="100">医保类型</th>
                                    <th width="200">地址</th>
                                    <th width="100">状态</th>
                                    <th width="150">创建日期</th>
                                </tr>
                            </thead>
                            <tbody>

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
</div>
    </div>




<script src="http://cdn.staticfile.org/jquery/1.11.1/jquery.min.js"></script>
<script src="http://cdn.staticfile.org/twitter-bootstrap/3.0.0/js/bootstrap.min.js"></script>
<script src="/static/plugins/datatables/js/jquery.dataTables.min.js"></script>
<script src="/static/plugins/datatables/js/dataTables.bootstrap.min.js"></script>
<script src="/static/plugins/moment/moment.min.js"></script>
<script src="/static"></script>

<script>

    $(function () {

        //DataTables
        var dataTable = $("#dataTable").DataTable({
            searching:false,
            serverSide:true,
            ajax:{
                url:"/patient/dataload",
                data:function(dataSouce){
                    dataSouce.patientname = $("#search_patientname").val();
                    dataSouce.idcard = $("#search_idcard").val();
                    dataSouce.tel = $("#search_tel").val();
                }
            },
            columns:[
//                {"data":"id"},
                {"data": function (row) {
                    return '<input type="checkbox" class="checked">';
                }},
                {"data": function (row) {
                    return "<a href='/patient/"+row.id+"' >"+row.patientname+"</a>";
                }},
                {"data":"sex"},
                {"data":"tel"},
                {"data": function (row) {
                    return row.medicalType.medicalensuretype;
                }},
                {"data":"address"},
                {"data":"state"},
                {"data": function (row) {
                    return moment(row.createtime).format("YYYY-MM-DD");
                }}
            ],
            ordering:false,
            "autoWidth": false,
            "language": { //定义中文
                "search": "请输入书籍名称:",
                "zeroRecords": "没有匹配的数据",
                "lengthMenu": "显示 _MENU_ 条数据",
                "info": "显示从 _START_ 到 _END_ 条数据 共 _TOTAL_ 条数据",
                "infoFiltered": "(从 _MAX_ 条数据中过滤得来)",
                "loadingRecords": "加载中...",
                "processing": "处理中...",
                "paginate": {
                    "first": "首页",
                    "last": "末页",
                    "next": "下一页",
                    "previous": "上一页"
                }
            }
        });

        $("#searchBtn").click(function () {
            dataTable.ajax.reload();
        });

    });

</script>



</body>
</html>