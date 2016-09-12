<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="schedule" class="by.mpc.core.model.Schedule" scope="request"></jsp:useBean>

<div class="container">
	<div class="span12">
		<table class="table table-condensed table-hover table-bordered">
			<thead>
				<tr>
					<td>Route</td>
					<td>Departure</td>
					<td>Arrival</td>
				</tr>
			</thead>
			<c:forEach items="${schedule.getList()}" var="item" begin="1" step="1" varStatus="counter">
				<tbody>
					<tr class="info"><td>${counter.count}</td><td></td><td></td></tr>
					<c:forEach items="${item.getList()}" var="it">
						<tr>
							<td>${it.getBStation().concat(" - ").concat(it.getEStation())}</td>
							<td>${it.getBTimeString("yyyy-MM-dd HH:mm")}</td>
							<td>${it.getETimeString("yyyy-MM-dd HH:mm")}</td>
						</tr>
					</c:forEach>
				</tbody>
			</c:forEach>
		</table>
	</div>
</div>


