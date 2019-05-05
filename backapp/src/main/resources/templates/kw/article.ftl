<div class="easyui-layout" fit="true">
  <div data-options="region:'north',border:false" style="height: 80px;padding: 10px;overflow: hidden;" title="内容发布">
    <form id="article_search_from" class="searcher-form">
      <input name="title" class="easyui-textbox field" label="标题：" labelWidth="45">
      <input name="type" class="easyui-textbox field" label="类型：" labelWidth="45">
      <a class="easyui-linkbutton searcher" data-options="iconCls:'fa fa-search'">检索</a>
      <a class="easyui-linkbutton reset" data-options="iconCls:'fa fa-repeat'">重置</a>
    </form>
  </div>
  <div data-options="region:'center',border:false" style="border-top: 1px solid #D3D3D3">
    <table id="article_dg"></table>
  </div>
</div>