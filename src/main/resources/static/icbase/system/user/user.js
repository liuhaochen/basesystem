var prefix = ctx + "system/user"


$(document).ready(function(){
	$('body').layout({ west__size: 185 });
	queryUserList();
	queryDeptTreeDaTa();
	//加载文件输入框
    fileinputlist();
});


function queryUserList() {
	var columns = [{
            checkbox: true
        },
        {
            field: 'userId',
            title: '用户ID'
        },
        {
            field: 'loginName',
            title: '登录名称'
        },
        {
            field: 'userName',
            title: '用户名称'
        },
        {
            field: 'dept.deptName',
            title: '部门'
        },
        {
            field: 'email',
            title: '邮箱'
        },
        {
            field: 'phonenumber',
            title: '手机'
        },
        {
            field: 'status',
            title: '状态',
            align: 'center',
            formatter: function(value, row, index) {
                if (value == '0') {
                    return '<span class="badge badge-primary">正常</span>';
                } else if (value == '1') {
                    return '<span class="badge badge-danger">禁用</span>';
                }
            }
        },
        {
            field: 'createDateTimeStr',
            title: '创建时间'
        },
        {
            title: '操作',
            align: 'center',
            formatter: function(value, row, index) {
	    		var actions = [];
	        	actions.push('<a class="btn btn-success btn-xs ' + editFlag + '" href="#" onclick="edit(\'' + row.userId + '\')"><i class="fa fa-edit"></i>编辑</a> ');
	        	actions.push('<a class="btn btn-danger btn-xs ' + removeFlag + '" href="#" onclick="remove(\'' + row.userId + '\')"><i class="fa fa-remove"></i>删除</a> ');
	        	actions.push('<a class="btn btn-info btn-xs ' + resetPwdFlag + '" href="#" onclick="resetPwd(\'' + row.userId + '\')"><i class="fa fa-key"></i>重置</a>');
	        	return actions.join('');
            }
        }];
	var url = prefix + "/list";
	$.initTableParams(columns, url, queryParams);
}

function queryParams(params) {
	return {
		// 传递参数查询参数
		pageSize:       params.limit,
		pageNum:        params.offset / params.limit + 1,
		searchValue:    params.search,
		orderByColumn:  params.sort,
		isAsc:          params.order,
		deptId:         $("#deptId").val(),
		parentId:       $("#parentId").val()
	};
}

function queryDeptTreeDaTa()
{
	// 树结构初始化加载
	var setting = {view:{selectedMulti:false},data:{key:{title:"title"},simpleData:{enable:true}},
		callback:{onClick:function(event, treeId, treeNode){
			tree.expandNode(treeNode);
			$("#deptId").val(treeNode.id);
			$("#parentId").val(treeNode.pId);
			$('.bootstrap-table').bootstrapTable('refresh', queryParams);
		}}
	}, tree, loadTree = function(){
		$.get(ctx + "system/dept/treeData", function(data) {
		    tree = $.fn.zTree.init($("#tree"), setting, data); //.expandAll(true);
		    // 展开第一级节点
		    var nodes = tree.getNodesByParam("level", 0);
		    for (var i = 0; i < nodes.length; i++) {
		        tree.expandNode(nodes[i], true, false, false);
		    }
		    // 展开第二级节点
		    nodes = tree.getNodesByParam("level", 1);
		    for (var i = 0; i < nodes.length; i++) {
		        tree.expandNode(nodes[i], true, false, false);
		    }
		}, null, null, "正在加载，请稍后...");
	};loadTree();
	
	$('#btnExpand').click(function() {
		tree.expandAll(true);
	    $(this).hide();
	    $('#btnCollapse').show();
	});
	$('#btnCollapse').click(function() {
		tree.expandAll(false);
	    $(this).hide();
	    $('#btnExpand').show();
	});
	$('#btnRefresh').click(function() {
	    loadTree();
	});
}
/** 初始化文件框*/
function fileinputlist(){
    $('#uploadfile').fileinput({
        language: 'zh', //设置语言
        showPreview: false,//是否显示预览,不写默认为true
        showUpload: false, //是否显示上传按钮,跟随文本框的那个
        showRemove : true, //显示移除按钮,跟随文本框的那个
        showCaption: true,//是否显示标题,就是那个文本框
        uploadAsync : false, //默认异步上传
        elErrorContainer: '#upload-file-errors',
        maxFileCount: 1, //表示允许同时上传的最大文件个数
        maxFileSize: 102400,      //单位为kb，如果为0表示不限制文件大小
        enctype: 'multipart/form-data',
        dropZoneTitle: '拖拽文件到这里 &hellip;<br>仅仅支持单个文件同时上传',//重新定义提示
        msgFilesTooMany: '选择上传的文件数量({n}) 超过允许的最大数值{m}！',
        allowedFileExtensions: ["xls", "xlsx"],
        uploadUrl: prefix+'/batchAdd'
    });
}
/*用户管理-部门*/
function dept() {
	var url = ctx + "system/dept";
	createMenuItem(url, "部门管理");
}

/*用户管理-删除*/
function remove(userId) {
	$.modalConfirm("确定要删除选中用户吗？", function() {
		_ajax(prefix + "/remove/" + userId, "", "post");
    })
}

/*用户管理-修改*/
function edit(userId) {
    var url = prefix + '/edit/' + userId;
    layer_showAuto("修改用户", url);
}

/*用户管理-新增*/
function add() {
    var url = prefix + '/add';
    layer_showAuto("新增用户", url);
}
/*用户管理-批量新增*/
function batchAdd() {
    //文件默认上传方法,传ID:uploadfile
    $('#uploadfile').fileinput("upload");
    //同步上传错误处理
    $('#uploadfile').on('filebatchuploaderror', function(event, data, msg) {
        // get message
        $.modalAlert(msg, "error");
        //重置
        $('#uploadfile').fileinput("clear");
        $('#uploadfile').fileinput("reset");
        $('#uploadfile').fileinput('refresh');
        $('#uploadfile').fileinput('enable');
    });
    //同步上传后从后台返回结果
    $('#uploadfile').on('filebatchuploadsuccess', function(event, data, previewId, index) {
        var result = data.response;
        if (result.code == 0) {
            //刷新数据表格
            $('.bootstrap-table').bootstrapTable('refresh');
            var count = result.msg;
            $.modalAlert("成功导入"  +count+ "条数据","success");
            $('#uploadfile').fileinput('reset');
            $('#exampleModal').modal('hide');

        } else {
            $.modalAlert(result.msg, "error");
            //重置
            $('#uploadfile').fileinput("clear");
            $('#uploadfile').fileinput("reset");
            $('#uploadfile').fileinput('refresh');
            $('#uploadfile').fileinput('enable');
        }
    });

}

/*用户管理-重置密码*/
function resetPwd(userId) {
    var url = prefix + '/resetPwd/' + userId;
    layer_show("重置密码", url, '800', '300');
}

// 批量删除用户
function batchRemove() {
	var rows = $.getSelections("userId");
	if (rows.length == 0) {
		$.modalMsg("请选择要删除的数据", modal_status.WARNING);
		return;
	}
	$.modalConfirm("确认要删除选中的" + rows.length + "条数据吗?", function() {
		_ajax(prefix + '/batchRemove', { "ids": rows }, "post");
	});
}
