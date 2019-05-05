<form class="app-form" id="dict-form">
<#if dict??>
  <input type="hidden" name="dictId">
</#if>
  <div class="field">
      <input class="easyui-textbox" name="dictCode" style="width:80%" data-options="label:'编码：',required:true">
  </div>
    <div class="field">
        <input class="easyui-textbox" name="dictName" style="width:80%" data-options="label:'名称：',required:true">
    </div>
  <div class="field">
    <input class="easyui-textbox" name="remark" style="width:80%" data-options="label:'备注：'">
  </div>
  <div class="field">
    <input class="easyui-textbox" name="orderNum" style="width:80%" data-options="label:'排序号：',required:true">
  </div>
</form>
<script>
	<#if dict??>
    $(function () {
      //需要延迟一点执行，等待页面所有组件都初始化好，再执行数据初始化
      setTimeout(function () {
        var dict = ${dict};
        $("#dict-form").form("load", dict);
      }, 200);
    });
	</#if>
</script>