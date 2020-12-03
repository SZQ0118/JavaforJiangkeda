<html>
<#include "../common/header.ftl">
<body>
<div id="wrapper" class="toggled">
    <#--    边栏nav-->
    <#include "../common/nav.ftl">
    <#--     右侧-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <table class="table table-bordered table-condensed">
                        <thead>
                        <tr>
                            <th>订单id</th>
                            <th>订单总金额</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#--                        通过.content 获取page中的每一个对象-->
                            <tr>
                                <td>${orderMaster.orderId}</td>
                                <td>${orderMaster.orderAmount}</td>
                            </tr>
                        </tbody>
                    </table>

                    <table class="table table-bordered table-condensed">
                        <thead>
                        <tr>
                            <th>商品id</th>
                            <th>商品名称</th>
                            <th>价格</th>
                            <th>数量</th>
                            <th>总额</th>
                        </tr>
                        </thead>
                        <#list orderDetailList as orderDetail>
                        <tbody>
                        <#--                        通过.content 获取page中的每一个对象-->
                        <tr>


                            <td>${orderDetail.productId}</td>
                            <td>${orderDetail.productName}</td>
                            <td>${orderDetail.productPrice}</td>
                            <td>${orderDetail.productQuantity}</td>
                            <td>${(orderDetail.productQuantity*orderDetail.productPrice)}</td>
                        </tr>
                        </tbody>
                        </#list>
                    </table>

                </div>
            </div>
        </div>
    </div>

</div>
</body>
</html>