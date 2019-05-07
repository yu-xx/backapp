define(function () {
  return function () {
    var dg = $("#article_dg");
    var searchFrom = $("#article_search_from");
    var form;

    // 使用edatagrid，需要而外导入edatagrid扩展
    dg.datagrid({
      url: '/kw/article/page',
      emptyMsg: "还未创建内容",
      idField: "articleId",
      fit: true,
      rownumbers: true,
      fitColumns: true,
      border: false,
      pagination: true,
      singleSelect: true,
      ignore: ['roles'],
      pageSize: 30,
      columns: [[{
        field: 'title',
        title: '标题',
        width: 30,
        editor: {
          type: 'validatebox',
          options: {
            required: true
          }
        },
        formatter: function (val) {
          return filterXSS(val);
        }
      },
          {
          field: 'typeName',
          title: '类型',
          width: 20,
          editor: {
              type: 'validatebox',
              options: {
                  required: true
              }
          },
          formatter: function (val) {
              return filterXSS(val);
          }
      },{
              field: 'statusName',
              title: '发布状态',
              width: 20,
              editor: {
                  type: 'validatebox',
                  options: {
                      required: true
                  }
              },
              formatter: function (val) {
                  return filterXSS(val);
              }
          }, {
        field: 'operTime',
        title: '操作时间',
        width: 30,
        editor: {
          type: 'validatebox',
          options: {
            required: true,
          }
        },
        formatter: function (val) {
          return filterXSS(val);
        }
      },
          {
          field: 'test',
          title: '操作',
          width: 50,
          align: 'center',
          formatter: function (value, row, index) {
              return authToolBar({
                  "article-edit": '<a data-id="' + row.articleId + '" class="ctr ctr-edit">编辑</a>',
                  "article-delete": '<a data-id="' + row.articleId + '" class="ctr ctr-delete">删除</a>',
                  "article-issue": '<a data-id="' + row.articleId + '" class="ctr ctr-up">发布</a>',
                  "article-noissue": '<a data-id="' + row.articleId + '" class="ctr ctr-down">取消发布</a>'
              }).join(" | ");
          }
      }
      ]],
      toolbar: authToolBar({
        "article-create": {
          iconCls: 'fa fa-plus-square',
          text: "创建",
          handler: function () {
            createForm()
          }
        }
      })
    });


    /**
     * 操作按钮绑定事件
     */
    dg.datagrid("getPanel").on('click', "a.ctr-edit", function () {// 编辑按钮事件
      createForm.call(this, this.dataset.id)
    }).on('click', "a.ctr-delete", function () {// 删除按钮事件
      var id = this.dataset.id;
      $.messager.confirm("删除提醒", "确认删除此内容?", function (r) {
        if (r) {
          $.get("/kw/article/delete", {articleId: id}, function () {
            // 数据操作成功后，对列表数据，进行刷新
            dg.datagrid("reload");
          });
        }
      });
    }).on('click', "a.ctr-up", function () {// 删除按钮事件
        var id = this.dataset.id;
        $.messager.confirm("发布提醒", "确认发布此内容?", function (r) {
            if (r) {
                $.get("/kw/article/issue", {articleId: id}, function () {
                    // 数据操作成功后，对列表数据，进行刷新
                    dg.datagrid("reload");
                });
            }
        });
    }).on('click', "a.ctr-down", function () {// 删除按钮事件
        var id = this.dataset.id;
        $.messager.confirm("取消发布提醒", "确认取消发布此内容?", function (r) {
            if (r) {
                $.get("/kw/article/cancel", {articleId: id}, function () {
                    // 数据操作成功后，对列表数据，进行刷新
                    dg.datagrid("reload");
                });
            }
        });
    });

    /**
     * 搜索区域事件
     */
    searchFrom.on('click', 'a.searcher', function () {//检索
      dg.datagrid('load', searchFrom.formToJson());
    }).on('click', 'a.reset', function () {//重置
      searchFrom.form('reset');
      dg.datagrid('load', {});
    });

    /**
       * 创建表单窗口
       * @returns
       */
      function createForm(id) {
          var dialog = $("<div/>", {class: 'noflow'}).dialog({
              title: (id ? "编辑" : "创建") + "内容",
              iconCls: 'fa ' + (id ? "fa-edit" : "fa-plus-square"),
              height: 600,
              width: 1000,
              href: '/kw/article/form',
              queryParams: {
                  articleId: id
              },
              modal: true,
              onClose: function () {
                  $(this).dialog("destroy");
              },
              onLoad: function () {
                  //窗口表单加载成功时执行
                  form = $("#article-form");
              },
              buttons: [{
                  iconCls: 'fa fa-save',
                  text: '保存',
                  handler: function () {
                      if (form.form('validate')) {
                          $.post("/kw/article/save", form.serialize(), function (res) {
                              dg.datagrid('reload');
                              dialog.dialog('close');
                          })
                      }
                  }
              }]
          });
      }
  }
});