<div class="easyui-layout" fit="true">
  <div data-options="region:'north',border:false" style="height: 80px;padding: 10px;overflow: hidden;" title="数据字典项">
    <form id="dictItem_search_from" class="searcher-form">
      <input id="dictId" class="easyui-combobox" name="dictId"
               data-options="valueField:'dictId',textField:'dictName',url:'/system/dict/list'" label="字典名称：" labelWidth="70">
      <input name="itemCode" class="easyui-textbox field" label="编号：" labelWidth="45">
      <input name="itemName" class="easyui-textbox field" label="名称：" labelWidth="45">
      <a class="easyui-linkbutton searcher" data-options="iconCls:'fa fa-search'">检索</a>
      <a class="easyui-linkbutton reset" data-options="iconCls:'fa fa-repeat'">重置</a>
    </form>
  </div>
  <div data-options="region:'center',border:false" style="border-top: 1px solid #D3D3D3">
    <table id="dictItem_dg"></table>
  </div>
</div>