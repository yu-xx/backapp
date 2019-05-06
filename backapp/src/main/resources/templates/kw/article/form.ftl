<form class="app-form" id="article-form">
  <input type="hidden" name="article">
  <div class="field">
    <input class="easyui-textbox" name="title" style="width:80%" data-options="label:'标题：',required:true">
    <input id="type" class="easyui-combobox" name="type"
             data-options="label:'类型：',panelMaxHeight:200,panelHeight:'auto',valueField:'itemCode',textField:'itemName',url:'/kw/article/type/list'"
             style="width:18%">
    </div>
  </div>
  <div class="field">
    <input class="easyui-textbox" name="summary" style="width:100%" data-options="label:'摘要：',required:true">
  </div>
  <#--<div class="field">
    <input class="easyui-textbox" name="lowSource" style="width:100%" data-options="label:'预览图：',required:true">
  </div>-->
  <div class="field">
    <textarea id="content" name="content" style="width:100%;height:400px;">
    &lt;strong&gt;请输入内容&lt;/strong&gt;
    </textarea>
  </div>
</form>
<script>
	<#if article??>
    $(function () {
      //需要延迟一点执行，等待页面所有组件都初始化好，再执行数据初始化
      setTimeout(function () {
        var article = ${article};
        $("#article-form").form("load", article);
      }, 200);
    });
	</#if>


    var KE;
    var TT = TAOTAO = {
        // 编辑器参数
        kingEditorParams : {
            resizeType : 1,
            allowImageUpload : true,
            allowFileManager : true,
            uploadJson : '/js/kindeditor/jsp/upload_json.jsp',
            fileManagerJson : '/js/kindeditor/jsp/kindeditor/jsp',
            afterUpload : function(url, data, name){
                this.sync();
                //对上传的图片宽度的控制
                if(name=="image" || name=="multiimage"){ //单个和批量上传图片时
                    var img = new Image(); img.src = url;
                    img.onload = function(){ //图片必须加载完成才能获取尺寸
                        if(img.width>600) KE.html(KE.html().replace('<img src="' + url + '"','<img src="' + url + '" width="600"'))
                    }
                }
            },
            afterBlur : function(){this.sync();},
            /*items : ['source','|','preview','|','wordpaste','|',
                    'image','media','|','hr','|','link','unlink','|','fullscreen','|','lineheight','|','anchor','/',
                'justifyleft','justifycenter','justifyright','justifyfull','|',
                'insertorderedlist','|','indent','outdent','|','fontname','fontsize','forecolor','hilitecolor','bold','italic','underline','strikethrough']*/
            items : ['source','|','preview','|','wordpaste','|',
                '|','hr','|','link','unlink','|','fullscreen','|','lineheight','|','anchor','/',
                'justifyleft','justifycenter','justifyright','justifyfull','|',
                'insertorderedlist','|','indent','outdent','|','fontname','fontsize','forecolor','hilitecolor','bold','italic','underline','strikethrough']
        },
        createEditor : function(select){
            KE = KindEditor.create(select, TT.kingEditorParams);
            return KE;
        }
    }

    var itemAddEditor ;
    //页面初始化完毕后执行此方法
    $(function(){
        //创建富文本编辑器
        itemAddEditor = TAOTAO.createEditor("#article-form [name=content]",TT.kingEditorParams);
        <#if article??>
            $(function () {
                //需要延迟一点执行，等待页面所有组件都初始化好，再执行数据初始化
                setTimeout(function () {
                    var article = ${article};
                    KE.html(article.content);
                }, 200);
            });
        </#if>
    });
    //提交表单
    function submitForm(){
        //编辑器中数据同步描述进textarea
        itemAddEditor.sync();
        //ajax的post方式提交表单
        //提交过去的是json数据，返回回来的数据是json解析的
        //$("#itemAddForm").serialize()将表单序列号为key-value形式的字符串
        $.post("/kw/article/save",$("#article-form").serialize(), function(data){
            alert(data);
        },"json");
    }
</script>