<form class="app-form" id="dictItem-form">
<#if dictItem??>
  <input type="hidden" name="dictItemId">
</#if>
    <div class="field">
   <input id="dictId" class="easyui-combobox" name="dictId"
           data-options="label:'字典名称：',panelMaxHeight:200,panelHeight:'auto',valueField:'dictId',textField:'dictName',url:'/system/dict/list'"
          style="width:80%">
    </div>
  <div class="field">
      <input class="easyui-textbox" name="itemCode" style="width:80%" data-options="label:'编码：',required:true">
  </div>
    <div class="field">
        <input class="easyui-textbox" name="itemName" style="width:80%" data-options="label:'名称：',required:true">
    </div>
  <div class="field">
    <input class="easyui-textbox" name="remark" style="width:80%" data-options="label:'备注：'">
  </div>
  <div class="field">
    <input class="easyui-textbox" name="orderNum" style="width:80%" data-options="label:'排序号：',required:true">
  </div>
</form>
<script>
	<#if dictItem??>
    $(function () {
      //需要延迟一点执行，等待页面所有组件都初始化好，再执行数据初始化
      setTimeout(function () {
        var dictItem = ${dictItem};
        $("#dictItem-form").form("load", dictItem);
      }, 200);
    });
	</#if>

</script>