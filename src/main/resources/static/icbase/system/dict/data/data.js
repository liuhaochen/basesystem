var prefix = ctx + "system/dict/data"

$(function() {
		var columns = [{
            checkbox: true
        },
        {
            field: 'dictCode',
            title: '字典编码'
        },
        {
            field: 'dictLabel',
            title: '字典标签'
        },
        {
            field: 'dictValue',
            title: '字典键值'
        },
        {
            field: 'dictSort',
            title: '字典排序'
        },
        {
            field: 'status',
            title: '状态',
            align: 'center',
            formatter: function(value, row, index) {
                if (value == 0) {
                    return '<span class="badge badge-primary">正常</span>';
                } else if (value == 1) {
                    return '<span class="badge badge-danger">停用</span>';
                }
            }
        },
        {
            field: 'remark',
            title: '备注'
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
            	actions.push('<a class="btn btn-success btn-xs ' + editFlag + '" href="#" onclick="edit(\'' + row.dictCode + '\')"><i class="fa fa-edit"></i>编辑</a> ');
            	actions.push('<a class="btn btn-danger btn-xs ' + removeFlag + '" href="#" onclick="remove(\'' + row.dictCode + '\')"><i class="fa fa-remove"></i>删除</a>');
            	return actions.join('');
            }
        }];
	var url = prefix + "/list?dictType=" + $("#dictType").val();
	$.initTable(columns, url);
});

/*字典管理-新增*/
function add() {
    var url = prefix + '/add/' + $("#dictType").val();
    layer_showAuto("新增字典类型", url);
}

/*角色管理-修改*/
function edit(dictCode) {
    var url = prefix + '/edit/' + dictCode;
    layer_showAuto("修改字典类型", url);
}

/*字典列表-详细*/
function detail(id) {
	createMenuItem(prefix + '/detail', "字典数据");
}

//单条删除
function remove(id) {
	$.modalConfirm("确定要删除选中字典数据吗？", function() {
		_ajax(prefix + "/remove/" + id, "", "post");
    })
}

// 批量删除
function batchRemove() {
	var rows = $.getSelections("dictCode");
	if (rows.length == 0) {
		$.modalMsg("请选择要删除的数据", modal_status.WARNING);
		return;
	}
	$.modalConfirm("确认要删除选中的" + rows.length + "条数据吗?", function() {
		_ajax(prefix + '/batchRemove', { "ids": rows }, "post");
	});
}
