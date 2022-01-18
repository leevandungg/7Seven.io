<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    response.setHeader("Cache-control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setHeader("Expires", "0");
    if (session.getAttribute("admin-username") == null) {
        response.sendRedirect(request.getContextPath() + "/admin/login");
    }
%>
<!-- Start header section -->
<jsp:include page = "./header/header.jsp" flush = "true" />
<div class="content-wrapper">
    <div class="container-fluid">
        <div class="card mt-3">
            <div class="card-content">
                <div class="row row-group m-0">
                    <div class="col-12 col-lg-6 col-xl-3 border-light">
                        <div class="card-body">
                            <h5 class="text-white mb-0">
                                <%=request.getAttribute("place-order")%>
                                <span class="float-right"><i class="fa fa-shopping-cart"></i></span>
                            </h5>
                            <div class="progress my-3" style="height:3px;">
                                <div class="progress-bar" style="width:55%"></div>
                            </div>
                            <p class="mb-0 text-white small-font">Đơn hàng đã đặt</p>
                        </div>
                    </div>
                    <div class="col-12 col-lg-6 col-xl-3 border-light">
                        <div class="card-body">
                            <h5 class="text-white mb-0"><%=request.getAttribute("sold-price")%> <span class="float-right">VNĐ</span></h5>
                            <div class="progress my-3" style="height:3px;">
                                <div class="progress-bar" style="width:55%"></div>
                            </div>
                            <p class="mb-0 text-white small-font">Số tiền thu được</p>
                        </div>
                    </div>
                    <div class="col-12 col-lg-6 col-xl-3 border-light">
                        <div class="card-body">
                            <h5 class="text-white mb-0"><%=request.getAttribute("sold-quantity")%><span class="float-right"><i class="fa fa-eye"></i></span></h5>
                            <div class="progress my-3" style="height:3px;">
                                <div class="progress-bar" style="width:55%"></div>
                            </div>
                            <p class="mb-0 text-white small-font">Sản phẩm bán ra</p>
                        </div>
                    </div>
                    <div class="col-12 col-lg-6 col-xl-3 border-light">
                        <div class="card-body">
                            <h5 class="text-white mb-0"><%=request.getAttribute("sold-sum")%><span class="float-right"><i class="fa fa-envira"></i></span></h5>
                            <div class="progress my-3" style="height:3px;">
                                <div class="progress-bar" style="width:55%"></div>
                            </div>
                            <p class="mb-0 text-white small-font">Đơn hàng đã hoàn thành</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-12 col-lg-4 col-xl-4">
                <div class="card">
                    <div class="card-header">Danh mục bán chạy
                    </div>
                    <div class="card-body">
                        <div class="chart-container-2">
                            <canvas id="chart2"></canvas>
                        </div>
                    </div>
                    <div class="table-responsive">
                        <table class="table align-items-center">
                            <tbody>
                                <tr>
                                    <td><i class="fa fa-circle text-white mr-2"></i>Máy chơi game</td>
                                    <td><%=request.getAttribute("sold-gameconsole")%> VNĐ</td>

                                </tr>
                                <tr>
                                    <td><i class="fa fa-circle text-light-1 mr-2"></i>Đĩa game</td>
                                    <td id="price-cd"><%=request.getAttribute("sold-cd")%> VNĐ</td>

                                </tr>
                                <tr>
                                    <td><i class="fa fa-circle text-light-2 mr-2"></i>Gaming</td>
                                    <td id="price-chair"><%=request.getAttribute("sold-gaming")%> VNĐ</td>

                                </tr>
                                <tr>
                                    <td><i class="fa fa-circle text-light-3 mr-2"></i>Phụ kiện</td>
                                    <td id="price-category"><%=request.getAttribute("sold-category")%> VNĐ</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<a href="javaScript:void();" class="back-to-top"><i class="fa fa-angle-double-up"></i> </a>
<jsp:include page = "./footer/footer.jsp" flush = "true" />