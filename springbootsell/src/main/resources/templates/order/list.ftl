<html>
<#include "../common/header.ftl">
<body>
<div id="wrapper" class="toggled">
    <#--    边栏nav-->
    <#include "../common/nav.ftl">
    <#--     右侧-->
    <div id="page-content-wrapper">
        <div class="container">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <h3>
                        订单列表
                    </h3>
                    <table class="table table-bordered table-condensed">
                        <thead>
                        <tr>
                            <th>订单id</th>
                            <th>姓名</th>
                            <th>手机号</th>
                            <th>地址</th>
                            <th>金额</th>
                            <th>订单状态</th>
                            <th>支付状态</th>
                            <th>创建时间</th>
                            <th>更新时间</th>
                            <th colspan="2">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#--                        通过.content 获取page中的每一个对象-->
                        <#list orderMasterPage.content as orderMaster>
                            <tr>
                                <td>${orderMaster.orderId}</td>
                                <td>${orderMaster.buyerName}</td>
                                <td>${orderMaster.buyerPhone}</td>
                                <td>${orderMaster.buyerAddress}</td>
                                <td>${orderMaster.orderAmount}</td>


                                <#if orderMaster.orderStatus==0>
                                    <td>新订单</td>
                                </#if>
                                <#if orderMaster.orderStatus==1>
                                    <td>完结</td>
                                </#if>
                                <#if orderMaster.orderStatus==2>
                                    <td>已取消</td>
                                </#if>

                                <#if orderMaster.payStatus==0>
                                    <td>等待支付</td>
                                </#if>
                                <#if orderMaster.payStatus==1>
                                    <td>支付成功</td>
                                </#if>
                                <td>${orderMaster.createTime}</td>
                                                      <td>${orderMaster.updateTime}</td>
                                <td>
                                    <a href="/seller/order/index?orderId=${orderMaster.orderId}">详情</a>
                                </td>
                                <#if orderMaster.orderStatus==0>
                                    <td>
                                        <a id="modal-616015" href="#modal-container-616015"  data-toggle="modal">取消</a>
                                        <div class="modal fade" id="modal-container-616015" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                            <div class="modal-dialog">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                                                        <h4 class="modal-title" id="myModalLabel">
                                                            提示
                                                        </h4>
                                                    </div>
                                                    <div class="modal-body">
                                                        是否取消订单
                                                    </div>
                                                    <div class="modal-footer">
                                                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                                                        <a href="/seller/order/cancel?orderId=${orderMaster.orderId}"><button type="button" class="btn btn-primary" >确定</button></a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                    </td>
                                </#if>

                            </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>
                <div class="col-md-12 column">
                    <#--                    pull-right 控件靠右-->
                    <ul class="pagination pull-right">
                        <#if page lte 1>
                            <li class="disabled">
                                <a href="#">上一页</a>
                            </li>
                        <#else >
                            <li><a  href="/seller/order/list?page=${page-1}&size=${size}">上一页</a></li>
                        </#if>
                        <#--                       获取查询数据的总页数 ${productInfoPage.getTotalPages()}-->
                        <#--                        1..val 代表从1 开始循环-->
                        <#list 1..orderMasterPage.getTotalPages() as index>
                            <#if page == index>
                                <li class="disabled"><a href="#">${index}</a></li>
                            <#else >
                                <li><a href="/seller/order/list?page=${index}&size=${size}">${index}</a></li>
                            </#if>
                        </#list>
                        <#if page gte orderMasterPage.getTotalPages()>
                            <li class="disabled"><a href="#">下一页</a></li>
                        <#else >
                            <li><a  href="/seller/order/list?page=${page+1}&size=${size}">下一页</a></li>
                        </#if>
                    </ul>

                </div>
            </div>
        </div>
    </div>

</div>
</body>
</html>